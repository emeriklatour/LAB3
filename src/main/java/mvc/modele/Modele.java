package mvc.modele;

import java.io.Serializable;
import java.util.stream.IntStream;

/******************************************************
 * 					Modele
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe représente le modèle du MVC.
 */
public class Modele implements Serializable {
	private final Image image;
	private final Perspective[] perspectives;

	/**
	 * Constructeur par paramêtre de la classe Modèle.
	 *
	 * @param image l'image du modèle
	 * @param perspectives les perspectives du modèle
	 */
	public Modele(Image image, Perspective[] perspectives) {
		this.image = image;
		this.perspectives = perspectives;
	}

	/**
	 * Retourne l'image du modèle.
	 *
	 * @return l'image du modèle
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Retourne une des perspective du modèle.
	 *
	 * @param side l'index de la perspective
	 * @return l'instance d'une des Perspective du modèle
	 */
	public Perspective getPerspective(int side) {
		return perspectives[side];
	}

	/**
	 * Retourne le nombre de perspective du modèle.
	 *
	 * @return le nombre de perspective du modèle
	 */
	public int getNbPerspective() {
		return perspectives.length;
	}

	/**
	 * Copie les paramêtre du modèle passée en paramêtre pour les configurer
	 * au modèle courrant.
	 *
	 * @param m le Modele à copier
	 */
	public void copyFrom(Modele m){
		this.image.setImagePath(m.getImage().getImagePath());
		IntStream.range(0, perspectives.length)
				.forEach(s -> perspectives[s].copyFrom(m.getPerspective(s)));
	}
}
