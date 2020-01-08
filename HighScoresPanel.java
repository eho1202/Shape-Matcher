import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HighScoresPanel extends JPanel{
	//Properties
	BufferedImage image;
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	//Constructor
	public HighScoresPanel (){
		try{
			image = ImageIO.read(getClass().getResource("High Scores.png"));
		}catch(IOException e){
		}
	}

}
