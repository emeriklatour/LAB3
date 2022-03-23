package mvc;

public class GestionnaireSauvegarde {
	private Controller controller;

	public GestionnaireSauvegarde(Controller controller) {
		this.controller = controller;
	}

	public void loadNewImage(String path) {
		controller.getModele().getImage().setImagePath(path);
	}
}
