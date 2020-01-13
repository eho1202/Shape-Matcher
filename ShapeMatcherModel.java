import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.ImageIcon.*;

public class ShapeMatcherModel {
	
	public card[] loadCards(String strDifficulty) {
		int intIncrement = 16;
		int intCards = 16;
		int intRandom;
		
		if (strDifficulty.equalsIgnoreCase("easy") || strDifficulty.equalsIgnoreCase("e")) {
			intCards = 16;
			intIncrement = 16;

		} else if (strDifficulty.equalsIgnoreCase("medium") || strDifficulty.equalsIgnoreCase("m")) {
			intCards = 24;
			intIncrement = 24;

		} else if (strDifficulty.equalsIgnoreCase("hard") || strDifficulty.equalsIgnoreCase("h")) {
			intCards = 32;
			intIncrement = 32;
		}
		
		card cards[] = new card[intIncrement];
		
		for (int i = 0; i < intIncrement; i++) {
			cards[i] = new card();
		}
		int intDeck[] = new int[intIncrement];
		
		for (int i = 1; i <= intIncrement; i++) {
			intDeck[i-1] = i;
		}
		
		for (int i = 0; i < intIncrement; i++) {
			intRandom = (int)(Math.random()*intCards);
			intCards--;
			cards[i].intShape = intDeck[intRandom];
			intDeck = removeElement(intDeck, intRandom);
			
			if (cards[i].intShape == 1 || cards[i].intShape == 2) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Circle.png"));
				}catch(IOException e){
					System.out.println("Error loading circle");
				}
			} else if (cards[i].intShape == 3 || cards[i].intShape == 4) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Eq Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading eq triangle");
				}
			} else if (cards[i].intShape == 5 || cards[i].intShape == 6) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Square.png"));
				}catch(IOException e){
					System.out.println("Error loading square");
				}
			} else if (cards[i].intShape == 7 || cards[i].intShape == 8) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Pentagon.png"));
				}catch(IOException e){
					System.out.println("Error loading pentagon");
				}
			} else if (cards[i].intShape == 9 || cards[i].intShape == 10) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Hexagon.png"));
				}catch(IOException e){
					System.out.println("Error loading hexagon");
				}
			} else if (cards[i].intShape == 11 || cards[i].intShape == 12) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Rhombus.png"));
				}catch(IOException e){
					System.out.println("Error loading rhombus");
				}
			} else if (cards[i].intShape == 13 || cards[i].intShape == 14) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Parallelogram.png"));
				}catch(IOException e){
					System.out.println("Error loading parallelogram");
				}
			} else if (cards[i].intShape == 15 || cards[i].intShape == 16) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Trapezoid.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
				}
			} else if (cards[i].intShape == 17 || cards[i].intShape == 18) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Star.png"));
				}catch(IOException e){
					System.out.println("Error loading star");
				}
			} else if (cards[i].intShape == 19 || cards[i].intShape == 20) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Heart.png"));
				}catch(IOException e){
					System.out.println("Error loading heart");
				}
			} else if (cards[i].intShape == 21 || cards[i].intShape == 22) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Rectangle.png"));
				}catch(IOException e){
					System.out.println("Error loading rectangle");
				}
			} else if (cards[i].intShape == 23 || cards[i].intShape == 24) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("RA Triangle.png"));
				}catch(IOException e){
					System.out.println("Error loading right angle triangle");
				}
			} else if (cards[i].intShape == 25 || cards[i].intShape == 26) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Scalene.png"));
				}catch(IOException e){
					System.out.println("Error loading scalene triangle");
				}
			} else if (cards[i].intShape == 27 || cards[i].intShape == 28) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Semicircle.png"));
				}catch(IOException e){
					System.out.println("Error loading semicircle");
				}
			} else if (cards[i].intShape == 29 || cards[i].intShape == 30) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Oval.png"));
				}catch(IOException e){
					System.out.println("Error loading oval");
				}
			} else if (cards[i].intShape == 31 || cards[i].intShape == 32) {
				try{
					cards[i].image = ImageIO.read(getClass().getResource("Crescent.png"));
				}catch(IOException e){
					System.out.println("Error loading trapezoid");
				}
			}
		}
		return cards;
		
	}
	
	public static int[] removeElement(int[] arr, int index) { 
  
        // If the array is empty 
        // or the index is not in array range 
        // return the original array 
        if (arr == null || index < 0 || index >= arr.length) { 
            return arr; 
        } 
  
        // Create another array of size one less 
        int[] anotherArray = new int[arr.length - 1]; 
  
        // Copy the elements except the index 
        // from original array to the other array 
        for (int i = 0, k = 0; i < arr.length; i++) { 
  
            // if the index is 
            // the removal element index 
            if (i == index) { 
                continue; 
            } 
  
            // if the index is not 
            // the removal element index 
            anotherArray[k++] = arr[i]; 
        } 
  
        // return the resultant array 
        return anotherArray; 
    }
}
