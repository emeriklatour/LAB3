package display;

import mvc.modele.Modele;
import utils.Side;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PanneauPrincipale extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanneauPrincipale(Modele modele) {
		setLayout();
		addVignette(modele);
		Arrays.stream(Side.values()).forEach((s) -> addPanneauImage(modele, s));
	}

	private void setLayout() {
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
