import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;

public class Board extends JPanel implements ActionListener, MouseListener{
	//Properties
	JFrame theframe = new JFrame();
	Timer theTimer = new Timer(1000/60, this);
	Font f1 = new Font("Nunito", Font.PLAIN,30);
	Font f2 = new Font("Nunito", Font.PLAIN,34);
	Font f3 = new Font("Nunito", Font.PLAIN,24);
	Font f4 = new Font("Nunito", Font.PLAIN,12);
	JLabel playerturn = new JLabel("Player 1's Turn!",JLabel.CENTER);
	JLabel scoreboard = new JLabel("Scoreboard");
	JLabel player1 = new JLabel("Player 1 - 0 point(s)");
	JLabel player2 = new JLabel("Player 2 - 0 point(s)");
	JTextArea textArea = new JTextArea();
	JScrollPane scroll = new JScrollPane(textArea);
	JTextField talk = new JTextField();
	int intx;
	int inty;
	int intwidth =75;
	int intheight =112;
	int intarcWidth = 22;
	int intarcHeight = 22;
	FileReader file;
	BufferedReader fileread;
	String strBoard;
	int intBoard;
	String strPlyrName;
	SuperSocketMaster ssm;
	String strFile;
	String strIP;
	int intPort;
	String strSub;
	String strDifficulty;
	boolean blnDraw = false;
	boolean blnCont = false;
	int intTime;
	boolean blnClick = false;

	//Methods
	public void paintComponent (Graphics g){
		if(blnDraw){
			if (strBoard.equals("0")){
				intBoard = 4;
				strDifficulty = "easy";
			}else if (strBoard.equals("1")){
				intBoard = 6;
				strDifficulty = "medium";
			}else if (strBoard.equals("2")){
				intBoard = 8;
				strDifficulty = "hard";
			}

			if (blnCont){
				//crdDeck = smm.loadCards(strDifficulty);
				blnCont=false;
			}

			super.paintComponent(g);
			intx=80;
			inty=100;

			//loop to draw the board
			for(int i = 0; i<intBoard;i++){
				for(int j =0; j<4;j++){
					g.drawRoundRect(intx,inty,intwidth,intheight,intarcWidth,intarcHeight);
					inty +=132;
				}
				intx += 95;
				inty = 100;
			}

			//pause game for selected number of seconds when card is selected
			if(blnClick){
				try{
					Thread.sleep(intTime*1000);
				}catch(InterruptedException e){
				}
				blnClick = false;
			}
		}
	}

	public void actionPerformed(ActionEvent evt){
		//repaint board at 60 fps
		if(evt.getSource()==theTimer){
			repaint();
		} else if(evt.getSource()==talk){
			//append chat messages sent to the text area and set text field to blank
			textArea.append(strPlyrName+": "+talk.getText()+"\n");
			ssm.sendText(strPlyrName+": "+talk.getText());
			talk.setText("");
		}else if(evt.getSource()== ssm){
			int intLength;
			int intCount =0;
			intLength = ssm.readText().length();

			//Check for a specific data format
			for(int i=0;i<intLength;i++){
				strSub = ssm.readText().substring(i,i+1);
				if(strSub.equals("|")){
					intCount++;
				}else if(intCount==2){
					i = intLength;
					strBoard = ssm.readText().substring(2,3);
					intTime = Integer.parseInt(ssm.readText().substring(5,6));
				}else{
					intCount = 0;
				}
			}

			//if the data sent is the data format, store the text. Otherwise, append it only
			if(intCount==2){
				blnDraw=true;
				blnCont=true;
			}else if(ssm.readText().equals("C0nnected")){
				ssm.sendText("||"+strBoard+"||"+intTime);
				blnDraw=true;
				blnCont=true;
			}else{
				textArea.append(ssm.readText()+"\n");
			}
		}
	}

