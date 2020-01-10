import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class HostSettingsPanel extends JPanel implements ActionListener, MouseListener{
	
	//Properties
	BufferedImage image;
	JLabel home = new JLabel(new ImageIcon("BacktoMain.png"));
	JLabel start = new JLabel(new ImageIcon("Start.png"));
	JLabel warning = new JLabel("Please enter a value higher than 1000");
	JTextField Plyr = new JTextField("Player 1"); 
	JTextField Port = new JTextField("3112");
	JTextField IP;
	JSlider slide = new JSlider(JSlider.HORIZONTAL,1,3,1);
	String[] modes = {"Traditional", "Real-Time"};
	JComboBox mode = new JComboBox<String>(modes);
	String[] boards = {"Easy 4x4", "Medium 6x4", "Hard 8x4"};
	JComboBox board = new JComboBox<String>(boards);
	Font f1 = new Font("Nunito", Font.PLAIN, 18);
	SuperSocketMaster ssm;
	int intPort = 3112;
	String strPlayer1;
	int intTime = 1;
	int intGameMode = 0;
	int intBoardSize = 0;
	boolean blnCont = true;
	FileWriter files;
	PrintWriter filewrite;
	ShapeMatcherHome smh;
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
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
			System.out.println("Back to main menu button pressed");
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
	public HostSettingsPanel (ShapeMatcherHome smh){
		
		try{
			image = ImageIO.read(getClass().getResource("Host Settings.png"));
		}catch(IOException e){
			System.out.println("Error loading host settings image");
		}
		
		this.smh = smh;
		
		//Panel dimensions and layout null
		setPreferredSize(new Dimension(1280,720));
		setLayout(null);
		
		ssm = new SuperSocketMaster(intPort, this); //initialize supersocketmaster (do this earlier on full program). Place here temporarily
		
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
		warning.setLocation(930,265);
		add(warning);
		warning.setVisible(false);
		
		//set text field size and location
		//Player name text field
		Plyr.setFont(f1);
		Plyr.setSize(new Dimension(250,50));
		Plyr.setLocation(560,210);
		Plyr.setHorizontalAlignment(SwingConstants.CENTER);
		add(Plyr);
		
		//port number
		Port.setFont(f1);
		Port.setSize(new Dimension(250,50));
		Port.setLocation(930,210);
		Port.setHorizontalAlignment(SwingConstants.CENTER);
		add(Port);
		
		//show computer ip address
		IP = new JTextField(ssm.getMyAddress());
		IP.setFont(f1);
		IP.setSize(new Dimension(250,50));
		IP.setLocation(930,520);
		IP.setEnabled(false);
		add(IP);
		
		//combo box for Game mode
		mode.setFont(f1);
		mode.setSize(new Dimension(250,50));
		mode.setLocation(50,520);
		mode.setSelectedIndex(0);
		add(mode);
		
		//Combo box for board size
		board.setFont(f1);
		board.setSize(new Dimension(250,50));
		board.setLocation(50,210);
		board.setSelectedIndex(0);
		add(board);
		
		//slider for time
		slide.setFont(f1);
		slide.setSize(new Dimension(270,50));
		slide.setLocation(510,520);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true); 
		slide.setPaintLabels(true);
		add(slide);
	}
}
