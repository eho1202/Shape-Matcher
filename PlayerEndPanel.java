/* SHAPE MATCHER
 * By: Venice Co, Ernestine Ho, Susan Chen
 * Panel: player end screen
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class PlayerEndPanel extends JPanel implements ActionListener, MouseListener{

	//PROPERTIES
    BufferedImage image; //End panel image
    ShapeMatcherHome smh; //Main menu
    
    //Buttons on help panel
    JButton btnHome = new JButton(); //Back to main menu
    JButton btnQuit = new JButton();
    JLabel lblOutcome = new JLabel("");
    JOptionPane optionQuit;
    
    //Timer (all panels repaint at 60 fps)
    Timer timer = new Timer(1000/60, this);

	Font f1 = new Font("Nunito", Font.PLAIN, 80);
	//METHODS
	//Draws help panel image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        repaint();
    }

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == timer) {
			repaint();
		}
	}
	
	//MouseListener methods
    public void mouseClicked(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            // go back to main menu
            btnHome.setCursor(Cursor.getDefaultCursor());
            smh.frmHome.setTitle("Shape Matcher");
            smh.backToMain();
        } else if (evt.getSource() == btnQuit) {
            int response = optionQuit.showConfirmDialog(null, "Are you sure you want to quit game?", "Quit Game", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				System.exit(0); //0, by convention, indicates a normal termination
			} else {
				return;// closes dialog box and returns to homepage
			}
        }

    }

	//Unused
    public void mousePressed(MouseEvent evt) {
    }

    public void mouseReleased(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            btnHome.setOpaque(false);
        } else if (evt.getSource() == btnQuit) {
            btnQuit.setOpaque(false);
        }
    }

    public void mouseEntered(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // changes the pointer to hand cursor (more user-friendly)
            btnHome.setOpaque(true);
            smh.playHover();
        } else if (evt.getSource() == btnQuit) {
            btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnQuit.setOpaque(true);
            smh.playHover();
        }

    }

    public void mouseExited(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            btnHome.setOpaque(false);
        } else if (evt.getSource() == btnQuit) {
            btnQuit.setOpaque(false);
        }

    }

	//CONSTRUCTOR
    public PlayerEndPanel(ShapeMatcherHome smh) {
		//Find help panel image
        try {
            image = ImageIO.read(getClass().getResource("img/Player End Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.smh = smh; //Links to main menu

        // JPanel
        setPreferredSize(new Dimension(1280,720));
        setLayout(null);

        // Button that goes back to main menu
        btnHome.setSize(new Dimension(360,50));
        btnHome.setLocation(470,370);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setBorder(BorderFactory.createEmptyBorder());
        btnHome.setBackground(new Color(128,128,128,30));
        btnHome.addMouseListener(this);
        add(btnHome);

        // Button to quit the game
        btnQuit.setSize(new Dimension(115,50));
        btnQuit.setLocation(590,430);
        btnQuit.setOpaque(false);
        btnQuit.setContentAreaFilled(false);
        btnQuit.setBorder(BorderFactory.createEmptyBorder());
        btnQuit.setBackground(new Color(128,128,128,30));
        btnQuit.addMouseListener(this);
        add(btnQuit);
        
        //Label that shows outcome
        lblOutcome.setSize(500, 300);
        lblOutcome.setLocation(435, 100);
        lblOutcome.setFont(f1);
        add(lblOutcome);
    }

}
