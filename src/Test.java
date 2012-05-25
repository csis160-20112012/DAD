
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Test {

	public static void main(String[] args) {
		BoxOfColors app = new BoxOfColors();

		PaintPanel paintPanel = new PaintPanel(); // create paint panel
		app.add(paintPanel, BorderLayout.CENTER); // in center to know,mclsdnfwfc

		// create a label and place it in SOUTH of BorderLayout
		app.add(new JLabel("Drag the mouse to draw"), BorderLayout.SOUTH);

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400, 200); // set frame size
		app.setVisible(true); // display frame
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setDefaultCloseOperation(BoxOfColors.EXIT_ON_CLOSE);

	}

}
