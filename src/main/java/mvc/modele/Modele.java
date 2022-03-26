package mvc.modele;

import copie.strategie.impl.CopyBoth;
import utils.Side;

import java.io.Serializable;
import java.util.Arrays;
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

	public void copyFrom(Modele m){
		this.image.setImagePath(m.getImage().getImagePath());
		CopyBoth copyBoth = new CopyBoth();
		Arrays.stream(Side.values()).forEach(s -> copyBoth.paste(perspectives.get(s), m.getPerspective(s)));
	}

	public Perspective getPerspective(Side side) {
		return perspectives.get(side);
	}
}
