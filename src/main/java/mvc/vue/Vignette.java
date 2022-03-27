package mvc.vue;

import mvc.modele.Image;

import java.awt.*;

public class Vignette extends Vue {
	protected Image image;

	public Vignette(Image image) {
		this.image = image;
		image.addObserver(this);
		this.setBackground(new Color(255, 255, 0));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(
				image.getImageToPaint(),
				16,
				300,
				300,
				300,
				null
		);
	}
}
