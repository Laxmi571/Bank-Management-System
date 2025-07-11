import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import com.toedter.calendar.JDateChooser;

import static java.time.LocalDate.*;

class CreateAccount extends JPanel
{
    Connection conn;
    PreparedStatement pstmt;
    int accountNumber;
    public CreateAccount() throws SQLException {

        super();
        JFrame frame=new JFrame();
        frame.setSize(1000,1500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
        String sql="insert into `Holder's info` values(?,?,?,?,?,?,?,?)";
        pstmt=conn.prepareStatement(sql);

        JTextArea account=new JTextArea("Your account number is:");

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel name=new JLabel("Name");
        JTextArea nameInput=new JTextArea(1,0);

        JLabel address=new JLabel("Address");
        JTextArea addressInput=new JTextArea(1,0);

        JLabel date=new JLabel("Date of Birth");
        JDateChooser dateInput=new JDateChooser();

        JLabel phone=new JLabel("Phone Number");
        JTextArea phoneInput=new JTextArea(1,0);

        JLabel openingAmount=new JLabel("Opening Amount");
        JTextArea amountInput=new JTextArea(1,0);

        JButton save=new JButton("Save");
        save.addActionListener(e -> {
            try {
                int year=dateInput.getDate().getYear();
                int month=dateInput.getDate().getMonth();
                int day=dateInput.getDate().getDay();
                Date finaldate=new Date(year,month,day);
                setValue(nameInput.getText(), addressInput.getText(),finaldate, phoneInput.getText(), Integer.parseInt(amountInput.getText()));
                account.append(String.valueOf(accountNumber));
            } catch (SQLException ex) {
                System.err.println("-------------------------------------error "+ex);
                //throw new RuntimeException(ex);
            }
        });

        add(name);
        add(nameInput);
        add(Box.createVerticalStrut(20));

        add(address);
        add(addressInput);
        add(Box.createVerticalStrut(20));

        add(phone);
        add(phoneInput);
        add(Box.createVerticalStrut(20));

        add(date);
        add(dateInput);
        add(Box.createVerticalStrut(20));

       add(openingAmount);
        add(amountInput);
        add(Box.createVerticalStrut(20));

        add(Box.createHorizontalStrut(20));
        add(save);
        add(Box.createVerticalStrut(20));
        add(Box.createHorizontalStrut(20));
        add(account);

        //frame.add(this);
       // frame.setVisible(true);
        setVisible(true);
    }
    void setValue(String text, String addressInputText, java.util.Date date, String phoneInputText,int openingAmountText) throws SQLException {
        pstmt.setString(1,text);
        pstmt.setString(2,addressInputText);
        pstmt.setDate(3, (Date) date);
        pstmt.setDate(4,java.sql.Date.valueOf(now()));
        pstmt.setString(5,phoneInputText);
        accountNumber=(Integer.parseInt(date.toString().substring(5,7)+(int)(Math.random()*100)));
        pstmt.setInt(6,accountNumber);
        pstmt.setLong(7,openingAmountText);
        pstmt.setLong(8,openingAmountText);
        System.out.println(pstmt.executeUpdate());
        pstmt.close();conn.close();
    }


}