import javax.swing.*;
import java.awt.*;

/**
 * Starting to design the Frame on the first appearing window of the app.
 * Introducing our own class with initial buttons and their action handlers.
 */
class IndexPageFrame extends JFrame {
    public IndexPageFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setSize(new Dimension(2000,2500));

        // Panel with background image
        JPanel panel = new Imageadd().getPanel();
        panel.setLayout(new BorderLayout());

        // button and label for front page
        JButton button = new JButton("Start Banking");
        JLabel label = new JLabel("CLIENT CORPORATE", SwingConstants.CENTER);
        JLabel label1 = new JLabel("Here for a better experience in banking!", SwingConstants.CENTER);

        label.setFont(new Font("", Font.BOLD, 50));
        label.setForeground(Color.WHITE);
        label1.setFont(new Font("",Font.PLAIN, 30));
        label1.setForeground(Color.WHITE);
        button.setFont(new Font("Mv Boli", Font.BOLD, 20));
        button.setBackground(new Color(65, 193, 236));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subpanel with vertical layout to add button and label
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        subPanel.setOpaque(false);

        // Add spacing and components
        subPanel.add(Box.createVerticalStrut(350));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        subPanel.add(label);
        subPanel.add(label1);
        subPanel.add(Box.createVerticalStrut(30));
        subPanel.add(button);
        subPanel.add(Box.createVerticalStrut(150));

        button.addActionListener(e -> {});

        panel.add(subPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

public class IndexPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IndexPageFrame("Index Page"));
    }
}
