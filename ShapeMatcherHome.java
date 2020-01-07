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
	 * Opens dialog to exit program
	 */
	public JOptionPane optionQuit;

	/**
	 * Font that the JButton in the home menu uses
	 */
	Font f1 = new Font("Nunito", Font.PLAIN, 27);

	/**
	 * (Preferred) host settings page
	 */
	HostSettings hs = new HostSettings(this);
	
	/**
	 * Player settings page
	 */
	PreGameSettings pgs = new PreGameSettings(this);

	/**
	 * Help page
	 */
	Help help = new Help(this);
	
	/**
	 * High Scores page
	 */
	 HighScores highscores = new HighScores(this);
	
	/**
	 * Contains the logic that occurs when each button on the home panel is pressed.
	 */
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnPlay) {
			System.out.println("Play button pressed");
			frmHome.setContentPane(pgs.pnlPre);
			frmHome.pack();
			frmHome.setVisible(true);
		} else if (evt.getSource() == btnHighScores) {
			System.out.println("High scores button pressed");
			frmHome.setContentPane(highscores.hsPanel);
			frmHome.pack();
			frmHome.setVisible(true);
		} else if (evt.getSource() == btnSettings) {
			System.out.println("Settings button pressed");
			frmHome.setContentPane(hs.HSetPanel);
			frmHome.setTitle("Host Settings");
			frmHome.pack();
			frmHome.setVisible(true);
		} else if (evt.getSource() == btnHelp) {
			System.out.println("Help button pressed");
			frmHome.setContentPane(help.helpPanel);
			frmHome.setTitle("Help");
			frmHome.pack();
			frmHome.setVisible(true);
		} else if (evt.getSource() == btnQuit) {
			int response = optionQuit.showConfirmDialog(null, "Are you sure you want to quit game?", "Quit Game", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				System.exit(0); //0, by convention, indicates a normal termination
			} else {
				return; // closes dialog box and returns to homepage
			}
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
			btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseExited(MouseEvent evt) {
			btnPlay.setCursor(Cursor.getDefaultCursor());
			btnHighScores.setCursor(Cursor.getDefaultCursor());
			btnSettings.setCursor(Cursor.getDefaultCursor());
			btnHelp.setCursor(Cursor.getDefaultCursor());
			btnQuit.setCursor(Cursor.getDefaultCursor());
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
		this.btnPlay.setBackground(Color.WHITE);
		this.btnPlay.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnPlay.setOpaque(false);
		this.btnPlay.setContentAreaFilled(false);
		this.btnPlay.setBorderPainted(false);
		this.btnPlay.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnPlay.addActionListener(this);
		this.btnPlay.addMouseListener(this);

		//High scores button setup
		this.btnHighScores = new JButton();
		this.btnHighScores.setSize(240, 50);
		this.btnHighScores.setLocation(520, 425);
		this.btnHighScores.setOpaque(false);
		this.btnHighScores.setContentAreaFilled(false);
		this.btnHighScores.setBorderPainted(false);
		this.btnHighScores.setBorder(BorderFactory.createEmptyBorder());
		this.btnHighScores.addActionListener(this);
		this.btnHighScores.addMouseListener(this);

		//Settings button setup
		this.btnSettings = new JButton();
		this.btnSettings.setSize(180, 50);
		this.btnSettings.setLocation(550, 490);
		this.btnSettings.setOpaque(false);
		this.btnSettings.setContentAreaFilled(false);
		this.btnSettings.setBorderPainted(false);
		this.btnSettings.setBorder(BorderFactory.createEmptyBorder());
		this.btnSettings.addActionListener(this);
		this.btnSettings.addMouseListener(this);

		//Help button setup
		this.btnHelp = new JButton();
		this.btnHelp.setSize(120, 50);
		this.btnHelp.setLocation(580, 555);
		this.btnHelp.setOpaque(false);
		this.btnHelp.setContentAreaFilled(false);
		this.btnHelp.setBorderPainted(false);
		this.btnHelp.setBorder(BorderFactory.createEmptyBorder());
		this.btnHelp.addActionListener(this);
		this.btnHelp.addMouseListener(this);

		//Quit button setup
		this.btnQuit = new JButton();
		this.btnQuit.setSize(120, 40);
		this.btnQuit.setLocation(580, 628);
		this.btnQuit.setOpaque(false);
		this.btnQuit.setContentAreaFilled(false);
		this.btnQuit.setBorderPainted(false);
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
		this.frmHome.setLocationRelativeTo(null); //causes frmHome to centre on screen
		this.frmHome.setResizable(false);
	}

	public static void main(String[] args) {
		ShapeMatcherHome smh = new ShapeMatcherHome();
	}
}
