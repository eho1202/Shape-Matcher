import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class PlayerSettingsPanel extends JPanel{
	//Properties
	BufferedImage image;
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	//Constructor
	public PlayerSettingsPanel (){
		try{
			image = ImageIO.read(getClass().getResource("Player Settings.png"));
		}catch(IOException e){
			System.out.println("Error loading player settings image");
		}
	}

}
