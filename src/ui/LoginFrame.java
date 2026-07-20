package ui;
import model.Student;
import ui.StudentDashboard;
import dao.AdminDAO;
import dao.AdministrationDAO;
import dao.StudentDAO;
import model.Admin;
import model.Administration;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginAsLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JRadioButton studentRadio;
    private JRadioButton administrationRadio;
    private JRadioButton adminRadio;

    private ButtonGroup group;

    private JButton loginButton;

    public LoginFrame() {

        setTitle("Scholarship Application System");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        Font titleFont = new Font("Arial", Font.BOLD, 22);
        Font normalFont = new Font("Arial", Font.PLAIN, 14);

       
        titleLabel = new JLabel("Scholarship Application System");
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(20, 25, 460, 35);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
       
        usernameLabel = new JLabel("Email / Username");
        usernameLabel.setFont(normalFont);
        usernameLabel.setBounds(60, 80, 150, 25);

        usernameField = new JTextField();
        usernameField.setBounds(60, 105, 360, 30);

        
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(normalFont);
        passwordLabel.setBounds(60, 150, 150, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(60, 175, 360, 30);

      
        loginAsLabel = new JLabel("Login As");
        loginAsLabel.setFont(normalFont);
        loginAsLabel.setBounds(60, 220, 100, 25);

        studentRadio = new JRadioButton("Student");
        studentRadio.setBounds(60, 250, 100, 25);

        administrationRadio = new JRadioButton("Administration");
        administrationRadio.setBounds(170, 250, 130, 25);

        adminRadio = new JRadioButton("Super Admin");
        adminRadio.setBounds(320, 250, 120, 25);

        group = new ButtonGroup();
        group.add(studentRadio);
        group.add(administrationRadio);
        group.add(adminRadio);

        studentRadio.setSelected(true);
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(150, 320, 180, 40);
        loginButton.addActionListener(this);

        add(titleLabel);

        add(usernameLabel);
        add(usernameField);

        add(passwordLabel);
        add(passwordField);

        add(loginAsLabel);

        add(studentRadio);
        add(administrationRadio);
        add(adminRadio);

        add(loginButton);
        getRootPane().setDefaultButton(loginButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter username/email and password.");
            return;
        }

        if (studentRadio.isSelected()) {

            StudentDAO dao = new StudentDAO();
            Student student = dao.login(username, password);

            if (student != null) {

                JOptionPane.showMessageDialog(this,
                        "Student Login Successful!");

                new StudentDashboard(student);
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Student Email or Password!");

            }

        }

        else if (administrationRadio.isSelected()) {

            AdministrationDAO dao = new AdministrationDAO();
            Administration admin = dao.login(username, password);

            if (admin != null) {

                JOptionPane.showMessageDialog(this,
                        "Administration Login Successful!");

                new AdministrationDashboard();
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Administration Email or Password!");

            }

        }

        else if (adminRadio.isSelected()) {

            AdminDAO dao = new AdminDAO();
            Admin admin = dao.login(username, password);

            if (admin != null) {

                JOptionPane.showMessageDialog(this,
                        "Super Admin Login Successful!");

                new AdminDashboard();
                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password!");
            }

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new LoginFrame());

    }

}