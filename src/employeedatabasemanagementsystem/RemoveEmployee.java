
package employeedatabasemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener{
     JButton  delete,back;
    Choice cemid ;
    RemoveEmployee(){
     getContentPane().setBackground(Color.WHITE);
     setLayout(null);
     
     JLabel labelemid = new JLabel("Employee id");
     labelemid.setBounds(50,50,100,30);
     add(labelemid);
     
     cemid = new Choice();
     cemid.setBounds(200,50,150,30);
     add(cemid);
     try {
                Dbfunction db = new Dbfunction();
                Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

                if (conn != null) {
                    String query = "Select * from employee";
                    Statement statement = conn.createStatement();
                   ResultSet rs = statement.executeQuery(query);
                    while(rs.next()){
                        cemid.add(rs.getString("emid"));
                    }

                   

                    statement.close();
                    conn.close();
                }
            }catch(Exception e){
         e.printStackTrace();
     }
     
     JLabel labelname = new JLabel("Name");
     labelname.setBounds(50,100,100,30);
     add(labelname);
     
     JLabel lblname = new JLabel();
     lblname.setBounds(200,100,100,30);
     add(lblname);
     
     JLabel labelphone = new JLabel("Phone");
     labelphone.setBounds(50,150,100,30);
     add(labelphone);
     
     JLabel lblphone = new JLabel();
     lblphone.setBounds(200,150,100,30);
     add(lblphone);
     
     JLabel labelemail = new JLabel("Email");
     labelemail.setBounds(50,200,100,30);
     add(labelemail);
     
     JLabel lblemail = new JLabel();
     lblemail.setBounds(200,200,100,30);
     add(lblemail);
     
     
     cemid.addItemListener(new ItemListener(){
       public  void itemStateChanged(ItemEvent ie){
       try {
                Dbfunction db = new Dbfunction();
                Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

                if (conn != null) {
                    String selectedEmid = cemid.getSelectedItem(); // Get the selected item from the JComboBox
                    String query = "Select * from employee where emid = '" + selectedEmid + "'";

                   Statement statement = conn.createStatement();
                   ResultSet rs = statement.executeQuery(query);
                    while(rs.next()){
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                    
                    }

                   

                    statement.close();
                    conn.close();
                }
            }catch(Exception e){
         e.printStackTrace();
     } 
       }
    });
     delete = new JButton("Delete");
     delete.setBounds(80,300,100,30);
     delete.setBackground(Color.BLACK);
     delete.setForeground(Color.WHITE);
     delete.addActionListener(this);
     add(delete);
      back = new JButton("Back");
     back.setBounds(220,300,100,30);
     back.setBackground(Color.BLACK);
     back.setForeground(Color.WHITE);
     back.addActionListener(this);
     add(back);
     
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,600,400);
        add(image);
     
     setSize(1000,400);
     setLocation(300,150);
     setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
      if(ae.getSource()==delete){
        try {
                Dbfunction db = new Dbfunction();
                Connection conn = db.connect_to_db("EmployeeDatabase", "postgres", "Alvisrohit@7250");

                if (conn != null) {
                    String query = "delete from employee where emid='"+cemid.getSelectedItem()+"'";
                    Statement statement = conn.createStatement();
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                   setVisible(false);
                   new Home();
                   

                    statement.close();
                    conn.close();
                }
            }catch(Exception e){
         e.printStackTrace();
     }
      }  else{
          setVisible(false);
          new Home();
      }
    }
   public static void main(String args[]){
       new RemoveEmployee();
   } 
}
