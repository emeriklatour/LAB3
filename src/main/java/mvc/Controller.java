package mvc;

import command.Command;
import copie.Clipboard;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import utils.Side;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements IController, Serializable {
	private static Controller controllerSingleton = new Controller();
	private Map<Side, ArrayList<Command>> executedCommands = generateEmptyHistory();
	private Modele modele;
	private Clipboard<Perspective> clipBoard = new Clipboard<>();

	public static IController getInstance() {
		return controllerSingleton;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public ListIterator<Command> getExecutedCommands(Side side) {
		ArrayList<Command> commands = executedCommands.get(side);
		return commands.listIterator(commands.size());
	}

	public Clipboard<Perspective> getClipboard () {
		return clipBoard;
	}

	@Override
	public void handleCommand(Command command) {
		command.execute(this);
		registerCommand(command);
	}

	private Map<Side, ArrayList<Command>> generateEmptyHistory() {
		return Arrays.stream(Side.values())
			.collect(
				Collectors.toMap(
					(side) -> side,
					(side) -> new ArrayList<>()
				)
			);
	}

	private void registerCommand(Command command) {
		if (command.getSide() != null) {
			executedCommands.get(command.getSide()).add(command);
		}
	}
}
