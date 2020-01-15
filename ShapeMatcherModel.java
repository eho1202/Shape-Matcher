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

public class ShapeMatcherModel {
	
	//METHODS
	//"Deals" cards (numbers only)
	public card[] loadCards(String strDifficulty) { 
		int intIncrement = 16; //Number of times the for loop runs (initial # of cards)
		int intCards = 16; //Number of cards remaining in the deck (this number will decrease as cards are "dealt")
		int intRandom; //Randomly generated card number
		
		if (strDifficulty.equalsIgnoreCase("easy") || strDifficulty.equalsIgnoreCase("e")) { //4x4 (16 cards)
			intCards = 16;
			intIncrement = 16;

		} else if (strDifficulty.equalsIgnoreCase("medium") || strDifficulty.equalsIgnoreCase("m")) { //6x4 (24 cards)
			intCards = 24;
			intIncrement = 24;

		} else if (strDifficulty.equalsIgnoreCase("hard") || strDifficulty.equalsIgnoreCase("h")) { //8x4 (32 cards)
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
	
	//Adds the corresponding shape image based on each card's number and the shape number for each shape type
	public card[] loadImages(card[] cards) { 
		int intIncrement = cards.length; //In other words, # of cards
		
		for (int i = 0; i < intIncrement; i++) {
			if (cards[i].intShape == 1 || cards[i].intShape == 2) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Circle.png"));
				}catch(IOException e){
					System.out.println("Error loading circle");
					e.printStackTrace();
				}
				cards[i].intN = 1;
			} else if (cards[i].intShape == 3 || cards[i].intShape == 4) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Eq Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading eq triangle");
					e.printStackTrace();
				}
				cards[i].intN = 2;
			} else if (cards[i].intShape == 5 || cards[i].intShape == 6) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Square.png"));
				}catch(IOException e){
					System.out.println("Error loading square");
					e.printStackTrace();
				}
				cards[i].intN = 3;
			} else if (cards[i].intShape == 7 || cards[i].intShape == 8) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Pentagon.png"));
				}catch(IOException e){
					System.out.println("Error loading pentagon");
					e.printStackTrace();
				}
				cards[i].intN = 4;
			} else if (cards[i].intShape == 9 || cards[i].intShape == 10) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Hexagon.png"));
				}catch(IOException e){
					System.out.println("Error loading hexagon");
					e.printStackTrace();
				}
				cards[i].intN = 5;
			} else if (cards[i].intShape == 11 || cards[i].intShape == 12) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Rhombus.png"));
				}catch(IOException e){
					System.out.println("Error loading rhombus");
					e.printStackTrace();
				}
				cards[i].intN = 6;
			} else if (cards[i].intShape == 13 || cards[i].intShape == 14) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Parallelogram.png"));
				}catch(IOException e){
					System.out.println("Error loading parallelogram");
					e.printStackTrace();
				}
				cards[i].intN = 7;
			} else if (cards[i].intShape == 15 || cards[i].intShape == 16) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Trapezoid.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
					e.printStackTrace();
				}
				cards[i].intN = 8;
			} else if (cards[i].intShape == 17 || cards[i].intShape == 18) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Star.png"));
				}catch(IOException e){
					System.out.println("Error loading star");
					e.printStackTrace();
				}
				cards[i].intN = 9;
			} else if (cards[i].intShape == 19 || cards[i].intShape == 20) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Heart.png"));
				}catch(IOException e){
					System.out.println("Error loading heart");
					e.printStackTrace();
				}
				cards[i].intN = 10;
			} else if (cards[i].intShape == 21 || cards[i].intShape == 22) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Rectangle.png"));
				}catch(IOException e){
					System.out.println("Error loading rectangle");
					e.printStackTrace();
				}
				cards[i].intN = 11;
			} else if (cards[i].intShape == 23 || cards[i].intShape == 24) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("RA Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading right angle triangle");
					e.printStackTrace();
				}
				cards[i].intN = 12;
			} else if (cards[i].intShape == 25 || cards[i].intShape == 26) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Scalene.png"));
				}catch(IOException e){
					System.out.println("Error loading scalene triangle");
					e.printStackTrace();
				}
				cards[i].intN = 13;
			} else if (cards[i].intShape == 27 || cards[i].intShape == 28) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Semicircle.png"));
				}catch(IOException e){
					System.out.println("Error loading semicircle");
					e.printStackTrace();
				}
				cards[i].intN = 14;
			} else if (cards[i].intShape == 29 || cards[i].intShape == 30) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Oval.png"));
				}catch(IOException e){
					System.out.println("Error loading oval");
					e.printStackTrace();
				}
				cards[i].intN = 15;
			} else if (cards[i].intShape == 31 || cards[i].intShape == 32) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Crescent.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
					e.printStackTrace();
				}
				cards[i].intN = 16;
			}
		}
		return cards;
		
	}
	
	//Remove an element from an array
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
}
