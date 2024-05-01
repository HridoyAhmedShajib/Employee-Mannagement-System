package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Select extends JFrame implements ActionListener{
    
    JButton admin, employee;
    
    Select() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
      
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Select.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 600);
        add(image);
        
        
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(250, 50, 500, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 30));
        heading.setForeground(Color.BLUE);
        image.add(heading);
        
        admin = new JButton("Admin Login");
        admin.setBounds(500, 200, 200, 50);
        admin.setFont(new Font("Raleway", Font.BOLD, 12));
        admin.addActionListener(this);
        admin.setFocusable(false);
        image.add(admin);
        
        employee = new JButton("Employee Login");
        employee.setBounds(500, 300, 200, 50);
        employee.setFont(new Font("Raleway", Font.BOLD, 12));
        employee.addActionListener(this);
        employee.setFocusable(false);
        image.add(employee);
        
        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == admin) {
            new Login();
        } else {
            setVisible(false);
            new ELogin();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
