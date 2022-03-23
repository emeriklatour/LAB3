package display;

import mvc.modele.Image;
import mvc.vue.Vignette;

import java.awt.*;

public class PanneauVignette extends Vignette {
	public PanneauVignette(Image image) {
		super(image);
		this.setBackground(new Color(255, 255, 0));
	}

	@Override
	protected void updateDisplay() {
	}
}
