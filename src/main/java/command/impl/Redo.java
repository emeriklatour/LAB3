package command.impl;

import command.Command;
import mvc.Controller;

public class Redo implements Command {
	private int side;
	private Undo revertedUndo;

	public Redo(int side) {
		this.side = side;
	}

	@Override
	public void execute(Controller controller) {
		controller.getExecutedCommands(side).forEachRemaining(
				(command) -> {
					if (command instanceof Undo) {
						revertedUndo = (Undo) command;
					}
				}
		);
		revertedUndo.revert(controller);
	}

	@Override
	public void revert(Controller controller) {
		revertedUndo.execute(controller);
	}

	@Override
	public int getSide(int side) {
		return side;
	}
}
