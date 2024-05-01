package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener{

    JTable table;
    Choice cemployeeId , position;
    JButton search1 , search2 , print, update,remove, back;
    
    ViewEmployee() {
        
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
        
        
        
        JLabel positionlbl = new JLabel("Search by Designation");
        positionlbl.setBounds(20, 50, 150, 20);
        add(positionlbl);
        
        position = new Choice();
        position.setBounds(180, 50, 150, 20);
        add(position);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()) {
                position.add(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 130, 850, 500);
        add(jsp);
        
        
        
        
        search1 = new JButton("Search");
        search1.setBounds(350, 20, 80, 20);
        search1.addActionListener(this);
        add(search1);
        
        search2 = new JButton("Search");
        search2.setBounds(350, 50, 80, 20);
        search2.addActionListener(this);
        add(search2);
        
        
        
        print = new JButton("Print");
        print.setBounds(20, 90, 80, 20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(120, 90, 80, 20);
        update.addActionListener(this);
        add(update);
        
        remove = new JButton("Remove");
        remove.setBounds(220, 90, 82, 20);
        remove.addActionListener(this);
        add(remove);
        
        back = new JButton("Back");
        back.setBounds(320, 90, 80, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(320, 80);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search1) {
            String query = "select * from employee where empId = '"+cemployeeId.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (ae.getSource() == search2) {
            String query = "select * from employee where designation = '"+position.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet res = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(res));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
            
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}