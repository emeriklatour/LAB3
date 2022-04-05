package mvc;

import command.impl.Copy;
import command.impl.Paste;
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
	}

	public void createCopyMenu(int side){
		JFrame copyFrame = new JFrame("Copy Menu");
		JRadioButton copyTranslate = new JRadioButton("Copy Translate");
		copyTranslate.setActionCommand("translate");
		JRadioButton copyZoom = new JRadioButton("Copy Zoom");
		copyZoom.setActionCommand("zoom");
		JRadioButton copyBoth = new JRadioButton("Copy Both");
		copyBoth.setActionCommand("both");
		JRadioButton copyNone = new JRadioButton("Copy None");
		copyNone.setActionCommand("none");

		ButtonGroup copyGroup = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(5,1,4,4));
		copyGroup.add(copyTranslate);
		copyGroup.add(copyZoom);
		copyGroup.add(copyBoth);
		copyGroup.add(copyNone);
		JButton confirmCopy = new JButton(new AbstractAction("copy") {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("copy")) {
					controller.handleCommand(setStrategie(copyGroup.getSelection().getActionCommand(), side));
					copyFrame.dispose();
				}
			}
		});
		confirmCopy.setPreferredSize(new Dimension(100,30));



		radioPanel.add(copyTranslate);
		radioPanel.add(copyZoom);
		radioPanel.add(copyBoth);
		radioPanel.add(copyNone);
		radioPanel.add(confirmCopy);

		radioPanel.setBounds(200, 200, 300, 100);
		radioPanel.setOpaque(false);

		copyFrame.getContentPane().add(radioPanel);

		copyFrame.setSize(400, 400);
		copyFrame.setVisible(true);
	}

	private Copy setStrategie(String strategie, int side) {
		Copy copy = new Copy(null, side);
		switch (strategie){
			case "translate":
				copy = new Copy(new CopyTranslate(), side);
				break;
			case "zoom":
				copy = new Copy(new CopyZoom(), side);
				break;
			case "both":
				copy = new Copy(new CopyBoth(), side);
				break;
			case "none":
				copy = new Copy(new CopyNone(), side);
				break;
		}
		return copy;
	}

	public void paste(int side){
		Paste paste = new Paste(controller.getModele().getPerspective(side), controller.getClipboard());
		controller.handleCommand(paste);
	}
}
