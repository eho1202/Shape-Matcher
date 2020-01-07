import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PreGameSettings implements ActionListener {
	public JFrame frmPre;
	public CreateOrJoinPanel pnlPre;
	public JButton btnCreate;
	public JButton btnJoin;
	public JButton btnBack;
	/**
	 * Main menu. This page appears when the "Play" button on the main menu is pressed.
	 */
	ShapeMatcherHome smh;
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnCreate) {
			System.out.println("Create button pressed");
		} else if (evt.getSource() == btnJoin) {
			System.out.println("Join button pressed");
		} else if (evt.getSource() == btnBack) {
			System.out.println("Back to main menu button pressed");
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		}
	}

	public PreGameSettings(ShapeMatcherHome smh) {
		//Connects this page to the main menu
		this.smh = smh;
		
		//Create or join panel setup
		this.pnlPre = new CreateOrJoinPanel();
		this.pnlPre.setPreferredSize(new Dimension(1280, 720));
		this.pnlPre.setLayout(null);
		
		//Create server button setup
		this.btnCreate = new JButton();
		this.btnCreate.setSize(320, 80);
		this.btnCreate.setLocation(140, 320);
		this.btnCreate.setBackground(Color.WHITE);
		this.btnCreate.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnCreate.setBackground(Color.WHITE);
		this.btnCreate.addActionListener(this);
		
		//Join server button setup
		this.btnJoin = new JButton();
		this.btnJoin.setSize(315, 100);
		this.btnJoin.setLocation(835, 310);
		this.btnJoin.setBackground(Color.WHITE);
		this.btnJoin.setBorder(BorderFactory.createEmptyBorder());
		this.btnJoin.setBackground(Color.WHITE);
		this.btnJoin.addActionListener(this);
		
		//Back to main menu button setup
		this.btnBack = new JButton();
		this.btnBack.setSize(300, 45);
		this.btnBack.setLocation(935, 650);
		this.btnBack.setBackground(Color.WHITE);
		this.btnBack.setBorder(BorderFactory.createEmptyBorder());
		this.btnBack.setBackground(Color.WHITE);
		this.btnBack.addActionListener(this);
		
		//Adding all of the buttons to the panel
		this.pnlPre.add(btnCreate);
		this.pnlPre.add(btnJoin);
		this.pnlPre.add(btnBack);
		
		//JFrame setup
		/*this.frmPre = new JFrame();
		this.frmPre.setResizable(false);
		this.frmPre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmPre.setContentPane(pnlPre);
		this.frmPre.pack();
		this.frmPre.setVisible(true);
		this.frmPre.setLocationRelativeTo(null);*/
	}
	
	/*public static void main(String[] args) {
		new PreGameSettings();
	}*/
}
