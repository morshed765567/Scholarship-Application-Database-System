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

public class AdministrationDashboard extends JFrame implements ActionListener {

    private JButton viewApplicationsButton;
    private JButton scholarshipButton;
    private JButton logoutButton;

    private JLabel studentCountLabel;
    private JLabel scholarshipCountLabel;
    private JLabel pendingCountLabel;
    private JLabel approvedCountLabel;
    private JLabel rejectedCountLabel;

    public AdministrationDashboard() {

        setTitle("Administration Dashboard");
        setSize(700, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel welcome = new JLabel("Welcome, Scholarship Officer!");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setBounds(180, 20, 350, 30);
        add(welcome);

        JLabel title = new JLabel("Administration Dashboard");
        title.setFont(new Font("Arial", Font.PLAIN, 16));
        title.setBounds(235, 50, 250, 20);
        add(title);

        JLabel dateLabel = new JLabel(
                "Today's Date : " +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));

        dateLabel.setBounds(30, 90, 250, 25);

        JLabel timeLabel = new JLabel(
                "Current Time : " +
                        LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));

        timeLabel.setBounds(380, 90, 200, 25);

        add(dateLabel);
        add(timeLabel);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(5,1));
        summaryPanel.setBounds(150,130,380,150);
        summaryPanel.setBorder(new TitledBorder("System Summary"));

        StatisticsDAO dao = new StatisticsDAO();

        studentCountLabel =
                new JLabel("Total Students : " + dao.getStudentCount());

        scholarshipCountLabel =
                new JLabel("Total Scholarships : " + dao.getScholarshipCount());

        pendingCountLabel =
                new JLabel("Pending Applications : " + dao.getPendingCount());

        approvedCountLabel =
                new JLabel("Approved Applications : " + dao.getApprovedCount());

        rejectedCountLabel =
                new JLabel("Rejected Applications : " + dao.getRejectedCount());

        summaryPanel.add(studentCountLabel);
        summaryPanel.add(scholarshipCountLabel);
        summaryPanel.add(pendingCountLabel);
        summaryPanel.add(approvedCountLabel);
        summaryPanel.add(rejectedCountLabel);

        add(summaryPanel);

        viewApplicationsButton =
                new JButton("📋 Manage Applications");

        scholarshipButton =
                new JButton("🎓 Manage Scholarships");

        logoutButton =
                new JButton("🚪 Logout");

        viewApplicationsButton.setBounds(220,300,250,40);
        scholarshipButton.setBounds(220,355,250,40);
        logoutButton.setBounds(220,410,250,40);

        viewApplicationsButton.setBackground(new Color(66,133,244));
        viewApplicationsButton.setForeground(Color.WHITE);

        scholarshipButton.setBackground(new Color(52,168,83));
        scholarshipButton.setForeground(Color.WHITE);

        logoutButton.setBackground(new Color(234,67,53));
        logoutButton.setForeground(Color.WHITE);

        viewApplicationsButton.addActionListener(this);
        scholarshipButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(viewApplicationsButton);
        add(scholarshipButton);
        add(logoutButton);

        JLabel footer = new JLabel(
                "Scholarship Application Database System | Version 1.0",
                SwingConstants.CENTER);

        footer.setBounds(130,485,420,20);

        add(footer);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==viewApplicationsButton){

            new ManageApplicationsFrame();

        }

        else if(e.getSource()==scholarshipButton){

            new ManageScholarshipsFrame();

        }

        else if(e.getSource()==logoutButton){

            new LoginFrame();
            dispose();

        }

    }

}