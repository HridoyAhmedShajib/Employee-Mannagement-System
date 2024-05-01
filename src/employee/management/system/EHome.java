package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EHome extends JFrame implements ActionListener{

    JButton view, add, update, remove, attendance, viewattendace, logout, salary;
    
    EHome() {
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home4.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);
        
        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(620, 50, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        heading.setForeground(Color.BLUE);
        image.add(heading);
        
        add = new JButton("Profile");
        add.setBounds(650, 110, 150, 40);
        add.addActionListener(this);
        image.add(add);
        add.setFocusable(false);
        
        view = new JButton("Work Schedule");
        view.setBounds(820, 110, 150, 40);
        view.addActionListener(this);
        image.add(view);
        view.setFocusable(false);

                
        update = new JButton("Notice Board");
        update.setBounds(650, 170, 150, 40);
        update.addActionListener(this);
        update.setFocusable(false);
        image.add(update);
        
        remove = new JButton("Projrcts");
        remove.setBounds(820, 170, 150, 40);
        remove.addActionListener(this);
        remove.setFocusable(false);
        image.add(remove);
        
        attendance = new JButton("Attendance");
        attendance.setBounds(650, 230, 150, 40);
        attendance.addActionListener(this);
        attendance.setFocusable(false);
        image.add(attendance);
        
        viewattendace = new JButton("Salary Details");
        viewattendace.setBounds(820, 230, 150, 40);
        viewattendace.addActionListener(this);
        viewattendace.setFocusable(false);
        image.add(viewattendace);
        
        salary = new JButton("Application");
        salary.setBounds(650, 290, 150, 40);
        salary.addActionListener(this);
        salary.setFocusable(false);
        image.add(salary);
        
        
        logout = new JButton("Log Out");
        logout.setBounds(820, 290, 150, 40);
        logout.addActionListener(this);
        logout.setFocusable(false);
        image.add(logout);
        
        setSize(1140, 670);
        setLocation(250, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        }else if (ae.getSource() == attendance) {
            setVisible(false);
            new Attendance();
        }else if (ae.getSource() == viewattendace) {
            setVisible(false);
            new ViewAttendance();
        }else if (ae.getSource() == logout) {
            dispose();
            new Select().setVisible(true);
        }else if (ae.getSource() == salary) {
            setVisible(false);
            new Salary();
        }else {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
