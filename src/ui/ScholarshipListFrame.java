package ui;

import dao.ScholarshipDAO;
import model.Scholarship;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ScholarshipListFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ScholarshipListFrame() {

        setTitle("Available Scholarships");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        add(scrollPane);

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

}