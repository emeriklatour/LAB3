package mvc.modele;

import utils.Side;

import java.io.Serializable;
import java.util.Map;

public class Modele implements Serializable {
	private Image image;
	private Map<Side, Perspective> perspectives;

	public Modele(Image image, Map<Side, Perspective> perspectives) {
		this.image = image;
		this.perspectives = perspectives;
	}

	public Image getImage() {
		return image;
	}

	public Perspective getPerspective(Side side) {
		return perspectives.get(side);
	}
}
