package mvc.vue;

import command.impl.*;
import copie.strategie.impl.CopyBoth;
import copie.strategie.impl.CopyNone;
import copie.strategie.impl.CopyTranslate;
import copie.strategie.impl.CopyZoom;
import mvc.Controller;
import mvc.modele.Image;
import mvc.modele.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/******************************************************
						Panneau
 * Cours:  LOG121
 * Laboratoire: Laboratoire 3
 * @author Emerik Latour, Lucas Cimino, Philippe Tanguay-Gaudreau
 * @date 2022/04/12
 *******************************************************/

/**
 * Cette classe représente le panneau des perspectives intéractives de
 * l'application.
 */
public class Panneau extends Vue {
	private final int side;
	private Image image;
	private Perspective perspective;
	private JPanel currPanel = this;

	/**
	 * Constructeur par paramêtre de la classe Panneau.
	 *
	 * @param side l'index du panneau
	 * @param image l'image à utiliser pour la perspective intéractive
	 * @param perspective la perspective à utiliser pour recueillir les données
	 *                    nécéssaire à peinturer l'image
	 */
	public Panneau(int side, Image image, Perspective perspective) {
		this.side = side;
		this.image = image;
		this.perspective = perspective;
		image.addObserver(this);
		perspective.addObserver(this);
		initListeners();
		this.setBackground(new Color(0, 0, 255));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(
				image.getImageToPaint(),
				perspective.getPosX(),
				perspective.getPosY(),
				(int) Math.floor(300 * (perspective.getZoomFactor() / 1000)),
				(int) Math.floor(300 * (perspective.getZoomFactor() / 1000)),
				null
		);
	}

	/**
	 * Initialise les listeners pour les actions que l'utilisateur peut
	 * réaliser à partir de ce Panneau.
	 */
	private void initListeners() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					createCommandMenu().show(currPanel, e.getX(), e.getY());
				}
			}
		});

		this.addMouseListener(new MouseAdapter() {
			Point origin = new Point();

			@Override
			public void mousePressed(MouseEvent e) {
				origin.setLocation(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int deltaX = e.getX() - origin.x;
				int deltaY = e.getY() - origin.y;
				if (deltaX != 0 && deltaY != 0) {
					Translate translate = new Translate(deltaX, deltaY, side);
					Controller.getInstance().handleCommand(translate);
				}
			}
		});

		this.addMouseWheelListener( e -> {
			if (perspective.getZoomFactor() + e.getPreciseWheelRotation() * 1000
					> 100) {
				Zoom zoom = new Zoom(side, e.getPreciseWheelRotation() * 1000);
				Controller.getInstance().handleCommand(zoom);
			}
		});
	}

	/**
	 * Créer le menu de commande qui est utilisée quand l'utilisateur appuie sur
	 * le click droit.
	 *
	 * @return l'instance du JPopupMenu créer
	 */
	private JPopupMenu createCommandMenu() {
		final JPopupMenu menu = new JPopupMenu("Menu");

		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener((ActionEvent e) -> createCopyMenu());
		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener((ActionEvent e) ->
			Controller.getInstance().handleCommand(new Paste(this.side)));
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener((ActionEvent e) ->
				Controller.getInstance().handleCommand(new Undo(this.side)));

		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener((ActionEvent e) ->
				Controller.getInstance().handleCommand(new Redo(this.side)));

		menu.add(copy);
		menu.add(paste);
		menu.add(undo);
		menu.add(redo);

		return menu;
	}

	/**
	 * Créer le menu de copie utilisée quand l'utilisateur sélectionne le
	 * l'option "Copy" du CommandMenu.
	 */
	private void createCopyMenu() {
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
				String s = copyGroup.getSelection().getActionCommand();
				if (e.getActionCommand().equals("copy")) {
					Controller.getInstance().handleCommand(new Copy(side,
							s.equals("translate")
									? new CopyTranslate()
									: s.equals("zoom")
									? new CopyZoom()
									: s.equals("both")
									? new CopyBoth()
									: new CopyNone()
					));
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
}
