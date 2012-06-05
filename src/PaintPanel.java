import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class PaintPanel extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {
	private BufferedImage _bufImage = null;
	Graphics2D gc;
	private int pointCount = 0; // count number of points
	private Panel psouth;
	// array of 10000 java.awt.Point references
	private Point points[] = new Point[10000];
	private JLabel color, drawlabel;
	private JComboBox drawchooser, colorchooser;
	private JButton draw;
	private int choice;
	private static int stroke;
	int xStart, yStart;
	int xEnd, yEnd;

	// set up GUI and register mouse event handler
	public PaintPanel() {
		super();

		// handle frame mouse motion event
		addMouseMotionListener(new PaintMouseListener());
		JButton button1 = new JButton("Thin Pen");
		button1.addActionListener(this);

		JButton button2 = new JButton("Medium Pen");
		button2.addActionListener(this);

		JButton button3 = new JButton("Thick Pen");
		button3.addActionListener(this);

		JButton color = new JButton("Color");
		color.addActionListener(this);

		JButton button4 = new JButton("Line");
		button4.addActionListener(this);

		JButton button5 = new JButton("Pen");
		button5.addActionListener(this);

		JButton button6 = new JButton("Empty Oval");
		button6.addActionListener(this);

		JButton button7 = new JButton("Empty Rect");
		button7.addActionListener(this);

		JButton button8 = new JButton("Filled Oval");
		button8.addActionListener(this);

		JButton button9 = new JButton("Filled Rect");
		button9.addActionListener(this);

		JRadioButton thin = new JRadioButton("Thin Line");
		thin.addActionListener(this);
		JRadioButton medium = new JRadioButton("Medium Line");
		medium.addActionListener(this);
		JRadioButton thick = new JRadioButton("Thick Line");
		thick.addActionListener(this);

		ButtonGroup lineOption = new ButtonGroup();
		lineOption.add(thin);
		lineOption.add(medium);
		lineOption.add(thick);

		psouth = new Panel();
		psouth.setBackground(Color.LIGHT_GRAY);

		psouth.add(color);
		psouth.addMouseListener(this);
		add("South", psouth);
		drawlabel = new JLabel("draw");
		draw = new JButton();
		draw.addActionListener(this);
		addMouseListener(this);

		this.add(color);
		this.add(button5);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(thin);
		this.add(medium);
		this.add(thick);
		this.add(button6);
		this.add(button8);
		this.add(button7);
		this.add(button9);
		addMouseListener(this);

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		if (_bufImage == null) {
			int w = this.getWidth();
			int h = this.getHeight();
			_bufImage = (BufferedImage) (this.createImage(w, h));
			gc = _bufImage.createGraphics();
			gc.setColor(Color.BLACK);
		}

		g2.drawImage(_bufImage, null, 0, 0);

	}

	public Color getColor() {
		Color c = gc.getColor();
		gc.setColor(c);
		return c;

	}

	// inner class PaintMouseListener has access to the paint points array
	class PaintMouseListener extends MouseMotionAdapter {

		
		public void mouseDragged(MouseEvent e) {

			switch (choice) {
			case 2:
				getColor();
				gc.fillOval(e.getX(), e.getY(), 6, 6);
				repaint();
				break;

			case 6:

				getColor();
				gc.fillOval(e.getX(), e.getY(), 20, 20);
				repaint();
				break;
			case 5:
				getColor();
				gc.fillOval(e.getX(), e.getY(), 10, 10);
				repaint();
				break;
			case 4:
				getColor();
				gc.fillOval(e.getX(), e.getY(), 5, 5);

				break;

			}
		}
		
	}

	public void check() {
		if (xStart > xEnd) {
			int z = 0;
			z = xStart;
			xStart = xEnd;
			xEnd = z;
		}
		if (yStart > yEnd) {
			int z = 0;
			z = yStart;
			yStart = yEnd;
			yEnd = z;
		}
	}

	public void draw() {
		/*
		 * getColor(); gc.setStroke(new BasicStroke(6)); gc.drawLine(xStart,
		 * yStart, xEnd, yEnd); repaint();
		 */
		Graphics2D g = (Graphics2D) getGraphics();
		int w = xEnd - xStart;
		if (w < 0)
			w = w * (-1);

		int h = yEnd - yStart;
		if (h < 0)
			h = h * (-1);

		switch (choice) {
		case 3:

			getColor();
			gc.setStroke(new BasicStroke(6));
			if (stroke == 0)
				gc.setStroke(new BasicStroke(1));
			if (stroke == 1)
				gc.setStroke(new BasicStroke(3));
			if (stroke == 2)
				gc.setStroke(new BasicStroke(6));

			gc.drawLine(xStart, yStart, xEnd, yEnd);
			repaint();
			break;
		case 7:

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawOval(xStart, yStart, w, h);
			repaint();
			break;
		case 8:

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawRect(xStart, yStart, w, h);
			repaint();
			break;

		case 9:

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawOval(xStart, yStart, w, h);
			gc.fillOval(xStart, yStart, w, h);
			repaint();
			break;
		case 10:

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawRect(xStart, yStart, w, h);
			gc.fillRect(xStart, yStart, w, h);
			repaint();
			break;

		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		xStart = e.getX();
		yStart = e.getY();
		// TODO Auto-generated method stub

	}

	@Override
	
	public void mouseReleased(MouseEvent e) {
		xEnd = e.getX();
		yEnd = e.getY();
		draw();
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Color")) {
			Color bgColor = JColorChooser.showDialog(this,
					"Choose Background Color", getBackground());
			if (bgColor != null)
				gc.setColor(bgColor);
		}

		if (e.getActionCommand().equals("Thin Pen")) {
			choice = 4;
		}

		if (e.getActionCommand().equals("Medium Pen")) {
			choice = 5;
		}

		if (e.getActionCommand().equals("Thick Pen")) {
			choice = 6;
		}
		if (e.getActionCommand().equals("Pen")) {
			choice = 2;
		}
		if (e.getActionCommand().equals("Empty Rect")) {
			choice = 8;

		}
		if (e.getActionCommand().equals("Empty Oval")) {
			choice = 7;

		}
		if (e.getActionCommand().equals("Filled Rect")) {
			choice = 10;

		}
		if (e.getActionCommand().equals("Filled Oval")) {
			choice = 9;

		}
		if (e.getActionCommand().equals("Line")) {
			choice = 3;
		}
		if (e.getActionCommand().equals("Thin Line")) {
			stroke = 0;
		}

		if (e.getActionCommand().equals("Medium Line")) {
			stroke = 1;
		}

		if (e.getActionCommand().equals("Thick Line")) {
			stroke = 2;
		}

	}
}
