package com.example.demo;

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
import com.example.demo.Analyser;
import javax.swing.*;
import com.example.demo.predictivekeyboard;
import com.example.demo.SuggestionEngine;
import com.example.demo.model.*;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

public class ResumeGUI3 extends JFrame implements ActionListener{
    private JLabel nameLabel, phoneLabel, summaryLabel, educationLabel, experienceLabel,profLabel;
    private JTextField nameField, phoneField,profField;
    private JTextArea summaryField, educationField, experienceField;
    private JButton createButton,b1,b2,b3,b4;
    private JComboBox<String> viewComboBox;
    private String[] viewOptions = {"View 1", "View 2", "View 3"};

	
	String[] ab;
	int len;
	String sug = "";

    predictivekeyboard ob1 = new predictivekeyboard();

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
        b1 = new JButton("Click 1");
        b2 = new JButton("Click 2");
        b3 = new JButton("Click 3");
        b4 = new JButton("get predictions");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        
        // Add the button and text field to the JFrame
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        
        // Set up predictive keyboard for summaryField
        summaryField.addKeyListener(new KeyListener(){
        	
            @Override
            public void keyReleased(KeyEvent e) {
            	System.out.println("HUH");
            	String sug = "";
            	String[] ab;
            	int len;
            	try {
            		ob1 = new predictivekeyboard();
            		ab = summaryField.getText().split(" ");
            		len = ab.length;
            		sug = ab[len-1];
					ob1.getSuggestions(sug);
					System.out.println(ob1.lista);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("ALLU YOU DUMB");
				}
            	
                }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
            });
        
        educationLabel = new JLabel("Education:");
        educationField = new JTextArea(10, 20);

        experienceLabel = new JLabel("Experience:");
        experienceField = new JTextArea(10, 20);
        // Set up predictive keyboard for experienceField

        profLabel = new JLabel("Proffesion:");
        profField = new JTextField(20);
        // Create the create button
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
        formPanel.add(educationLabel);
        formPanel.add(educationField);
        formPanel.add(experienceLabel);
        formPanel.add(experienceField);
        formPanel.add(summaryLabel);
        formPanel.add(summaryField);
        formPanel.add(b1);
        formPanel.add(b2);
        formPanel.add(b3);
        formPanel.add(b4);
        formPanel.add(profLabel);
        formPanel.add(profField);
        formPanel.add(createButton);

        // Add the form panel and view selection combo box to the JFrame
        add(formPanel, BorderLayout.CENTER);
        add(viewComboBox, BorderLayout.NORTH);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == b4) {
            // Set the text of the text field
        	b1.setVisible(true);
        	b2.setVisible(true);
        	b3.setVisible(true);
        	b1.setText(ob1.lista.get(0));
        	b2.setText(ob1.lista.get(1));
        	b3.setText(ob1.lista.get(2));
        	
        } 
    	if (e.getSource() == b1) {
            // Set the text of the text field
    		
    		summaryField.setText(summaryField.getText()+b1.getText());
        }
    	if (e.getSource() == b2) {
            // Set the text of the text field
    		summaryField.setText(summaryField.getText()+b2.getText());
        }
    	if (e.getSource() == b3) {
            // Set the text of the text field
    		summaryField.setText(summaryField.getText()+b3.getText());
        }
        if (e.getSource() == createButton) {
            // Get the form data
            String name = nameField.getText();
            String phone = phoneField.getText();
            String education = educationField.getText();
            String experience = experienceField.getText();
            String summary = summaryField.getText();
            String prof = profField.getText();

            // Create the resume based on the selected view
            String selectedView = (String) viewComboBox.getSelectedItem();
            String resume = "";

            if (selectedView.equals("View 1")) {
                resume += "Name: " + name + "\n";
                resume += "Phone: " + phone + "\n";
                resume += "Education: " + education + "\n";
                resume += "Experience: " + experience + "\n";
                resume += "Summary: " + summary + "\n";
                resume += "Proffesion: " + prof + "\n";
            } else if (selectedView.equals("View 2")) {
                resume += "Name: " + name + "\n";
                resume += "Education: " + education + "\n\n";
                resume += "Experience:\n" + experience.replace("\n", "\n\t") + "\n\n";
                resume += "Summary:\n" + summary;
                resume += "Proffesion: " + prof + "\n";
                } 
            else if (selectedView.equals("View 3")) {
                // Load the PDF template
                try {
                PDDocument template = Loader.loadPDF(new File("template.pdf"));
                // Get the fields from the PDF template
                List<PDField> fields = template.getDocumentCatalog().getAcroForm().getFields();

                // Fill in the fields with the form data
                for (PDField field : fields) {
                    String fieldName = field.getFullyQualifiedName();
                    String fieldValue = "";

                    switch (fieldName) {
                        case "Name":
                            fieldValue = name;
                            break;
                        case "Phone":
                            fieldValue = phone;
                            break;
                        case "Education":
                            fieldValue = education;
                            break;
                        case "Experience":
                            fieldValue = experience;
                            break;
                        case "Summary":
                            fieldValue = summary;
                            break;
                    }

                    if (!fieldValue.isEmpty()) {
                        field.setValue(fieldValue);
                    }
                }

                // Save the filled-in template as a new PDF
                template.save("resume.pdf");
                template.close();
                

                
                
                JOptionPane.showMessageDialog(this, "Resume created successfully!");
                

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading PDF template.");
                ex.printStackTrace();
            }
        }
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Resume", "root", "");
                String query = "INSERT INTO resumes (name, phone, education, experience, summary) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, phone);
                stmt.setString(3, education);
                stmt.setString(4, experience);
                stmt.setString(5, summary);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Resume created successfully.");
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to create resume: " + ex.getMessage());
            }
         // Clear the form fields
            nameField.setText("");
            phoneField.setText("");
            educationField.setText("");
            experienceField.setText("");
            summaryField.setText("");
            /*
        Analyser n=new Analyser(prof);
        // Display the resume in a message dialog
        JOptionPane.showMessageDialog(this, resume);
        resume r = new resume(1.0,name,phone,summery,);
        JOptionPane.showMessageDialog(this,n.getComments(resume)+n.getScore(resume)); */
    }
}

public static void main(String[] args) {
    ResumeGUI3 resumeGUI3 = new ResumeGUI3();
    resumeGUI3.setVisible(true);
}
}
