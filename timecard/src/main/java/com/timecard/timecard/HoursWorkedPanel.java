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
import java.awt.Dimension;

import javax.swing.JTextField;

public class HoursWorkedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_MinHours;
	private JTextField textField_MaxHours;
	private static MySQLConnect mydatabase;
	private JDateChooser dateChooserEndDate; 
	private JDateChooser dateChooserBeginDate; 
	private Boolean dStatus;
	/**
	 * Create the panel.
	 */
	public HoursWorkedPanel(final MySQLConnect mydatabase, Boolean dStatus) {
		this.mydatabase = mydatabase;
		this.dStatus = dStatus;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setPreferredSize(new Dimension(450, 178)); // (Width, Height)

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
					for ( int i =0; i < list.size(); i++) {
						System.out.println( list.get(i).toString());
					}

				} 
				if (dStatus) {
					mydatabase.clearList();
				}         }

		});

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MySQLConnect mydatabase = null;
		String PAYROLL_HOURS_DATABASE_TABLE = "dailyhours";
		//Boolean dStatus;
		Boolean dStatus;

		/* 273 */     String credentialsFilename = "mysqlsignonstuff.txt";
		/* 274 */     String DELIMITER = "%";
		/* 275 */     String[] myDatastuff = getCredentialsFromFile("mysqlsignonstuff.txt").split("%");
		/*     */     
		/* 277 */     mydatabase = new MySQLConnect(myDatastuff[0], myDatastuff[1], myDatastuff[2]);
		/*     */     dStatus = mydatabase.isConnected();
		/* 279 */     if (dStatus) {
			/* 280 */       JOptionPane.showMessageDialog(null, "Database Connected");
			/* 281 */    HoursWorkedPanel hrPanel = new HoursWorkedPanel(mydatabase, dStatus);
			JFrame myFrame = new JFrame();
			myFrame.setResizable(true);
			myFrame.setTitle("My Query Results");
			//myFrame.getContentPane().setLayout(new BorderLayout());
			myFrame.setSize(475,250);
			myFrame.getContentPane().add(hrPanel);
			myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			myFrame.setVisible(true);
		/*     */     } else {

			/* 283 */     JOptionPane.showMessageDialog(null, "Trouble Connecting to Database");
			/* 284 */  
		/*     */   }
		/*     */   

	}



	private static String getCredentialsFromFile(String inputFile) {
		/* 355 */     int myMagicNumber = 36;
		/* 356 */     String allData = null;
		/* 357 */     int count = 1;
		/* 358 */     BufferedReader bufferedReader = null;
		/*     */     
		/*     */     try {
			/* 361 */       bufferedReader = new BufferedReader(new FileReader(inputFile));
			/*     */       try {
				/*     */         String data;
				/* 364 */         while ((data = bufferedReader.readLine()) != null) {
					/* 365 */           if (count == 36) {
						/* 366 */             allData = data;
					/*     */           }
					/* 368 */           count++;
				/*     */         } 
				/* 370 */         if (count < 36) {
					/* 371 */           JOptionPane.showMessageDialog(null, "TimeCardSU: Credentials can not be found.");
				/*     */         }
			/* 373 */       } catch (IOException e) {
				/*     */         
				/* 375 */         e.printStackTrace();
				/* 376 */         JOptionPane.showMessageDialog(null, "TimeCardSU: HomeMainGUI: Can not read  from file ");
			/*     */       } 
		/* 378 */     } catch (FileNotFoundException e) {
			/*     */       String data;
			/* 380 */       JOptionPane.showMessageDialog(null, 
					/* 381 */           "TimeCardSU : Can not find MYSQL database \ncredential file. Program terminated ");
			/* 382 */       e.printStackTrace();
		/*     */     } finally {
			/*     */       try {
				/* 385 */         bufferedReader.close();
			/* 386 */       } catch (IOException e) {
				/*     */         
				/* 388 */         JOptionPane.showMessageDialog(null, "TimeCardSU :Error Closing The File" + e);
				/* 389 */         e.printStackTrace();
			/*     */       } 
		/*     */     } 
		/*     */     
		/* 393 */     return allData;
	/*     */   }
}
