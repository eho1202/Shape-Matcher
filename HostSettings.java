import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class HostSettings implements ActionListener, MouseListener{
	//Properties
	/**
	 * The label for the "Back to Main Menu" button
	 */
	JLabel home = new JLabel(new ImageIcon("BacktoMain.png"));
	/**
	 * The label for the "Start" button
	 */
	JLabel start = new JLabel(new ImageIcon("Start.png"));
	/**
	 * The label that asks the user to enter a higher port number
	 */
	JLabel warning = new JLabel("Please enter a value higher than 1000");
	/**
	 * Text field for user to change their name. The default is "Player 1".
	 */
	JTextField Plyr = new JTextField("Player 1");
	/**
	 * Text field for user to change the port number. The default is 3112.
	 */
	JTextField Port = new JTextField("3112");
	/**
	 * Text field to display the user's IP address.
	 */
	JTextField IP;
	/**
	 * Slider to adjust the time a card stays flipped over. The values are in miliseconds. Default is 1000 miliseconds.
	 */
	JSlider slide = new JSlider(JSlider.HORIZONTAL,1,3,1);
	/**
	 * String array for the different game modes available.
	 */
	String[] modes = {"Traditional", "Real-Time"};
	/**
	 * Combo box to display the different game modes.
	 */
	JComboBox mode = new JComboBox<String>(modes);
	/**
	 * String array to keep track of the game board sizes.
	 */
	String[] boards = {"Easy 4x4", "Medium 6x4", "Hard 8x4"};
	/**
	 * Combo box to display the different game board sizes.
	 */
	JComboBox board = new JComboBox<String>(boards);
	/**
	 * The frame for the settings.
	 */
	JFrame frame = new JFrame();
	/**
	 * Font to make everything more uniform
	 */
	Font f1 = new Font("Nunito", Font.PLAIN, 18);

	/**
	 * The panel for the host settings.
	 */
	HostSettingsPanel HSetPanel = new HostSettingsPanel();
	/**
	 * Supersocketmaster for network communication
	 */
	SuperSocketMaster ssm;
	/**
	 * Integer variable to keep track of the port number. The default is 3112.
	 */
	int intPort = 3112;
	/**
	 * String variable to keep track of the player name.
	 */
	String strPlayer1;
	/**
	 * Integer variable to keep track of the time a card spends flipped over.
	 */
	int intTime = 1;
	/**
	 * Integer variable to keep track of the game mode.
	 */
	int intGameMode = 0;
	/** 
	 * Integer variable to keep track of the game board size.
	 */
	int intBoardSize = 0;
	/**
	 * Boolean variable to control whether the panel continues on to the game screen or not.
	 */
	boolean blnCont = true;
	/**
	 * FileWriter to open a file to write to.
	 */
	FileWriter files;
	/**
	 * PrintWriter to write to the file.
	 */
	PrintWriter filewrite;
	
	/**
	 * Main menu. This page appears when the "Settings" button on the main menu is pressed.
	 */
	ShapeMatcherHome smh;
	
	//Methods
	/**
	 * Method created for the supersocketmaster object.
	 */
	public void actionPerformed (ActionEvent evt){
	}
	
	/**
	 * Changes panel based on selection of "button". If the "start" button is selected, then all the game settings are loaded into their respective variables and writen to the file. 
	 */
	public void mouseClicked (MouseEvent evt){
		//change panels based on selection of button (temporarily make panel invisible)
		if (evt.getSource()==home){
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} else if (evt.getSource()==start){
			//Load all settings into variables
			strPlayer1 = Plyr.getText();
			
			intPort = Integer.parseInt(Port.getText());
			//check if user has entered a number greater than 1000. 
			if (intPort<=1000){
				warning.setVisible(true);
				blnCont=false; //set to false to prevent panels from swapping
			}else{
				warning.setVisible(false);
				blnCont=true; //set to true to allow panels to swap
			}
			
			intGameMode = mode.getSelectedIndex();
			intBoardSize = board.getSelectedIndex();
			intTime = slide.getValue()*1000;
			
			if (blnCont){
				//write settings to a file
				try{
					files = new FileWriter("Host_Settings.txt");
					filewrite = new PrintWriter(files);
					filewrite.println(intBoardSize+"\n"+intGameMode+"\n"+strPlayer1+"\n"+intTime+"\n"+intPort+"\n"+IP.getText());
					files.close();
					filewrite.close();
				}catch(IOException e){
				}
				HSetPanel.setVisible(false);
			}
		}
	}
	/**
	 * Created for MouseListener.
	 */
	public void mousePressed (MouseEvent evt){	
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseReleased (MouseEvent evt){	
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseEntered (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} else if (evt.getSource() == start) {
			start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseExited (MouseEvent evt){
	}

	//Constructor
	public HostSettings (ShapeMatcherHome smh){
		this.smh = smh;
		
		//Panel dimensions and layout null
		HSetPanel.setPreferredSize(new Dimension(1280,720));
		HSetPanel.setLayout(null);
		
		ssm = new SuperSocketMaster(intPort, this);//initialize supersocketmaster (do this earlier on full program). Place here temporarily
		
		//set labels' size and location
		home.setSize(new Dimension(500,50));
		home.setLocation(-55,50);
		home.addMouseListener(this);
		HSetPanel.add(home);
		
		start.setSize(new Dimension(200,50));
		start.setLocation(1080,670);
		start.addMouseListener(this);
		HSetPanel.add(start);
		
		warning.setSize(new Dimension(300,50));
		warning.setLocation(930,265);
		HSetPanel.add(warning);
		warning.setVisible(false);
		
		//set text field size and location
		//Player name text field
		Plyr.setSize(new Dimension(250,50));
		Plyr.setLocation(560,210);
		Plyr.setHorizontalAlignment(SwingConstants.CENTER);
		Plyr.setFont(f1);
		HSetPanel.add(Plyr);
		
		//port number
		Port.setSize(new Dimension(250,50));
		Port.setLocation(930,210);
		Port.setHorizontalAlignment(SwingConstants.CENTER);
		Port.setFont(f1);
		HSetPanel.add(Port);
		
		//show computer ip address
		IP = new JTextField(ssm.getMyAddress());
		IP.setSize(new Dimension(250,50));
		IP.setLocation(930,520);
		IP.setHorizontalAlignment(SwingConstants.CENTER);
		IP.setFont(f1);
		IP.setEnabled(false);
		HSetPanel.add(IP);
		
		//combo box for Game mode
		mode.setSize(new Dimension(250,50));
		mode.setLocation(50,520);
		mode.setSelectedIndex(0);
		mode.setFont(f1);
		HSetPanel.add(mode);
		
		//Combo box for board size
		board.setSize(new Dimension(250,50));
		board.setLocation(50,210);
		board.setSelectedIndex(0);
		board.setFont(f1);
		HSetPanel.add(board);
		
		//slider for time
		slide.setSize(new Dimension(270,50));
		slide.setLocation(510,520);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true); 
		slide.setPaintLabels(true);
		slide.setFont(f1);
		HSetPanel.add(slide);
		
		/*frame.setResizable(false);
		frame.setContentPane(HSetPanel);
		frame.pack();
		//frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	}
	/*public static void main(String[] args){
		new HostSettings(null);
	}*/
}
