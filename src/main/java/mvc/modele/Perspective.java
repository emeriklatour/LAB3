package mvc.modele;

import copie.strategie.impl.CopyBoth;
import mvc.observateur.Observable;
import java.io.Serializable;

/******************************************************
 * 					Perspective
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe tient les données du modèle pour un des Panneau
 * de l'application.
 */
public class Perspective extends Observable implements Serializable {
	private int posX;
	private int posY;
	private double zoomFactor;

	/**
	 * Constructeur par défaut de la classe Perspective.
	 */
	public Perspective() {
		this.posX = 0;
		this.posY = 0;
		this.zoomFactor = 1000;
	}

	/**
	 * Constructeur par paramêtre de la classe Perspective.
	 *
	 * @param posX la position en X de l'image
	 * @param posY la posiiton en Y de l'image
	 * @param zoomFactor la facteur de zoom de l'image
	 */
	public Perspective(int posX, int posY, double zoomFactor) {
		this.posX = posX;
		this.posY = posY;
		this.zoomFactor = zoomFactor;
	}

	/**
	 * Retourne la position en X de l'image.
	 *
	 * @return la position en X de l'image
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Retourne le la position en Y de l'image.
	 *
	 * @return la position en Y de l'image
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Retroune le facteur de zoom de l'image.
	 *
	 * @return le facteur de zoom de l'image
	 */
	public double getZoomFactor() {
		return zoomFactor;
	}

	/**
	 * Configure la position en X de l'imgage et notifie les observateurs.
	 *
	 * @param posX la nouvelle position en X
	 */
	public void setPosX(int posX) {
		this.posX = posX;
		notifyObservers();
	}

	/**
	 * Configure la posiiton en Y de l'image et notifie les observateurs.
	 *
	 * @param posY la nouvelle position en Y
	 */
	public void setPosY(int posY) {
		this.posY = posY;
		notifyObservers();
	}

	/**
	 * Configure le facteur de zoom de l'image et notifie les observateurs.
	 *
	 * @param zoomFactor le nouveau facteur de zoom
	 */
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
		notifyObservers();
	}

	/**
	 * Copie les valeurs d'une Perspective en paramêtre pour les configurer
	 * à la Perspective courrante.
	 *
	 * @param p la Perspective à copier
	 */
	public void copyFrom(Perspective p){
		CopyBoth copyBoth = new CopyBoth();
		copyBoth.paste(p, this);
	}

	/**
	 * Clone la Perspective.
	 *
	 * @return une nouvelle Perspective avec les mêmes paramêtres que la
	 * 			courrante.
	 */
	public Perspective clone() {
		return new Perspective(this.posX, this.posY, this.zoomFactor);
	}
}
