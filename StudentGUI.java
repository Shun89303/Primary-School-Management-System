package student_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGUI extends JFrame {

    private final StudentManager manager = new StudentManager();

    // Table for displaying students
    private final String[] columnNames = {"ID", "Name", "Grade"};
    private final JTable table = new JTable(new DefaultTableModel(columnNames, 0));

    public StudentGUI() {
        super("Student Management System GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 650);
        setLocationRelativeTo(null);

        // --- Add Student Panel ---
        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Student"));

        JTextField nameField = new JTextField(15);
        
        String[] grades = {"1", "2", "3", "4"};
        JComboBox<String> gradeBox = new JComboBox<>(grades);
        
        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> 
        {
            String name = nameField.getText().trim();
            int grade = Integer.parseInt((String) gradeBox.getSelectedItem());
            manager.addStudent(name, grade);
            JOptionPane.showMessageDialog(this, "✅ Student added!");
            nameField.setText("");
            gradeBox.setSelectedIndex(0);
        });

        addPanel.add(new JLabel("Name:"));
        addPanel.add(nameField);
        addPanel.add(new JLabel("Grade:"));
        addPanel.add(gradeBox);
        addPanel.add(addButton);

        // --- Search Student Panel ---
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Student"));

        JTextField searchIdField = new JTextField(5);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(searchIdField.getText().trim());
                Student s = manager.findStudent(id);
                if (s != null) {
                    JOptionPane.showMessageDialog(this, s.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid ID input.");
            }
            searchIdField.setText("");
        });

        searchPanel.add(new JLabel("Student ID:"));
        searchPanel.add(searchIdField);
        searchPanel.add(searchButton);

        // --- View All Students Panel ---
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(BorderFactory.createTitledBorder("View All Students"));

        JButton viewAllButton = new JButton("View All");
        viewAllButton.addActionListener(e -> refreshTable());

        viewPanel.add(viewAllButton, BorderLayout.NORTH);
        viewPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Layout Panels in Frame ---
        setLayout(new BorderLayout());
        add(addPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(viewPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Refresh table data from database
    private void refreshTable() {
        List<Student> students = manager.viewAllStudents();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // clear previous rows
        for (Student s : students) {
            model.addRow(new Object[]{s.getStudentId(), s.getName(), s.getGrade()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGUI::new);
    }
}