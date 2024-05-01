package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener{
    
    JTextField tfusername;
    JPasswordField pfpassword;
    
    Login() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Admin Login");
        heading.setBounds(200, 10, 500, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        add(heading);
        
        
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 70, 100, 30);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150, 70, 150, 30);
        add(tfusername);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 120, 100, 30);
        add(lblpassword);

        pfpassword = new JPasswordField(); 
        pfpassword.setBounds(150, 120, 150, 30);
        add(pfpassword);
        
        
        
        

        
        JButton SignUp = new JButton("Sign up");
        SignUp.setBounds(150, 150, 50, 30);
        SignUp.setForeground(Color.RED);
        SignUp.setBorder(null);
        SignUp.setBackground(null);
        SignUp.setForeground(Color.RED);
        SignUp.setFocusable(false);          
        
        SignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new SignUp().setVisible(true);
            }
        });
        add(SignUp);

        
        
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 190, 80, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFocusable(false);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 200, 200);
        add(image);
        
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = new String(pfpassword.getPassword());
            
            Conn c = new Conn();
            String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
            
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
