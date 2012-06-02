import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;


public class Test {
	public static void main (String[] args){
/*		JFrame application=new JFrame("A simple paint program");
		
		PaintPanel paintPanel=new PaintPanel();
		application.add(paintPanel,BorderLayout.CENTER);
		application.add(new JLabel("Drag the mouse to draw"),BorderLayout.SOUTH);
		paintPanel.setLayout(new BorderLayout());
		paintPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Paint"));

	

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(400,200);
		application.setVisible(true);
		
		
		//getting focus to the painting panel
		paintPanel.getComponent(0).requestFocus();*/
		
		
			JFrame application=new JFrame("A simple paint program");
			
			PaintPanel paintPanel=new PaintPanel();
			application.add(paintPanel,BorderLayout.CENTER);
			application.add(new JLabel("Drag the mouse to draw"),BorderLayout.SOUTH);
			
		
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.setSize(1200, 800);
			application.setVisible(true);
			
	}

}
