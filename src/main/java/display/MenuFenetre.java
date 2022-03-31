package display;

import mvc.GestionnaireSauvegarde;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuFenetre extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger une image";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_FICHIER_SAVE_MODELE = "Sauvegarder le modele courant";
	private static final String MENU_FICHIER_LOAD_MODELE = "Charger un modele";

	private GestionnaireSauvegarde gSauvegarde;

	public MenuFenetre() {
		this.gSauvegarde = GestionnaireSauvegarde.getInstance();
		ajouterMenuFichier();
	}

	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuSaveModele = new JMenuItem(MENU_FICHIER_SAVE_MODELE);
		JMenuItem menuLoadModele = new JMenuItem(MENU_FICHIER_LOAD_MODELE);

		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Sélectionnez une image");
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".png", "png");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				this.gSauvegarde.loadNewImage(selectedFile.getAbsolutePath());
			}
		});

		menuSaveModele.addActionListener((ActionEvent e) -> {
			JFrame frame = new JFrame();
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Choisissez un fichier");
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".ser", "ser");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showSaveDialog(frame);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File savedFile = fileChooser.getSelectedFile();
				System.out.println("Save as file: " + savedFile.getAbsolutePath());
				GestionnaireSauvegarde.getInstance().saveModel(savedFile.getAbsolutePath() + ".ser");
			}
		});

		menuLoadModele.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Sélectionnez un fichier de sauvegarde");
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".ser", "ser");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				GestionnaireSauvegarde.getInstance().loadModel(selectedFile.getAbsolutePath());
			}

		});

		menuQuitter.addActionListener((ActionEvent e) -> System.exit(0));

		menuFichier.add(menuCharger);
		menuFichier.add(menuLoadModele);
		menuFichier.add(menuSaveModele);
		menuFichier.add(menuQuitter);

		add(menuFichier);
	}

}
