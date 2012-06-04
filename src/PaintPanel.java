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
		JButton button1 = new JButton("Thin Line");
		button1.addActionListener(this);

		JButton button2 = new JButton("Medium Line");
		button2.addActionListener(this);

		JButton button3 = new JButton("Thick Line");
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
		button6.addActionListener(this);

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
		this.add(button4);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button6);
		this.add(button7);
		addMouseListener(this);

	}

	// draw oval in a 5-by-5 bounding box at specified location on window
	public void paintComponent(Graphics g) {
		// super.paintComponent( g ); // clears drawing area
		// g.setColor(getColor());
		// // draw all points in array
		// switch (stroke){
		//
		// case 2:
		// for ( int i = 0; i < pointCount; i++ )
		// g.fillOval( points[ i ].x, points[ i ].y, 5, 5 );
		// case 3:
		// for (int i=0;i<pointCount;i++)
		// g.fillOval(points[i].x, points[i].y, 10, 10);
		// case 4:
		// for (int i=0;i<pointCount;i++)
		// g.fillOval(points[i].x, points[i].y, 20, 20);
		// }
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		if (_bufImage == null) {
			int w = this.getWidth();
			int h = this.getHeight();
			_bufImage = (BufferedImage) (this.createImage(w, h));
			gc = _bufImage.createGraphics();
			gc.setColor(Color.BLUE);
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

		// store drag coordinates and repaint
		// Jasko: ovdje se odredjuje logika za crtanje olovkom. Provjerite ima
		// li neki bolji nacin obzirom da se ne crtaju linije vec tackice pa ne
		// ispada kao olovka...
		public void mouseDragged(MouseEvent e) {

			switch (choice) {
			case 2:
				getColor();
				gc.fillOval(e.getX(), e.getY(), 10, 10);
				repaint();

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
		// if ( pointCount < points.length )
		// {
		// getColor();
		// points[ pointCount ] = event.getPoint(); // find point
		// pointCount++; // increment number of points in array
		// draw();
		// repaint(); // repaint JFrame
		//
		// }
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
		getColor();
		gc.setStroke(new BasicStroke(6));
		gc.drawLine(xStart, yStart, xEnd, yEnd);
		repaint();

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
	// Jasko: pogresno smo odredjivali koordinatu sa getWidth odnosno getHeight.
	// Ne ide tako :)
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

		if (e.getActionCommand().equals("Thin Line")) {
			choice = 4;
		}

		if (e.getActionCommand().equals("Medium Line")) {
			choice = 5;
		}

		if (e.getActionCommand().equals("Thick Line")) {
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
		if (e.getActionCommand().equals("Line"))
			choice = 3;

	}
}
