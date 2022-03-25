package mvc;

import command.Command;
import mvc.modele.Clipboard;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import utils.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller implements IController {
	private static Controller controllerSingleton = new Controller();
	private GestionnaireSauvegarde gSauvegarde = new GestionnaireSauvegarde(this);
	private Map<Side, ArrayList<Command>> executedCommands = new HashMap<>();
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

	public Clipboard<Perspective> getClipBoard() {
		return clipBoard;
	}

	public ArrayList<Command> getExecutedCommands(Side side) {
		return executedCommands.get(side);
	}

	@Override
	public void handleCommand(Command command) {
		command.execute(this);
	}

	@Override
	public GestionnaireSauvegarde getGSauvegarde() {
		return gSauvegarde;
	}
}
