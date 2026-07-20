package ui;

import dao.ApplicationDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MyApplicationsFrame extends JFrame {

    private Student student;

    private JTable table;
    private DefaultTableModel model;

    public MyApplicationsFrame(Student student) {

        this.student = student;

        setTitle("My Applications");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Application ID",
                "Scholarship",
                "Application Date",
                "Status",
                "Remarks"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        loadApplications();

        setVisible(true);
    }

    private void loadApplications() {

        ApplicationDAO dao = new ApplicationDAO();

        ArrayList<Object[]> list =
                dao.getApplicationsWithScholarshipName(student.getStudentId());

        model.setRowCount(0);

        for (Object[] row : list) {

            model.addRow(row);

        }

    }

}