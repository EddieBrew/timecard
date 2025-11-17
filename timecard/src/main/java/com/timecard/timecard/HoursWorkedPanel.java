package com.timecard.timecard;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;

public class HoursWorkedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public HoursWorkedPanel() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
	    setPreferredSize(new Dimension(450, 178)); // (Width, Height)
		
		JLabel lblBeginningDate = new JLabel("BEGIN DATE");
		add(lblBeginningDate);
		
		JLabel lblEndingDate = new JLabel("END DATE");
		springLayout.putConstraint(SpringLayout.WEST, lblEndingDate, 50, SpringLayout.EAST, lblBeginningDate);
		springLayout.putConstraint(SpringLayout.NORTH, lblBeginningDate, 0, SpringLayout.NORTH, lblEndingDate);
		add(lblEndingDate);
		
		JDateChooser dateChooserPayRollBegin = new JDateChooser();
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndingDate, -2, SpringLayout.NORTH, dateChooserPayRollBegin);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserPayRollBegin, 91, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserPayRollBegin, 150, SpringLayout.WEST, this);
		dateChooserPayRollBegin.setDateFormatString("MM/dd/yyyy");
		add(dateChooserPayRollBegin);
		
		JDateChooser dateChooserPayRollEnd = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, lblBeginningDate, 0, SpringLayout.WEST, dateChooserPayRollEnd);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserPayRollEnd, 91, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserPayRollEnd, 27, SpringLayout.WEST, this);
		dateChooserPayRollEnd.setDateFormatString("MM/dd/yyyy");
		add(dateChooserPayRollEnd);
		
		JLabel lblNewLabel = new JLabel("HOURS WORKED PANEL");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 100, SpringLayout.WEST, this);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblNewLabel);
		
		JLabel lblMinHours = new JLabel("MIN HOURS");
		springLayout.putConstraint(SpringLayout.WEST, lblMinHours, 43, SpringLayout.EAST, lblEndingDate);
		add(lblMinHours);
		
		JLabel lblMinHours_1 = new JLabel("MAX HOURS");
		springLayout.putConstraint(SpringLayout.WEST, lblMinHours_1, 16, SpringLayout.EAST, lblMinHours);
		add(lblMinHours_1);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblMinHours, -2, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, dateChooserPayRollBegin);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblMinHours);
		add(textField);
		textField.setColumns(7);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblMinHours_1, -2, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, dateChooserPayRollBegin);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, lblMinHours_1);
		textField_1.setColumns(7);
		add(textField_1);
		
		JButton btnNewButton = new JButton("RESULTS");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 23, SpringLayout.SOUTH, dateChooserPayRollBegin);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -164, SpringLayout.EAST, this);
		add(btnNewButton);

	}
}
