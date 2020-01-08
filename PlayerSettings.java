import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class PlayerSettings implements MouseListener{
	//Properties
	/**
	 * The frame for the player settings screen.
	 */
	JFrame PlyrSetFrame = new JFrame();
	/**
	 * The panel for the player settings page.
	 */
	PlayerSettingsPanel PlyrSetPanel = new PlayerSettingsPanel();
	/**
	 * The text field for the player name.
	 */
	JTextField Plyr2 = new JTextField("Player 2");
	/**
	 * The text field for the host's port number.
	 */
	JTextField HPort = new JTextField();
	/**
	 * The text field for the host's IP address.
	 */
	JTextField HIP = new JTextField();
	/**
	 * The label for the "Back to Main Menu" button.
	 */
	JLabel home = new JLabel(new ImageIcon("BacktoMain.png"));
	/**
	 * The label for the "Start" button.
	 */
	JLabel start = new JLabel(new ImageIcon("Start.png"));
	/**
	 * The label to ask user to enter numbers only.
	 */
	JLabel warning = new JLabel("Please enter numbers only");
	/**
	 * The label to ask user to enter numbers only.
	 */
	JLabel warning1 = new JLabel("Please enter numbers only");
	/**
	 * String variable for the player name.
	 */
	String strPlayer2;
	/**
	 * Integer variable for the port number entered.
	 */
	int intP2Port;
	/**
	 * String variable for the ip address entered.
	 */
	String strP2IP;
	/**
	 * Opens a file to write to.
	 */
	FileWriter PlyrSet;// = new FileWriter("Player_Settings.txt");
	/**
	 * Writes to the file.
	 */
	PrintWriter PlyrWrite;// = new PrintWriter(PlyrSet);
	/**
	 * Boolean variable to control whether or not the panel continues to the game screen or not.
	 */
	Boolean blnCheck = true;
	/**
	 * Integer variable to keep track of the number of periods in the variable, strP2IP.
	 */
	int intDot;
	/**
	 * String variable to keep track of each substring in the variable, strP2IP.
	 */
	String strSub;
	/**
	 * Integer variable to keep track of substring index.
	 */
	int intI;
	/**
	 * Integer variable to keep track of the string's length.
	 */
	int intLength;
	/**
	 * Integer variable to see if periods were right after one another in the variable, strP2IP.
	 */
	int intRept;
	   
	//Methods
	/**
	 * Changes panel based on the "button" selection.
	 */
	public void mouseClicked (MouseEvent evt){
		if(evt.getSource()== home){
			PlyrSetPanel.setVisible(false);
		}else if(evt.getSource() == start){
			//load settings info into variables
			strPlayer2 = Plyr2.getText();
			try{
				intP2Port = Integer.parseInt(HPort.getText());
				blnCheck=true;
			}catch(NumberFormatException e){
				warning.setVisible(true);
				blnCheck=false;
			}
			
			intDot = 0;
			intI = 1;
			intRept = 0;
			strP2IP = HIP.getText();
			intLength = strP2IP.length();
			for(int i=0; i<intLength;i++){
				strSub = strP2IP.substring(i,i+1);
				if(strSub.equals(".")){
					intDot++;
					intRept++;
				}else{
					intRept=0;
				}
			}
			
			if(intDot!=3||intRept!=0){
				warning1.setVisible(true);
				blnCheck=false;
			}else{
				warning1.setVisible(false);
			}
			
			if(blnCheck){
				//load info onto a file
				try{
					PlyrSet = new FileWriter("Player_Settings.txt");
					PlyrWrite = new PrintWriter(PlyrSet);
					PlyrWrite.println(strPlayer2+"\n"+intP2Port+"\n"+strP2IP);
					PlyrSet.close();
					PlyrWrite.close();
				}catch(IOException e){
				}

				PlyrSetPanel.setVisible(false);
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
	public PlayerSettings(){
		PlyrSetPanel.setPreferredSize(new Dimension(1280,720));
		PlyrSetPanel.setLayout(null);
		
		//set labels' size and location
		home.setSize(new Dimension(500,50));
		home.setLocation(-55,50);
		home.addMouseListener(this);
		PlyrSetPanel.add(home);
		
		start.setSize(new Dimension(200,50));
		start.setLocation(1080,670);
		start.addMouseListener(this);
		PlyrSetPanel.add(start);
		
		warning.setSize(new Dimension(300,50));
		warning.setLocation(510,440);
		warning.setVisible(false);
		PlyrSetPanel.add(warning);
		
		warning1.setSize(new Dimension(300,50));
		warning1.setLocation(510,640);
		warning1.setVisible(false);
		PlyrSetPanel.add(warning1);
		
		//set text fields' size and location
		//Player name
		Plyr2.setSize(new Dimension(250,50));
		Plyr2.setLocation(590,190);
		PlyrSetPanel.add(Plyr2);
		
		//Port number
		HPort.setSize(new Dimension(250,50));
		HPort.setLocation(510,400);
		PlyrSetPanel.add(HPort);
		
		//IP address
		HIP.setSize(new Dimension(250,50));
		HIP.setLocation(510,600);
		PlyrSetPanel.add(HIP);
		
		/*PlyrSetFrame.setResizable(false);
		PlyrSetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlyrSetFrame.setContentPane(PlyrSetPanel);
		PlyrSetFrame.pack();
		PlyrSetFrame.setVisible(true);*/
	}
	
	//Main
	public static void main(String[] args){
		new PlayerSettings();
	}

}
