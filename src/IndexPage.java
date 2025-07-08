import javax.swing.*;
import java.awt.*;

/**Starting to design the Frame on the first appearing window of the app
 * So Introducing our own class with inital buttons and their action Handlers
 **/
class IndexPageFrame extends JFrame
{
    public IndexPageFrame(String title)
    {
        super(title);
        setLayout(new BorderLayout());
        // Set the content pane to a JLabel with ImageIcon as background
        JLabel background = new JLabel(new ImageIcon("Front.jpg")); // ← put your image here
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        setContentPane(background); // Set as the window's background
        //Grid Bag
        JPanel panel=new BackgroundPanel(".", "Front_Page.jpg");
        //
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());
        setSize(500,700);
        JButton openAccount=CreateButton("Create Account",panel);
        JButton withDrawalForm=CreateButton("Withdrawal Form",panel);
        JButton DepositForm=CreateButton("Deposit Form",panel);
        JButton interestCalculator=CreateButton("Interest Calculator",panel);
        add(panel,BorderLayout.WEST);
        panel.add(Box.createVerticalStrut(400));
        setVisible(true);
        panel.setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    JButton CreateButton(String Name,JPanel panel)
    {
        JButton button=new JButton(Name);
        button.setFont( new Font("MV boli",Font.BOLD,16));
       // button.setPreferredSize(new Dimension(10,20));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50)); // Size respected


        panel.add(button);
        //
        return button;
    }
}

class IndexPage
{
    public static void main(String[] args) {
        new IndexPageFrame("Index Page");

    }
}