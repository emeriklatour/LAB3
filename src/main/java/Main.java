import command.Command;
import display.FenetrePrincipale;
import mvc.Controller;
import mvc.GestionnaireSauvegarde;
import mvc.modele.Image;
import mvc.modele.Modele;
import mvc.modele.Perspective;
import mvc.vue.Panneau;
import mvc.vue.Vignette;
import utils.Side;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		Modele modele = getDefaultModele();
		createVue(modele);
		Controller.getInstance().handleCommand(getInitCommand(modele));
	}

	private static Modele getDefaultModele() {
		Map<Side, Perspective> perspectives = new HashMap<>();
		Arrays.stream(Side.values()).forEach(
			(s) -> perspectives.put(s, new Perspective())
		);
		return new Modele(new Image(), perspectives);
	}

	private static void createVue(Modele modele) {
		FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
		fenetrePrincipale.addToPanneau(new Vignette(modele.getImage()));
		Arrays.stream(Side.values()).forEach(
				(s) -> fenetrePrincipale.addToPanneau(
						new Panneau(
							s,
							modele.getImage(),
							modele.getPerspective(s)
						)
				)
		);
	}

	private static Command getInitCommand(Modele modele) {
		return new Command() {
			@Override
			public void execute(Controller controller) {
				controller.setModele(modele);
				GestionnaireSauvegarde.getInstance()
						.registerController(controller);
				controller.initializeView();
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
