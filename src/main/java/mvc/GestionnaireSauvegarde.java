package mvc;

import command.impl.Copy;
import copie.strategie.impl.CopyBoth;
import copie.strategie.impl.CopyNone;
import copie.strategie.impl.CopyTranslate;
import copie.strategie.impl.CopyZoom;
import mvc.modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class GestionnaireSauvegarde {
	private static GestionnaireSauvegarde gSauvegarde = new GestionnaireSauvegarde();
	private Controller controller;
	private boolean isImageLoaded = false;

	public static GestionnaireSauvegarde getInstance() {
		return gSauvegarde;
	}

	public void registerController(Controller controller) {
		this.controller = controller;
	}

	public void loadNewImage(String path) {
		controller.getModele().getImage().setImagePath(path);
		isImageLoaded = true;
	}

	/*
		CODE EMPRUNTÉ:
		Les lignes suivantes (29-60) viennent d'un exemple donné sur le site de Laboratoire du cours LOG121
		de L'ÉTS pour la sérialisation d'objets :
		https://cours.etsmtl.ca/log121/private/lab/lab3/index.shtml
		https://www.tutorialspoint.com/java/java_serialization.htm
		(consulté le 26 mars 2022)
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

		System.out.println();
	}
}