	public void mouseClicked(MouseEvent evt) {
		if(evt.getSource()==this){
			intx = evt.getX();
			inty = evt.getY();
			//checks if a card has been clicked
			if (intx>80&&intx<155&&inty>100&&inty<212){
				System.out.println("Card 1");
			}else if(intx>175&&intx<250&&inty>100&&inty<212){
				System.out.println("Card 2");
			}else if(intx>270&&intx<345&&inty>100&&inty<212){
				System.out.println("Card 3");
			}else if(intx>365&&intx<440&&inty>100&&inty<212){
				System.out.println("Card 4");
			}else if(intx>80&&intx<155&&inty>232&&inty<344){
				System.out.println("Card 5");
			}else if(intx>175&&intx<250&&inty>232&&inty<344){
				System.out.println("Card 6");
			}else if(intx>270&&intx<345&&inty>232&&inty<344){
				System.out.println("Card 7");
			}else if(intx>365&&intx<440&&inty>232&&inty<344){
				System.out.println("Card 8");
			}else if(intx>80&&intx<155&&inty>364&&inty<476){
				System.out.println("Card 9");
			}else if(intx>175&&intx<250&&inty>364&&inty<476){
				System.out.println("Card 10");
			}else if(intx>270&&intx<345&&inty>364&&inty<476){
				System.out.println("Card 11");
			}else if(intx>365&&intx<440&&inty>364&&inty<476){
				System.out.println("Card 12");
			}else if(intx>80&&intx<155&&inty>496&&inty<608){
				System.out.println("Card 13");
			}else if(intx>175&&intx<250&&inty>496&&inty<608){
				System.out.println("Card 14");
			}else if(intx>270&&intx<345&&inty>496&&inty<608){
				System.out.println("Card 15");
			}else if(intx>365&&intx<440&&inty>496&&inty<608){
				System.out.println("Card 16");
			}
			if (intBoard>=6){
				if(intx>460&&intx<535&&inty>100&&inty<212){
					System.out.println("Card 17");
				}else if(intx>460&&intx<535&&inty>232&&inty<344){
					System.out.println("Card 18");
				}else if(intx>460&&intx<535&&inty>364&&inty<476){
					System.out.println("Card 19");
				}else if(intx>460&&intx<535&&inty>496&&inty<608){
					System.out.println("Card 20");
				}else if(intx>555&&intx<630&&inty>100&&inty<212){
					System.out.println("Card 21");
				}else if(intx>555&&intx<630&&inty>232&&inty<344){
					System.out.println("Card 22");
				}else if(intx>555&&intx<630&&inty>364&&inty<476){
					System.out.println("Card 23");
				}else if(intx>555&&intx<630&&inty>496&&inty<608){
					System.out.println("Card 24");
				}
			}
			if (intBoard==8){
				if(intx>650&&intx<725&&inty>100&&inty<212){
					System.out.println("Card 25");
				}else if(intx>650&&intx<725&&inty>232&&inty<344){
					System.out.println("Card 26");
				}else if(intx>650&&intx<725&&inty>364&&inty<476){
					System.out.println("Card 27");
				}else if(intx>650&&intx<725&&inty>496&&inty<608){
					System.out.println("Card 28");
				}else if(intx>745&&intx<820&&inty>100&&inty<212){
					System.out.println("Card 25");
				}else if(intx>745&&intx<820&&inty>232&&inty<344){
					System.out.println("Card 26");
				}else if(intx>745&&intx<820&&inty>364&&inty<476){
					System.out.println("Card 27");
				}else if(intx>745&&intx<820&&inty>496&&inty<608){
					System.out.println("Card 28");
				}
			}
		}
	}

	public void mousePressed(MouseEvent evt) {

	}

	public void mouseReleased(MouseEvent evt) {

	}

	public void mouseEntered(MouseEvent evt) {

	}

	public void mouseExited(MouseEvent evt){

	}

	//Constructor
	public Board (String strHorJ){
		setLayout(null);
		setPreferredSize(new Dimension(1280,720));
		addMouseListener(this);

		strFile = strHorJ;

		//Open file and load needed information into variables
		try{
			file = new FileReader(strFile);
			fileread = new BufferedReader(file);
			try{
				if(strFile.equals("Host_Settings.txt")){
					strBoard = fileread.readLine();
					fileread.readLine();
					strPlyrName = fileread.readLine();
					intTime = Integer.parseInt(fileread.readLine());
					intPort = Integer.parseInt(fileread.readLine());
					ssm = new SuperSocketMaster(intPort,this);
					ssm.connect();
				}else if(strFile.equals("Player_Settings.txt")){
					strPlyrName = fileread.readLine();
					intPort = Integer.parseInt(fileread.readLine());
					strIP = fileread.readLine();
					ssm = new SuperSocketMaster(strIP,intPort,this);
					ssm.connect();
					ssm.sendText("C0nnected");
				}
				file.close();
				fileread.close();
			}catch(IOException e){
			}
		} catch (FileNotFoundException e){
		}

		//set label szie, font, location
		playerturn.setFont(f1);
		playerturn.setSize(new Dimension(1280,60));
		playerturn.setLocation(0,20);
		add(playerturn);

		scoreboard.setFont(f2);
		scoreboard.setSize(new Dimension(300,60));
		scoreboard.setLocation(900,100);
		add(scoreboard);

		player1.setFont(f3);
		player1.setSize(new Dimension(300,50));
		player1.setLocation(900,170);
		add(player1);

		player2.setFont(f3);
		player2.setSize(new Dimension(300,50));
		player2.setLocation(900,230);
		add(player2);

		//chat text field scroll pane
		textArea.setEditable(false);
		scroll.setFont(f4);
		scroll.setSize(new Dimension(300,300));
		scroll.setLocation(900,300);
		add(scroll);

		//chat message bar
		talk.setFont(f4);
		talk.setSize(new Dimension(300,30));
		talk.setLocation(900,620);
		talk.addActionListener(this);
		add(talk);

		theTimer.start();

		/*
		theframe.setResizable(false);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(this);
		theframe.pack();
		theframe.setVisible(true);
		*/
	}

}
