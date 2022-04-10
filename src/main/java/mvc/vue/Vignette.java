package mvc.vue;

import mvc.modele.Image;

import java.awt.*;

/******************************************************
					 Vignette
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe représente le panneau de la vignette qui tient la miniature
 * de l'image selectionnée dans l'application.
 */
public class Vignette extends Vue {
	private Image image;

	/**
	 * Contructeur par paramètre de la classe Vignette.
	 *
	 * @param image l'image à utiliser pour la miniature
	 */
	public Vignette(Image image) {
		this.image = image;
		image.addObserver(this);
		this.setBackground(new Color(255, 255, 0));
	}

	/**
	 * Peinture la miniature dans le panneau de la vignette.
	 *
	 * @param g l'instance de Graphics sur laquelle nous peinturons
	 */
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
