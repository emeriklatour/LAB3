import Initializer.DefaultViewInitializer;
import Initializer.ViewInitializer;
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
		ViewInitializer initializer = new DefaultViewInitializer();
		initializer.start();
	}
}
