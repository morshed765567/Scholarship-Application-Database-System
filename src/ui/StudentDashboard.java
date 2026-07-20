package ui;
import model.Student; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private Student student;
    private JButton viewScholarshipsButton;
    private JButton applyButton;
    private JButton myApplicationsButton;
    private JButton profileButton;
    private JButton logoutButton;

    public StudentDashboard(Student student) {

        this.student = student;

        setTitle("Student Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        Font titleFont = new Font("Arial", Font.BOLD, 26);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        titleLabel = new JLabel("Student Dashboard");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, 800, 40);

        welcomeLabel = new JLabel("Welcome, " + student.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(40, 80, 300, 30);

        viewScholarshipsButton = new JButton("View Scholarships");
        viewScholarshipsButton.setBounds(250, 140, 300, 45);
        viewScholarshipsButton.setFont(buttonFont);

        applyButton = new JButton("Apply for Scholarship");
        applyButton.setBounds(250, 210, 300, 45);
        applyButton.setFont(buttonFont);

        myApplicationsButton = new JButton("My Applications");
        myApplicationsButton.setBounds(250, 280, 300, 45);
        myApplicationsButton.setFont(buttonFont);

        profileButton = new JButton("My Profile");
        profileButton.setBounds(250, 350, 300, 45);
        profileButton.setFont(buttonFont);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(250, 450, 300, 45);
        logoutButton.setFont(buttonFont);

        viewScholarshipsButton.addActionListener(this);
        applyButton.addActionListener(this);
        myApplicationsButton.addActionListener(this);
        profileButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(titleLabel);
        add(welcomeLabel);

        add(viewScholarshipsButton);
        add(applyButton);
        add(myApplicationsButton);
        add(profileButton);
        add(logoutButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logoutButton) {

            dispose();
            new LoginFrame();

        }

        else if (e.getSource() == viewScholarshipsButton) {

            new ScholarshipListFrame();

        }

        else if (e.getSource() == applyButton) {

            new ApplyScholarshipFrame(student);

        }

        else if (e.getSource() == myApplicationsButton) {

        new MyApplicationsFrame(student);
        }

        else if (e.getSource() == profileButton) {

        new StudentProfileFrame(student);
        }

    }

}