import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class PHostSettingsPanel extends JPanel implements ActionListener, MouseListener{
	
	//Properties
	BufferedImage image;
	JButton home = new JButton();
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

	public void actionPerformed (ActionEvent evt){
	}

	public void mouseClicked (MouseEvent evt){		
		//change panels based on selection of button (temporarily make panel invisible)
		if (evt.getSource()==home){
			System.out.println("Back to main menu button pressed");
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
		} 
	}

	public void mousePressed (MouseEvent evt){	
	}

	public void mouseReleased (MouseEvent evt){	
	}

	public void mouseEntered (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		} 
	}

	public void mouseExited (MouseEvent evt){
	}

	//Constructor
	public PHostSettingsPanel (ShapeMatcherHome smh) {

		try {
			image = ImageIO.read(getClass().getResource("Preferred Host Settings.png"));
		} catch (IOException e) {
			System.out.println("Error loading host settings image");
		}

		this.smh = smh;

		//Panel dimensions and layout null
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);

		ssm = new SuperSocketMaster(intPort, this); //initialize supersocketmaster (do this earlier on full program). Place here temporarily

		//set labels' size and location
		home.setSize(new Dimension(291, 50));
		home.setLocation(33, 30);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.addMouseListener(this);
		add(home);

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
		slide.setSize(new Dimension(270, 70));
		slide.setLocation(510, 520);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		add(slide);

	}

}
