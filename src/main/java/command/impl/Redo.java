package command.impl;

import command.Command;
import mvc.Controller;

import java.util.ArrayList;

/******************************************************
						Redo
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Permet de "refaire" une commande "defaite"
 */
public class Redo implements Command {
	private int side;
	private Undo revertedUndo;

	/**
	 * Constructeur de la classe Redo
	 * @param side l'index du panneau sur lequel la commande doit etre refaite
	 */
	public Redo(int side) {
		this.side = side;
	}

	/**
	 * Saisi la derniere commande "Undo" executee et appelle simplement sa methode
	 * "revert" afin de refaire la commande
	 * @param controller Controleur de l'application
	 */
	@Override
	public void execute(Controller controller) {
		revertedUndo = getLastUndo(controller);
		revertedUndo.revert(controller);
	}

	/**
	 * Annule la commande executee. Utilise l'ancienne commande "Undo" afin de l'executer
	 * @param controller Controleur de l'application
	 */
	@Override
	public void revert(Controller controller) {
		revertedUndo.execute(controller);
	}

	/**
	 * Retourne l'index d'un des panneaux. Sert a determiner sur quel panneau
	 * la commande a ete effectuee.
	 * @return l'index du panneau sur lequel la commande a ete effectuee.
	 */
	@Override
	public int getSide() {
		return side;
	}

	/**
	 *
	 * @param controller Controleur de l'application
	 * @return La derniere commande "Undo" executee
	 */
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
