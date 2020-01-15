/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: high scores
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HighScoresPanel extends JPanel implements MouseListener{
	
	//PROPERTIES
	BufferedImage image; //High scores panel image
	
	//JComponents (labels and button)
	JLabel easy = new JLabel();
	JLabel medium = new JLabel();
	JLabel hard = new JLabel();
	JButton home = new JButton("Back to Main Menu");
	
	//Fonts
	Font f1 = new Font("Nunito",Font.PLAIN,27);
	Font f2 = new Font("Nunito",Font.PLAIN,34);
	
	ShapeMatcherHome smh; //Main menu
	
	//METHODS
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		repaint();
	}

	//MouseListener methods
	public void mouseClicked (MouseEvent evt){
		if(evt.getSource()==home){
			smh.frmHome.setTitle("Shape Matcher");
			smh.backToMain(); //Consult backToMain method in ShapeMatcherHome class

			// check if toggle music button is on mute or not
			if (smh.btnMusic.getIcon().equals(smh.unmute)) {
				smh.playMusic.setMicrosecondPosition(smh.clipTimePosition); // resume music to where it was paused
				smh.playMusic.start();
			} else {

			}

		}
	}


	public void mousePressed (MouseEvent evt){
	}

	public void mouseReleased (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setOpaque(false);
		}
	}

	public void mouseEntered (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			home.setOpaque(true);
			smh.playHover();
		}
	}

	public void mouseExited (MouseEvent evt){
		if (evt.getSource() == home) {
			home.setOpaque(false);
		}
	}

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
				e.printStackTrace();
				return null;
			}
		}catch(FileNotFoundException e){
			return null;
		}
	}
	
	//CONSTRUCTOR
	public HighScoresPanel (ShapeMatcherHome smh){
		try{
			image = ImageIO.read(getClass().getResource("img/High Scores.png"));
		}catch(IOException e){
			e.printStackTrace();
		}

		this.smh = smh;

		//High score panel dimensions and layout null
		setPreferredSize(new Dimension(1280,720));
		setLayout(null);

		//labels' size, dimension, and content
		easy.setFont(f1);
		easy.setText(loadFile("EasyScores.txt"));
		easy.setSize(new Dimension(200,500));
		easy.setLocation(170,140);
		add(easy);

		medium.setFont(f1);
		medium.setText(loadFile("MediumScores.txt"));
		medium.setSize(new Dimension(200,500));
		medium.setLocation(520,140);
		add(medium);

		hard.setFont(f1);
		hard.setText(loadFile("HardScores.txt"));
		hard.setSize(new Dimension(200,500));
		hard.setLocation(920,140);
		add(hard);

		home.setSize(new Dimension(320,65));
		home.setLocation(925,640);
		home.setFont(f2);
		home.setForeground(Color.BLACK);
		home.setBackground(new Color(128,128,128,30));
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.setBorder(BorderFactory.createEmptyBorder());
		home.addMouseListener(this);
		add(home);

	}

}
