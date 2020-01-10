import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class PlayerSettingsPanel extends JPanel implements MouseListener {
	
	//Properties
	BufferedImage image;	
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
	JLabel warning1 = new JLabel("Please enter an IP address");
	/**
	 * String variable for the player name.
	 */
	String strPlayer2;
	/**
	 * Integer variable for the port number entered.
	 */
	int intP2Port;
	
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
	ShapeMatcherHome smh;
	int intDot;
	String strSub;
	int intI;
	int intLength;
	int intRept;
	Font f1 = new Font("Nunito", Font.PLAIN, 18);
	
	//Methods
	/**
	 * Changes panel based on the "button" selection.
	 */
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	public void mouseClicked (MouseEvent evt){
		if(evt.getSource()== home){
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
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

				setVisible(false);
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
	public PlayerSettingsPanel(ShapeMatcherHome smh){
		this.smh = smh;
		
		try{
			image = ImageIO.read(getClass().getResource("Player Settings.png"));
		}catch(IOException e){
			System.out.println("Error loading player settings image");
		}
		
		setPreferredSize(new Dimension(1280,720));
		setLayout(null);
		
		//set labels' size and location
		home.setSize(new Dimension(500,50));
		home.setLocation(-55,50);
		home.addMouseListener(this);
		add(home);
		
		start.setSize(new Dimension(200,50));
		start.setLocation(1080,670);
		start.addMouseListener(this);
		add(start);
		
		warning.setFont(f1);
		warning.setSize(new Dimension(300,50));
		warning.setLocation(510,440);
		warning.setVisible(false);
		add(warning);
		
		warning1.setFont(f1);
		warning1.setSize(new Dimension(300,50));
		warning1.setLocation(510,640);
		warning1.setVisible(false);
		add(warning1);
		
		//set text fields' size and location
		//Player name
		Plyr2.setFont(f1);
		Plyr2.setSize(new Dimension(250,50));
		Plyr2.setLocation(590,190);
		add(Plyr2);
		
		//Port number
		HPort.setFont(f1);
		HPort.setSize(new Dimension(250,50));
		HPort.setLocation(510,400);
		add(HPort);
		
		//IP address
		HIP.setFont(f1);
		HIP.setSize(new Dimension(250,50));
		HIP.setLocation(510,600);
		add(HIP);
	}
}
