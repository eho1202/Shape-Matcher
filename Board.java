/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: Game view
 */

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Board extends JPanel implements ActionListener, MouseListener{
	//PROPERTIES
	//Timers
	Timer theTimer = new Timer(1000/60, this);
	Timer cardTimer;
	Timer cardTimer2;
	
	//Fonts
	Font f1 = new Font("Nunito", Font.PLAIN,30);
	Font f2 = new Font("Nunito", Font.PLAIN,34);
	Font f3 = new Font("Nunito", Font.PLAIN,24);
	Font f4 = new Font("Nunito", Font.PLAIN,12);
	
	//JComponents
	JLabel playerturn = new JLabel("Waiting for other player...",JLabel.CENTER);
	JLabel scoreboard = new JLabel("Scoreboard");
	JLabel player1 = new JLabel("Player 1 - 0 point(s)");
	JLabel player2 = new JLabel("Player 2 - 0 point(s)");
	
	//Chat
	JTextArea textArea = new JTextArea();
	JScrollPane scroll = new JScrollPane(textArea);
	JTextField talk = new JTextField();
	
	//X and Y coordinates of cards
	int intx;
	int inty;
	
	//File IO
	FileReader file;
	BufferedReader fileread; 
	FileWriter theFile;
	PrintWriter filewrite;
	String strFile; //settings file of the user
	
	//Players
	String strPlyrName;
	String strPlyrName2 ="";
	int intPlyr1Pts =0;
	int intPlyr2Pts =0;
	
	//Settings parameters
	String strBoard; //holds board selection as stored in the settings text file
	int intBoard; //holds number of columns in the board 
	String strIP;
	int intPort;
	int intTime;
	int intMode;
	
	//Networking
	SuperSocketMaster ssm;
	String strSend ="";
	String strNumbers[];
	
	//Model of program
	ShapeMatcherModel smm = new ShapeMatcherModel();
	
	//Main menu
	ShapeMatcherHome smh;
	
	//Deck of cards
	card crdDeck[];
	
	//Checking variables
	int intIndex;//index of card selected
	int intCard1=-1; //holds card index for later comparison
	int intCard2; //holds card index for later comparison
	int intCard3=-1; //holds card index for later comparison (Real Time mode)
	int intCard4; //holds card index for later comparison (Real time mode)
	int intj=0; //controls section after user initially selects a card (controls what happens with what info)
	int intj1=0; //controls section after user initially selects a card (real time mode for client user)
	int intT=1; //keeps track of how many times program goes into the blnClick section 
	int intTurn=1; //controls the turns (traditional mode)
	int intGo; //controls which turn player goes on
	boolean blnDraw = false; //ensures sections aren't accessed until after both players have all the info loaded
	boolean blnCheck = false;
	boolean blnCont = false; //controls when the check if the cards flipped are the same happens
	boolean blnClick = false;//controls loop for when cards have been selected
	int intOrigin1;//to organize host code (real time mode)
	int intOrigin2;//to organize client code (real time mode)
	int intT2=1;//keeps track of how many times program goes into the blnClick section (real time mode, client user)
	
	//METHODS
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		if(blnDraw){
			intx=80;
			inty=100;
			
			//loop to draw board
			for(int i = 0; i<intBoard;i++){
				for(int j =0; j<4;j++){
					g.setColor(Color.WHITE);
					g.fillRoundRect(intx,inty,75,112,22,22);
					g.setColor(Color.BLACK);
					g.drawRoundRect(intx,inty,75,112,22,22);
					inty +=132;
				}
				intx += 95;
				inty = 100;
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
				
			if(blnClick){
				if(intOrigin1==1||intMode==0){
					if(intT==1){
						intT++;
						blnClick=false;
						blnCont=false;
						intOrigin1=0;
					}else if(intT==2){
						intT++;
						cardTimer.start();
						cardTimer.restart();
						intOrigin1=0;
					}
				}
				if(intOrigin2==1&&intMode==1){
					if(intT2==1){
						intT2++;
						blnClick=false;
						blnCont=false;
						intOrigin2=0;
					}else if(intT2==2){
						intT2++;
						cardTimer2.start();
						cardTimer2.restart();
						intOrigin2=0;
					}
				}
					
				if(blnCheck){
					//if cards have the same shape, then keep blnFlipped = true. Otherwise, it equals false
					if(intOrigin1==1||intMode==0){
						if(crdDeck[intCard1].intN==crdDeck[intCard2].intN){
							crdDeck[intCard1].blnFlipped=true;
							crdDeck[intCard1].blnPair=true;
							crdDeck[intCard2].blnFlipped=true;
							crdDeck[intCard2].blnPair=true;
						}else{
							System.out.println("Index1: "+intCard1+" Index2: "+intCard2);
							crdDeck[intCard1].flip();
							crdDeck[intCard2].flip();
						}
					}
					if(intOrigin2==1&&intMode==1){
						if(crdDeck[intCard3].intN==crdDeck[intCard4].intN){
							crdDeck[intCard3].blnFlipped=true;
							crdDeck[intCard3].blnPair=true;
							crdDeck[intCard4].blnFlipped=true;
							crdDeck[intCard4].blnPair=true;
						}else{
							crdDeck[intCard3].flip();
							crdDeck[intCard4].flip();
						}
					}
					
					if(intMode==0){
						if(intTurn==1){
							intPlyr1Pts = smm.updatePoints(intPlyr1Pts, crdDeck[intCard1].intN, crdDeck[intCard2].intN);
							player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//update score
							playerturn.setText(strPlyrName2+"'s Turn!");
							intTurn++;
						}else if(intTurn==2){
							intPlyr2Pts = smm.updatePoints(intPlyr2Pts, crdDeck[intCard1].intN, crdDeck[intCard2].intN);
							player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
							playerturn.setText(strPlyrName+"'s Turn!");
							intTurn=1;
						}
						intCard1=-1;
						intj=0;
						intT=1;
					}else if(intMode==1){
						if(intOrigin1==1){
							intPlyr1Pts = smm.updatePoints(intPlyr1Pts, crdDeck[intCard1].intN, crdDeck[intCard2].intN);
							player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//update score
							intCard1=-1;
							intj=0;
							intT=1;
						}
						if(intOrigin2==1){
							intPlyr2Pts = smm.updatePoints(intPlyr2Pts, crdDeck[intCard3].intN, crdDeck[intCard4].intN);
							player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");//update score
							intCard3=-1;
							intj1=0;
							intT2=1;
						}
					}
					//reset variables
					intOrigin1=0;
					intOrigin2=0;
					blnClick = false;
					blnCheck = false;
				}	
			} 	
		}//blnDraw if statement
	}
	
	public void actionPerformed(ActionEvent evt){
		//repaint board at 60 fps
		if(evt.getSource()==theTimer){
			repaint();
			
			if(blnDraw){
				//check if game has finished 
				int intDone = 0;
				for(int i = 0; i< intBoard*4;i++){
					if(crdDeck[i].blnPair==true){
						intDone++;
					}else{
						intDone=0;
					}
				}
				if (intDone==intBoard*4){
					theTimer.stop(); //stop timer after game is finished
					try{
						FileWriter theFile = smm.getFileWrite(strBoard);
						PrintWriter filewrite = new PrintWriter(theFile);
						//based on the winner, confirm their win or loss and write the winner's score to the appropriapte high scores file
						if(intPlyr1Pts>intPlyr2Pts){
							if(intGo==1){
								System.out.println("You Won!");
								ssm.disconnect();
								smh.frmHome.setContentPane(smh.pnlHostEnd);
								smh.pnlHostEnd.lblOutcome.setText("YOU WON!");
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							}else if(intGo==2){
								System.out.println("You Lost...");
								ssm.disconnect();
								smh.frmHome.setContentPane(smh.pnlPlayerEnd);
								smh.pnlPlayerEnd.lblOutcome.setText("YOU LOST...");
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							}

							filewrite.println(strPlyrName);
							filewrite.println(intPlyr1Pts);
						}else if(intPlyr1Pts<intPlyr2Pts){
							if(intGo==1){
								System.out.println("You Lost...");
								smh.pnlHostEnd.lblOutcome.setText("YOU LOST...");
								smh.frmHome.setContentPane(smh.pnlHostEnd);
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							}else if(intGo==2){
								System.out.println("You Won!");
								smh.pnlPlayerEnd.lblOutcome.setText("YOU WON!");
								smh.frmHome.setContentPane(smh.pnlPlayerEnd);
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							}

							filewrite.println(strPlyrName2);
							filewrite.println(intPlyr2Pts);
						}else if(intPlyr1Pts==intPlyr2Pts){
							System.out.println("You Tied!");
							if (intGo == 1) {
								smh.pnlHostEnd.lblOutcome.setText("YOU TIED!");
								smh.frmHome.setContentPane(smh.pnlHostEnd);
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							} else if (intGo == 2) {
								smh.pnlPlayerEnd.lblOutcome.setText("YOU TIED!");
								smh.frmHome.setContentPane(smh.pnlPlayerEnd);
								smh.frmHome.pack();
								smh.frmHome.setVisible(true);
							}
							filewrite.println("Tie");
							filewrite.println(intPlyr1Pts);
						}
						//close fileWriter and PrintWriter
						filewrite.close();
						theFile.close();
					}catch(IOException e){
					}
					
					//reset variable values
					strPlyrName2="";
					blnCont=false;
					blnClick=false;
					intCard1=-1;
					intCard3=-1;
					intj=0;
					intj1=0;
					intT=1;
					intPlyr1Pts=0;
					intPlyr2Pts=0;
					intTurn=1;
					strSend="";
					blnCheck=false;
					intT2=1;
					
					setVisible(false);
				}
			}
		}else if(evt.getSource()==cardTimer){
			cardTimer.stop();
			blnCheck=true;
			blnClick=true;
			intOrigin1=1;
		}else if(evt.getSource()==cardTimer2){
			cardTimer2.stop();
			blnCheck=true;
			blnClick=true;
			intOrigin2=1;
		}else if(evt.getSource()==talk){
			//append chat messages sent to the text area and set text field to blank
			if(intGo==1){
				textArea.append(smm.getTime()+" - "+strPlyrName+": "+talk.getText()+"\n");
				ssm.sendText(strPlyrName+": "+talk.getText());
				talk.setText("");
			}else if(intGo==2){
				textArea.append(smm.getTime()+" - "+strPlyrName2+": "+talk.getText()+"\n");
				ssm.sendText(strPlyrName2+": "+talk.getText());
				talk.setText("");
			}
		}else if(evt.getSource()== ssm){
			int intLength;
			int intCount1 =0;
			int intCount2 =0;
			int intCount3 =0;
			
			//Setting scoreboard and chat to visible after client joins
			scoreboard.setVisible(true);
			player1.setVisible(true);
			player2.setVisible(true);
			scroll.setVisible(true);
			talk.setVisible(true);
			
			intLength = ssm.readText().length();
			
			//Check for a specific data format
			intCount1 = smm.checkFormat(intLength, ssm.readText(), "&");
			
			intCount2 = smm.checkFormat(intLength, ssm.readText(), ",");
			
			intCount3 = smm.checkFormat(intLength, ssm.readText(), "@");		
		
			//if the data sent is the data format, store the text. Otherwise, append it only
			if(intCount1>=38){
				//split data and load first three into variables
				strNumbers = ssm.readText().split("&&");
				strBoard = strNumbers[0];
				intTime = Integer.parseInt(strNumbers[1]);
				intMode = Integer.parseInt(strNumbers[2]);
				strPlyrName = strNumbers[3];
				
				//create timers (for real-time mode)
				cardTimer = new Timer(intTime,this);
				cardTimer2 = new Timer(intTime,this);
				
				if(intMode==1){
					intTurn=intGo;
					playerturn.setText("REAL-TIME MODE!");
				}else if(intMode==0){
					playerturn.setText(strPlyrName+"'s Turn!");//update with entered name
				}
				
				//determine value for intBoard and strDifficulty based on intBoard
				intBoard = smm.boardColumns(strBoard);
				
				//initialize crdDeck,load values of crdDeck.intShape into it, and set .blnFlipped to false
				crdDeck = new card[intBoard*4];
				for(int i=0;i<intBoard*4;i++){
					crdDeck[i]=new card();
					crdDeck[i].intShape=Integer.parseInt(strNumbers[4+i]);
				}
				crdDeck = smm.loadImages(crdDeck); //load images of shapes 
				
				blnDraw=true;
				
				player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//change from default to entered name
				player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
			}else if(intCount2==2){
				ssm.sendText(strBoard+"&&"+intTime+"&&"+intMode+"&&"+strPlyrName+strSend);
				
				//split and load name
				strNumbers = ssm.readText().split(",,");
				strPlyrName2 = strNumbers[1];
				blnDraw=true;
				
				player1.setText(strPlyrName+" - "+intPlyr1Pts+" point(s)");//change from default to entered name
				player2.setText(strPlyrName2+" - "+intPlyr2Pts+" point(s)");
				
				if(intMode==0){
					playerturn.setText(strPlyrName+"'s Turn!");//update with entered name
				}else if(intMode==1){
					playerturn.setText("REAL-TIME MODE!");
				}
			}else if(intCount3==2){
				strNumbers = ssm.readText().split("@@");//split data
				//load intCard1 or intCard2 value, flip the card, set blnClick to true, and load x and y integers. Differ based on if it's the first or second card
				if(intGo==1&&intMode==1){//if user is server or mode is traditional
					if(strNumbers[0].equals("1")){
						intCard3=Integer.parseInt(strNumbers[1]);
						crdDeck[intCard3].flip();
						blnClick=true;
					}else if(strNumbers[0].equals("2")){
						intCard4=Integer.parseInt(strNumbers[1]);
						crdDeck[intCard4].flip();
						blnClick=true;
					}
					intOrigin2 = 1;//server sent message
				}else if(intMode==0||intGo==2){//if user is client
					if(strNumbers[0].equals("1")){
						intCard1=Integer.parseInt(strNumbers[1]);
						crdDeck[intCard1].flip();
						blnClick=true;
					}else if(strNumbers[0].equals("2")){
						intCard2=Integer.parseInt(strNumbers[1]);
						crdDeck[intCard2].flip();
						blnClick=true;
					}
					intOrigin1=1;//client sent message
				}
			}else{//if not a data format, append it
				textArea.append(smm.getTime()+" - "+ssm.readText()+"\n");
			}
		}
	}

	//MouseListener methods
	public void mouseClicked(MouseEvent evt) {
		if(evt.getSource()==this&&blnDraw&&intGo==intTurn){ //If it is the player's turn
			intx = evt.getX();
			inty = evt.getY();
			//checks if a card has been clicked, changes value of intx1 and iny1, index, and crdDeck[].blnFlipped
			if (intx>80&&intx<155&&inty>100&&inty<212){
				intIndex=0;
				crdDeck[0].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>100&&inty<212){
				intIndex=1;
				crdDeck[1].flip();	
				blnCont=true;			
			}else if(intx>270&&intx<345&&inty>100&&inty<212){
				intIndex=2;
				crdDeck[2].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>100&&inty<212){
				intIndex=3;
				crdDeck[3].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>232&&inty<344){
				intIndex=4;
				crdDeck[4].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>232&&inty<344){
				intIndex=5;
				crdDeck[5].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>232&&inty<344){
				intIndex=6;
				crdDeck[6].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>232&&inty<344){
				intIndex=7;
				crdDeck[7].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>364&&inty<476){
				intIndex=8;
				crdDeck[8].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>364&&inty<476){
				intIndex=9;
				crdDeck[9].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>364&&inty<476){
				intIndex=10;
				crdDeck[10].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>364&&inty<476){
				intIndex=11;
				crdDeck[11].flip();
				blnCont=true;
			}else if(intx>80&&intx<155&&inty>496&&inty<608){
				intIndex=12;
				crdDeck[12].flip();
				blnCont=true;
			}else if(intx>175&&intx<250&&inty>496&&inty<608){
				intIndex=13;
				crdDeck[13].flip();
				blnCont=true;
			}else if(intx>270&&intx<345&&inty>496&&inty<608){
				intIndex=14;
				crdDeck[14].flip();
				blnCont=true;
			}else if(intx>365&&intx<440&&inty>496&&inty<608){
				intIndex=15;
				crdDeck[15].flip();
				blnCont=true;
			}
			//loads more cards if the board size selected is larger than 4x4
			if (intBoard>=6){
				if(intx>460&&intx<535&&inty>100&&inty<212){
					intIndex=16;
					crdDeck[16].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>100&&inty<212){
					intIndex=17;
					crdDeck[17].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>232&&inty<344){
					intIndex=18;
					crdDeck[18].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>232&&inty<344){
					intIndex=19;
					crdDeck[19].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>364&&inty<476){
					intIndex=20;
					crdDeck[20].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>364&&inty<476){
					intIndex=21;
					crdDeck[21].flip();
					blnCont=true;
				}else if(intx>460&&intx<535&&inty>496&&inty<608){
					intIndex=22;
					crdDeck[22].flip();
					blnCont=true;
				}else if(intx>555&&intx<630&&inty>496&&inty<608){
					intIndex=23;
					crdDeck[23].flip();
					blnCont=true;
				}
			}
			if (intBoard==8){
				if(intx>650&&intx<725&&inty>100&&inty<212){
					intIndex=24;
					crdDeck[24].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>100&&inty<212){
					intIndex=25;
					crdDeck[25].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>232&&inty<344){
					intIndex=26;
					crdDeck[26].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>232&&inty<344){
					intIndex=27;
					crdDeck[27].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>364&&inty<476){
					intIndex=28;
					crdDeck[28].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>364&&inty<476){
					intIndex=29;
					crdDeck[29].flip();
					blnCont=true;
				}else if(intx>650&&intx<725&&inty>496&&inty<608){
					intIndex=30;
					crdDeck[30].flip();
					blnCont=true;
				}else if(intx>745&&intx<820&&inty>496&&inty<608){
					intIndex=31;
					crdDeck[31].flip();
					blnCont=true;
				}
			}
			
			if(blnCont){
				//if statement to take the required values of the selected cards
				intj++;  
				intj1++;		
				if(cardTimer.isRunning()&&intGo==1){
					intj=0;
					crdDeck[intIndex].flip();
				}else if(cardTimer2.isRunning()&&intGo==2){
					intj1=0;
					crdDeck[intIndex].flip();
				}else if(intGo==1||intMode==0){	
					if (crdDeck[intIndex].blnRepeat){//if select a card in a pair
						intj=0;
						crdDeck[intIndex].blnRepeat = false;
					}else if(intT2>=2&&intIndex==intCard3||intT2>=2&&intIndex==intCard4){ //prevent host from selecting a card opponent has selected on real time mode
						crdDeck[intIndex].flip();
						intj--;
					}else if(intj == 1&&intCard1==-1){//selection of first card
						intCard1 = intIndex;
						intOrigin1 = 1;
						ssm.sendText("1@@"+intCard1);
						blnClick = true;
					}else if(intj == 2&&intIndex!=intCard1){//selection of the second card
						intCard2 = intIndex;
						intOrigin1 = 1;
						ssm.sendText("2@@"+intCard2);
						blnClick = true;
					}else if(intj == 2&&intIndex==intCard1){ //if select first card again
						intj=1;
						crdDeck[intCard1].flip();
					}
				}else if(intGo==2&&intMode==1){
					if (crdDeck[intIndex].blnRepeat){//if select a card in a pair
						intj1=0;
						crdDeck[intIndex].blnRepeat = false;
					}else if(intT>=2&&intIndex==intCard1||intT>=2&&intIndex==intCard2){//prevent client from selecting a card opponent has selected on real time mode
						crdDeck[intIndex].flip();
						intj1--;
					}else if(intj1 == 1&&intCard3==-1){//selection of first card
						intCard3 = intIndex;
						intOrigin2 = 1;
						ssm.sendText("1@@"+intCard3);
						blnClick = true;
					}else if(intj1 == 2&&intIndex!=intCard3){//selection of the second card
						intCard4 = intIndex;
						intOrigin2 = 1;
						ssm.sendText("2@@"+intCard4);
						blnClick = true;
					}else if(intj1 == 2&&intIndex==intCard3){ //if select first card again
						intj1=1;
						crdDeck[intCard3].flip();
					}
				}
				//split and add if statement for intCard3 and intCard4
				blnCont=false;
			}
			
		}//if statement
	}
	
	public void mousePressed(MouseEvent evt) {

	}
	
	public void mouseReleased(MouseEvent evt) {

	}
	
	public void mouseEntered(MouseEvent evt) {
		
	}
	
	public void mouseExited(MouseEvent evt){
		
	}
	
	//CONSTRUCTOR
	public Board (String strHorJ, ShapeMatcherHome smh){
		this.smh = smh;
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
					//Load settings parameters from text file
					strBoard = fileread.readLine();
					intMode = Integer.parseInt(fileread.readLine());
					strPlyrName = fileread.readLine();
					intGo = 1;
					intTime = Integer.parseInt(fileread.readLine())*1000;
					intPort = Integer.parseInt(fileread.readLine());
					
					//Network connection(host)
					ssm = new SuperSocketMaster(intPort,this);
					ssm.connect();
					
					//Timers (for real-time mode)
					cardTimer = new Timer(intTime,this);
					cardTimer2 = new Timer(intTime,this);
					
					if(intMode==1){
						intTurn=intGo;
					}
					
					//change value of board columns and strDifficulty based on board size
					intBoard = smm.boardColumns(strBoard);
					
					//load the cards into the array once
					crdDeck = smm.loadCards(strBoard);
					crdDeck = smm.loadImages(crdDeck);
					
					//add intShape of the deck to variable strSend and set all .blnFlipped to false
					for(int i=0; i<intBoard*4; i++){
						strSend+= "&&"+crdDeck[i].intShape;
					}
					
				}else if(strFile.equals("Player_Settings.txt")){
					//Load settings parameters from text file
					strPlyrName2 = fileread.readLine();
					intGo=2;
					intPort = Integer.parseInt(fileread.readLine());
					strIP = fileread.readLine();
					
					//Network connection (client)
					ssm = new SuperSocketMaster(strIP,intPort,this);
					ssm.connect();
					ssm.sendText("Connected,,"+strPlyrName2); //Signal sent to the host that a client has connected
				}
				file.close();
				fileread.close();
			}catch(IOException e){
			}
		} catch (FileNotFoundException e){
		}
		
		//set label size, font, location	
		playerturn.setFont(f1);
		playerturn.setSize(new Dimension(1280,60));
		playerturn.setLocation(0,20);
		add(playerturn);
		
		scoreboard.setVisible(false);
		scoreboard.setFont(f2);
		scoreboard.setSize(new Dimension(300,60));
		scoreboard.setLocation(900,100);
		add(scoreboard);
		
		player1.setVisible(false);
		player1.setFont(f3);
		player1.setSize(new Dimension(300,50));
		player1.setLocation(900,170);
		add(player1);
		
		player2.setVisible(false);
		player2.setFont(f3);
		player2.setSize(new Dimension(300,50));
		player2.setLocation(900,230);
		add(player2);
		
		textArea.setEditable(false);
		
		//chat text field scroll pane
		scroll.setVisible(false);
		scroll.setFont(f4);
		scroll.setSize(new Dimension(300,300));
		scroll.setLocation(900,300);
		add(scroll);
		
		//chat message bar
		talk.setVisible(false);
		talk.setFont(f4);
		talk.setSize(new Dimension(300,30));
		talk.setLocation(900,620);
		talk.addActionListener(this);
		add(talk);
		
		theTimer.start(); //Start repainting the panel
	}
}
