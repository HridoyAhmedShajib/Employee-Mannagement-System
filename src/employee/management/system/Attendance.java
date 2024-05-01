package employee.management.system;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Attendance extends JFrame implements ActionListener {

    Choice empIdChoice;
    JDateChooser dateChooser;
    JButton add, back;
    JComboBox attendanceComboBox;
    JLabel nameLabel;

    Attendance() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/add_employee2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        JLabel heading = new JLabel("Employee Attendance");
        heading.setBounds(250, 30, 350, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        image.add(heading);

        JLabel labelEmpId = new JLabel("Employee Id");
        labelEmpId.setBounds(50, 170, 150, 30);
        image.add(labelEmpId);

        empIdChoice = new Choice();
        empIdChoice.setBounds(200, 170, 150, 30);
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
        labelName.setBounds(50, 220, 150, 30);
        image.add(labelName);

        nameLabel = new JLabel();
        nameLabel.setBounds(200, 220, 150, 30);
        image.add(nameLabel);

        JLabel labelDate = new JLabel("Date");
        labelDate.setBounds(50, 120, 150, 30);
        image.add(labelDate);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(200, 120, 150, 22);
        image.add(dateChooser);

        JLabel labelAttendance = new JLabel("Attendance");
        labelAttendance.setBounds(50, 270, 150, 30);
        image.add(labelAttendance);
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + empIdChoice.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                nameLabel.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] attendanceOptions = {"Present", "Absent"};
        attendanceComboBox = new JComboBox(attendanceOptions);
        attendanceComboBox.setBackground(Color.WHITE);
        attendanceComboBox.setBounds(200, 270, 150, 22);
        image.add(attendanceComboBox);

        empIdChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from employee where empId = '" + empIdChoice.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        nameLabel.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        add = new JButton("Submit");
        add.setBounds(200, 380, 100, 30);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        image.add(add);

        back = new JButton("Back");
        back.setBounds(400, 380, 100, 30);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        image.add(back);

        setSize(700, 500);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String selectedEmpId = empIdChoice.getSelectedItem();
            String name = nameLabel.getText();
            String date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String attendance = (String) attendanceComboBox.getSelectedItem();

            try {
                Conn conn = new Conn();
                String query = "insert into attendance values('" + selectedEmpId + "', '" + name + "', '" + date + "', '" + attendance + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Attendance Submitted successfully");
                setVisible(false);
                new Attendance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new Attendance();
    }
}
