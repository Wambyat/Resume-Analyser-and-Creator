package com.example.resume.gui;
import javax.swing.*;

import com.example.resume.Service.ResumeService;
import com.example.resume.model.resume;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import com.example.demo.Analyser;
//import javax.swing.*;
//import com.example.demo.predictivekeyboard;
//import com.example.demo.SuggestionEngine;
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//import org.apache.pdfbox.text.PDFTextStripper;

@SuppressWarnings("serial")
public class ResumeGUI3 extends JFrame implements ActionListener {
    private JLabel nameLabel, phoneLabel, summaryLabel, skill1Label , skill2Label , skill3Label;
    private JTextField nameField, phoneField,skill1Field,skill2Field,skill3Field;
    private JTextArea summaryField;
    private JButton createButton;
    private JComboBox<String> viewComboBox;
    private String[] viewOptions = {"View 1", "View 2", "View 3"};
    


    public ResumeGUI3() {
        // Set up the JFrame
        setTitle("Create New Resume");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the form fields
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(20);

        summaryLabel = new JLabel("Summary:");
        summaryField = new JTextArea(10, 20);
        // Set up predictive keyboard for summaryField
       
        

        skill1Label = new JLabel("Skill 1:");
        skill1Field = new JTextField(20);
        
        skill2Label = new JLabel("Skill 2:");
        skill2Field = new JTextField(20);
        
        skill3Label = new JLabel("Skill 3:");
        skill3Field = new JTextField(20);


        
        createButton = new JButton("Create Resume");
        createButton.addActionListener(this);

        // Create the view selection combo box
        viewComboBox = new JComboBox<String>(viewOptions);
        viewComboBox.addActionListener(this);

        // Create the form panel
        JPanel formPanel = new JPanel(new GridLayout(14, 2));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
//        formPanel.add(educationLabel);
//        formPanel.add(educationField);
//        formPanel.add(experienceLabel);
//        formPanel.add(experienceField);
        formPanel.add(summaryLabel);
        formPanel.add(summaryField);
        formPanel.add(skill1Label);
        formPanel.add(skill1Field);
        formPanel.add(skill2Label);
        formPanel.add(skill2Field);
        formPanel.add(skill3Label);
        formPanel.add(skill3Field);
        

        // Add the form panel and view selection combo box to the JFrame
        add(formPanel, BorderLayout.CENTER);
        add(viewComboBox, BorderLayout.NORTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            // Get the form data
            String name = nameField.getText();
            String phone = phoneField.getText();
//            String education = educationField.getText();
//            String experience = experienceField.getText();
            String skill1 = skill1Field.getText();
            String skill2 = skill1Field.getText();
            String skill3 = skill1Field.getText();
            String summary = summaryField.getText();

            
            // Saving to the database
            resume res = new resume();
            res.setName(name);
            res.setPhone(phone);
            res.setskill1(skill1);
            res.setSummary(summary);
            res.setskill2(skill2);
            res.setskill3(skill3);
            
            ResumeService resServ = new ResumeService();
            resServ.saveResume(res);
            
            JOptionPane.showMessageDialog(ResumeGUI3.this, "Resume saved successfully!");
            

            // Create the resume based on the selected view
            String selectedView = (String) viewComboBox.getSelectedItem();
            String resume = "";

            if (selectedView.equals("View 1")) {
                resume += "Name: " + name + "\n";
                resume += "Phone: " + phone + "\n";
//                resume += "Education: " + education + "\n";
//                resume += "Experience: " + experience + "\n";
                resume += "Skill-1: " + skill1 + "\n";
                resume += "Skill-2: " + skill2 + "\n";
                resume += "Skill-3: " + skill3 + "\n";
                resume += "Summary: " + summary + "\n";

            } else if (selectedView.equals("View 2")) {
                resume += "Name: " + name + "\n";
//                resume += "Education: " + education + "\n\n";
//                resume += "Experience:\n" + experience.replace("\n", "\n\t") + "\n\n";
                resume += "Summary:\n" + summary  ;

                } 
        }
        
        
    }

 

public static void main(String[] args) {
    ResumeGUI3 resumeGUI3 = new ResumeGUI3();
    resumeGUI3.setVisible(true);
}
}

