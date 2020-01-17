/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: player settings
 */
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;

public class PlayerSettingsPanel extends JPanel implements ActionListener, MouseListener {
	
	//PROPERTIES
	BufferedImage image; //Player settings panel image
	
	//JComponents
	JTextField Plyr2 = new JTextField("Player 2");
	JTextField HPort = new JTextField();
	JTextField HIP = new JTextField();
	JLabel lblHome = new JLabel("Back to Main Menu");
	JLabel lblStart = new JLabel("Start");
	JButton home = new JButton();
	JButton start = new JButton();
	JLabel warning = new JLabel("Please enter numbers only");
	JLabel warning1 = new JLabel("Please enter an IP address");
	
	//Timer (all panels repaint at 60 fps)
	Timer timer = new Timer(1000/60, this);
	
	//Settings parameters
	String strPlayer2;
	int intP2Port;
	String strP2IP;
	
	//Text files
	FileWriter PlyrSet;// = new FileWriter("Player_Settings.txt");
	PrintWriter PlyrWrite;// = new PrintWriter(PlyrSet);
	
	//To check whether or not a real IP address has been entered
	int intDot; //Number of periods in string
	String strSub;
	int intLength;
	int intRept; //makes sure that user doesn't type multiple periods in a row
	Boolean blnCheck = true; //If all checks are passed, then the settings are written to a text file
	
	ShapeMatcherHome smh; //Links to main menu
	
	Board brd;
	
	Font f1 = new Font("Nunito", Font.PLAIN, 23); //Font
	
	int intRuns=0;
	
	//METHODS
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == timer) {
			repaint();
		}
	}
	
	//MouseListener methods
	public void mouseClicked (MouseEvent evt){
		if(evt.getSource()== home){
			home.setCursor(Cursor.getDefaultCursor());
			smh.frmHome.setTitle("Shape Matcher");
			smh.backToMain();

			// check if toggle music button is on mute or not
			if (smh.btnMusic.getIcon().equals(smh.unmute)) {
				smh.playMusic.setMicrosecondPosition(smh.clipTimePosition); // resume music to where it was paused
				smh.playMusic.start();
			}
			
			warning.setVisible(false);
			warning1.setVisible(false);
			intRuns=0;//reset variable
			
		}else if(evt.getSource() == start){
			start.setCursor(Cursor.getDefaultCursor());
			
			//load settings info into variables
			strPlayer2 = Plyr2.getText();
			try{
				intP2Port = Integer.parseInt(HPort.getText());
				blnCheck=true;
			}catch(NumberFormatException e){
				warning.setVisible(true);
				blnCheck=false;
				e.printStackTrace();
			}
			
			//To check whether or not a real IP address has been entered
			intDot = 0;
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
				warning1.setVisible(true); //Show warning if an IP address is not inputted
				blnCheck=false;
			}else{
				warning1.setVisible(false);
			} 
			
			//If all checks are passed
			if(blnCheck){
				//load info onto a file
				try{
					PlyrSet = new FileWriter("Player_Settings.txt");
					PlyrWrite = new PrintWriter(PlyrSet);
					PlyrWrite.println(strPlayer2+"\n"+intP2Port+"\n"+strP2IP);
					PlyrSet.close();
					PlyrWrite.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				
				intRuns=0;//reset variable
				setVisible(false);
				brd = new Board("Player_Settings.txt");
				smh.frmHome.setContentPane(brd);
				smh.frmHome.pack();
				smh.frmHome.setVisible(true);
			}
		}
	}
	
	public void mousePressed (MouseEvent evt){	
	}
	public void mouseReleased (MouseEvent evt){	
		if (evt.getSource() == home) {
		    home.setCursor(Cursor.getDefaultCursor());
		    home.setOpaque(false);
		} else if (evt.getSource() == start) {
		    start.setCursor(Cursor.getDefaultCursor());
		    start.setOpaque(false);
		}
	}
	
	public void mouseEntered (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // changes the pointer to hand cursor (more user-friendly)
			home.setOpaque(true);
		} else if (evt.getSource() == start) {
			start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			start.setOpaque(true);
		} else if(evt.getSource()==this){
			if(intRuns==0){
				try{
					thefile = new FileReader("Player_Settings.txt");
					readFiles = new BufferedReader(thefile);
					try{
						Plyr2.setText(readFiles.readLine());
						HPort.setText(readFiles.readLine());
						HIP.setText(readFiles.readLine());
						thefile.close();
						readFiles.close();
					}catch(IOException e){
					}
				}catch(FileNotFoundException e){
				}
				intRuns++;
			}
		}
	}
	public void mouseExited (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getDefaultCursor());
            home.setOpaque(false);
        } else if (evt.getSource() == start) {
			start.setCursor(Cursor.getDefaultCursor());
			start.setOpaque(false);
		}
	}
	
	//Constructor
	public PlayerSettingsPanel(ShapeMatcherHome smh){
		this.smh = smh;
		
		try{
			image = ImageIO.read(getClass().getResource("img/Player Settings.png"));
		}catch(IOException e){
			System.out.println("Error loading player settings image");
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(1280,720));
		setLayout(null);
		addMouseListener(this);
		
		//set labels size and location
		lblHome.setSize(291, 50);
		lblHome.setLocation(33, 30);
		lblHome.setFont(f1);
		add(lblHome);

		lblStart.setSize(85, 40);
		lblStart.setLocation(1160, 650);
		lblStart.setFont(f1);
		add(lblStart);

		//set buttons size and location
		home.setSize(new Dimension(291, 50));
		home.setLocation(33, 30);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.addMouseListener(this);
		add(home);
		
		start.setSize(new Dimension(85, 40));
		start.setLocation(1160, 650);
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.addMouseListener(this);
		add(start);
		
		//set labels' size and location
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
