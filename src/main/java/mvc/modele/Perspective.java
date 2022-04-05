package mvc.modele;

import copie.strategie.impl.CopyBoth;
import mvc.observateur.Observable;
import java.io.Serializable;

public class Perspective extends Observable implements Serializable {
	private int posX;
	private int posY;
	private double zoomFactor;

	public Perspective() {
		this.posX = 0;
		this.posY = 0;
		this.zoomFactor = 1000;
	}

	public Perspective(int posX, int posY, double zoomFactor) {
		this.posX = posX;
		this.posY = posY;
		this.zoomFactor = zoomFactor;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public double getZoomFactor() {
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

	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
		notifyObservers();
	}

	public void copyFrom(Perspective p){
		CopyBoth copyBoth = new CopyBoth();
		copyBoth.paste(p, this);
	}

	public Perspective clone() {
		return new Perspective(this.posX, this.posY, this.zoomFactor);
	}
}
