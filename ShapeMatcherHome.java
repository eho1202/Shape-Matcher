/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Main menu
 */
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ShapeMatcherHome implements ActionListener, MouseListener {

	//PROPERTIES
	public JFrame frmHome;
	public mainPanel pnlHome;

	//Home screen options
	public JButton btnPlay;
	public JButton btnHighScores;
	public JButton btnSettings;
	public JButton btnHelp;
	public JButton btnQuit;
	public JOptionPane optionQuit;
	
	//Music
	Clip playMusic;
	long clipTimePosition;
	JToggleButton btnMusic;
	Icon mute;
	Icon unmute;
	
	//All game panels
	CreateOrJoinPanel pnlCreateOrJoin = new CreateOrJoinPanel(this); //Create or join server pre-game
	PHostSettingsPanel pnlPHS = new PHostSettingsPanel(this); //Settings --> Preferred host settings
	HostSettingsPanel pnlHS = new HostSettingsPanel(this); //Create server --> Host settings
	PlayerSettingsPanel pnlPS = new PlayerSettingsPanel(this); //Join server --> player settings
	HighScoresPanel pnlHigh = new HighScoresPanel(this); //High scores
	HelpPanel pnlHelp = new HelpPanel(this); //Help panel
	InfoPanel pnlInfo = new InfoPanel(this); //Help --> Information about settings
	HowToPlayPanel pnlHow = new HowToPlayPanel(this); //Help --> How to play
	EndPanel pnlEnd = new EndPanel(this); //Victory or defeat panel

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnPlay) {
			System.out.println("Play button pressed");
			frmHome.setContentPane(pnlCreateOrJoin);
			frmHome.pack();
			frmHome.setVisible(true);
			clipTimePosition = playMusic.getMicrosecondPosition(); // get time where music is stopped
			playMusic.stop();
		} else if (evt.getSource() == btnHighScores) {
			System.out.println("High scores button pressed");
			playMusic.stop();
			frmHome.setContentPane(pnlHigh);
			frmHome.setTitle("High Scores");
			frmHome.pack();
			frmHome.setVisible(true);
			clipTimePosition = playMusic.getMicrosecondPosition();
			playMusic.stop();
		} else if (evt.getSource() == btnSettings) {
			System.out.println("Settings button pressed");
			playMusic.stop();
			frmHome.setContentPane(pnlPHS);
			frmHome.setTitle("Preferred Host Settings");
			frmHome.pack();
			frmHome.setVisible(true);
			clipTimePosition = playMusic.getMicrosecondPosition();
			playMusic.stop();
		} else if (evt.getSource() == btnHelp) {
			System.out.println("Help button pressed");
			playMusic.stop();
			frmHome.setContentPane(pnlHelp);
			frmHome.setTitle("Help");
			frmHome.pack();
			frmHome.setVisible(true);
			clipTimePosition = playMusic.getMicrosecondPosition();
			playMusic.stop();
		} else if (evt.getSource() == btnQuit) {
			int response = optionQuit.showConfirmDialog(null, "Are you sure you want to quit game?", "Quit Game", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				System.exit(0); //0, by convention, indicates a normal termination
			} else {
				return;// closes dialog box and returns to homepage
			}
		} else if (evt.getSource() == btnMusic) {
			if (btnMusic.getIcon().equals(unmute)) {
				btnMusic.setIcon(mute);
				btnMusic.setBackground(Color.DARK_GRAY);
				clipTimePosition = playMusic.getMicrosecondPosition(); // get time where music is stopped
				playMusic.stop();
			} else if (btnMusic.getIcon().equals(mute)){
				btnMusic.setIcon(unmute);
				playMusic.setMicrosecondPosition(clipTimePosition); // resume music to where it was paused
				playMusic.start();
			}
		}
	}

	//MouseListener methods
	public void mouseClicked(MouseEvent evt) {
	}
	public void mousePressed(MouseEvent evt) {
	}
	public void mouseReleased(MouseEvent evt) {
		// Added to prevent the light grey background to show when going back to main menu
		if (evt.getSource() == btnPlay) {
			btnPlay.setOpaque(false);
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setOpaque(false);
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setOpaque(false);
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setOpaque(false);
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setOpaque(false);
		}

	}

	public void mouseEntered(MouseEvent evt) {
		if (evt.getSource() == btnPlay) {
			btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Changes to hand cursor (more user-friendly)
			btnPlay.setOpaque(true);
			playHover();
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnHighScores.setOpaque(true);
			playHover();
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSettings.setOpaque(true);
			playHover();
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnHelp.setOpaque(true);
			playHover();
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnQuit.setOpaque(true);
			playHover();
		} else if (evt.getSource() == btnMusic) {
			btnMusic.setBackground(new Color(128,128,128,30));
		}
	}

	public void mouseExited(MouseEvent evt) {
		if (evt.getSource() == btnPlay) {
			btnPlay.setOpaque(false);
		} else if (evt.getSource() == btnHighScores) {
			btnHighScores.setOpaque(false);
		} else if (evt.getSource() == btnSettings) {
			btnSettings.setOpaque(false);
		} else if (evt.getSource() == btnHelp) {
			btnHelp.setOpaque(false);
		} else if (evt.getSource() == btnQuit) {
			btnQuit.setOpaque(false);
		} else if (evt.getSource() == btnMusic) {
			btnMusic.setBackground(Color.WHITE);
		}

	}

	// method to play a sound when hovering over a button
    public void playHover(){
		try {
			AudioInputStream hover = AudioSystem.getAudioInputStream(new File("audio/hover.wav"));
			Clip hovering = AudioSystem.getClip();
			hovering.open(hover);
			hovering.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// plays the music file bgm.wav
	private void playMusic() {
		try {
			AudioInputStream music = AudioSystem.getAudioInputStream(new File("audio/bgm.wav"));
			playMusic = AudioSystem.getClip();
			playMusic.open(music);
			FloatControl gainControl = (FloatControl) playMusic.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f);
			playMusic.start();
			playMusic.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to go back to main menu
	public void backToMain() {
	    frmHome.setContentPane(pnlHome);
	    frmHome.pack();
	    frmHome.setVisible(true);
    }

	public ShapeMatcherHome() {
		playMusic();

		// get images for mute and unmute icons
		mute = new ImageIcon("mute.png");
		unmute = new ImageIcon("unmute.png");
		
		//Home panel setup
		this.pnlHome = new mainPanel();
		this.pnlHome.setPreferredSize(new Dimension(1280, 720));
		this.pnlHome.setLayout(null);

		//Play button setup
		this.btnPlay = new JButton();
		this.btnPlay.setSize(130, 50);
		this.btnPlay.setLocation(580, 355);
		this.btnPlay.setOpaque(false);
		this.btnPlay.setContentAreaFilled(false);
		this.btnPlay.setBorderPainted(false);
		this.btnPlay.setBorder(BorderFactory.createEmptyBorder()); //Creates a borderless, transparent button
		this.btnPlay.setBackground(new Color(128,128,128,30));
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
		this.btnHighScores.setBackground(new Color(128,128,128,30));
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
		this.btnSettings.setBackground(new Color(128,128,128,30));
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
		this.btnHelp.setBackground(new Color(128,128,128,30));
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
		this.btnQuit.setBackground(new Color(128,128,128,30));
		this.btnQuit.addActionListener(this);
		this.btnQuit.addMouseListener(this);

		// Toggle music button setup
		this.btnMusic = new JToggleButton();
		this.btnMusic.setSize(50,50);
		this.btnMusic.setLocation(1190,640);
		this.btnMusic.setBorderPainted(false);
		this.btnMusic.setBorder(BorderFactory.createEmptyBorder());
		this.btnMusic.setIcon(unmute);
		this.btnMusic.setBackground(Color.WHITE);
		this.btnMusic.addActionListener(this);
		this.btnMusic.addMouseListener(this);

		//Adding all of the buttons to the home panel
		this.pnlHome.add(btnPlay);
		this.pnlHome.add(btnHighScores);
		this.pnlHome.add(btnSettings);
		this.pnlHome.add(btnHelp);
		this.pnlHome.add(btnQuit);
		this.pnlHome.add(btnMusic);

		//JFrame setup
		this.frmHome = new JFrame("Shape Matcher");
		this.frmHome.setResizable(false);
		this.frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmHome.setContentPane(pnlHome);
		this.frmHome.pack();
		this.frmHome.setVisible(true);
		this.frmHome.setLocationRelativeTo(null); //causes frmHome to centre on screen
	}

	public static void main(String[] args) {
		ShapeMatcherHome smh = new ShapeMatcherHome();
	}
}
