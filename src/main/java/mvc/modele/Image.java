package mvc.modele;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.Serializable;

import mvc.observateur.Observable;

/******************************************************
						 Image
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe représente l'image du Modele qui sera utilisé par l'application.
 */
public class Image extends Observable implements Serializable {
	private String imagePath;
	private transient java.awt.Image imageToPaint;

	/**
	 * Constructeur par défaut de la classe Image.
	 */
	public Image() {}

	/**
	 * Retourne la chemin vers l'image.
	 *
	 * @return le chemin vers l'image
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Retourne l'image à peinturer.
	 *
	 * @return l'image à peinturer
	 */
	public java.awt.Image getImageToPaint() {
		return imageToPaint;
	}

	/**
	 * Configure le chemin vers l'image.
	 *
	 * @param imagePath le chemin vers l'image
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		loadImage();
		notifyObservers();
	}

	/**
	 * Charge l'image qui se retrouve au chemin vers l'image.
	 */
	private void loadImage() {
		try {
			imageToPaint = ImageIO.read(new File(imagePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
