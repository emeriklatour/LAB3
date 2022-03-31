package mvc.modele;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.Serializable;

import mvc.observateur.Observable;

public class Image extends Observable implements Serializable {
	private String imagePath;
	private transient java.awt.Image imageToPaint;

	public Image() {}

	public String getImagePath() {
		return imagePath;
	}

	public java.awt.Image getImageToPaint() {
		return imageToPaint;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		loadImage();
		notifyObservers();
	}

	private void loadImage() {
		try {
			imageToPaint = ImageIO.read(new File(imagePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
