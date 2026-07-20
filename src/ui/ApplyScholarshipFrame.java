package ui;

import dao.ApplicationDAO;
import dao.ScholarshipDAO;
import model.Scholarship;
import model.ScholarshipApplication;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ApplyScholarshipFrame extends JFrame implements ActionListener {

    private Student student;

    private JTable table;
    private DefaultTableModel model;

    private JButton applyButton;
    private JButton closeButton;

    public ApplyScholarshipFrame(Student student) {

        this.student = student;

        setTitle("Apply for Scholarship");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        String[] columns = {
                "ID",
                "Scholarship",
                "Minimum CGPA",
                "Amount",
                "Deadline"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 800, 330);

        applyButton = new JButton("APPLY");
        applyButton.setBounds(220, 380, 150, 40);

        closeButton = new JButton("CLOSE");
        closeButton.setBounds(450, 380, 150, 40);

        applyButton.addActionListener(this);
        closeButton.addActionListener(this);

        add(scrollPane);
        add(applyButton);
        add(closeButton);

        loadScholarships();

        setVisible(true);
    }

    private void loadScholarships() {

        ScholarshipDAO dao = new ScholarshipDAO();

        ArrayList<Scholarship> list = dao.getAllScholarships();

        model.setRowCount(0);

        for (Scholarship s : list) {

            Object[] row = {

                    s.getScholarshipId(),
                    s.getScholarshipName(),
                    s.getMinimumCgpa(),
                    s.getAmount(),
                    s.getDeadline()

            };

            model.addRow(row);

        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == closeButton) {

            dispose();
            return;

        }

        if (e.getSource() == applyButton) {

            int row = table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please select a scholarship.");

                return;

            }

            int scholarshipId =
                    Integer.parseInt(model.getValueAt(row, 0).toString());

            ScholarshipApplication application =
                    new ScholarshipApplication();

            application.setStudentId(student.getStudentId());

            application.setScholarshipId(scholarshipId);

            application.setApplicationDate(LocalDate.now().toString());

            application.setStatus("Pending");

            application.setReviewedBy(1);

            application.setRemarks("");

            ApplicationDAO dao = new ApplicationDAO();

            if (dao.hasAppliedForScholarship(
                    student.getStudentId(),
                    scholarshipId)) {

                JOptionPane.showMessageDialog(this,
                        "You have already applied for this scholarship.");

                return;
            }
         if (dao.addApplication(application)) {

             JOptionPane.showMessageDialog(this,
                     "Application Submitted Successfully!");

         } else {

             JOptionPane.showMessageDialog(this,
                     "Application Failed!");

         }

        }

    }

}