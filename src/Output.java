import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
class Output extends JPanel
{

    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;
    int acc;
    int size=0;
Output() throws SQLException
{
        super();


    setLayout(new FlowLayout());
    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");

    //pstmt=conn.prepareStatement();
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
    String [][] data=new String[size][3];
    list.addListSelectionListener((e -> {
        text.setText(String.valueOf(list.getSelectedValue()));
        String resultsql="Select * from `transactions` where `Account Number`= "+list.getSelectedValue();
        acc=list.getSelectedValue();
        ResultSet set;
        try {
            set =stmt.executeQuery(resultsql);

            while(set.next())
            {
                ++size;
            }
            set.first();
            int j;
            for(int k=0;k<size;k++)
            {
                j=0;
                while(j<3)
                {
                    data[k][j++]=set.getString("Account Number");
                    data[k][j++]=set.getString("Old Balance");
                    data[k][j++]=set.getString("New Balance");

                }
            }
            //balanceOutput.setText(set.getString("Balance"));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }));
    String []columns={"Account Number","Old Balance","new Balance"};
    JTable table=new JTable(data,columns);
   // stmt=conn.createStatement( TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//    ResultSet result=stmt.executeQuery(sql);
//    Vector<Integer> accountNumberlist=new Vector<>();
//
//    JTextArea balanceOutput=new JTextArea(1,0);
//    int i,j;
//    while(result.next())
//    {
//        size=++size;
//    }
//    result.first();
//    String [][]data=new String[size][8];
//
//    while(result.next())
//    {
//        for(i=0;i<size;i++)
//        {
//            {
//                j=0;
//                while(j<8)
//                {
//                    data[i][j++]=result.getString("Name");
//                    data[i][j++]=result.getString("Address");
//                    data[i][j++]=result.getString("Phone Number");
//                    data[i][j++]=result.getString("D.O.B");
//                    data[i][j++]=result.getString("Date of Opening");
//                    data[i][j++]=result.getString("Account Number");
//                    data[i][j++]=result.getString("Opening Amount");
//                    data[i][j++]=result.getString("Balance");
//                }
//
//
//            }
//        }
//    }
//    String []columns={"Name","Address","Phone Number","D.O.B","Date of Opening","Account Number","Opening Amount","Balance"};

//
//    System.out.println(accountNumberlist);
//
     add(table);
     add(text);
     setVisible(true);


}


}