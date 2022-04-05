package mvc;

import command.Command;
import copie.Clipboard;
import mvc.modele.Modele;
import mvc.modele.Perspective;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller implements IController, Serializable {
	private static Controller controllerSingleton = new Controller();
	private ArrayList<ArrayList<Command>> executedCommands;
	private Modele modele;
	private Clipboard<Perspective> clipBoard = new Clipboard<>();
	private int side;

	public static IController getInstance() {
		return controllerSingleton;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
		executedCommands = generateEmptyHistory(modele.getNbPerspective());
	}

	public void setNewModele(Perspective p, int side){
		this.modele.copyPerspective(p, side);
	}

	public ListIterator<Command> getExecutedCommands(int side) {
		ArrayList<Command> commands = executedCommands.get(side);
		return commands.listIterator(commands.size());
	}

	public Clipboard<Perspective> getClipboard () {
		return clipBoard;
	}

	public void setClipBoard(Clipboard<Perspective> clipBoard) {
		this.clipBoard = clipBoard;
	}

	@Override
	public void handleCommand(Command command) {
		command.execute(this);
		registerCommand(command);
	}

	private ArrayList<ArrayList<Command>> generateEmptyHistory(int nbSide) {
		return IntStream.range(0, nbSide)
				.mapToObj(s -> new ArrayList<Command>())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private void registerCommand(Command command) {
		if (command.getSide(0) != -1) {
			executedCommands.get(command.getSide(0)).add(command);
		}
	}

	public void setSide(int side){
		this.side = side;
	}

	public int getSide(){
		return side;
	}


}
