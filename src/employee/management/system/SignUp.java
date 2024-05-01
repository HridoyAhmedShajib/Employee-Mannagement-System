package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SignUp extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField pfpassword; // Use JPasswordField for password input

    SignUp() {

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

        pfpassword = new JPasswordField(); // Use JPasswordField for password input
        pfpassword.setBounds(150, 70, 150, 30);
        add(pfpassword);

        JButton login = new JButton("Already have a Account");
        login.setBounds(140, 100, 150, 25);
        login.setBorder(null);
        login.setBackground(null);
        login.setForeground(Color.RED);
        login.setFocusable(false);
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new Login().setVisible(true);
            }
        });
        add(login);

        JButton SignUp = new JButton("Sign up");
        SignUp.setBounds(150, 140, 80, 30);
        SignUp.setBackground(Color.BLACK);
        SignUp.setForeground(Color.WHITE);
        SignUp.addActionListener(this);
        SignUp.setFocusable(false);
        add(SignUp);

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
        try {
            String username = tfusername.getText();
            char[] passwordChars = pfpassword.getPassword(); // Get the password as char array
            String password = new String(passwordChars); // Convert char array to String

            Conn c = new Conn();
            String query = "select * from login where username = '" + username + "' and password = '" + password + "'";

            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "User Already exists.");
                setVisible(false);
            } else {

                String query1 = "insert into login values('" + username + "','" + password + "')";

                int rowsAffected = c.s.executeUpdate(query1);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Sign Up Successful.");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(SignUp.this, "User sign up failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}