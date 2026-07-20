package ui;

import dao.ApplicationDAO;
import model.ScholarshipApplication;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ManageApplicationsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton refreshButton;
    private JTextField remarksField;

    public ManageApplicationsFrame() {

        setTitle("Manage Applications");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Application ID",
                "Student",
                "Scholarship",
                "Application Date",
                "Status",
                "Remarks"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, java.awt.BorderLayout.CENTER);
        loadApplications();
        
        JPanel panel = new JPanel();

        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        refreshButton = new JButton("Refresh");

        remarksField = new JTextField(20);

        panel.add(new JLabel("Remarks:"));
        panel.add(remarksField);

        panel.add(approveButton);
        panel.add(rejectButton);
        panel.add(refreshButton);

        add(panel, java.awt.BorderLayout.SOUTH);
        approveButton.addActionListener(e -> updateStatus("Approved"));

        rejectButton.addActionListener(e -> updateStatus("Rejected"));

        refreshButton.addActionListener(e -> loadApplications());

        add(panel, java.awt.BorderLayout.SOUTH);

        approveButton.addActionListener(e -> updateStatus("Approved"));
        rejectButton.addActionListener(e -> updateStatus("Rejected"));
        refreshButton.addActionListener(e -> loadApplications());

        setVisible(true);
    }

    private void loadApplications() {

        ApplicationDAO dao = new ApplicationDAO();

        ArrayList<Object[]> list = dao.getAllApplicationDetails();

        model.setRowCount(0);

        for (Object[] row : list) {
            model.addRow(row);
        }
    }
    private void updateStatus(String status) {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an application.");
            return;
        }

        int applicationId =
                Integer.parseInt(model.getValueAt(row, 0).toString());

        ApplicationDAO dao = new ApplicationDAO();

        ScholarshipApplication application =
                dao.getApplicationById(applicationId);

        application.setStatus(status);

        application.setReviewedBy(1);

        application.setRemarks(remarksField.getText());

        if (dao.updateApplicationStatus(application)) {

            JOptionPane.showMessageDialog(this,
                    "Application " + status + " successfully.");

            loadApplications();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Update failed.");

        }
    }

}