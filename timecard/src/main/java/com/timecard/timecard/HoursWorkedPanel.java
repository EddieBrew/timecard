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
import java.util.ArrayList;
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
import java.awt.EventQueue;

import javax.swing.JTextField;
import java.awt.FlowLayout;

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
		//setPreferredSize(new Dimension(450, 178)); // (Width, Height)
		setBackground(Color.PINK);
        /*
		JLabel lblBeginDate = new JLabel("BEGIN DATE");
		springLayout.putConstraint(SpringLayout.NORTH, lblBeginDate, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblBeginDate, 10, SpringLayout.WEST, this);
		add(lblBeginDate);
*/
		
		JLabel lblBeginDate = new JLabel("BEGIN DATE");
		springLayout.putConstraint(SpringLayout.WEST, lblBeginDate, 27, SpringLayout.WEST, this);
		add(lblBeginDate);
		
		/*
		
		JLabel lblEndDate = new JLabel("END DATE");
		springLayout.putConstraint(SpringLayout.NORTH, lblEndDate, 0, SpringLayout.NORTH, lblBeginDate);
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 56, SpringLayout.EAST, lblBeginDate);
		add(lblEndDate);
*/
		
		JLabel lblEndDate = new JLabel("END DATE");
		springLayout.putConstraint(SpringLayout.WEST, lblEndDate, 50, SpringLayout.EAST, lblBeginDate);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndDate, -89, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblBeginDate, 0, SpringLayout.NORTH, lblEndDate);
		add(lblEndDate);
		
		/*
		JDateChooser dateChooserEndDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserEndDate, 5, SpringLayout.SOUTH, lblEndDate);
		springLayout.putConstraint(SpringLayout.SOUTH, dateChooserEndDate, 24, SpringLayout.SOUTH, lblEndDate);
		dateChooserEndDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserEndDate);

		JDateChooser dateChooserBeginDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateChooserEndDate, -20, SpringLayout.EAST, dateChooserBeginDate);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserBeginDate, 6, SpringLayout.SOUTH, lblBeginDate);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserBeginDate, 0, SpringLayout.WEST, lblBeginDate);
		springLayout.putConstraint(SpringLayout.EAST, dateChooserBeginDate, 115, SpringLayout.WEST, this);
		dateChooserBeginDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserBeginDate);


*/
		JDateChooser dateChooserEndDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserEndDate, 6, SpringLayout.SOUTH, lblEndDate);
		dateChooserEndDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserEndDate);

		JDateChooser dateChooserBeginDate = new JDateChooser();
		springLayout.putConstraint(SpringLayout.EAST, dateChooserEndDate, 103, SpringLayout.EAST, dateChooserBeginDate);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooserBeginDate, 6, SpringLayout.SOUTH, lblBeginDate);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserEndDate, 6, SpringLayout.EAST, dateChooserBeginDate);
		springLayout.putConstraint(SpringLayout.WEST, dateChooserBeginDate, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, dateChooserBeginDate, 115, SpringLayout.WEST, this);
		dateChooserBeginDate.setDateFormatString("MM/dd/yyyy");
		add(dateChooserBeginDate);

		JLabel lblNewLabel = new JLabel("HOURS WORKED PANEL");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 100, SpringLayout.WEST, this);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblNewLabel);

		JLabel lblMinHours = new JLabel("MIN HOURS");
		springLayout.putConstraint(SpringLayout.NORTH, lblMinHours, 0, SpringLayout.NORTH, lblBeginDate);
		add(lblMinHours);

		JLabel lblMaxHours = new JLabel("MAX HOURS");
		springLayout.putConstraint(SpringLayout.NORTH, lblMaxHours, 0, SpringLayout.NORTH, lblBeginDate);
		springLayout.putConstraint(SpringLayout.EAST, lblMaxHours, 100, SpringLayout.EAST, lblMinHours);
		add(lblMaxHours);

		textField_MinHours = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_MinHours, 6, SpringLayout.SOUTH, lblMinHours);
		springLayout.putConstraint(SpringLayout.WEST, textField_MinHours, 24, SpringLayout.EAST, dateChooserEndDate);
		springLayout.putConstraint(SpringLayout.WEST, lblMinHours, 0, SpringLayout.WEST, textField_MinHours);
		add(textField_MinHours);
		textField_MinHours.setColumns(7);

		textField_MaxHours = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, textField_MinHours, -29, SpringLayout.WEST, textField_MaxHours);
		springLayout.putConstraint(SpringLayout.NORTH, textField_MaxHours, 6, SpringLayout.SOUTH, lblMaxHours);
		springLayout.putConstraint(SpringLayout.WEST, textField_MaxHours, 346, SpringLayout.WEST, this);
		//springLayout.putConstraint(SpringLayout.EAST, textField_MaxHours, -26, SpringLayout.EAST, this);
		textField_MaxHours.setColumns(7);
		add(textField_MaxHours);

		JButton btnResults = new JButton("RESULTS");
		springLayout.putConstraint(SpringLayout.SOUTH, textField_MinHours, -27, SpringLayout.NORTH, btnResults);
		springLayout.putConstraint(SpringLayout.WEST, btnResults, 175, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnResults, -10, SpringLayout.SOUTH, this);
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


				if (!dStatus) { //Query from the csv file
					list = PayrollQueryPanel.getDataFromFile(beginDateString, endDateString);
					System.out.println("Size of list is : " + list.size());
					List<DailyInfoModel> hoursWorked = new ArrayList<>();
					for (DailyInfoModel obj : list){

						if (obj.getRhours()  >=  minHours  && obj.getRhours() <= maxHours) {
							System.out.println( minHours  + ", " + obj.getRhours() + ", " + maxHours);

							hoursWorked.add(obj);
						}
					}

					if (hoursWorked.size() == 0) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, 
								"No Data Available Between Min and Max Worked Hour  Range. ", 
								"Data Error", 
								0);
					}

					String title = "Daily Regular  Worked From " + beginDateString + " to " + endDateString;
					new MyTable(hoursWorked, title);//produces the output table 

				}
				else {//Query from the MYSQL database
					String result = "SELECT * FROM dailyhours WHERE  DATE >= '" + beginDateString + "' AND DATE <= '" + endDateString + "' AND REGHOURS >=  " + 
							minHours +  "AND REGHOURS <=  " + maxHours + " ORDER BY DATE DESC ";


					mydatabase.getDateRangeResults(result);
					list = mydatabase.getList();
					if (list.size() == 0) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, 
								"No Data Available Between Min and Max Worked Hour  Range. ", 
								"Data Error", 
								0);

					} else {

						//String title = "Daily Regular Worked Hours  " + minHours + " and "  +  maxHours;
						String title = "Daily Regular Hours Worked From " + beginDateString + " to " + endDateString;
						new MyTable(list, title);//produces the output table
						//getPayrollHours(list);
					} 
					mydatabase.clearList();					        

				} //end else

			}

		});

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
        HoursWorkedPanel hoursworkedpanel = new HoursWorkedPanel(null, null);
		JFrame myFrame = new JFrame();
		myFrame.setResizable(true);
		myFrame.setTitle("Queried Hours");
		myFrame.getContentPane().setLayout(new BorderLayout());
		myFrame.setSize(500,250);
		myFrame.getContentPane().add(hoursworkedpanel);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);

			  

	}


}
