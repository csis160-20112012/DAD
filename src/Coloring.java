import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class Coloring extends JPanel {
	//Algorithm for coloring the background ali nezz calling issue --
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(new Color (255,0,0));
		g.fillRect(15,25,100,20);
		g.drawString("Current RGB"+g.getColor(),130,40);
		
		
		g.setColor(Color.BLUE);
		g.fillRect(15, 50, 100, 20);
		g.drawString("Current RGB"+g.getColor(), 130, 65);
		
		Color color=Color.MAGENTA;
		g.setColor(color);
		g.fillRect(15, 100, 100, 20);
		g.drawString("RGB values"+color.getRed()+","+color.getGreen()+","+color.getBlue(),130,115);
		
		
		
	
	}
 
}
