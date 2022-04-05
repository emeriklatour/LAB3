package mvc.vue;

import command.impl.Translate;
import command.impl.Zoom;
import mvc.CommandAction;
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

	public Panneau() {
	}

	private JPopupMenu createCommandMenu(int side) {
		final JPopupMenu menu = new JPopupMenu("Menu");

		JMenuItem copy = new JMenuItem(new CommandAction("Copy", side));
		JMenuItem paste = new JMenuItem(new CommandAction("Paste", side));
		JMenuItem undo = new JMenuItem(new CommandAction("Undo", side));
		JMenuItem redo = new JMenuItem(new CommandAction("Redo", side));

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

		/*AffineTransform at = g2.getTransform();
		at.translate(zoomX, zoomY);
		at.scale(zoom, zoom);
		at.translate(-zoomX, -zoomY);
		g2.setTransform(at);*/

		/*g.drawImage(
				image.getImageToPaint(),
				perspective.getPosX(),
				perspective.getPosY(),
				300 * (perspective.getZoomFactor() / 1000),
				300 * (perspective.getZoomFactor() / 1000),
				null
		);*/

		currPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					createCommandMenu(side).show(currPanel, e.getX(), e.getY());
				}
				else if (SwingUtilities.isLeftMouseButton(e)) {

				}
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
}
