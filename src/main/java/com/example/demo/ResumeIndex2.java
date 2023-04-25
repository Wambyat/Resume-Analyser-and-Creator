package com.example.demo;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ResumeIndex2 extends JFrame {
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton searchButton; // new search button
    private JTextField searchBar; // new search bar
    private JButton addButton;
    private JButton editButton;
    private Connection conn;
    
    public ResumeIndex2() {
        super("Resume Index");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Connect to the database
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Resume", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + e.getMessage());
            System.exit(1);
        }
        
        // Create a list to show the resumes
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                editButton.setEnabled(list.getSelectedIndex() != -1);
            }
        });
        JScrollPane listScrollPane = new JScrollPane(list);
        
        // Create the add and edit buttons
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Redirect to ResumeGui.java
                ResumeGUI3 resumeGui = new ResumeGUI3();
                
                resumeGui.setVisible(true);
            }
        });
        editButton = new JButton("Edit");
        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Redirect to ResumeGui.java
                ResumeGUI3 resumeGui = new ResumeGUI3();
                resumeGui.setVisible(true);
            }
        });
        searchButton = new JButton("Search"); // new search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String searchTerm = searchBar.getText();
            	
            	// Clear the existing list
            	listModel.clear();
            	
            	// Execute a SQL query to search for resumes by name
            	try {
            		PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM resumes WHERE name LIKE ?");
            		pstmt.setString(1, "%" + searchTerm + "%");
            		ResultSet rs = pstmt.executeQuery();
            		while (rs.next()) {
            			listModel.addElement(rs.getString("name"));
            		}
            	} catch (SQLException ex) {
            		ex.printStackTrace();
            		JOptionPane.showMessageDialog(ResumeIndex2.this, "Failed to retrieve data from the database: " + ex.getMessage());
            	}
            }
        });
        
        // Create the search bar
        searchBar = new JTextField(20); // new search bar
        
        // Add the components to the frame
        JPanel buttonPane = new JPanel();
        buttonPane.add(addButton);
        buttonPane.add(editButton);
        buttonPane.add(searchBar); // add search bar
        buttonPane.add(searchButton); // add search button
        getContentPane().add(listScrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        
        // Initialize the list with data from the database
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM resumes");
            while (rs.next()) {
                listModel.addElement(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve data from the database: " + e.getMessage());
        }
        
        pack();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ResumeIndex2 resumeIndex = new ResumeIndex2();
                resumeIndex.setVisible(true);
            }
        });
    }
}
