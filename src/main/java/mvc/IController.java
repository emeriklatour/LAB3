package mvc;

import command.Command;

/******************************************************
 						IController
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette interface est utiliser pour encapsuler l'instance du Controller et
 * enforcer les Commande(s) comme méthode d'intéraction.
 */
public interface IController {
	/**
	 * Execute la commande passée en paramêtre.
	 *
	 * @param command la command à executer
	 */
	void handleCommand(Command command);
}
