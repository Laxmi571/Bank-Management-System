import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

class SecondPagePanel extends JPanel
{
    public SecondPagePanel(String title) throws SQLException
    {
        super();
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//ye overrise krna padega
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JPanel panel=new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton nothing=new JButton("Nothing");


        JButton openAccount=CreateButton("Open Account");
        JButton withDrawalForm=CreateButton("Withdrawal Form");
        JButton DepositForm=CreateButton("Deposit Form");
        JButton interestCalculator=CreateButton("Data");

        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(openAccount);
        buttonPanel.add(Box.createVerticalStrut(30));

        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(withDrawalForm);
        buttonPanel.add(Box.createVerticalStrut(30));

        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(DepositForm);
        buttonPanel.add(Box.createVerticalStrut(30));

        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(interestCalculator);


        buttonPanel.add(nothing);
        buttonPanel.add(Box.createVerticalStrut(30));

        CardLayout cardLayout=new CardLayout();
        JPanel cardPanel=new JPanel();
        cardPanel.setLayout(cardLayout);

        JPanel card0=new JPanel();
        JPanel card1=new JPanel();
        JPanel card2 = new JPanel();
        JPanel card3 = new JPanel();
        JPanel card4 = new JPanel();
        try
        {
            card1.add(new JLabel("Create Account"));
            card1.add(new CreateAccount());

            card2.add(new JLabel("Deposit Form"));
            card2.add(new DepositForm());

            card3.add(new JLabel("Withdraw"));
            card3.add(new WithdrawForm());

            card4.add(new JLabel("output"));
            card4.add(new Output());
    }
            catch (SQLException e) {
    throw new RuntimeException(e);
}


        cardPanel.add(card1,"card1");
        cardPanel.add(card2,"card2");
        cardPanel.add(card3,"card3");
        cardPanel.add(card4,"card4");
        cardPanel.add(card0,"card5");

        openAccount.addActionListener(e -> {cardLayout.show(cardPanel,"card1");});
        withDrawalForm.addActionListener(e -> {cardLayout.show(cardPanel,"card2");});

        DepositForm.addActionListener(e -> {cardLayout.show(cardPanel,"card3");});
        interestCalculator.addActionListener(e -> {cardLayout.show(cardPanel,"card4");});
        nothing.addActionListener(e -> {cardLayout.show(cardPanel,"card0");});

        //frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout());
        panel.add(buttonPanel);panel.add(cardPanel);
        frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);


    }
    JButton CreateButton(String Name)
    {
        JButton button=new JButton(Name);
        button.setFont( new Font("MV boli",Font.BOLD,16));
       // button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // button.setPreferredSize(new Dimension(10,20));
//        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50)); // Size respected

        // Add spacing and components
        add(Box.createVerticalStrut(350));
        return button;
    }
}
class SecondPage
{
    public static void main(String[] args) throws SQLException {
        new SecondPagePanel("Second page frame");
    }
}