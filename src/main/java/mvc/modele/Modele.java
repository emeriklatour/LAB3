package mvc.modele;

import java.io.Serializable;
import java.util.stream.IntStream;

public class Modele implements Serializable {
	private final Image image;
	private final Perspective[] perspectives;

	public Modele(Image image, Perspective[] perspectives) {
		this.image = image;
		this.perspectives = perspectives;
	}

	public Image getImage() {
		return image;
	}

	public void copyFrom(Modele m){
		this.image.setImagePath(m.getImage().getImagePath());
		IntStream.range(0, perspectives.length)
				.forEach(s -> perspectives[s].copyFrom(m.getPerspective(s)));
	}

	public Perspective getPerspective(int side) {
		return perspectives[side];
	}

	public int getNbPerspective() {
		return perspectives.length;
	}
}
