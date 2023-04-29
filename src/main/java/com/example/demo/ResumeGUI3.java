package com.example.resume.gui;

import javax.swing.*;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.resume.Controller.ResumeController;
import com.example.resume.Service.ResumeService;
import com.example.resume.model.resume;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.example.demo.Analyser;
//import javax.swing.*;
//import com.example.demo.predictivekeyboard;
//import com.example.demo.SuggestionEngine;
//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//import org.apache.pdfbox.text.PDFTextStripper;

@SpringBootApplication
@Controller
@SuppressWarnings("serial")
@ComponentScan("com.example")
public class ResumeGUI3 extends JFrame implements ActionListener {

//    @Autowired
//    private ResumeService service;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private JLabel nameLabel, phoneLabel, summaryLabel, skill1Label, skill2Label, skill3Label;
	private JTextField nameField, phoneField, skill1Field, skill2Field, skill3Field;
	private JTextArea summaryField;
	private JButton createButton;
	private JComboBox<String> viewComboBox;
	private String[] viewOptions = { "View 1", "View 2", "View 3" };

	public ResumeGUI3() throws Exception {

		try {

			System.out.println("Trying to create new Resume!");

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
			formPanel.add(createButton);

			// Add the form panel and view selection combo box to the JFrame
			add(formPanel, BorderLayout.CENTER);
			add(viewComboBox, BorderLayout.NORTH);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		

//    	ResumeGUI3 a = new ResumeGUI3();
//    	a.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == createButton) {
			// Get the form data
			String name = nameField.getText();
			String phone = phoneField.getText();
//            String education = educationField.getText();
//            String experience = experienceField.getText();
			String skill1 = skill1Field.getText();
			String skill2 = skill2Field.getText();
			String skill3 = skill3Field.getText();
			String summary = summaryField.getText();

			// Saving to the database
			resume res = new resume();

			res.setName(name);
			res.setPhone(phone);
			res.setskill1(skill1);
			res.setSummary(summary);
			res.setskill2(skill2);
			res.setskill3(skill3);

			String a = String.valueOf(id);

			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ResumeFinale", "root",
						"1234");
				String query = "INSERT INTO Resume (id, name, phone,summary,skill1,skill2,skill3) VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, a);
				stmt.setString(2, name);
				stmt.setString(3, phone);
				stmt.setString(4, summary);
				stmt.setString(5, skill1);
				stmt.setString(6, skill2);
				stmt.setString(7, skill3);
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "Resume created successfully.");
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Failed to create resume: " + e1.getMessage());
			}

			nameField.setText("");
			phoneField.setText("");
			skill1Field.setText("");
			skill2Field.setText("");
			summaryField.setText("");
			skill3Field.setText("");

//            createButton = new JButton("Create Resume");
//            createButton.addActionListener(this);

//            service.saveResume(res);

			JOptionPane.showMessageDialog(ResumeGUI3.this, "Resume saved successfully!");

			// Create the resume based on the selected view
			String selectedView = (String) viewComboBox.getSelectedItem();

			if (selectedView.equals("View 1")) {
				String resume = "";
				resume += "Name: " + name + "\n";
				resume += "Phone: " + phone + "\n";
//                resume += "Education: " + education + "\n";
//                resume += "Experience: " + experience + "\n";
				resume += "Skill-1: " + skill1 + "\n";
				resume += "Skill-2: " + skill2 + "\n";
				resume += "Skill-3: " + skill3 + "\n";
				resume += "Summary: " + summary + "\n";

			} else if (selectedView.equals("View 2")) {
				String resume = "";
				resume += "Name: " + name + "\n";
//                resume += "Education: " + education + "\n\n";
//                resume += "Experience:\n" + experience.replace("\n", "\n\t") + "\n\n";
				resume += "Summary:\n" + summary;

			}
		}
		
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String searchTerm = searchBar.getText();
				
				
			}
		});
		
		
		
		

		setVisible(true);

	}

	public List<resume> Search(String term) {

		List<resume> ans = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ResumeFinale", "root", "1234");
			String query = "Select id from Resume where skill1 like ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, term);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				resume resu = new resume();
				String query1 = "Select * from Resume where id LIKE ?";
				PreparedStatement stmt1 = conn.prepareStatement(query1);
				stmt1.setString(1, res.getString("id"));
				ResultSet res1 = stmt1.executeQuery();

				int id = Integer.parseInt(res1.getString("id"));
				resu.setId(id);
				resu.setName(res1.getString("name"));
				resu.setPhone(res1.getString("phone"));
				resu.setSummary(res1.getString("summary"));
				resu.setskill1(res1.getString("skill1"));
				resu.setskill2(res1.getString("skill2"));
				resu.setskill3(res1.getString("skill3"));

				ans.add(resu);

			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to create resume: " + e1.getMessage());
		}

		return ans;
	}

	public static void main(String Args[]) throws Exception {
//    	System.out.println("Hello World!!");
//    	SpringApplication.run(ResumeGUI3.class, Args);

		// ApplicationContext appContext = (ApplicationContext)
		// SpringApplication.run(ResumeGUI3.class, Args);
		/*
		 * service = ((BeanFactory) appContext).getBean(ResumeService.class); if(service
		 * == null) { System.out.println("Not found service"); } else {
		 * System.out.println("Service found!!"); }
		 */

		// a.setVisible(true);

		ResumeGUI3 a = new ResumeGUI3();
		a.setVisible(true);
	}
}

;