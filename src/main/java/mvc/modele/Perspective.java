package mvc.modele;

import mvc.observateur.Observable;
import java.io.Serializable;

public class Perspective extends Observable implements Serializable {
	private int posX;
	private int posY;
	private int zoomFactor;

	public Perspective() {
		this.posX = 0;
		this.posY = 0;
		this.zoomFactor = 1000;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getZoomFactor() {
		return zoomFactor;
	}

	public void setPosX(int posX) {
		this.posX = posX;
		notifyObservers();
	}

	public void setPosY(int posY) {
		this.posY = posY;
		notifyObservers();
	}

	public void setZoomFactor(int zoomFactor) {
		this.zoomFactor = zoomFactor;
		notifyObservers();
	}
}
