package mvc;
import mvc.modele.Modele;
import java.io.*;

/******************************************************
 				GestionnaireSauvegarde
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Classe singleton permettant d'enregistrer le controleur et d'y effectuer
 * les operations necessaires a la sauvegarde et la restauration du modele.
 */
public class GestionnaireSauvegarde {
	/**
	 * Variable statique privée de la classe GestionnaireSauvegarde.
	 */
	private static GestionnaireSauvegarde gSauvegarde = new GestionnaireSauvegarde();
	private Controller controller;

	/**
	 * Retourne l'instance gSauvegarde. Ceci nous permet d'appliquer le patron singleton.
	 * @return L'instance singleton GestionnaireSauvegarde
	 */
	public static GestionnaireSauvegarde getInstance() {
		return gSauvegarde;
	}

	/**
	 * Enregistre le controleur afin d'effecteur les operatrions de sauvegarde et de restauration.
	 * @param controller Controleur de l'application
	 */
	public void registerController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Permet d'enregistrer une nouvelle image au modele.
	 * @param path chemin absolu vers l'image dans l'arborescence de fichiers de la machine.
	 */
	public void loadNewImage(String path) {
		controller.getModele().getImage().setImagePath(path);
	}

	/*
		CODE EMPRUNTÉ:
		Les lignes suivantes (57-97) viennent d'un exemple donné sur le site de Laboratoire du cours LOG121
		de L'ÉTS pour la sérialisation d'objets :
		https://cours.etsmtl.ca/log121/private/lab/lab3/index.shtml
		https://www.tutorialspoint.com/java/java_serialization.htm
		(consulté le 26 mars 2022)
 	*/

	/**
	 * Sauvegarde le modele dans un fichier serialise dont la localisation et le nom sont
	 * choisis par l'utilisateur.
	 * @param path chemin absolu vers le fichier de serialisation dans l'arborescence
	 *             de fichiers de la machine.
	 */
	public void saveModel(String path){
		try {
			FileOutputStream fileOut =
					new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(controller.getModele());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Charge un modele recemment sauvegarde dans un fichier serialise.
	 * @param path chemin absolu vers le fichier de serialisation dans l'arborescence
	 *             de fichiers de la machine.
	 */
	public void loadModel(String path){
		Modele m = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (Modele) in.readObject();
			in.close();
			fileIn.close();
			controller.getModele().copyFrom(m);
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("");
			c.printStackTrace();
		}
	}
}
