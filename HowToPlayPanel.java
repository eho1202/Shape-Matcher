/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: help --> how to play
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HowToPlayPanel extends JPanel implements MouseListener{

	//PROPERTIES
    BufferedImage image; //How to play panel image
    ShapeMatcherHome smh; //Main menu
    
    //JComponents
    JButton home = new JButton(); 
    JButton help = new JButton();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(1280, 720, Image. SCALE_SMOOTH), 0, 0, this); //Scales image to 1280 by 720
        repaint();
    }

	//MouseListener methods
    public void mouseClicked(MouseEvent evt) {
        if (evt.getSource() == home) {
            // go back to main menu
            home.setCursor(Cursor.getDefaultCursor());
            smh.frmHome.setContentPane(smh.pnlHome);
            smh.frmHome.setTitle("Shape Matcher");
            smh.frmHome.pack();
            smh.frmHome.setVisible(true);
        } else if (evt.getSource() == help) {
            // go back to help
            help.setCursor(Cursor.getDefaultCursor());
            smh.frmHome.setContentPane(smh.pnlHelp);
            smh.frmHome.setTitle("Help");
            smh.frmHome.pack();
            smh.frmHome.setVisible(true);
        }

    }

    public void mousePressed(MouseEvent evt) {
    }

    public void mouseReleased(MouseEvent evt) {
        if (evt.getSource() == home) {
            home.setCursor(Cursor.getDefaultCursor());
        } else if (evt.getSource() == help) {
            help.setCursor(Cursor.getDefaultCursor());
        }

    }

    public void mouseEntered(MouseEvent evt) {
        if (evt.getSource() == home) {
            home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // changes the pointer to hand cursor (more user-friendly)
            home.setOpaque(true);
        } else if (evt.getSource() == help) {
            help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            help.setOpaque(true);
        }

    }

    public void mouseExited(MouseEvent evt) {
        if (evt.getSource() == home) {
            home.setCursor(Cursor.getDefaultCursor());
            home.setOpaque(false);
        } else if (evt.getSource() == help) {
            help.setCursor(Cursor.getDefaultCursor());
            home.setOpaque(false);
        }

    }
	
	//CONSTRUCTOR
    public HowToPlayPanel(ShapeMatcherHome smh) {
        try {
            image = ImageIO.read(getClass().getResource("img/HowToPlay.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.smh = smh; //Links to main menu

        // JPanel
        setPreferredSize(new Dimension(1280,720));
        setLayout(null);

        // Button that goes back to main menu
        home.setSize(new Dimension(320,65));
        home.setLocation(925,640);
        home.setOpaque(false);
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        home.setBorder(BorderFactory.createEmptyBorder());
        home.setBackground(new Color(128,128,128,30));
        home.addMouseListener(this);
        add(home);

        // Button that goes back to help
        help.setSize(new Dimension(240,50));
        help.setLocation(1020,595);
        help.setOpaque(false);
        help.setContentAreaFilled(false);
        help.setBorder(BorderFactory.createEmptyBorder());
        help.setBackground(new Color(128,128,128,30));
        help.addMouseListener(this);
        add(help);

    }

}
