package display;

import mvc.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 3 : LOG121";
	private static final Dimension DIMENSION = new Dimension(1000, 1000);

	public FenetrePrincipale(Modele modele) {
		setWindowDefaults();
		add(new MenuFenetre(), BorderLayout.NORTH);
		add(new PanneauPrincipale(modele));
	}

	private void setWindowDefaults() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
