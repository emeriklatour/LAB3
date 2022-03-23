package display;

import mvc.modele.Modele;
import utils.Side;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 3 : LOG121";
	private static final Dimension DIMENSION = new Dimension(1000, 1000);

	public FenetrePrincipale(Modele modele) {
		setWindowDefaults();
		addVignette(modele);
		for (Side side : Side.values()) {
			addPanneauImage(modele, side);
		}
	}

	private void setWindowDefaults() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);

		GridLayout layout = new GridLayout(1, 1 + Side.values().length);
		setLayout(layout);
	}

	private void addVignette(Modele modele) {
		add(new PanneauVignette(modele.getImage()));
	}

	private void addPanneauImage(Modele modele, Side side) {
		add(new PanneauImage(
				side,
				modele.getImage(),
				modele.getPerspective(side)
		));
	}
}
