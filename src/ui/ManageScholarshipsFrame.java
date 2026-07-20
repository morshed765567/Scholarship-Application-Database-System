package ui;

import dao.ScholarshipDAO;
import model.Scholarship;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageScholarshipsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField nameField;
    private JTextField cgpaField;
    private JTextField amountField;
    private JTextField deadlineField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private int selectedScholarshipId = -1;

    public ManageScholarshipsFrame() {

        setTitle("Manage Scholarships");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {
                "ID",
                "Scholarship Name",
                "Minimum CGPA",
                "Amount",
                "Deadline"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(5,2,10,10));

        nameField = new JTextField();
        cgpaField = new JTextField();
        amountField = new JTextField();
        deadlineField = new JTextField();

        panel.add(new JLabel("Scholarship Name"));
        panel.add(nameField);

        panel.add(new JLabel("Minimum CGPA"));
        panel.add(cgpaField);

        panel.add(new JLabel("Amount"));
        panel.add(amountField);

        panel.add(new JLabel("Deadline (YYYY-MM-DD)"));
        panel.add(deadlineField);

        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        JPanel southPanel = new JPanel(new BorderLayout());

        southPanel.add(panel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        loadScholarships();

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                selectedScholarshipId =
                        Integer.parseInt(model.getValueAt(row,0).toString());

                nameField.setText(model.getValueAt(row,1).toString());
                cgpaField.setText(model.getValueAt(row,2).toString());
                amountField.setText(model.getValueAt(row,3).toString());
                deadlineField.setText(model.getValueAt(row,4).toString());

            }

        });

        addButton.addActionListener(e -> addScholarship());

        updateButton.addActionListener(e -> updateScholarship());

        deleteButton.addActionListener(e -> deleteScholarship());

        refreshButton.addActionListener(e -> loadScholarships());

        setVisible(true);

    }

    private void loadScholarships(){

        ScholarshipDAO dao = new ScholarshipDAO();

        ArrayList<Scholarship> list = dao.getAllScholarships();

        model.setRowCount(0);

        for(Scholarship s : list){

            model.addRow(new Object[]{
                    s.getScholarshipId(),
                    s.getScholarshipName(),
                    s.getMinimumCgpa(),
                    s.getAmount(),
                    s.getDeadline()
            });

        }

    }

    private void addScholarship(){

        Scholarship s = new Scholarship();

        s.setScholarshipName(nameField.getText());
        s.setMinimumCgpa(Double.parseDouble(cgpaField.getText()));
        s.setAmount(Double.parseDouble(amountField.getText()));
        s.setDeadline(deadlineField.getText());

        ScholarshipDAO dao = new ScholarshipDAO();

        if(dao.addScholarship(s)){

            JOptionPane.showMessageDialog(this,"Scholarship Added");

            loadScholarships();

        }else{

            JOptionPane.showMessageDialog(this,"Failed");

        }

    }

    private void updateScholarship(){

        if(selectedScholarshipId==-1){

            JOptionPane.showMessageDialog(this,"Select a scholarship");

            return;

        }

        Scholarship s = new Scholarship();

        s.setScholarshipId(selectedScholarshipId);
        s.setScholarshipName(nameField.getText());
        s.setMinimumCgpa(Double.parseDouble(cgpaField.getText()));
        s.setAmount(Double.parseDouble(amountField.getText()));
        s.setDeadline(deadlineField.getText());

        ScholarshipDAO dao = new ScholarshipDAO();

        if(dao.updateScholarship(s)){

            JOptionPane.showMessageDialog(this,"Updated");

            loadScholarships();

        }else{

            JOptionPane.showMessageDialog(this,"Failed");

        }

    }

    private void deleteScholarship(){

        if(selectedScholarshipId==-1){

            JOptionPane.showMessageDialog(this,"Select a scholarship");

            return;

        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this scholarship?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if(confirm==JOptionPane.YES_OPTION){

            ScholarshipDAO dao = new ScholarshipDAO();

            if(dao.deleteScholarship(selectedScholarshipId)){

                JOptionPane.showMessageDialog(this,"Deleted");

                loadScholarships();

            }else{

                JOptionPane.showMessageDialog(this,"Delete Failed");

            }

        }

    }

}