/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: Create or Join (pre-game)
 */
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;

public class CreateOrJoinPanel extends JPanel implements ActionListener, MouseListener {
	
	//PROPERTIES
	BufferedImage image; //Create or join panel image
	
	//Buttons
	public JButton btnCreate;
	public JButton btnJoin;
	public JButton btnBack;
	
	//Timer (all panels repaint at 60 fps)
	Timer timer = new Timer(1000/60, this);
	
	ShapeMatcherHome smh; //Main menu
	
	//METHODS
	//Draws panel image
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnCreate) { //Create server button
			smh.frmHome.setContentPane(smh.pnlHS); //Switch to host settings panel
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} else if (evt.getSource() == btnJoin) { //Join server button
			smh.pnlPS.setVisible(true);
			smh.frmHome.setContentPane(smh.pnlPS); //Switch to player settings panel
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} else if (evt.getSource() == btnBack) { //Back to main menu button
			smh.backToMain(); //Consult backToMain method in ShapeMatcherHome class

			// check if toggle music button is on mute or not
			if (smh.btnMusic.getIcon().equals(smh.unmute)) {
				smh.playMusic.setMicrosecondPosition(smh.clipTimePosition); // resume music to where it was paused
				smh.playMusic.start();
			}
		} else if (evt.getSource() == timer) {
			repaint();
		}
	}

	//MouseListener methods
	public void mouseClicked(MouseEvent evt) {
	}
	public void mousePressed(MouseEvent evt) {
	}

	public void mouseReleased(MouseEvent evt) {
		if (evt.getSource() == btnBack) {
			btnBack.setOpaque(false);
		} else if (evt.getSource() == btnCreate) {
			btnCreate.setOpaque(false);
		} else if (evt.getSource() == btnJoin) {
			btnJoin.setOpaque(false);
		}

	}

	//Change to hand cursor so the user knows they are hovering over a button
	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == btnBack) {
			btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBack.setOpaque(true);
			smh.playHover();
		} else if (evt.getSource() == btnCreate) {
			btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCreate.setOpaque(true);
			smh.playHover();
		} else if (evt.getSource() == btnJoin) {
			btnJoin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnJoin.setOpaque(true);
			smh.playHover();
		}
	}

	public void mouseExited(MouseEvent evt) {
		if (evt.getSource() == btnBack) {
			btnBack.setOpaque(false);
		} else if (evt.getSource() == btnCreate) {
			btnCreate.setOpaque(false);
		} else if (evt.getSource() == btnJoin) {
			btnJoin.setOpaque(false);
		}

	}

	//CONSTRUCTOR
	public CreateOrJoinPanel(ShapeMatcherHome smh) {
		this.smh = smh; //Linking to main menu
		
		//Finding create or join panel image
		try{
			image = ImageIO.read(getClass().getResource("img/Create or Join.png"));
		}catch(IOException e){
			System.out.println("Error loading create or join server image");
			e.printStackTrace();
		}
		
		//Create or join panel setup
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		
		//Create server button setup
		this.btnCreate = new JButton();
		this.btnCreate.setSize(320, 80);
		this.btnCreate.setLocation(140, 320);
		this.btnCreate.setOpaque(false);
		this.btnCreate.setContentAreaFilled(false);
		this.btnCreate.setBorderPainted(false);
		this.btnCreate.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnCreate.setBackground(new Color(128,128,128,30));
		this.btnCreate.addActionListener(this);
		this.btnCreate.addMouseListener(this);
		
		//Join server button setup
		this.btnJoin = new JButton();
		this.btnJoin.setSize(315, 100);
		this.btnJoin.setLocation(835, 310);
		this.btnJoin.setOpaque(false);
		this.btnJoin.setContentAreaFilled(false);
		this.btnJoin.setBorderPainted(false);
		this.btnJoin.setBorder(BorderFactory.createEmptyBorder());
		this.btnJoin.setBackground(new Color(128,128,128,30));
		this.btnJoin.addActionListener(this);
		this.btnJoin.addMouseListener(this);
		
		//Back to main menu button setup
		this.btnBack = new JButton();
		this.btnBack.setSize(300, 45);
		this.btnBack.setLocation(935, 650);
		this.btnBack.setOpaque(false);
		this.btnBack.setContentAreaFilled(false);
		this.btnBack.setBorderPainted(false);
		this.btnBack.setBorder(BorderFactory.createEmptyBorder());
		this.btnBack.setBackground(new Color(128,128,128,30));
		this.btnBack.addActionListener(this);
		this.btnBack.addMouseListener(this);
		
		//Adding all of the buttons to the panel
		add(btnCreate);
		add(btnJoin);
		add(btnBack);
	}
}
