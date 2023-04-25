package com.example.demo;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class ResumeGUI2 extends JFrame implements ActionListener {
    private JComboBox<String> resumeComboBox;
    private String[] resumeOptions = {"Resume 1", "Resume 2", "Resume 3"};
    private JLabel nameLabel, phoneLabel, summaryLabel, educationLabel, experienceLabel;
    private JTextField nameField, phoneField;
    private JTextArea summaryField, educationField, experienceField;
    private JButton saveButton, cancelButton;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Resume";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection conn;

    public ResumeGUI2() {
        // Set up the JFrame
        setTitle("Edit Existing Resume");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the resume selection combo box
        resumeComboBox = new JComboBox<String>(resumeOptions);
        resumeComboBox.addActionListener(this);

        // Create the form fields
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);

        summaryLabel = new JLabel("Summary:");
        summaryField = new JTextArea(10, 20);
        // Set up predictive keyboard for summaryField
        //summaryField.addKeyListener(new PredictiveKeyboard(summaryField, 3));

        educationLabel = new JLabel("Education:");
        educationField = new JTextArea(10, 20);

        experienceLabel = new JLabel("Experience:");
        experienceField = new JTextArea(10, 20);

        // Set up predictive keyboard for experienceField
        //experienceField.addKeyListener(new PredictiveKeyboard(experienceField, 3));

        // Create the save and cancel buttons
        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        // Create the form panel
        JPanel formPanel = new JPanel(new GridLayout(14, 2));
        formPanel.add(resumeComboBox);
        formPanel.add(new JLabel(""));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(educationLabel);
        formPanel.add(educationField);
        formPanel.add(experienceLabel);
        formPanel.add(experienceField);
        formPanel.add(summaryLabel);
        formPanel.add(summaryField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);
        // Add the form panel to the JFrame
        add(formPanel, BorderLayout.CENTER);

        // Connect to the database
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database.");
            ex.printStackTrace();
        }

        // Set the default values of the form fields
        setDefaultValues();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeComboBox) {
            // Load the selected resume
            loadResume();
        } else if (e.getSource() == saveButton) {
            // Save the changes to the selected resume
            saveResume();
        } else if (e.getSource() == cancelButton) {
            // Close the window without saving changes
            dispose();
        }
    }

    private void setDefaultValues() {
        // Set the default resume to the first option
        resumeComboBox.setSelectedIndex(0);

        // Load the data for the first resume
        loadResume();
    }

    private void loadResume() {
        // Retrieve the selected resume from the database
        String selectedResume = (String)resumeComboBox.getSelectedItem();
        String query = "SELECT * FROM resumes WHERE name=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, selectedResume);
            ResultSet rs = stmt.executeQuery();

            // Populate the form fields with the retrieved data
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                phoneField.setText(rs.getString("phone"));
                summaryField.setText(rs.getString("summary"));
                educationField.setText(rs.getString("education"));
                experienceField.setText(rs.getString("experience"));
            }

        } catch (SQLException ex) {
            System.out.println("Failed to load resume data.");
            ex.printStackTrace();
        }
    }


    private void saveResume() {
        // Save the changes to the selected resume in the database
        String selectedResume = (String)resumeComboBox.getSelectedItem();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String education = educationField.getText();
        String experience = experienceField.getText();
        String summary = summaryField.getText();

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resumes", "root", "");

            // Prepare the SQL statement
            PreparedStatement stmt = conn.prepareStatement("UPDATE resumes SET name = ?, phone = ?, education = ?, experience = ?, summary = ? WHERE name = ?");

            // Set the parameter values
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, education);
            stmt.setString(4, experience);
            stmt.setString(5, summary);
            stmt.setString(6, selectedResume);

            // Execute the update statement
            int rows = stmt.executeUpdate();

            // Close the database connection and statement
            stmt.close();
            conn.close();

            // Display a message if the update was successful
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Resume changes saved.");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to save resume changes.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResumeGUI2 resumeGUI2 = new ResumeGUI2();
        resumeGUI2.setVisible(true);
    }
}
