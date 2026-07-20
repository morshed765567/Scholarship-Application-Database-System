package ui;

import dao.AdminDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentStatusFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton searchButton;
    private JButton refreshButton;

    public StudentStatusFrame() {

        setTitle("Student Scholarship Status");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Top Panel =====
        JPanel topPanel = new JPanel();

        topPanel.add(new JLabel("Search Student:"));

        searchField = new JTextField(20);
        topPanel.add(searchField);

        searchButton = new JButton("Search");
        refreshButton = new JButton("Refresh");

        topPanel.add(searchButton);
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        // ===== Table =====
        String[] columns = {
                "Student ID",
                "Student Name",
                "Scholarship",
                "Status"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadStudents();

        // ===== Button Events =====

        searchButton.addActionListener(e -> {

            AdminDAO dao = new AdminDAO();

            ArrayList<Object[]> list =
                    dao.searchStudentStatus(searchField.getText());

            model.setRowCount(0);

            for (Object[] row : list) {
                model.addRow(row);
            }

        });

        refreshButton.addActionListener(e -> {

            searchField.setText("");
            loadStudents();

        });

        setVisible(true);
    }

    private void loadStudents() {

        AdminDAO dao = new AdminDAO();

        ArrayList<Object[]> list =
                dao.getAllStudentStatus();

        model.setRowCount(0);

        for (Object[] row : list) {

            model.addRow(row);

        }

    }

}