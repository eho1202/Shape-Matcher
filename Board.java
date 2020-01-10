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
	int intptx;
	int intpty;
	int intx =20;
	int inty =20;
	int intwidth =75;
	int intheight =112;
	int intarcWidth = 22;
	int intarcHeight = 22; 
	
	//Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		intx=100;
		inty=100;
		for(int i = 0; i<4;i++){
			for(int j =0; j<4;j++){
				g.drawRoundRect(intx,inty,intwidth,intheight,intarcWidth,intarcHeight);
				inty +=132;
			}
			intx += 95;
			inty = 100;
		}
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==theTimer){
			repaint();
		}
	}

	public void mouseClicked(MouseEvent evt) {
		if(evt.getSource()==this){
			intx = evt.getX();
			inty = evt.getY();
			//checks if a card has been clicked
			if (intx>100&&intx<175&&inty>100&&inty<212){
				System.out.println("Card 1");
			}else if(intx>195&&intx<270&&inty>100&&inty<212){
				System.out.println("Card 2");
			}else if(intx>290&&intx<365&&inty>100&&inty<212){
				System.out.println("Card 3");
			}else if(intx>385&&intx<460&&inty>100&&inty<212){
				System.out.println("Card 4");
			}else if(intx>100&&intx<175&&inty>232&&inty<344){
				System.out.println("Card 5");
			}else if(intx>195&&intx<270&&inty>232&&inty<344){
				System.out.println("Card 6");
			}else if(intx>290&&intx<365&&inty>232&&inty<344){
				System.out.println("Card 7");
			}else if(intx>385&&intx<460&&inty>232&&inty<344){
				System.out.println("Card 8");
			}else if(intx>100&&intx<175&&inty>364&&inty<476){
				System.out.println("Card 9");
			}else if(intx>195&&intx<270&&inty>364&&inty<476){
				System.out.println("Card 10");    
			}else if(intx>290&&intx<365&&inty>364&&inty<476){
				System.out.println("Card 11");
			}else if(intx>385&&intx<460&&inty>364&&inty<476){
				System.out.println("Card 12");
			}else if(intx>100&&intx<175&&inty>496&&inty<608){
				System.out.println("Card 13");
			}else if(intx>195&&intx<270&&inty>496&&inty<608){
				System.out.println("Card 14");
			}else if(intx>290&&intx<365&&inty>496&&inty<608){
				System.out.println("Card 15");
			}else if(intx>385&&intx<460&&inty>496&&inty<608){
				System.out.println("Card 16");
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
	public Board (){
		setLayout(null);
		setPreferredSize(new Dimension(1280,720));
		addMouseListener(this);
		
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
		
		//chat text field
		scroll.setFont(f4);
		scroll.setSize(new Dimension(300,300));
		scroll.setLocation(900,300);
		add(scroll);
		
		//chat message bar
		talk.setFont(f4);
		talk.setSize(new Dimension(300,30));
		talk.setLocation(900,620);
		add(talk);
		
		theTimer.start();
		
		theframe.setResizable(false);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(this);
		theframe.pack();
		theframe.setVisible(true);
	}
	
	public static void main (String[] args){
		new Board();
	}
}
