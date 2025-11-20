package com.timecard.timecard;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class HoursWorkedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_MinHours;
	private JTextField textField_MaxHours;
	/**
	 * Create the panel.
	 */
	public HoursWorkedPanel(final MySQLConnect mydatabase, Boolean dStatus) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setPreferredSize(new Dimension(450, 178)); // (Width, Height)
	    setBackground(Color.PINK);

		JLabel lblBeginDate = new JLabel("BEGIN DATE");
		add(lblBeginDate);

		JLabel lblEndDate = new JLabel("END DATE");
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 50, SpringLayout.EAST, lblBeginDate);
		springLayout.putConstraint(SpringLayout.NORTH, lblBeginDate, 0, SpringLayout.NORTH, lblEndDate);
		add(lblEndDate);

		JDateChooser dateChooserEndDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndDate, -2, SpringLayout.NORTH, dateChooserEndDate);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserEndDate, 91, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserEndDate, 150, SpringLayout.WEST, this);
		dateChooserEndDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserEndDate);

		JDateChooser dateChooserBeginDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, lblBeginDate, 0, SpringLayout.WEST, dateChooserBeginDate);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserBeginDate, 91, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserBeginDate, 27, SpringLayout.WEST, this);
		dateChooserBeginDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserBeginDate);

		JLabel lblNewLabel = new JLabel("HOURS WORKED PANEL");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 100, SpringLayout.WEST, this);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblNewLabel);

		JLabel lblMinHours = new JLabel("MIN HOURS");
		springLayout.putConstraint(SpringLayout.WEST, lblMinHours, 43, SpringLayout.EAST, lblEndDate);
		add(lblMinHours);

		JLabel lblMinHours_1 = new JLabel("MAX HOURS");
		springLayout.putConstraint(SpringLayout.WEST, lblMinHours_1, 16, SpringLayout.EAST, lblMinHours);
		add(lblMinHours_1);

		textField_MinHours = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblMinHours, -2, SpringLayout.NORTH, textField_MinHours);
		springLayout.putConstraint(SpringLayout.NORTH, textField_MinHours, 0, SpringLayout.NORTH, dateChooserEndDate);
		springLayout.putConstraint(SpringLayout.WEST, textField_MinHours, 0, SpringLayout.WEST, lblMinHours);
		add(textField_MinHours);
		textField_MinHours.setColumns(7);

		textField_MaxHours = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblMinHours_1, -2, SpringLayout.NORTH, textField_MaxHours);
		springLayout.putConstraint(SpringLayout.NORTH, textField_MaxHours, 0, SpringLayout.NORTH, dateChooserEndDate);
		springLayout.putConstraint(SpringLayout.WEST, textField_MaxHours, 0, SpringLayout.WEST, lblMinHours_1);
		textField_MaxHours.setColumns(7);
		add(textField_MaxHours);

		JButton btnResults = new JButton("RESULTS");
		springLayout.putConstraint(SpringLayout.NORTH, btnResults, 23, SpringLayout.SOUTH, dateChooserEndDate);
		springLayout.putConstraint(SpringLayout.EAST, btnResults, -164, SpringLayout.EAST, this);
		add(btnResults);

		btnResults.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				Date dateStartBegin = dateChooserBeginDate.getDate();
				Date dateStartEnd = dateChooserEndDate.getDate();
				String beginDateString = TimePanel.getDateString(dateStartBegin);
				String endDateString = TimePanel.getDateString(dateStartEnd);
				float minHours = Float.parseFloat(textField_MinHours.getText());
				float maxHours = Float.parseFloat(textField_MaxHours.getText());

				List<DailyInfoModel> list = null;


				if (!dStatus) {
					list = PayrollQueryPanel.getDataFromFile(beginDateString, endDateString);
					System.out.println("Size of list is : " + list.size());

				}
				else {
					String result = "SELECT * FROM dailyhours WHERE  DATE >= '" + beginDateString + "' AND DATE <= '" + endDateString + "' AND REGHOURS >=  " + 
							minHours +  "AND REGHOURS <=  " + maxHours + " ORDER BY DATE DESC ";


					mydatabase.getDateRangeResults(result);
					list = mydatabase.getList();
				} 

				if (list.size() == 0) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, 
							"No Data Available Between Date Range. Verify that the Beginning and Ending Dates are accurate", 
							"Data Error", 
							0);
				} else {

					//String title = "Daily Regular Worked Hours  " + minHours + " and "  +  maxHours;
					String title = "Pay Period for " + beginDateString + " to " + endDateString;
					new MyTable(list, title);//produces the output table
				    //getPayrollHours(list);
				} 
				if (dStatus) {
					mydatabase.clearList();
				}         }

		});

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}



	
}
