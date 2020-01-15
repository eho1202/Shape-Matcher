import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.ImageIcon.*;

public class card {
	//Properties
	int intShape;
	BufferedImage image;
	boolean blnFlipped;
	int intN;
	boolean blnPair;
	boolean blnRepeat = false;
	
	//Methods
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
