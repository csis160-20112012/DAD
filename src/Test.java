import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;


public class Test{ 
	public static void main (String[] args){

		
			JFrame application=new JFrame("Paint.app"); //creating JFrame object to our application
			PaintPanel paintPanel=new PaintPanel(); //creating PaintPanel object
			application.add(paintPanel); //adding paintPanel object to the frame application
			application.add(new JLabel("\nAll rights reserved: 2012, INC. DAD.doo"),BorderLayout.SOUTH);
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closing the application in computer memory
			application.setSize(1366, 768);
			application.setVisible(true);		  
	}

}
