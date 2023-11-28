package employeedatabasemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfusername;
    JPasswordField tfpassword; // Use JPasswordField for password input

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField(); // Use JPasswordField for password input
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof JButton) {
            String username = tfusername.getText();
            char[] passwordChars = tfpassword.getPassword(); // Get the password as a char array
            String password = new String(passwordChars); // Convert the char array to a string

            // Replace this with your actual logic to check the username and password
            if (isValidLogin(username, password)) {
                // Successful login
                JOptionPane.showMessageDialog(this, "Login successful!");
                setVisible(false);
                new Home();
                // You can navigate to another window or perform other actions here
            } else {
                // Invalid login
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
                // You can clear the text fields or handle the error as needed
            }

            // Clear the password field for security
            tfpassword.setText("");
        }
    }

    // Add your logic to check the username and password against your data source here
    private boolean isValidLogin(String username, String password) {
        // Replace this with your actual logic
        // For example, you could check against a database or a predefined list of users
        // Return true if the login is valid, otherwise return false
        return (username.equals("rohit") && password.equals("Alvisrohit"));
    }

    public static void main(String args[]) {
        new Login();
    }
}
