package employee.management.system;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Salary extends JFrame implements ActionListener {

    Choice empIdChoice;
    JComboBox month;
    JButton back;
    JLabel nameLabel , countLabel, dailyLabel, monthlyLabel;

    Salary() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        
         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/add_employee2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);
        
        
        
        
        
        JLabel heading = new JLabel("Employee Salary Calculator");
        heading.setBounds(200, 30, 350, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        image.add(heading);

        
        
        
        
        JLabel labelmonth = new JLabel("Month");
        labelmonth.setBounds(50, 120, 150, 30);
        image.add(labelmonth);
    
       
        String courses[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        month = new JComboBox(courses);
        month.setBackground(Color.WHITE);
        month.setBounds(250, 120, 150, 30);
        image.add(month);
        
        
        
        
        
        JLabel labelEmpId = new JLabel("Employee Id");
        labelEmpId.setBounds(50, 160, 150, 30);
        image.add(labelEmpId);

        empIdChoice = new Choice();
        empIdChoice.setBounds(250, 160, 150, 30);
        image.add(empIdChoice);

        try {
            Conn c = new Conn();
            String query = "select * from employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                empIdChoice.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
        
        
        
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 200, 150, 30);
        image.add(labelName);

        nameLabel = new JLabel();
        nameLabel.setBounds(250, 200, 150, 30);
        image.add(nameLabel);
        
        
        empIdChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from employee where empId = '"+empIdChoice.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        nameLabel.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        
        
        JLabel labeldaily = new JLabel("Salary per Day");
        labeldaily.setBounds(50, 240, 150, 30);
        image.add(labeldaily);

        dailyLabel = new JLabel();
        dailyLabel.setBounds(250, 240, 150, 30);
        image.add(dailyLabel);
        
        
        empIdChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from employee where empId = '"+empIdChoice.getSelectedItem()+"'";
                    ResultSet rst = c.s.executeQuery(query);
                    while(rst.next()) {
                        dailyLabel.setText(rst.getString("salary"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
       
        
        JLabel labelcount = new JLabel("Total Number of Present");
        labelcount.setBounds(50, 280, 150, 30);
        image.add(labelcount);

        countLabel = new JLabel();
        countLabel.setBounds(250, 280, 150, 30);
        image.add(countLabel);
        
        empIdChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String quer1y = "select count(Attendance) from attendance where empId = '"+empIdChoice.getSelectedItem()+"' and Date LIKE '"+month.getSelectedItem()+"%'";
                    ResultSet res = c.s.executeQuery(quer1y);
                    while(res.next()) {
                        countLabel.setText(res.getString(1));
                    }
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        
        JLabel labelmonthly = new JLabel("Total Salary");
        labelmonthly.setBounds(50, 320, 150, 30);
        image.add(labelmonthly);

        monthlyLabel = new JLabel();
        monthlyLabel.setBounds(250, 320, 150, 30);
        image.add(monthlyLabel);
        
        empIdChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String quer1y = "select '"+dailyLabel.getText()+"' * '"+countLabel.getText()+"'  ";
                    ResultSet res = c.s.executeQuery(quer1y);
                    while(res.next()) {
                        monthlyLabel.setText(res.getString(1));
                    }
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
      

        back = new JButton("Back to Home");
        back.setBounds(250, 380, 150,30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        image.add(back);

       

        setSize(700, 500);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            String selectedEmpId = empIdChoice.getSelectedItem();
            String name = nameLabel.getText();
            String Daily = dailyLabel.getText();
            String monthh = (String) month.getSelectedItem();
            String Count = countLabel.getText();
            
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new Salary();
    }
}
