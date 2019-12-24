import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HostSettingsPanel extends JPanel{
	//Properties
	BufferedImage image;
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	//Constructor
	public HostSettingsPanel (){
		try{
			image = ImageIO.read(getClass().getResource("Host Settings.png"));
		}catch(IOException e){
		}
	}

}
