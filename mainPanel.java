/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: main menu
 */
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class mainPanel extends JPanel implements ActionListener {
	
	//PROPERTIES
	public BufferedImage imgLogo;
	
	//Timer (all panels repaint at 60 fps)
	Timer timer = new Timer(1000/60, this);
	
	//METHODS
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgLogo, 0, 0, this);
		repaint();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == timer) {
			repaint();
		}
	}
	
	//CONSTRUCTOR
	public mainPanel() {
		try {
			imgLogo = ImageIO.read(getClass().getResource("img/Shape Matcher Home.png"));
		} catch (IOException e) {
			System.out.println("Error loading main menu image");
			e.printStackTrace();
		}
	}
}
