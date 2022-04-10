package display;

import javax.swing.*;
import java.awt.*;

/******************************************************
 					PanneauPrincipale
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Panneau principal que nous ajoutons a la fenetre afin d'ajouter les composants (JPanel) necessaires
 * au fonctionnement du laboratoire.
 */
public class PanneauPrincipale extends JPanel {
	private static final long serialVersionUID = 1L;
	int nbrVue = 0;

	@Override
	public Component add(Component comp) {
		nbrVue++;
		setLayout(new GridLayout(1, nbrVue));
		return super.add(comp);
	}
}
