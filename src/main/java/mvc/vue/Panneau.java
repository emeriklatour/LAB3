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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Panneau extends Vue {
	protected int side;
	protected Image image;
	protected Perspective perspective;
	protected JPanel currPanel;
	protected double zoom = 0.5;
	protected int zoomX;
	protected int zoomY;

	public Panneau(int side, Image image, Perspective perspective) {
		this.side = side;
		this.image = image;
		this.perspective = perspective;
		image.addObserver(this);
		perspective.addObserver(this);
		this.setBackground(new Color(0, 0, 255));
	}

	private JPopupMenu createCommandMenu(int side) {
		final JPopupMenu menu = new JPopupMenu("Menu");

		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener((ActionEvent e) -> {
			createCopyMenu(side);
		});
		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener((ActionEvent e) -> {
			Controller.getInstance().handleCommand(new Paste(side));

		});
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener((ActionEvent e) -> {
			Controller.getInstance().handleCommand(new Undo(side));
		});

		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener((ActionEvent e) -> {
			Controller.getInstance().handleCommand(new Redo(side));
		});

		menu.add(copy);
		menu.add(paste);
		menu.add(undo);
		menu.add(redo);

		return menu;
	}

	public void setCurrPanel(JPanel currPanel) {
		this.currPanel = currPanel;
	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		int w = perspective.getPosX();
		int h = perspective.getPosY();
		double imageWidth = zoom * ( perspective.getZoomFactor() / 1000);
		double imageHeight = zoom * ( perspective.getZoomFactor() / 1000);
		double x = (w - zoom * imageWidth);
		double y = (h - zoom * imageHeight);
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.scale(zoom, zoom);
		g2.drawRenderedImage((BufferedImage) image.getImageToPaint(), at);

		currPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					createCommandMenu(side).show(currPanel, e.getX(), e.getY());
				}
				else if (SwingUtilities.isLeftMouseButton(e)) {

				}
			}

			public void mouseReleased(MouseEvent e){
				//GestionnaireSauvegarde.getInstance().savePerspective(perspective, side); WTF
			}
		});

		currPanel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Translate translate = new Translate(e.getX(), e.getY(), side);
				Controller.getInstance().handleCommand(translate);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

		currPanel.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				zoomX = e.getX();
				zoomY = e.getY();
				if (e.getPreciseWheelRotation() > 0){
					zoom -= 0.001;
				} else {
					zoom += 0.001;
				}
				if (zoom < 0.0001) {
					zoom = 0.001;
				}
				Zoom zoomClass = new Zoom(e.getX(), e.getY(), zoom, side);
				Controller.getInstance().handleCommand(zoomClass);
			}
		});
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
