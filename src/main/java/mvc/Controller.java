package mvc;

import command.Command;
import copie.Clipboard;
import mvc.modele.Modele;
import mvc.modele.Perspective;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/******************************************************
 						Controller
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe représente le Controlleur du patron MVC. Elle agit commme
 * coordonatrice pour les Commandes en leurs fournissant l'état de
 * l'appplication.
 */
public class Controller implements IController, Serializable {
	private static Controller controllerSingleton = new Controller();
	private ArrayList<ArrayList<Command>> executedCommands;
	private Modele modele;
	private Clipboard<Perspective> clipBoard;

	/**
	 * Retourne l'instance singleton du Controller à travers l'interface
	 * IController.
	 *
	 * @return l'instance singletion du Controller à travers l'interface
	 * 			IController
	 */
	public static IController getInstance() {
		return controllerSingleton;
	}

	/**
	 * Retournle l'instance du modèle de l'application.
	 *
	 * @return l'instance du modèle de l'application
	 */
	public Modele getModele() {
		return modele;
	}

	/**
	 * Configure l'instance du modèle de l'application et réinitialise
	 * l'historique des commandes.
	 *
	 * @param modele l'instance du modèle à utiliser pour l'application
	 */
	public void setModele(Modele modele) {
		this.modele = modele;
		executedCommands = generateEmptyHistory(modele.getNbPerspective());
	}

	/**
	 * Retourne un itérateur sur l'historique des commandes pour une certaines
	 * perspective de l'application.
	 *
	 * @param side la perspective pour laquelle nous voulons recueillir
	 *             l'itérateur
	 * @return un itérateur sur l'historique des commandes pour la perspective
	 * 			spécifiée
	 */
	public ListIterator<Command> getExecutedCommands(int side) {
		ArrayList<Command> commands = executedCommands.get(side);
		return commands.listIterator();
	}

	/**
	 * Retourne l'instance du Clipboard sauvegarder par le Controller.
	 *
	 * @return l'instnace du Clipboard sauvegarder par le Controller
	 */
	public Clipboard<Perspective> getClipboard () {
		return clipBoard;
	}

	/**
	 * Configure l'instance du Clipboard utilisée par le Controller.
	 *
	 * @param clipBoard l'instnace du Clipboard à étre utilisée par le
	 *                    Controller
	 */
	public void setClipBoard(Clipboard<Perspective> clipBoard) {
		this.clipBoard = clipBoard;
	}

	/**
	 * Execute la commande passée en paramêtre.
	 *
	 * @param command la command à executer
	 */
	@Override
	public void handleCommand(Command command) {
		command.execute(this);
		registerCommand(command);
	}

	/**
	 * Génère un historique de commande vide en fonction du nombre de
	 * perspective spécifiée en paramêtre.
	 *
	 * @param nbSide le nombre de perspective à utiliser pour la génération
	 * @return un historique de commande vide
	 */
	private ArrayList<ArrayList<Command>> generateEmptyHistory(int nbSide) {
		return IntStream.range(0, nbSide)
				.mapToObj(s -> new ArrayList<Command>())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Enregistre une commande auprès de son historique de commande respective.
	 *
	 * @param command la commande à enregistrer
	 */
	private void registerCommand(Command command) {
		if (command.getSide() != -1) {
			executedCommands.get(command.getSide()).add(command);
		}
	}
}
