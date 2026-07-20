package ui;

import model.Student;

import javax.swing.*;

public class StudentProfileFrame extends JFrame {

    private Student student;

    public StudentProfileFrame(Student student) {

        dao.StudentDAO dao = new dao.StudentDAO();
        this.student = dao.getStudentById(student.getStudentId());

        setTitle("My Profile");
        setSize(500, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("MY PROFILE");
        title.setBounds(180, 20, 200, 30);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(40, 70, 120, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 110, 120, 25);

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(40, 150, 120, 25);

        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setBounds(40, 190, 120, 25);

        JLabel cgpaLabel = new JLabel("CGPA:");
        cgpaLabel.setBounds(40, 230, 120, 25);

        JLabel dobLabel = new JLabel("Date of Birth (DD-MM-YYYY):");
        dobLabel.setBounds(40, 270, 140, 25);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(40, 310, 120, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 350, 120, 25);

        JLabel nameValue = new JLabel(this.student.getName());
        nameValue.setBounds(180, 70, 250, 25);

        JLabel emailValue = new JLabel(this.student.getEmail());
        emailValue.setBounds(180, 110, 250, 25);

        JLabel departmentValue = new JLabel(this.student.getDepartment());
        departmentValue.setBounds(180, 150, 250, 25);

        JLabel semesterValue = new JLabel(String.valueOf(this.student.getSemester()));
        semesterValue.setBounds(180, 190, 250, 25);

        JLabel cgpaValue = new JLabel(String.valueOf(this.student.getCgpa()));
        cgpaValue.setBounds(180, 230, 250, 25);

        // Show date as DD-MM-YYYY if it exists
        String dob = this.student.getDateOfBirth();
        if (dob != null && dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] parts = dob.split("-");
            dob = parts[2] + "-" + parts[1] + "-" + parts[0];
        }

        JTextField dobField = new JTextField(dob);
        dobField.setBounds(180, 270, 200, 25);

        JTextField phoneField = new JTextField(this.student.getPhone());
        phoneField.setBounds(180, 310, 200, 25);

        JPasswordField passwordField = new JPasswordField(this.student.getPassword());
        passwordField.setBounds(180, 350, 200, 25);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(110, 410, 100, 35);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(240, 410, 100, 35);

        updateButton.addActionListener(e -> {

            String newDob = dobField.getText().trim();

 
            if (newDob.matches("\\d{2}-\\d{2}-\\d{4}")) {

                String[] parts = newDob.split("-");
                newDob = parts[2] + "-" + parts[1] + "-" + parts[0];
            }

            this.student.setDateOfBirth(newDob);
            this.student.setPhone(phoneField.getText().trim());
            this.student.setPassword(new String(passwordField.getPassword()));

            if (dao.updateProfile(this.student)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Profile Updated Successfully!"
                );

                dispose();
                new StudentProfileFrame(this.student);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Failed!"
                );
            }
        });

        closeButton.addActionListener(e -> dispose());

        add(title);

        add(nameLabel);
        add(emailLabel);
        add(departmentLabel);
        add(semesterLabel);
        add(cgpaLabel);
        add(dobLabel);
        add(phoneLabel);
        add(passwordLabel);

        add(nameValue);
        add(emailValue);
        add(departmentValue);
        add(semesterValue);
        add(cgpaValue);

        add(dobField);
        add(phoneField);
        add(passwordField);

        add(updateButton);
        add(closeButton);

        setVisible(true);
    }
}