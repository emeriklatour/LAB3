package mvc.vue;

import mvc.observateur.Observer;

import javax.swing.*;

public abstract class BaseVue extends JPanel implements Observer {
	@Override
	public void update() {
		repaint();
	}
}
