package mvc.vue;

import mvc.modele.Image;
import mvc.modele.Perspective;

import java.awt.*;

public class Panneau extends Vue {
	protected int side;
	protected Image image;
	protected Perspective perspective;

	public Panneau(int side, Image image, Perspective perspective) {
		this.side = side;
		this.image = image;
		this.perspective = perspective;
		image.addObserver(this);
		perspective.addObserver(this);
		this.setBackground(new Color(0, 0, 255));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(
				image.getImageToPaint(),
				perspective.getPosX(),
				perspective.getPosY(),
				300 * (perspective.getZoomFactor() / 1000),
				300 * (perspective.getZoomFactor() / 1000),
				null
		);
	}
}
