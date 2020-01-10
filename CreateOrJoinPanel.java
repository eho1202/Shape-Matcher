import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class CreateOrJoinPanel extends JPanel implements ActionListener, MouseListener {
	BufferedImage image;
	public JButton btnCreate;
	public JButton btnJoin;
	public JButton btnBack;
	ShapeMatcherHome smh;
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnCreate) {
			System.out.println("Create button pressed");
			smh.frmHome.setContentPane(smh.pnlHS);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} else if (evt.getSource() == btnJoin) {
			System.out.println("Join button pressed");
			smh.frmHome.setContentPane(smh.pnlPS);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} else if (evt.getSource() == btnBack) {
			System.out.println("Back to main menu button pressed");
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		}
	}

	public void mouseClicked(MouseEvent evt) {

	}

	public void mousePressed(MouseEvent evt) {

	}

	public void mouseReleased(MouseEvent evt) {

	}

	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == btnBack) {
			btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnCreate) {
			btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnJoin) {
			btnJoin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	public void mouseExited(MouseEvent evt) {

	}

	public CreateOrJoinPanel(ShapeMatcherHome smh) {
		this.smh = smh;
		
		try{
			image = ImageIO.read(getClass().getResource("Create or Join.png"));
		}catch(IOException e){
			System.out.println("Error loading create or join server image");
		}
		
		//Create or join panel setup
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		
		//Create server button setup
		this.btnCreate = new JButton();
		this.btnCreate.setSize(320, 80);
		this.btnCreate.setLocation(140, 320);
		this.btnCreate.setBackground(Color.WHITE);
		this.btnCreate.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnCreate.setBackground(Color.WHITE);
		this.btnCreate.addActionListener(this);
		this.btnCreate.addMouseListener(this);
		
		//Join server button setup
		this.btnJoin = new JButton();
		this.btnJoin.setSize(315, 100);
		this.btnJoin.setLocation(835, 310);
		this.btnJoin.setBackground(Color.WHITE);
		this.btnJoin.setBorder(BorderFactory.createEmptyBorder());
		this.btnJoin.setBackground(Color.WHITE);
		this.btnJoin.addActionListener(this);
		this.btnJoin.addMouseListener(this);
		
		//Back to main menu button setup
		this.btnBack = new JButton();
		this.btnBack.setSize(300, 45);
		this.btnBack.setLocation(935, 650);
		this.btnBack.setBackground(Color.WHITE);
		this.btnBack.setBorder(BorderFactory.createEmptyBorder());
		this.btnBack.setBackground(Color.WHITE);
		this.btnBack.addActionListener(this);
		this.btnBack.addMouseListener(this);
		
		//Adding all of the buttons to the panel
		add(btnCreate);
		add(btnJoin);
		add(btnBack);
	}
}
