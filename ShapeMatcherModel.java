/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Game model
 */
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;
import java.util.Calendar;

public class ShapeMatcherModel {
	
	//METHODS
	/**
	 * Deals a deck of cards (numbers only) based on the difficulty.
	 * - Easy = 16 cards
	 * - Medium = 24 cards
	 * - Hard = 32 cards
	 * @param intIncrement The number of times that each for loop in this method runs (initial # of cards).
	 * @param intCards The number of cards remaining in the deck.  Decreases as cards are dealt.
	 * @param intRandom A randomly generated card number from 1-intCards.
	 * @return A deck of cards, each with a unique number.
	 */
	public card[] loadCards(String strDifficulty) { 
		int intIncrement = 16; //Number of times the for loop runs (initial # of cards)
		int intCards = 16; //Number of cards remaining in the deck (this number will decrease as cards are "dealt")
		int intRandom; //Randomly generated card number
		
		if (strDifficulty.equalsIgnoreCase("0")) { //4x4 (16 cards)
			intCards = 16;
			intIncrement = 16;

		} else if (strDifficulty.equalsIgnoreCase("1")) { //6x4 (24 cards)
			intCards = 24;
			intIncrement = 24;

		} else if (strDifficulty.equalsIgnoreCase("2")) { //8x4 (32 cards)
			intCards = 32;
			intIncrement = 32;
		}
		
		//Create new card array based on difficulty (# of cards)
		card cards[] = new card[intIncrement];
		
		//Construct each card object
		for (int i = 0; i < intIncrement; i++) {
			cards[i] = new card();
		}
		
		//Create new "unshuffled" deck based on difficulty (# of cards)
		int intDeck[] = new int[intIncrement];
		
		//Each card in the deck will be assigned a unique number, from 1 to the # of cards in the deck (based on difficulty)
		for (int i = 1; i <= intIncrement; i++) {
			intDeck[i-1] = i;
		}
		
		//Randomly "dealing" cards
		for (int i = 0; i < intIncrement; i++) {
			intRandom = (int)(Math.random()*intCards);
			intCards--; //Number of cards decreases by 1 each time one is "dealt"
			cards[i].intShape = intDeck[intRandom];
			intDeck = removeElement(intDeck, intRandom); //Removes a card from the intDeck array
		}
		return cards;
	}
	
