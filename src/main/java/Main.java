import command.Command;
import display.FenetrePrincipale;
import mvc.Controller;
import mvc.GestionnaireSauvegarde;
import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import utils.Side;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Modele modele = getDefaultModele();
		Controller.getInstance().handleCommand(getInitCommand(modele));
		FenetrePrincipale fenetrePrincipale = new FenetrePrincipale(modele);
	}

	private static Modele getDefaultModele() {
		Map<Side, Perspective> perspectives = new HashMap<>();
		Arrays.stream(Side.values()).forEach(
			(s) -> perspectives.put(s, new Perspective())
		);
		return new Modele(new Image(), perspectives);
	}

	private static Command getInitCommand(Modele modele) {
		return new Command() {
			@Override
			public void execute(Controller controller) {
				controller.setModele(modele);
				GestionnaireSauvegarde.getInstance()
						.registerController(controller);
			}

			@Override
			public void revert(Controller controller) {}

			@Override
			public Side getSide() {
				return null;
			}
		};
	}
}
