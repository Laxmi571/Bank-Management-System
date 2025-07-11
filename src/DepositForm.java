import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

class DepositForm extends JPanel
{
    int bal;
    int acc;
    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;

   public DepositForm() throws SQLException
    {

        JFrame frame=new JFrame();
        JTextArea text=new JTextArea();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");

        //pstmt=conn.prepareStatement();
        String sql="Select *  from `holder's info`";
        stmt=conn.createStatement( TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet result=stmt.executeQuery(sql);
        Vector<Integer> accountNumberlist=new Vector<>();
        int i=0;
        JLabel Balance=new JLabel("balance");
        JTextArea balanceOutput=new JTextArea(1,0);
        while(result.next())
        {
            accountNumberlist.add(Integer.parseInt(result.getString("Account Number")));
        }
        System.out.println(accountNumberlist);
        JList<Integer> list= new JList<Integer>(accountNumberlist);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(30);


        // Optional: set layout orientation if you want wrapping
        //list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        // JScrollPane setup
        JScrollPane pane = new JScrollPane(list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        pane.setPreferredSize(new Dimension(100, 80));


       add(list);
       list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener((e -> {
            text.setText(String.valueOf(list.getSelectedValue()));
            String resultsql="Select Balance from `holder's info` where `Account Number`= "+list.getSelectedValue();
            acc=list.getSelectedValue();

            ResultSet set;
            try {
                 set =stmt.executeQuery(resultsql);
                set.first();
                String bala=set.getString("Balance");
                balanceOutput.setText(bala);
                //balanceOutput.setText(set.getString("Balance"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }));
        JLabel depositAmount=new JLabel("Deposit Amount");
        JTextField amount=new JTextField(2);
        amount.setMaximumSize(new Dimension(100,50));

        amount.addActionListener((e -> {
            System.out.println(acc);
            String balance="Select Balance from `holder's info` where `account number`="+acc;
            try {
                ResultSet balanceInfo=stmt.executeQuery(balance);
                balanceInfo.first();
                bal= Integer.parseInt(balanceInfo.getString("Balance"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String insert="UPDATE `holder's info` SET `Balance`= "+(bal+Integer.parseInt(amount.getText()))+" WHERE `Account Number`= "+acc;
            System.out.println(insert);
            try {
                stmt.executeUpdate(insert);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }));


         add(text);
         add(Balance);
        add(balanceOutput);
         add(list);
         add(depositAmount);
        add(amount);

       setVisible(true);


    }

}