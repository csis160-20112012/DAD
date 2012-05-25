import java.awt.BorderLayout;
import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JColorChooser;
	import javax.swing.JPanel;


	public class BoxOfColors extends JFrame{
		private JButton changeColorJButton;
		private Color color =Color.LIGHT_GRAY;
		private JPanel colorJPanel;
		
		public BoxOfColors() {
			
		 
			super("Using JColorChooser");
			colorJPanel=new JPanel();
			colorJPanel.setBackground(color);
			changeColorJButton=new JButton ("Change Color");
			changeColorJButton.addActionListener(
					new ActionListener() {
						
						
						
						public void actionPerformed(ActionEvent e) {
						color=JColorChooser.showDialog(BoxOfColors.this, "Choose a color", color);
						if (color==null)
							color=Color.LIGHT_GRAY;
						colorJPanel.setBackground(color);
						
							
						}
					}
					);
			add(colorJPanel,BorderLayout.CENTER);
			add(changeColorJButton,BorderLayout.SOUTH);
			setSize(500,300);
			setVisible(true);
			
			
			
		}

		
	}



