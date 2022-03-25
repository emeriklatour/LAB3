package mvc;

public class GestionnaireSauvegarde {
	private static GestionnaireSauvegarde gSauvegarde = new GestionnaireSauvegarde();
	private Controller controller;

	public static GestionnaireSauvegarde getInstance() {
		return gSauvegarde;
	}

	public void registerController(Controller controller) {
		this.controller = controller;
	}

	public void loadNewImage(String path) {
		controller.getModele().getImage().setImagePath(path);
	}
}
