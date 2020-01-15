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
	boolean blnFlipped = false;
	int intN;
	boolean blnPair = false;
	boolean blnRepeat = false;

	//METHODS
	 public void flip() {
		if (this.blnFlipped == true && this.blnPair == true) {
			this.blnFlipped = true;
			blnRepeat = true;
		}else if (this.blnFlipped == true) {
			this.blnFlipped = false;
		} else if (this.blnFlipped == false) {
			this.blnFlipped = true;
		}
	}
}
