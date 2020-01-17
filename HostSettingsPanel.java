/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: host settings
 */
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;

public class HostSettingsPanel extends JPanel implements ActionListener, MouseListener{
	
	//PROPERTIES
	BufferedImage image;
	
	//String arrays for JComboBoxes
	String[] modes = {"Traditional", "Real-Time"};
	String[] boards = {"Easy 4x4", "Medium 6x4", "Hard 8x4"};
	
	//JComponents
	JButton home = new JButton();
	JButton start = new JButton();
	JLabel warning = new JLabel("Please enter a value higher than 1000");
	JTextField Plyr = new JTextField("Player 1"); 
	JTextField Port = new JTextField("3112");
	JTextField IP;
	JSlider slide = new JSlider(JSlider.HORIZONTAL,1,3,1);
	JComboBox mode = new JComboBox<String>(modes);
	JComboBox board = new JComboBox<String>(boards);
	
	//Font
	Font f1 = new Font("Nunito", Font.PLAIN, 18);
	
	//Timer (all panels repaint at 60 fps)
	Timer timer = new Timer(1000/60, this);
	
	//Enables networking
	SuperSocketMaster ssm;
	
	//Default settings parameters
	int intPort = 3112; //Port #
	String strPlayer1; //Name of player
	int intTime = 1; //Time that cards stay flipped up
	int intGameMode = 0; //Traditional
	int intBoardSize = 0; //Easy
	
	boolean blnCont = true; //If true, settings are written to a text file and panels are allowed to be swapped
	
	//Text files
	FileWriter files;
	PrintWriter filewrite;
	FileReader thefile;
	BufferedReader readFiles;
	
	ShapeMatcherHome smh; //Main menu
	Board brd;
	
	int intRuns=0; //limits number of times if statement is entered
	
	//METHODS
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}

	public void actionPerformed (ActionEvent evt){
		if (evt.getSource() == timer) {
			repaint();
		}
	}

	public void mouseClicked (MouseEvent evt){		
		//change panels based on selection of button (temporarily make panel invisible)
		if (evt.getSource()==home){
			intRuns = 0; //reset to 0
			smh.frmHome.setTitle("Shape Matcher");
			smh.backToMain();

			// check if toggle music button is on mute or not
			if (smh.btnMusic.getIcon().equals(smh.unmute)) {
				smh.playMusic.setMicrosecondPosition(smh.clipTimePosition); // resume music to where it was paused
				smh.playMusic.start();
			} else {

			}

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
					e.printStackTrace();
				}
				intRuns = 0;
				brd  = new Board("Host_Settings.txt", smh);
				smh.frmHome.setContentPane(brd);
				smh.frmHome.pack();
				smh.frmHome.setVisible(true);
			}
		}
	}

	public void mousePressed (MouseEvent evt){	
	}

	public void mouseReleased (MouseEvent evt){
	}

	public void mouseEntered (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			smh.playHover();
		} else if (evt.getSource() == start) {
			start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			smh.playHover();
		} else if (evt.getSource() == this) {
			//load from file when panel is entered
			if(intRuns==0){
				try{
					thefile = new FileReader("Host_Settings.txt");
					readFiles = new BufferedReader(thefile);
					try{
						board.setSelectedIndex(Integer.parseInt(readFiles.readLine()));
						mode.setSelectedIndex(Integer.parseInt(readFiles.readLine()));
						Plyr.setText(readFiles.readLine());
						slide.setValue(Integer.parseInt(readFiles.readLine()));
						Port.setText(readFiles.readLine());
						thefile.close();
						readFiles.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}
				intRuns++;
			}
		}
	}

	public void mouseExited (MouseEvent evt){
	}

	//CONSTRUCTOR
	public HostSettingsPanel (ShapeMatcherHome smh) {

		try {
			image = ImageIO.read(getClass().getResource("img/Host Settings.png"));
		} catch (IOException e) {
			System.out.println("Error loading host settings image");
			e.printStackTrace();
		}

		this.smh = smh;

		//Panel dimensions and layout null
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		addMouseListener(this);

		ssm = new SuperSocketMaster(intPort, this); //initialize supersocketmaster (do this earlier on full program). Place here temporarily
		
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
		
		//set label size and location
		warning.setFont(f1);
		warning.setSize(new Dimension(300, 50));
		warning.setLocation(930, 265);
		add(warning);
		warning.setVisible(false);

		//set text field size and location
		//Player name text field
		Plyr.setFont(f1);
		Plyr.setSize(new Dimension(250, 50));
		Plyr.setLocation(560, 210);
		Plyr.setHorizontalAlignment(SwingConstants.CENTER);
		add(Plyr);

		//port number
		Port.setFont(f1);
		Port.setSize(new Dimension(250, 50));
		Port.setLocation(930, 210);
		Port.setHorizontalAlignment(SwingConstants.CENTER);
		add(Port);

		//show computer ip address
		IP = new JTextField(ssm.getMyAddress());
		IP.setFont(f1);
		IP.setSize(new Dimension(250, 50));
		IP.setLocation(930, 520);
		IP.setEnabled(false);
		add(IP);

		//combo box for Game mode
		mode.setFont(f1);
		mode.setSize(new Dimension(250, 50));
		mode.setLocation(50, 520);
		mode.setSelectedIndex(0);
		add(mode);

		//Combo box for board size
		board.setFont(f1);
		board.setSize(new Dimension(250, 50));
		board.setLocation(50, 210);
		board.setSelectedIndex(0);
		add(board);

		//slider for time
		slide.setFont(f1);
		slide.setSize(new Dimension(270, 50));
		slide.setLocation(510, 520);
		slide.setMajorTickSpacing(1);
		slide.setSnapToTicks(true);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		add(slide);
		
		timer.start();

	}

}
