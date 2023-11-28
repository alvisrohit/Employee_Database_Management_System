package employeedatabasemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;


public class ViewEmployee extends JFrame implements ActionListener{

    JTable table;
    Choice cemployeeId;
    JButton search,print,update,back;

    ViewEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20,20,150,20);
        add(searchlbl);
        cemployeeId = new Choice();
         cemployeeId.setBounds(180, 20,150 ,60 );
         add( cemployeeId);
            try {
            Dbfunction db = new Dbfunction();
            Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM employee");
              // table.setModel(DbUtils.resultSetToTableModel(rs));
                // Now you can use the ResultSet 'rs' to populate your table or perform other actions
//                 rs.beforeFirst();

           int itemCount = 0;
            while (rs.next()) {
            cemployeeId.add(rs.getString("emid"));
            itemCount++;
        }
        System.out.println("Number of items in the dropdown: " + itemCount);
    }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        
        
        table = new JTable();

        try {
            Dbfunction db = new Dbfunction();
            Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM employee");
               table.setModel(DbUtils.resultSetToTableModel(rs));
                // Now you can use the ResultSet 'rs' to populate your table or perform other actions
//                 rs.beforeFirst();
//                while(rs.next()){
//                    cemployeeId.add(rs.getString("empId"));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table); // Use JScrollPane here
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);
         back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }
     public void actionPerformed(ActionEvent ae){
         if(ae.getSource()==search){
             String query = "SELECT * FROM employee WHERE  emid = '" + cemployeeId.getSelectedItem() + "'";
             try{
                 Dbfunction db = new Dbfunction();
                 Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");
//               Statement statement = conn.createStatement();
                 Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(query);
                 table.setModel(DbUtils.resultSetToTableModel(rs));
               
             }catch(Exception e){
                e.printStackTrace();
             }
         }else if(ae.getSource()==print){
             try{
                 table.print();
             }catch(Exception e){
                 e.printStackTrace();
             }
         }else if(ae.getSource()==update){
              setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
         }else{
             setVisible(false);
             new Home();
         }
     }
    public static void main(String args[]) {
        new ViewEmployee();
    }
}
