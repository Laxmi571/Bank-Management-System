import javax.swing.*;
import java.awt.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath,String name) {
        backgroundImage = new ImageIcon(imagePath + "/" + name).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image to fill entire panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
