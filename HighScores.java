import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.ImageIcon.*;

public class HighScores implements MouseListener{
	//Properties
	/**
	 * The high scores panel. Loads the design of the page.
	 */
	HighScoresPanel hsPanel = new HighScoresPanel();
	/**
	 * The frame that holds the high scores panel.
	 */
	JFrame frame = new JFrame();
	/**
	 * The label that holds the top 10 scores for easy mode.
	 */
	JLabel easy = new JLabel();
	/**
	 * The label that holds the top 10 scores for medium mode.
	 */
	JLabel medium = new JLabel();
	/**
	 * The label that holds the top 10 scores for hard mode.
	 */
	JLabel hard = new JLabel();
	/**
	 * The label that loads the "back to main menu" button.
	 */
	JLabel home = new JLabel(new ImageIcon("BacktoMain.png"));
	/**
	 * Load the font for the text.
	 */
	Font f1 = new Font("Nunito",Font.PLAIN,27);
	/**
	 * Main menu. This page appears when the "Settings" button on the main menu is pressed.
	 */
	ShapeMatcherHome smh;
	
	//Methods
	/**
	 * Change panel when the "button" is clicked.
	 */
	public void mouseClicked (MouseEvent evt){
		if(evt.getSource()==home){
			smh.frmHome.setContentPane(smh.pnlHome);
			smh.frmHome.pack();
			smh.frmHome.setVisible(true);
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
		} 
	}
	/**
	 * Created for MouseListener.
	 */
	public void mouseExited (MouseEvent evt){
	}
	
	/**
	 * Reads the specified file and loads the contents into a 2d array. The array is bubble sorted based on the scores from highest to lowest. The top 10 scores are organized into a string variable and returned.
	 */
	public String loadFile (String strFile){
		//variables 
		String strContent;
		String strScores[][];
		int intIndex;
		int intIndex2;
		String strLine;
		int intLeft;
		int intRight;
		
		try{
			FileReader files = new FileReader(strFile); //initialize the FileReader
			BufferedReader readFiles = new BufferedReader(files);
			intIndex=0;//set index to 0
			try{
				//count the number of lines in the file
				strLine = readFiles.readLine();
				while(strLine!=null){
					intIndex++;
					strLine=readFiles.readLine();
				 }
				 
				 files.close(); //close the file
				 strScores = new String[intIndex/2][2]; //declare array
				 files = new FileReader(strFile); //open file again  
				 readFiles = new BufferedReader(files); //read file from the top
				 intIndex=0; //set index to 0
				 intIndex2=0; //set second index to 2
				 
				 strLine = readFiles.readLine(); //read from file again
				
				//load file into a 2d array
				 while(strLine!=null){
					 strScores[intIndex][intIndex2] = strLine;
					 strLine = readFiles.readLine();
					 if(intIndex2==1){
						 intIndex2=0;
						 intIndex++;
					 }else{
						intIndex2=1;
					}
				 }
				 
				 //close FileReader and BufferedReader
				 files.close();
				 readFiles.close();
				 
				 //bubble sort
				 for(int i = 0; i<intIndex-1;i++){
					 for(int j = 0; j<intIndex-1;j++){
						 intLeft = Integer.parseInt(strScores[j][1]);
						 intRight = Integer.parseInt(strScores[j+1][1]);
						 
						 if(intLeft<intRight){
							 for(int k = 0; k<2;k++){
								 strLine = strScores[j][k];
								 strScores[j][k] = strScores[j+1][k];
								 strScores[j+1][k] = strLine;
							 }
						 }
					 }
				 }
				 
				 //statement to be returned
				 strContent="<html>"+strScores[0][0]+" - "+strScores[0][1]+"<br>"+strScores[1][0]+" - "+strScores[1][1]+"<br>"+strScores[2][0]+" - "+strScores[2][1]+"<br>"+strScores[3][0]+" - "+strScores[3][1]+"<br>"+strScores[4][0]+" - "+strScores[4][1]+"<br>"+strScores[5][0]+" - "+strScores[5][1]+"<br>"+strScores[6][0]+" - "+strScores[6][1]+"<br>"+strScores[7][0]+" - "+strScores[7][1]+"<br>"+strScores[8][0]+" - "+strScores[8][1]+"<br>"+strScores[9][0]+" - "+strScores[9][1]+"</html";
				 
				 return strContent;
				 
			 }catch(IOException e){
				 return null;
			 }
		 }catch(FileNotFoundException e){
			 return null;
		 }
	}
	
	//Constructor
	public HighScores (ShapeMatcherHome smh){
		this.smh = smh;
		
		//High score panel dimensions and layout null
		hsPanel.setPreferredSize(new Dimension(1280,720));
		hsPanel.setLayout(null);
				 
		//labels' size, dimension, and content
		easy.setFont(f1);
		easy.setText(loadFile("EasyScores.txt"));
		easy.setSize(new Dimension(200,500));
		easy.setLocation(170,140);
		hsPanel.add(easy);
		
		medium.setFont(f1);
		medium.setText(loadFile("MediumScores.txt"));
		medium.setSize(new Dimension(200,500));
		medium.setLocation(520,140);
		hsPanel.add(medium);
		
		hard.setFont(f1);
		hard.setText(loadFile("HardScores.txt"));
		hard.setSize(new Dimension(200,500));
		hard.setLocation(920,140);
		hsPanel.add(hard);
		
		home.setSize(new Dimension(300,100));
		home.setLocation(1000,650);
		home.addMouseListener(this);
		hsPanel.add(home);
		
		/*
		frame.setResizable(false);
		frame.setContentPane(hsPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
	}
	
	//Main
	/*
	public static void main (String[] args){
		new HighScores();
	}
	*/
}

