package mvc.vue;

import mvc.modele.Image;
import mvc.modele.Perspective;
import utils.Side;

public abstract class Panneau extends BaseVue {
	protected Side side;
	protected Image image;
	protected Perspective perspective;

	public Panneau(Side side, Image image, Perspective perspective) {
		this.side = side;
		this.image = image;
		this.perspective = perspective;
		image.addObserver(this);
		perspective.addObserver(this);
	}
}
