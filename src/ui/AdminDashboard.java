package ui;

import dao.StatisticsDAO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboard extends JFrame implements ActionListener {

    private JButton studentStatusButton;
    private JButton logoutButton;

    public AdminDashboard() {

        setTitle("Super Admin Dashboard");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel welcome = new JLabel("Welcome, Super Admin!");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setBounds(200,20,300,30);
        add(welcome);

        JLabel title = new JLabel("System Administration");
        title.setFont(new Font("Arial", Font.PLAIN,16));
        title.setBounds(245,50,220,20);
        add(title);

        JLabel dateLabel = new JLabel(
                "Today's Date : " +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));

        dateLabel.setBounds(30,90,250,25);

        JLabel timeLabel = new JLabel(
                "Current Time : " +
                        LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));

        timeLabel.setBounds(420,90,180,25);

        add(dateLabel);
        add(timeLabel);

        StatisticsDAO dao = new StatisticsDAO();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,1));
        panel.setBounds(150,130,400,150);
        panel.setBorder(new TitledBorder("System Overview"));

        panel.add(new JLabel("Total Students : "
                + dao.getStudentCount()));

        panel.add(new JLabel("Total Scholarships : "
                + dao.getScholarshipCount()));

        panel.add(new JLabel("Pending Applications : "
                + dao.getPendingCount()));

        panel.add(new JLabel("Approved Applications : "
                + dao.getApprovedCount()));

        panel.add(new JLabel("Rejected Applications : "
                + dao.getRejectedCount()));

        add(panel);

        studentStatusButton =
                new JButton("🔍 Student Scholarship Status");

        studentStatusButton.setBounds(200,315,280,45);

        studentStatusButton.setBackground(new Color(66,133,244));
        studentStatusButton.setForeground(Color.WHITE);

        logoutButton =
                new JButton("🚪 Logout");

        logoutButton.setBounds(200,375,280,45);

        logoutButton.setBackground(new Color(234,67,53));
        logoutButton.setForeground(Color.WHITE);

        studentStatusButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(studentStatusButton);
        add(logoutButton);

        JLabel footer = new JLabel(
                "Scholarship Application Database System | Version 1.0",
                SwingConstants.CENTER);

        footer.setBounds(120,470,450,20);

        add(footer);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==studentStatusButton){

            new StudentStatusFrame();

        }

        else if(e.getSource()==logoutButton){

            new LoginFrame();
            dispose();

        }

    }

}