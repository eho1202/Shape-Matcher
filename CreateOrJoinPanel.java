import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class CreateOrJoinPanel extends JPanel {
	//Properties
	BufferedImage image;
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	//Constructor
	public CreateOrJoinPanel (){
		try{
			image = ImageIO.read(getClass().getResource("Create or Join.png"));
		}catch(IOException e){
			System.out.println("Error loading create or join server image");
		}
	}

}