	/**
	 * Adds the corresponding shape image based on each card's shape number
	 * @param intIncrement The number of cards in the deck.
	 * @return A deck of cards, each with a unique number and the corresponding shape.
	 */
	public card[] loadImages(card[] cards) { 
		int intIncrement = cards.length; //In other words, # of cards
		
		for (int i = 0; i < intIncrement; i++) {
			if (cards[i].intShape == 1 || cards[i].intShape == 2) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Circle.png"));
				}catch(IOException e){
					System.out.println("Error loading circle");
					e.printStackTrace();
				}
				cards[i].intN = 1;
			} else if (cards[i].intShape == 3 || cards[i].intShape == 4) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Eq Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading eq triangle");
					e.printStackTrace();
				}
				cards[i].intN = 2;
			} else if (cards[i].intShape == 5 || cards[i].intShape == 6) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Square.png"));
				}catch(IOException e){
					System.out.println("Error loading square");
					e.printStackTrace();
				}
				cards[i].intN = 3;
			} else if (cards[i].intShape == 7 || cards[i].intShape == 8) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Pentagon.png"));
				}catch(IOException e){
					System.out.println("Error loading pentagon");
					e.printStackTrace();
				}
				cards[i].intN = 4;
			} else if (cards[i].intShape == 9 || cards[i].intShape == 10) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Hexagon.png"));
				}catch(IOException e){
					System.out.println("Error loading hexagon");
					e.printStackTrace();
				}
				cards[i].intN = 5;
			} else if (cards[i].intShape == 11 || cards[i].intShape == 12) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Rhombus.png"));
				}catch(IOException e){
					System.out.println("Error loading rhombus");
					e.printStackTrace();
				}
				cards[i].intN = 6;
			} else if (cards[i].intShape == 13 || cards[i].intShape == 14) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Parallelogram.png"));
				}catch(IOException e){
					System.out.println("Error loading parallelogram");
					e.printStackTrace();
				}
				cards[i].intN = 7;
			} else if (cards[i].intShape == 15 || cards[i].intShape == 16) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Trapezoid.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
					e.printStackTrace();
				}
				cards[i].intN = 8;
			} else if (cards[i].intShape == 17 || cards[i].intShape == 18) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Star.png"));
				}catch(IOException e){
					System.out.println("Error loading star");
					e.printStackTrace();
				}
				cards[i].intN = 9;
			} else if (cards[i].intShape == 19 || cards[i].intShape == 20) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Heart.png"));
				}catch(IOException e){
					System.out.println("Error loading heart");
					e.printStackTrace();
				}
				cards[i].intN = 10;
			} else if (cards[i].intShape == 21 || cards[i].intShape == 22) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Rectangle.png"));
				}catch(IOException e){
					System.out.println("Error loading rectangle");
					e.printStackTrace();
				}
				cards[i].intN = 11;
			} else if (cards[i].intShape == 23 || cards[i].intShape == 24) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/RA Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading right angle triangle");
					e.printStackTrace();
				}
				cards[i].intN = 12;
			} else if (cards[i].intShape == 25 || cards[i].intShape == 26) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Scalene.png"));
				}catch(IOException e){
					System.out.println("Error loading scalene triangle");
					e.printStackTrace();
				}
				cards[i].intN = 13;
			} else if (cards[i].intShape == 27 || cards[i].intShape == 28) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Semicircle.png"));
				}catch(IOException e){
					System.out.println("Error loading semicircle");
					e.printStackTrace();
				}
				cards[i].intN = 14;
			} else if (cards[i].intShape == 29 || cards[i].intShape == 30) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Oval.png"));
				}catch(IOException e){
					System.out.println("Error loading oval");
					e.printStackTrace();
				}
				cards[i].intN = 15;
			} else if (cards[i].intShape == 31 || cards[i].intShape == 32) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("shapes/Crescent.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
					e.printStackTrace();
				}
				cards[i].intN = 16;
			}
		}
		return cards;
		
	}
	
	/**
	 * Removes an element from an array.
	 * @param intArray2 The resulting array with one less element.
	 * @return The resulting array with one less element.
	 */
	public static int[] removeElement(int[] intArray, int intIndex) { 
  
        // If the array is empty or the index is out of bounds, return the original array
        if (intArray == null || intIndex < 0 || intIndex >= intArray.length) { 
            return intArray; 
        } 
  
        // Create another array of size one less 
        int[] intArray2 = new int[intArray.length - 1]; 
  
        // Copy the elements except the index from the original array to the other array
        for (int i = 0, k = 0; i < intArray.length; i++) { 
  
            // if the index is the one to be removed
            if (i == intIndex) { 
                continue; 
            } 
  
            // if the index is not the one to be removed
            intArray2[k++] = intArray[i]; 
        } 
  
        // return the resulting array 
        return intArray2; 
    }
    
    /**
     * Checks the data format of incoming network messages.
     * @param strSub The substring that is used to detect the presence of strSign (either & , or @).
     * @param intCount The number of times that strSign appears.
     * @return The number of times that strSign appears.
     */
    public static int checkFormat (int intLength, String strText, String strSign){
		String strSub;
		int intCount=0;
		for(int i=0;i<intLength;i++){
			strSub = strText.substring(i,i+1);
			if(strSub.equals(strSign)){
				intCount++;
			}
		}
		return intCount;
	}
	
	/**
	 * Calculates the number of columns in the board of cards.
	 * @param intColumns The number of columns in the board of cards.
	 * @return The number of columns in the board of cards.
	 */
	public static int boardColumns (String strBoard){
		int intColumns = 1;
		if (strBoard.equals("0")){
			intColumns = 4;
		}else if (strBoard.equals("1")){
			intColumns = 6;
		}else if (strBoard.equals("2")){
			intColumns = 8;
		}
		
		return intColumns;
	}
	
	/**
	 * Updates a player's number of points.
	 * @return The player's final number of points.
	 */
	public static int updatePoints (int intPoints, int intN, int intN1 ){
		if(intN==intN1){
			intPoints++;
		}
		return intPoints;
	}
	
	/**
	 * Obtains the current time in mm:ss format (m - minutes, s - seconds).
	 * @param calendar Initialized with current date and time.
	 * @param intHour The hour of day.
	 * @param intMinute The minute of the hour.
	 * @param strMinute A zero must be added in front of one-digit minutes to correctly display the current time.
	 * @param strTime The current time in mm:ss format.
	 * @return The current time in mm:ss format.
	 */
	public static String getTime(){
		Calendar calendar = Calendar.getInstance();
		int intHour;
		int intMinute;
		String strMinute;
		String strTime;
		intHour = calendar.get(Calendar.HOUR);
		intMinute = calendar.get(Calendar.MINUTE);
		
		//if intMinute is a single digit, add a zero in front
		if(intMinute<10){
			strMinute = "0"+intMinute;
		}else{
			strMinute = Integer.toString(intMinute);
		}

		strTime = intHour + ":"+strMinute;

		return strTime;
	}

	/**
	 * Determines which file high scores are written to based on game difficulty.
	 * @param theFile The file to which high scores are written.
	 * @return The file to which high scores are written.
	 */
	public static FileWriter getFileWrite(String strBoard){
		FileWriter theFile = null;
		try{
			if(strBoard.equals("0")){
				theFile = new FileWriter("EasyScores.txt", true);
			}else if(strBoard.equals("1")){	
				theFile = new FileWriter("MediumScores.txt", true);
			}else if(strBoard.equals("2")){
				theFile = new FileWriter("HardScores.txt", true);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return theFile;
	}
	
	/**
	 * Checks whether or not the game has finished (i.e. all card pairs have been found).
	 * @param intDone The number of consecutive cards whose pairs have been found.
	 * @return Whether or not the game has finished.
	 */
	public static boolean gameFinished(int intBoard, card cards[]) {
		int intDone = 0;
		for(int i = 0; i< intBoard*4;i++){ //Loop through all of the cards in the board
			if(cards[i].blnPair==true){
				intDone++;
			}else{
				intDone=0;
			}
		}
		if (intDone==intBoard*4){ //If all pairs have been found
			return true;
		}
		return false;
	}
	
	/**
	 * Sends a chat message through the network.
	 */
	public static void sendMessage(String strPlayerName, JTextArea textArea, String strMessage, SuperSocketMaster ssm) {
		textArea.append(getTime()+" - "+strPlayerName+": "+strMessage+"\n");
		ssm.sendText(strPlayerName+": "+strMessage);
	}
}
