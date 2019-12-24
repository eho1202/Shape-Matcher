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
	 * The label for the time slider, so the user sees the values in seconds while the slider values are in miliseconds
	 */
	JLabel time = new JLabel("0.5                 1                 1.5                  2                  2.5");
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
	JSlider slide = new JSlider(JSlider.HORIZONTAL,500,2500,1000);
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
	int intTime = 1000;
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
			HSetPanel.setVisible(false);
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
			intTime = slide.getValue();
			
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
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseExited (MouseEvent evt){
	}

	//Constructor
	public HostSettings (){
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
		
		//label for time instead of paint labels so viewers see slider values in seconds instead of miliseconds
		time.setSize(new Dimension(500,50));
		time.setLocation(510,535);
		HSetPanel.add(time);
		
		//set text field size and location
		//Player name text field
		Plyr.setSize(new Dimension(250,50));
		Plyr.setLocation(560,210);
		HSetPanel.add(Plyr);
		
		//port number
		Port.setSize(new Dimension(250,50));
		Port.setLocation(930,210);
		HSetPanel.add(Port);
		
		//show computer ip address
		IP = new JTextField(ssm.getMyAddress());
		IP.setSize(new Dimension(250,50));
		IP.setLocation(930,520);
		IP.setEnabled(false);
		HSetPanel.add(IP);
		
		//combo box for Game mode
		mode.setSize(new Dimension(250,50));
		mode.setLocation(50,520);
		mode.setSelectedIndex(0);
		HSetPanel.add(mode);
		
		//Combo box for board size
		board.setSize(new Dimension(250,50));
		board.setLocation(50,210);
		board.setSelectedIndex(0);
		HSetPanel.add(board);
		
		//slider for time
		slide.setSize(new Dimension(270,30));
		slide.setLocation(510,520);
		slide.setMajorTickSpacing(500);
		slide.setPaintTicks(true);
		//slide.setPaintLabels(true);
		HSetPanel.add(slide);
		
		frame.setResizable(false);
		frame.setContentPane(HSetPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		new settings();
	}
}
