import javax.swing.*;
import java.awt.*;

/**
 * This class creates a JPanel that paints a background image.
 * It is intended to be reused as the background panel of main screens.
 */
class Imageadd {
    // JPanel has the functionality to add image on its own
    JPanel panel;

    Imageadd() {
        // Image is a class that holds image data
        // ImageIcon is a utility class to load images from files, URLs, or resources
        Image img = new ImageIcon("Front_Page.jpg").getImage();

        panel = new JPanel() {
            /**
             * paintComponent is the function that is called by the Java runtime
             * each time a panel is requested to repaint.
             * It is in the JComponent class, and we can override it to work as we wish.
             * Graphics g will be passed by the runtime automatically when repaint is called.
             */
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setPreferredSize(new Dimension(500, 700));
        panel.setOpaque(false);
    }

    public JPanel getPanel() {
        return panel;
    }
}