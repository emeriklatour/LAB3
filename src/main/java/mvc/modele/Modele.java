package mvc.modele;

import copie.strategie.impl.CopyBoth;
import mvc.observateur.Observer;

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
		CopyBoth copyBoth = new CopyBoth();
		IntStream.range(0, perspectives.length)
				.forEach(s -> copyBoth.paste(m.getPerspective(s), perspectives[s]));
	}

	public Perspective getPerspective(int side) {
		return perspectives[side];
	}

	public int getNbPerspective() {
		return perspectives.length;
	}

	public void copyPerspective(Perspective p, int side){
		CopyBoth copyBoth = new CopyBoth();
		copyBoth.paste(p, this.perspectives[side]);
	}
}
