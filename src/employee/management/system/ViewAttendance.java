package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewAttendance extends JFrame implements ActionListener{

    JTable table;
    Choice cemployeeId;
    JButton search, print, attendance, back;
    JComboBox month;
    
    ViewAttendance() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);
        
        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        
        JLabel labelmonth = new JLabel("Search by Month");
        labelmonth.setBounds(400, 20, 150, 20);
        add(labelmonth);
    
       
        String courses[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        month = new JComboBox(courses);
        month.setBackground(Color.WHITE);
        month.setBounds(560, 20, 150, 20);
        add(month);
        
        
        
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from attendance");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 100, 840, 550);
        add(jsp);
        
      
        
        
        search = new JButton("Search");
        search.setBounds(20, 60, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 60, 80, 20);
        print.addActionListener(this);
        add(print);
        
        attendance = new JButton("Attendance");
        attendance.setBounds(220, 60, 80, 20);
        attendance.addActionListener(this);
        add(attendance);
        
        
        back = new JButton("Back");
        back.setBounds(320, 60, 80, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from attendance where empId = '"+cemployeeId.getSelectedItem()+"' and Date LIKE '"+month.getSelectedItem()+"%'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (ae.getSource() == attendance) {
            setVisible(false);
            new Attendance();
        }
        else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewAttendance();
    }
}
