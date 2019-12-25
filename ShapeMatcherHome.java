import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ShapeMatcherHome implements ActionListener, MouseListener {
	/**
	 * The frame that holds the home panel.
	 */
	public JFrame frmHome;
	/**
	 * The home panel. Loads the design and holds the JButtons for each option.
	 */
	public mainPanel pnlHome;
	
	//Home screen options
	/**
	 * When pressed, the Shape Matcher game begins.
	 */
	public JButton btnPlay;
	/**
	 * When pressed, the high scores page is loaded.
	 */
	public JButton btnHighScores;
	/**
	 * When pressed, the settings page is loaded.
	 */
	public JButton btnSettings;
	/**
	 * When pressed, the help page is loaded.
	 */
	public JButton btnHelp;
	/**
	 * When pressed, the program is exited.
	 */
	public JButton btnQuit;

	/**
	 * Font that the Jbutton in Home menu uses
	 */
	Font f1 = new Font("Nunito",Font.PLAIN,27);

	/**
	 * Contains the logic that occurs when each button on the home panel is pressed.
	 */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnPlay) {
			System.out.println("Play button pressed");
		} else if (evt.getSource() == btnHighScores) {
			System.out.println("High scores button pressed");
		} else if (evt.getSource() == btnSettings) {
			System.out.println("Settings button pressed");
		} else if (evt.getSource() == btnHelp) {
			System.out.println("Help button pressed");
		} else if (evt.getSource() == btnQuit) {
			System.exit(0); //0, by convention, indicates a normal termination
		}
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseClicked(MouseEvent evt) {

	}
	/**
	 * Created for MouseListener.
	 */
	public void mousePressed(MouseEvent evt) {

	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseReleased(MouseEvent evt) {

	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == btnPlay) {
			btnPlay.setBackground(Color.LIGHT_GRAY);
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setBackground((Color.LIGHT_GRAY));
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setBackground((Color.LIGHT_GRAY));
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setBackground((Color.LIGHT_GRAY));
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setBackground((Color.LIGHT_GRAY));
		}
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseExited(MouseEvent evt) {
		if (evt.getSource() == btnPlay) {
			btnPlay.setBackground(Color.WHITE);
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setBackground((Color.WHITE));
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setBackground((Color.WHITE));
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setBackground((Color.WHITE));
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setBackground((Color.WHITE));
		}
	}
	
	public ShapeMatcherHome() {
		//Home panel setup
		this.pnlHome = new mainPanel();
		this.pnlHome.setPreferredSize(new Dimension(1280, 720));
		this.pnlHome.setLayout(null);
		
		//Play button setup
		this.btnPlay = new JButton();
		this.btnPlay.setSize(130, 50);
		this.btnPlay.setLocation(580, 355);
		this.btnPlay.setText("Play");
		this.btnPlay.setFont(f1);
		this.btnPlay.setBackground(Color.WHITE);
		this.btnPlay.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnPlay.setBackground(Color.WHITE);
		this.btnPlay.addActionListener(this);
		this.btnPlay.addMouseListener(this);
		
		//High scores button setup
		this.btnHighScores = new JButton();
		this.btnHighScores.setSize(240, 50);
		this.btnHighScores.setLocation(520, 425);
		this.btnHighScores.setText("High Scores");
		this.btnHighScores.setFont(f1);
		this.btnHighScores.setBackground(Color.WHITE);
		this.btnHighScores.setBorder(BorderFactory.createEmptyBorder());
		this.btnHighScores.addActionListener(this);
		this.btnHighScores.addMouseListener(this);
		
		//Settings button setup
		this.btnSettings = new JButton();
		this.btnSettings.setSize(180, 50);
		this.btnSettings.setLocation(550, 490);
		this.btnSettings.setText("Settings");
		this.btnSettings.setFont(f1);
		this.btnSettings.setBackground(Color.WHITE);
		this.btnSettings.setBorder(BorderFactory.createEmptyBorder());
		this.btnSettings.addActionListener(this);
		this.btnSettings.addMouseListener(this);
		
		//Help button setup
		this.btnHelp = new JButton();
		this.btnHelp.setSize(120, 50);
		this.btnHelp.setLocation(580, 555);
		this.btnHelp.setText("Help");
		this.btnHelp.setFont(f1);
		this.btnHelp.setBackground(Color.WHITE);
		this.btnHelp.setBorder(BorderFactory.createEmptyBorder());
		this.btnHelp.addActionListener(this);
		this.btnHelp.addMouseListener(this);
		
		//Quit button setup
		this.btnQuit = new JButton();
		this.btnQuit.setSize(120, 40);
		this.btnQuit.setLocation(580, 628);
		this.btnQuit.setText("Quit");
		this.btnQuit.setFont(f1);
		this.btnQuit.setBackground(Color.WHITE);
		this.btnQuit.setBorder(BorderFactory.createEmptyBorder());
		this.btnQuit.addActionListener(this);
		this.btnQuit.addMouseListener(this);
		
		//Adding all of the buttons to the home panel
		this.pnlHome.add(btnPlay);
		this.pnlHome.add(btnHighScores);
		this.pnlHome.add(btnSettings);
		this.pnlHome.add(btnHelp);
		this.pnlHome.add(btnQuit);
		
		//JFrame setup
		this.frmHome = new JFrame("Shape Matcher");
		this.frmHome.setResizable(false);
		this.frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmHome.setContentPane(pnlHome);
		this.frmHome.pack();
		this.frmHome.setVisible(true);
		this.frmHome.setLocationRelativeTo(null); // causes frmHome to centre on screen
		this.frmHome.setResizable(false);
	}

	public static void main(String[] args) {
		ShapeMatcherHome smh = new ShapeMatcherHome();
	}
}
