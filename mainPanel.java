import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class mainPanel extends JPanel {
	
	//PROPERTIES
	public BufferedImage imgLogo;
	
	//METHODS
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgLogo, 0, 0, this);
	}
	
	//CONSTRUCTOR
	public mainPanel() {
		try {
			imgLogo = ImageIO.read(getClass().getResource("Shape Matcher Home.png"));
		} catch (IOException e) {
			System.out.println("Error loading main menu image");
		}
	}
}
