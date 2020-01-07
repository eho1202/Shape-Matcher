import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HelpPanel extends JPanel{

    BufferedImage image;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(1280, 720, Image. SCALE_SMOOTH), 0, 0, this);
    }

    public HelpPanel() {
        try {
            image = ImageIO.read(getClass().getResource("Help.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
