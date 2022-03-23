package mvc.vue;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public abstract class BaseVue extends JPanel implements Observer {
	protected abstract void updateDisplay();

	@Override
	public void update(Observable o, Object arg) {
		updateDisplay();
	}
}
