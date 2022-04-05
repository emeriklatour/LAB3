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

public class Panneau extends Vue {
	protected int side;
	protected Image image;
	protected Perspective perspective;
	protected JPanel currPanel = this;

	public Panneau(int side, Image image, Perspective perspective) {
		this.side = side;
		this.image = image;
		this.perspective = perspective;
		image.addObserver(this);
		perspective.addObserver(this);
		initListeners();
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

	public void initListeners() {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
					createCommandMenu(side).show(currPanel, e.getX(), e.getY());
				}
				else if (SwingUtilities.isLeftMouseButton(e)) {

				}
			}
		});

		Point origin = new Point();
		this.addMouseListener(new MouseAdapter() {
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

		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				Zoom zoom = new Zoom(side, e.getPreciseWheelRotation() * 100);
				Controller.getInstance().handleCommand(zoom);
			}
		});
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
