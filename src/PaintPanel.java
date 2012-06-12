import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PaintPanel extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	private BufferedImage _bufImage = null; // used for repainting components
	Graphics2D gc; // used for graphics (paint component)
	private int choice;// for deciding on which button we have clicked
	private static int stroke, clear = 0; // used for JRadiobutton's, clear=0
											// for clearing panel
	int xStart, yStart;// initial positions of the mouse
	int xEnd, yEnd;// final mouse positions
	private JPanel buttonPanel; // Position of the buttons

	/**
	 * @author Dina
	 */
	// constructor
	public PaintPanel() {
		super();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(18, 6)); // reserved space for the
														// button placement
														// (rows, columns)

		// handle frame mouse motion event
		addMouseMotionListener(new PaintMouseListener());

		JRadioButton button2 = new JRadioButton("Medium Pen");
		button2.addActionListener(this);

		JRadioButton button3 = new JRadioButton("Thick Pen");
		button3.addActionListener(this);

		JRadioButton button5 = new JRadioButton("Thin Pen");
		button5.addActionListener(this);

		JButton color = new JButton("Color");
		color.addActionListener(this);

		JButton button4 = new JButton("Line");
		button4.addActionListener(this);

		JButton button10 = new JButton("Pen");
		button10.addActionListener(this);

		JButton button6 = new JButton("Empty Oval");
		button6.addActionListener(this);

		JButton button7 = new JButton("Empty Rect");
		button7.addActionListener(this);

		JButton button8 = new JButton("Filled Oval");
		button8.addActionListener(this);

		JButton button9 = new JButton("Filled Rect");
		button9.addActionListener(this);

		JButton clear = new JButton("Clear");
		clear.addActionListener(this);

		JRadioButton thin = new JRadioButton("Thin Line");
		thin.addActionListener(this);

		JRadioButton medium = new JRadioButton("Medium Line");
		medium.addActionListener(this);

		JRadioButton thick = new JRadioButton("Thick Line");
		thick.addActionListener(this);

		ButtonGroup lineOption = new ButtonGroup(); // adding sub-buttons for
													// line
		lineOption.add(thin);
		lineOption.add(medium);
		lineOption.add(thick);

		ButtonGroup penOption = new ButtonGroup(); // adding sub-buttons for pen
		penOption.add(button2);
		penOption.add(button3);
		penOption.add(button5);

		// adding the order of the buttons
		this.add(color);
		this.add(clear);
		this.add(button10);
		this.add(button3);
		this.add(button2);
		this.add(button5);
		this.add(button4);
		this.add(thin);
		this.add(medium);
		this.add(thick);
		this.add(button6);
		this.add(button8);
		this.add(button7);
		this.add(button9);
		// adding mouse listener to the buttons
		addMouseListener(this);

		buttonPanel.add(color);
		buttonPanel.add(clear);
		buttonPanel.add(Box.createVerticalStrut(1));
		buttonPanel.add(button10);
		buttonPanel.add(button3);
		buttonPanel.add(button2);
		buttonPanel.add(button5);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 9)));
		buttonPanel.add(button4);
		buttonPanel.add(thin);
		buttonPanel.add(medium);
		buttonPanel.add(thick);
		buttonPanel.add(Box.createVerticalStrut(1));
		buttonPanel.add(button6);
		buttonPanel.add(button8);
		buttonPanel.add(button7);
		buttonPanel.add(button9);

		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.WEST); // positioning the buttons to
													// the left side of panel

	}

	/**
	 * @author Dina Amela Dino
	 */

	// GUI implementation
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// creating graphics
		Graphics2D g2 = (Graphics2D) g;
		if (_bufImage == null) {
			int w = this.getWidth();
			int h = this.getHeight();
			_bufImage = (BufferedImage) (this.createImage(w, h)); // creating
																	// background
																	// for
																	// drawing
			gc = _bufImage.createGraphics();
			gc.setColor(Color.BLACK); // initial mouse drawing color set to
										// black

		}

		g2.drawImage(_bufImage, null, 0, 0); // drawing image

	}

	/**
	 * @author Dino
	 */
	// implementing colorchooser
	public Color getColor() {
		Color c = gc.getColor();
		gc.setColor(c);
		return c;

	}

	/**
	 * @author Dina
	 */
	// implementing the shapes options with MouseListener
	class PaintMouseListener extends MouseMotionAdapter {

		// mousedragged events
		public void mouseDragged(MouseEvent e) {

			switch (choice) {

			case 2: // sizes of pen
				getColor();
				gc.setStroke(new BasicStroke(6));
				if (stroke == 3) // thin pen
					gc.fillOval(e.getX(), e.getY(), 5, 5);
				if (stroke == 4) // medium pen
					gc.fillOval(e.getX(), e.getY(), 10, 10);
				if (stroke == 5) // thick pen
					gc.fillOval(e.getX(), e.getY(), 20, 20);

				gc.fillOval(e.getX(), e.getY(), 6, 6); // pen buttons
				repaint(); // repainting the frame
				break;

			}
		}
	}

	/**
	 * @author Amela
	 */
	public void check() {
		if (xStart > xEnd) { // swap variables if starting position of x
								// variable is bigger than ending position
			int z = 0;// declaring a new variable for swaping
			z = xStart;// putting the starting position of x
			xStart = xEnd; // swaping it with xEnd position
			xEnd = z; // end of swaping
		}
		if (yStart > yEnd) {
			int z = 0;
			z = yStart;
			yStart = yEnd;
			yEnd = z;
		}
	}

	/**
	 * @author Amela
	 */
	// method for drawing
	public void draw() {

		Graphics2D g = (Graphics2D) getGraphics();
		int w = xEnd - xStart; // in case of negative width turn into positive
								// value
		if (w < 0)
			w = w * (-1);

		int h = yEnd - yStart; // in case of negative height turn into positive
		if (h < 0)
			h = h * (-1);

		switch (getChoice()) {
		case 3:

			getColor();
			gc.setStroke(new BasicStroke(6)); // Changes the width and size of
												// the mouse stroke, point
			if (getStroke() == 0)
				gc.setStroke(new BasicStroke(1)); // thin line
			if (getStroke() == 1)
				gc.setStroke(new BasicStroke(3)); // medium line
			if (getStroke() == 2)
				gc.setStroke(new BasicStroke(6)); // thick line

			gc.drawLine(xStart, yStart, xEnd, yEnd); // drawing the actual line
			repaint();
			break;

		case 7: // drawing empty oval

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawOval(xStart, yStart, w, h);
			repaint();
			break;

		case 8: // drawing empty rectangle

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawRect(xStart, yStart, w, h);
			repaint();
			break;

		case 9:// drawing filled oval

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawOval(xStart, yStart, w, h);
			gc.fillOval(xStart, yStart, w, h);
			repaint();
			break;

		case 10: // drawing filled rectangle

			getColor();
			gc.setStroke(new BasicStroke(6));
			gc.drawRect(xStart, yStart, w, h);
			gc.fillRect(xStart, yStart, w, h);
			repaint();
			break;

		case 11: // Clear the frame

			repaint();
			Color temp = gc.getColor();
			gc.setColor(Color.WHITE);
			gc.fillRect(0, 0, getWidth(), getHeight()); // clearing the whole
														// frame
			gc.setColor(temp); // white
			repaint();
			break;

		case 12: // calling to the case 11

			if (clear == 1) { // applying the click on the frame cleares it
				gc.clearRect(xStart, yStart, w, h);
			} else {

			}
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

	/**
	 * @author Dino
	 */
	@Override
	public void mousePressed(MouseEvent e) { // taking actual position of the
												// mouse when pressed

		xStart = e.getX();
		yStart = e.getY();

		// TODO Auto-generated method stub

	}

	/**
	 * @author Dino
	 */
	@Override
	public void mouseReleased(MouseEvent e) { // taking position of the mouse
												// when released

		xEnd = e.getX();
		yEnd = e.getY();
		draw();

		// TODO Auto-generated method stub

	}

	/**
	 * @author Dina
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Cursor hand = new Cursor(Cursor.DEFAULT_CURSOR);// cursor type
		setCursor(hand);

		// implementing JColorChooser, box of colors
		if (e.getActionCommand().equals("Color")) {
			Color bgColor = JColorChooser.showDialog(this, "Pick your colour",
					getBackground());
			if (bgColor != null)
				gc.setColor(bgColor);
		}

		// setting strokes and cases for implementing methods for draw and
		// paintComponent
		if (e.getActionCommand().equals("Thin Pen")) {
			setStroke(3);
		}

		if (e.getActionCommand().equals("Medium Pen")) {
			setStroke(4);
		}

		if (e.getActionCommand().equals("Thick Pen")) {
			setStroke(5);
		}
		if (e.getActionCommand().equals("Pen")) {
			setChoice(2);
		}
		if (e.getActionCommand().equals("Empty Rect")) {
			setChoice(8);

		}
		if (e.getActionCommand().equals("Empty Oval")) {
			setChoice(7);

		}
		if (e.getActionCommand().equals("Filled Rect")) {
			setChoice(10);

		}
		if (e.getActionCommand().equals("Filled Oval")) {
			setChoice(9);

		}
		if (e.getActionCommand().equals("Line")) {
			setChoice(3);
		}
		if (e.getActionCommand().equals("Thin Line")) {
			setStroke(0);
		}

		if (e.getActionCommand().equals("Medium Line")) {
			setStroke(1);
		}

		if (e.getActionCommand().equals("Thick Line")) {
			setStroke(2);
		}
		if (e.getActionCommand().equals("Clear")) {
			setChoice(11);
		}

	}

	public void setChoice(int choice) {//
		this.choice = choice;
	}

	public int getChoice() {
		return choice;
	}

	public static void setStroke(int stroke) {
		PaintPanel.stroke = stroke;
	}

	public static int getStroke() {
		return stroke;
	}
}