package com.example.demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {

    private JButton myButton1,myButton2,myButton3;
    private JTextField myTextArea;
    static final long serialVersionUID = 0;

    public MyFrame() {
        // Set up the JFrame
        setTitle("My Frame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create the button and text field
        myButton1 = new JButton("Click Me");
        myButton2 = new JButton("Click 2");
        myButton3 = new JButton("Click 3");
        myButton1.addActionListener(this);
        myButton2.addActionListener(this);
        myButton3.addActionListener(this);

        myTextArea = new JTextField(20);

        // Add the button and text field to the JFrame
        add(myButton1);
        add(myButton2);
        add(myButton3);
        add(myTextArea);
        myButton2.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton1) {
            // Set the text of the text field
        	myButton2.setVisible(true);
        	myTextArea.setText("Button clicked!");
        	
        } else if (e.getSource() == myButton2) {
        	// Set the text of the text field
        	myTextArea.setText("Button2 clicked!");
        }
        else if (e.getSource() == myButton3) {
        	// Set the text of the text field
        	myTextArea.setText("Button3 clicked!");
        }
        
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.setVisible(true);
    }
}
