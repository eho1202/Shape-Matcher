import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class HelpPanel extends JPanel implements MouseListener{

    BufferedImage image;
    ShapeMatcherHome smh;
    JButton btnHome = new JButton();
    JButton btnInfo = new JButton();
    JButton btnHow = new JButton();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getScaledInstance(1280, 720, Image. SCALE_SMOOTH), 0, 0, this);
    }

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
            btnHome.setCursor(Cursor.getDefaultCursor());
            smh.frmHome.setContentPane(smh.pnlInfo);
            smh.frmHome.setTitle("Information about Settings");
            smh.frmHome.pack();
            smh.frmHome.setVisible(true);
            btnInfo.setCursor(Cursor.getDefaultCursor());
        } else if (evt.getSource() == btnHow) {
            // go to how to play
            btnHow.setCursor(Cursor.getDefaultCursor());
        }

    }

    public void mousePressed(MouseEvent evt) {

    }

    public void mouseReleased(MouseEvent evt) {

    }

    public void mouseEntered(MouseEvent evt) {
        if (evt.getSource() == btnHome) {
            btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // changes the pointer to hand cursor
        } else if (evt.getSource() == btnInfo) {
            btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else if (evt.getSource() == btnHow) {
            btnHow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }

    public void mouseExited(MouseEvent evt) {
        btnHome.setCursor(Cursor.getDefaultCursor());
        btnInfo.setCursor(Cursor.getDefaultCursor());
        btnHow.setCursor(Cursor.getDefaultCursor());

    }

    public HelpPanel(ShapeMatcherHome smh) {
        try {
            image = ImageIO.read(getClass().getResource("Help.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.smh = smh;

        // JPanel
        setPreferredSize(new Dimension(1280,720));
        setLayout(null);

        // Button that goes back to main menu
        btnHome.setSize(new Dimension(320,65));
        btnHome.setLocation(925,640);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setBorder(BorderFactory.createEmptyBorder());
        btnHome.addMouseListener(this);
        add(btnHome);

        // Button that goes to information about settings page
        btnInfo.setSize(new Dimension(500,50));
        btnInfo.setLocation(390,375);
        btnInfo.setOpaque(false);
        btnInfo.setContentAreaFilled(false);
        btnInfo.setBorder(BorderFactory.createEmptyBorder());
        btnInfo.addMouseListener(this);
        add(btnInfo);

        // Button that goes to how to play page
        btnHow.setSize(new Dimension(500,50));
        btnHow.setLocation(390,440);
        btnHow.setOpaque(false);
        btnHow.setContentAreaFilled(false);
        btnHow.setBorder(BorderFactory.createEmptyBorder());
        btnHow.addMouseListener(this);
        add(btnHow);
    }

}
