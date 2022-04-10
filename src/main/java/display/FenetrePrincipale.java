package display;

import javax.swing.*;
import java.awt.*;

/******************************************************
 					FenetrePrincipale
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe est nous permet de faire apparaitre une fenetre au lancement de l'application.
 * Tous les composants de notre application seront ajoutes a cette fenetre
 */
public class FenetrePrincipale extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 3 : LOG121";
	private static final Dimension DIMENSION = new Dimension(800, 600);
	private JPanel panneauPricipale;

	public FenetrePrincipale() {
		setWindowDefaults();

		add(new MenuFenetre(), BorderLayout.NORTH);

		panneauPricipale = new PanneauPrincipale();
		add(panneauPricipale);
	}

	public void addToPanneau(JPanel vue) {
		panneauPricipale.add(vue);
		this.validate();
		this.repaint();
	}

	private void setWindowDefaults() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
	}
}
