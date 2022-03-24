package mvc.vue;

import mvc.modele.Image;

public abstract class Vignette extends BaseVue {
	protected Image image;

	public Vignette(Image image) {
		this.image = image;
		image.addObserver(this);
	}
}
