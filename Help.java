import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Help implements MouseListener{


    ShapeMatcherHome smh;

    /**
     * Panel for help page
     */
    HelpPanel helpPanel = new HelpPanel();

    /**
     * Button to go back to main menu
     */
    JButton btnHome = new JButton();

    /**
     * Button to go to information about settings
     */
    JButton btnInfo = new JButton();

    /**
     * Button to go to how to play instructions
     */
    JButton btnHow = new JButton();

    /**
     * Created for MouseListener
     */
    public void mouseClicked(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            // go back to main menu
            btnHome.setCursor(Cursor.getDefaultCursor());
            smh.frmHome.setContentPane(smh.pnlHome);
            smh.frmHome.setTitle("Shape Matcher");
            smh.frmHome.pack();
            smh.frmHome.setVisible(true);
        } else if (evt.getSource() == btnInfo) {
            // go to information about settings
            btnInfo.setCursor(Cursor.getDefaultCursor());
        } else if (evt.getSource() == btnHow) {
            // go to how to play
            btnHow.setCursor(Cursor.getDefaultCursor());
        }

    }

    /**
     * Created for MouseListener
     */
    public void mousePressed(MouseEvent evt) {

    }

    /**
     * Created for MouseListener
     */
    public void mouseReleased(MouseEvent evt) {

    }

    /**
     * Created for MouseListener
     */
    public void mouseEntered(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // changes the pointer to hand cursor
        } else if (evt.getSource() == btnInfo) {
            btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else if (evt.getSource() == btnHow) {
            btnHow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }

    /**
     * Created for MouseListener
     */
    public void mouseExited(MouseEvent evt) {
        btnHome.setCursor(Cursor.getDefaultCursor());
        btnInfo.setCursor(Cursor.getDefaultCursor());
        btnHow.setCursor(Cursor.getDefaultCursor());

    }

    public Help(ShapeMatcherHome smh) {

        this.smh = smh;

        // JPanel
        helpPanel.setPreferredSize(new Dimension(1280,720));
        helpPanel.setLayout(null);

        // Button that goes back to main menu
        btnHome.setSize(new Dimension(320,65));
        btnHome.setLocation(925,640);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setBorder(BorderFactory.createEmptyBorder());
        btnHome.addMouseListener(this);
        helpPanel.add(btnHome);

        // Button that goes to information about settings page
        btnInfo.setSize(new Dimension(500,50));
        btnInfo.setLocation(390,375);
        btnInfo.setOpaque(false);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorder(BorderFactory.createEmptyBorder());
        btnInfo.addMouseListener(this);
        helpPanel.add(btnInfo);

        // Button that goes to how to play page
        btnHow.setSize(new Dimension(500,50));
        btnHow.setLocation(390,440);
        btnHow.setOpaque(false);
        btnHow.setContentAreaFilled(false);
        btnHow.setBorder(BorderFactory.createEmptyBorder());
        btnHow.addMouseListener(this);
        helpPanel.add(btnHow);

        /*frame.setTitle("Help");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(helpPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // causes frmHome to centre on screen
        frame.setResizable(false);*/

    }

}
