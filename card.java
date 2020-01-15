/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Object: card
 */
 
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.ImageIcon.*;

public class card {
	//PROPERTIES
	int intShape; //Shape number that can range from 1-32
	BufferedImage image; //Image associated with each shape
	boolean blnFlipped = false; //Indicates if card is face up or down
	int intN; //Holds the number associated with each shape
	boolean blnPair = false; //Indicates if a pair has been found
	boolean blnRepeat = false; //Indicates if a card faced up has been selected again

	//METHODS
	 public void flip() {
		if (this.blnFlipped == true && this.blnPair == true) { //keeps card flipped over if user selects a card that's faced up
			this.blnFlipped = true;
			blnRepeat = true;
		}else if (this.blnFlipped == true) { //flip face up card to be face down
			this.blnFlipped = false;
		} else if (this.blnFlipped == false) { //flip face down card to be face up
			this.blnFlipped = true;
		}
	}
}
