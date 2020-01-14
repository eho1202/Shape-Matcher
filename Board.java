import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
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
	String strPlyrName ="";
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
	ShapeMatcherModel smm = new ShapeMatcherModel();
	card crdDeck[];
	int intIndex;
	int intCard1=-1;
	int intCard2;
	int intj=0;
	int intT=1;
	int intTurn=1;
	String strSend ="";
	String strNumbers[];
	
	//Methods
	public void paintComponent (Graphics g){			
		if(blnDraw){
			super.paintComponent(g);
			intx=80;
			inty=100;

			//loop to draw the board
			for(int i = 0; i<intBoard;i++){
				for(int j =0; j<4;j++){
					g.setColor(Color.WHITE);
					g.fillRoundRect(intx,inty,intwidth,intheight,intarcWidth,intarcHeight);
					inty +=132;
				}
				intx += 95;
				inty = 100;
			}
			
			//after two cards have been selected, go into if statement
			if(blnClick){
				//draw images
				g.drawImage(crdDeck[intCard1].image,intx2,inty2,null);
				if(intT>=2){
					g.drawImage(crdDeck[intCard2].image,intx3,inty3,null);
				}
				
				//pause game the second time when images have been drawn
				if(intT==1){
					intT++;
					blnClick=false;
				}else if(intT==2){
					intT++;
				}else if(intT==3){
					try{
						Thread.sleep(intTime*1000);
					}catch(InterruptedException e){
					}
					
					//if cards have the same shape, then keep blnFlipped = true. Otherwise, it equals false
					if(crdDeck[intCard1].intN==crdDeck[intCard2].intN){
						crdDeck[intCard1].blnFlipped=true;
						crdDeck[intCard2].blnFlipped=true;
					}else{
						crdDeck[intCard1].flip();
						crdDeck[intCard2].flip();
					}
					
					//update labels and variables based on the player's turn
					if(intTurn==1){
						if(crdDeck[intCard1].intN==crdDeck[intCard2].intN){
							intPlyr1Pts++;
						}
						player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//update score
						playerturn.setText(strPlyrName2+"'s Turn!");
						intTurn++;
					}else if(intTurn==2){
						if(crdDeck[intCard1].intN==crdDeck[intCard2].intN){
							intPlyr2Pts++;
						}
						player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
						playerturn.setText(strPlyrName+"'s Turn!");
						intTurn=1;
					}
					
					//reset variables
					intT=1;
					intCard1=-1;
					intCard2=-1;
					blnClick = false;
					intj=0;
				}	
			}
			//draw cards if blnFlipped=true
			if (crdDeck[0].blnFlipped){
				g.drawImage(crdDeck[0].image,102,141,null);
			}
			if(crdDeck[1].blnFlipped){
				g.drawImage(crdDeck[1].image,197,141,null);
			}
			if(crdDeck[2].blnFlipped){
				g.drawImage(crdDeck[2].image,292,141,null);
			}
			if(crdDeck[3].blnFlipped){
				g.drawImage(crdDeck[3].image,387,141,null);
			}
			if(crdDeck[4].blnFlipped){
				g.drawImage(crdDeck[4].image,102,273,null);
			}
			if(crdDeck[5].blnFlipped){
				g.drawImage(crdDeck[5].image,197,273,null);
			}
			if(crdDeck[6].blnFlipped){
				g.drawImage(crdDeck[6].image,292,273,null);
			}
			if(crdDeck[7].blnFlipped){
				g.drawImage(crdDeck[7].image,387,273,null);
			}
			if(crdDeck[8].blnFlipped){
				g.drawImage(crdDeck[8].image,102,405,null);
			}
			if(crdDeck[9].blnFlipped){
				g.drawImage(crdDeck[9].image,197,405,null);
			}
			if(crdDeck[10].blnFlipped){
				g.drawImage(crdDeck[10].image,292,405,null);
			}
			if(crdDeck[11].blnFlipped){
				g.drawImage(crdDeck[11].image,387,405,null);
			}
			if(crdDeck[12].blnFlipped){
				g.drawImage(crdDeck[12].image,102,537,null);
			}
			if(crdDeck[13].blnFlipped){
				g.drawImage(crdDeck[13].image,197,537,null);
			}
			if(crdDeck[14].blnFlipped){
				g.drawImage(crdDeck[14].image,292,537,null);
			}
			if(crdDeck[15].blnFlipped){
				g.drawImage(crdDeck[15].image,387,537,null);
			}
			//extra set of cards for a larger board
			if (intBoard>=6){
				if(crdDeck[16].blnFlipped){
					g.drawImage(crdDeck[16].image,482,141,null);
				}
				if(crdDeck[17].blnFlipped){
					g.drawImage(crdDeck[17].image,577,141,null);
				}
				if(crdDeck[18].blnFlipped){
					g.drawImage(crdDeck[18].image,482,273,null);
				}
				if(crdDeck[19].blnFlipped){
					g.drawImage(crdDeck[19].image,577,273,null);
				}
				if(crdDeck[20].blnFlipped){
					g.drawImage(crdDeck[20].image,482,405,null);
				}
				if(crdDeck[21].blnFlipped){
					g.drawImage(crdDeck[21].image,577,405,null);
				}
				if(crdDeck[22].blnFlipped){
					g.drawImage(crdDeck[22].image,482,537,null);
				}
				if(crdDeck[23].blnFlipped){
					g.drawImage(crdDeck[23].image,577,537,null);
				}
			}
			//extra set of cards for a larger board
			if (intBoard==8){
				if(crdDeck[24].blnFlipped){
					g.drawImage(crdDeck[24].image,672,141,null);
				}
				if(crdDeck[25].blnFlipped){
					g.drawImage(crdDeck[25].image,767,141,null);
				}
				if(crdDeck[26].blnFlipped){
					g.drawImage(crdDeck[26].image,672,273,null);
				}
				if(crdDeck[27].blnFlipped){
					g.drawImage(crdDeck[27].image,767,273,null);
				}
				if(crdDeck[28].blnFlipped){
					g.drawImage(crdDeck[28].image,672,405,null);
				}
				if(crdDeck[29].blnFlipped){
					g.drawImage(crdDeck[29].image,767,405,null);
				}
				if(crdDeck[30].blnFlipped){
					g.drawImage(crdDeck[30].image,672,537,null);
				}
				if(crdDeck[31].blnFlipped){
					g.drawImage(crdDeck[31].image,767,537,null);
				}
			}
		}//blnDraw if statement
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
			int intCount1 =0;
			int intCount2 =0;
			intLength = ssm.readText().length();
			
			//Check for a specific data format
			for(int i=0;i<intLength;i++){
				strSub = ssm.readText().substring(i,i+1);
				if(strSub.equals("&")){
					intCount1++;
				}
			}

			for(int i=0;i<intLength;i++){
				strSub = ssm.readText().substring(i,i+1);
				if(strSub.equals(",")){
					intCount2++;
				}
			}
			
			strSub="";
			for(int i=0;i<intLength;i++){
				strSub = ssm.readText().substring(i,i+1);
				if(strSub.equals("@")){
					strSub+=strSub;
					if(strSub.equals("@@")){
						i=intLength;
					}
				}
			}
		
			//if the data sent is the data format, store the text. Otherwise, append it only
			if(intCount1>=36){
				blnDraw=true;
				
				//split data and load first three into variables
				strNumbers = ssm.readText().split("&&");
				strBoard=strNumbers[0];
				intTime= Integer.parseInt(strNumbers[1]);
				strPlyrName2=strNumbers[2];
				
				//determine value for intBoard and strDifficulty based on intBoard
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
				
				//initialize crdDeck,load values of crdDeck.intShape into it, and set .blnFlipped to false
				crdDeck = new card[intBoard*4];
				for(int i=0;i<intBoard*4;i++){
					crdDeck[i]=new card();
					crdDeck[i].intShape=Integer.parseInt(strNumbers[3+i]);
					crdDeck[i].blnFlipped=false;
				}
				crdDeck = smm.loadImages(crdDeck); //load images of shapes 
				player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//change from default to entered name
				player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
			}else if(intCount2==2){
				ssm.sendText(strBoard+"&&"+intTime+"&&"+strPlyrName+strSend);
				
				//split and load name
				strNumbers = ssm.readText().split(",,");
				strPlyrName2 = strNumbers[1];
				blnDraw=true;
				
				player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//change from default to entered name
				player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
				playerturn.setText(strPlyrName+"'s Turn!");//update with entered name
			}else if(strSub.equals("@@")){
				strNumbers = ssm.readText().split("@@");//split data
				
				//load intCard1 or intCard2 value, flip the card, set blnClick to true, and load x and y integers. Differ based on if it's the first or second card
				if(strNumbers[0].equals("1")){
					intCard1=Integer.parseInt(strNumbers[1]);
					crdDeck[intCard1].flip();
					blnClick=true;
					intx2=Integer.parseInt(strNumbers[2]);
					inty2=Integer.parseInt(strNumbers[3]);
				}else if(strNumbers[0].equals("2")){
					intCard2=Integer.parseInt(strNumbers[1]);
					crdDeck[intCard2].flip();
					blnClick=true;
					intx3=Integer.parseInt(strNumbers[2]);
					inty3=Integer.parseInt(strNumbers[3]);
				}
			}else{
				textArea.append(ssm.readText()+"\n");
			}
		}
	}
	}

	public void mouseClicked(MouseEvent evt) {
		if(evt.getSource()==this){
			intx = evt.getX();
			inty = evt.getY();
			//checks if a card has been clicked, changes value of intx1 and iny1, index, and crdDeck[].blnFlipped
			if (intx>80&&intx<155&&inty>100&&inty<212){
				intx1=102;
				inty1=141;
				intIndex=0;
				crdDeck[0].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>100&&inty<212){
				intx1=197;
				inty1=141;
				intIndex=1;
				crdDeck[1].flip();	
				blnCont=true;			
			}else if(intx>270&&intx<345&&inty>100&&inty<212){
				intx1=292;
				inty1=141;
				intIndex=2;
				crdDeck[2].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>100&&inty<212){
				intx1=387;
				inty1=141;
				intIndex=3;
				crdDeck[3].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>232&&inty<344){
				intx1=102;
				inty1=273;
				intIndex=4;
				crdDeck[4].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>232&&inty<344){
				intx1=197;
				inty1=273;
				intIndex=5;
				crdDeck[5].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>232&&inty<344){
				intx1=292;
				inty1=273;
				intIndex=6;
				crdDeck[6].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>232&&inty<344){
				intx1=387;
				inty1=273;
				intIndex=7;
				crdDeck[7].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>364&&inty<476){
				intx1=102;
				inty1=405;
				intIndex=8;
				crdDeck[8].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>364&&inty<476){
				intx1=197;
				inty1=405;
				intIndex=9;
				crdDeck[9].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>364&&inty<476){
				intx1=292;
				inty1=405;
				intIndex=10;
				crdDeck[10].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>364&&inty<476){
				intx1=387;
				inty1=405;
				intIndex=11;
				crdDeck[11].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>496&&inty<608){
				intx1=102;
				inty1=537;
				intIndex=12;
				crdDeck[12].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>496&&inty<608){
				intx1=197;
				inty1=537;
				intIndex=13;
				crdDeck[13].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>496&&inty<608){
				intx1=292;
				inty1=537;
				intIndex=14;
				crdDeck[14].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>496&&inty<608){
				intx1=387;
				inty1=537;
				intIndex=15;
				crdDeck[15].flip();
				blnCont=true;
			}
			//loads more cards if the board size selected is larger than 4x4
			if (intBoard>=6){
				if(intx>460&&intx<535&&inty>100&&inty<212){
					intx1=482;
					inty1=141;
					intIndex=16;
					crdDeck[16].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>100&&inty<212){
					intx1=577;
					inty1=141;
					intIndex=17;
					crdDeck[17].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>232&&inty<344){
					intx1=482;
					inty1=273;
					intIndex=18;
					crdDeck[18].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>232&&inty<344){
					intx1=577;
					inty1=273;
					intIndex=19;
					crdDeck[19].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>364&&inty<476){
					intx1=482;
					inty1=405;
					intIndex=20;
					crdDeck[20].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>364&&inty<476){
					intx1=577;
					inty1=405;
					intIndex=21;
					crdDeck[21].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>496&&inty<608){
					intx1=482;
					inty1=537;
					intIndex=22;
					crdDeck[22].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>496&&inty<608){
					intx1=577;
					inty1=537;
					intIndex=23;
					crdDeck[23].flip();
					blnCont=true;
				}
			}
			if (intBoard==8){
				if(intx>650&&intx<725&&inty>100&&inty<212){
					intx1=672;
					inty1=141;
					intIndex=24;
					crdDeck[24].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>100&&inty<212){
					intx1=767;
					inty1=141;
					intIndex=25;
					crdDeck[25].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>232&&inty<344){
					intx1=672;
					inty1=273;
					intIndex=26;
					crdDeck[26].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>232&&inty<344){
					intx1=767;
					inty1=273;
					intIndex=27;
					crdDeck[27].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>364&&inty<476){
					intx1=672;
					inty1=405;
					intIndex=28;
					crdDeck[28].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>364&&inty<476){
					intx1=767;
					inty1=405;
					intIndex=29;
					crdDeck[29].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>496&&inty<608){
					intx1=672;
					inty1=537;
					intIndex=30;
					crdDeck[30].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>496&&inty<608){
					intx1=672;
					inty1=537;
					intIndex=31;
					crdDeck[31].flip();
					blnCont=true;
				}
			}
			
			if(blnCont){
				//if statement to take the required values of the selected cards
				intj++;  
				if(intj == 1&&intCard1==-1){
					intCard1=intIndex;
					blnClick=true;
					intx2 = intx1;
					inty2 = inty1;
					ssm.sendText("1@@"+intCard1+"@@"+intx2+"@@"+inty2);
				}else if(intj == 2&&intIndex!=intCard1){
					intCard2=intIndex;
					blnClick = true;
					intx3 = intx1;
					inty3 = inty1;
					ssm.sendText("2@@"+intCard2+"@@"+intx3+"@@"+inty3);
				}else if(intj == 2&&intIndex==intCard1){
					intj=1;
				}
				blnCont=false;
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
					
					//change value of board columns and strDifficulty based on board size
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
					
					//load the cards into the array once
					crdDeck = smm.loadCards(strDifficulty);
					crdDeck = smm.loadImages(crdDeck);
					
					//add intShape of the deck to variable strSend and set all .blnFlipped to false
					for(int i=0; i<intBoard*4; i++){
						strSend+= "&&"+crdDeck[i].intShape;
						crdDeck[i].blnFlipped=false;
					}
				}else if(strFile.equals("Player_Settings.txt")){
					strPlyrName = fileread.readLine();
					intPort = Integer.parseInt(fileread.readLine());
					strIP = fileread.readLine();
					ssm = new SuperSocketMaster(strIP,intPort,this);
					ssm.connect();
					ssm.sendText("Connected,,"+strPlyrName);
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
	/*
	public static void main (String[] args){
		new Board();
	}
	*/
}
