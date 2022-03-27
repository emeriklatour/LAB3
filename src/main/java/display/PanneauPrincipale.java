package display;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipale extends JPanel {
	private static final long serialVersionUID = 1L;
	int nbrVue = 0;

	@Override
	public Component add(Component comp) {
		nbrVue++;
		setLayout(new GridLayout(1, nbrVue));
		return super.add(comp);
	}
}
