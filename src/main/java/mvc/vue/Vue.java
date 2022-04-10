package mvc.vue;

import mvc.observateur.Observer;

import javax.swing.*;

/******************************************************
 						Vue
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe abstraite représente les vues du patron MVC.
 */
public abstract class Vue extends JPanel implements Observer {

	/**
	 * Actualise la vue.
	 */
	@Override
	public void update() {
		revalidate();
		repaint();
	}
}
