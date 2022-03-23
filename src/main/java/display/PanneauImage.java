package display;

import mvc.modele.Image;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import utils.Side;

import java.awt.*;

public class PanneauImage extends Panneau {
	private static final long serialVersionUID = 1L;

	public PanneauImage(Side side, Image image, Perspective perspective) {
		super(side, image, perspective);
		this.setBackground(new Color(0, 0, 255));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(
				image.getImageToPaint(),
				perspective.getPosX(),
				perspective.getPosY(),
				null
		);

	}
}
