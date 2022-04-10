package command;

import mvc.Controller;

/******************************************************
 						Command
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Interface a implementer pour toutes les comandes
 */
public interface Command {
	/**
	 * Execute la commande envoyé au controleur.
	 * @param controller Controleur de l'application
	 */
	void execute(Controller controller);

	/**
	 * Utilise afin de revenir en arriere et annuler la commande executee.
	 * @param controller Controleur de l'application
	 */
	void revert(Controller controller);

	/**
	 * Retourne l'index d'un des panneaux. Sert a determiner sur quel panneau
	 * la commande a ete effectuee.
	 * @return l'index du panneau sur lequel la commande a ete effectuee.
	 */
	int getSide();
}
