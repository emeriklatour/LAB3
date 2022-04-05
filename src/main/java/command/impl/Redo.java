package command.impl;

import command.Command;
import mvc.Controller;

import java.util.ArrayList;

public class Redo implements Command {
	private int side;
	private Undo revertedUndo;

	public Redo(int side) {
		this.side = side;
	}

	@Override
	public void execute(Controller controller) {
		revertedUndo = getLastUndo(controller);
		revertedUndo.revert(controller);
	}

	@Override
	public void revert(Controller controller) {
		revertedUndo.execute(controller);
	}

	@Override
	public int getSide() {
		return side;
	}

	private Undo getLastUndo(Controller controller) {
		ArrayList<Undo> undos = new ArrayList<>();
		controller.getExecutedCommands(side).forEachRemaining(
				(command) -> {
					if (command instanceof Undo) {
						undos.add((Undo) command);
					} else if (command instanceof Redo) {
						undos.remove(((Redo) command).revertedUndo);
					}
				}
		);

		return undos.get(undos.size() - 1);
	}
}
