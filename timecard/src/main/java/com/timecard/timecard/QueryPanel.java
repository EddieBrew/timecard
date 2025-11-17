package com.timecard.timecard;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class QueryPanel extends JPanel{

	/**
	 * Create the panel.
	 */

	JLabel lblFieldName, lblItemName, lblMessage;
	//JList FieldName_list, EventName_list, DayName_list, EventNumber_list;
	JButton btnClickForQuery;
	JComboBox<Object> comboBoxITEMNAME;
	JComboBox<Object> comboBoxFIELDNAME;
	String eventNames[] = {"Bing", "Football", "Foothills", "MemorialChurch","MenBB", "PowWow", "SEPAnnualTraining", 
			"TourBus", "TreeHack", "Sick", "CallOff", "VadenPharmacy", "WBSecurity", "WomenBB"};
	String[] dayNames = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
	String eventNumbers[] = {"12029", "12032", "12036"};

	public QueryPanel() {

		setupPanel();

	}


	public void setupPanel() {
		setBackground(Color.RED);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblFieldName = new JLabel("SELECT FIELD");
		lblFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblFieldName);




		lblItemName = new JLabel("SELECT ITEM");
		springLayout.putConstraint(SpringLayout.WEST, lblItemName, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblFieldName, 29, SpringLayout.SOUTH, lblItemName);
		springLayout.putConstraint(SpringLayout.WEST, lblFieldName, 0, SpringLayout.WEST, lblItemName);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblItemName);



		btnClickForQuery = new JButton("Click For Query Result");
		btnClickForQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> sqlColumnHashMap = new HashMap<>();
				sqlColumnHashMap.put("Day", "DAY_ID");
				sqlColumnHashMap.put("EventName", "EVENT_NAME");
				sqlColumnHashMap.put("EventNumber", "EVENT_NUMBER");
				sqlColumnHashMap.put("RegHours", "EVENT_REGHOURS");
				sqlColumnHashMap.put("OtHours", "EVENT_OTHOURS");


				String columnName = (String) comboBoxITEMNAME.getSelectedItem();
				String valueName= (String) comboBoxFIELDNAME.getSelectedItem();


				List<DailyInfoModel> listReturn ;
				
				/*
				MyAWSConnection mystuff = new MyAWSConnection();

				mystuff.getQueryResultsFromDatabase(sqlColumnHashMap.get(columnName), valueName);
				listReturn = mystuff.getList();
				System.out.println("Size = " + listReturn.size());
				MyQueryTablePanel tablePanel = new MyQueryTablePanel(listReturn, "My First Table");//create Table
				*/


			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnClickForQuery, 28, SpringLayout.SOUTH, lblFieldName);
		springLayout.putConstraint(SpringLayout.WEST, btnClickForQuery, 48, SpringLayout.WEST, this);

		add(btnClickForQuery);

		lblMessage = new JLabel("Message....");
		springLayout.putConstraint(SpringLayout.NORTH, lblMessage, 22, SpringLayout.SOUTH, btnClickForQuery);
		springLayout.putConstraint(SpringLayout.WEST, lblMessage, 10, SpringLayout.WEST, this);

		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//add(lblMessage);

		JLabel lblQueryRequest = new JLabel("QUERY REQUEST");
		springLayout.putConstraint(SpringLayout.NORTH, lblItemName, 28, SpringLayout.SOUTH, lblQueryRequest);
		springLayout.putConstraint(SpringLayout.NORTH, lblQueryRequest, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblQueryRequest, 0, SpringLayout.WEST, lblFieldName);
		lblQueryRequest.setFont(new Font("Tahoma", Font.PLAIN, 23));
		add(lblQueryRequest);

		comboBoxITEMNAME = new JComboBox<Object>();
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxITEMNAME, 0, SpringLayout.NORTH, lblItemName);
		comboBoxITEMNAME.setModel(new DefaultComboBoxModel<Object>(new String[] {"Day", "EventName", "EventNumber","Date", "RegHours", "OtHours"}));
		comboBoxITEMNAME.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxITEMNAME.setEditable(true);
		comboBoxITEMNAME.setToolTipText("");
		comboBoxITEMNAME.setPreferredSize(new Dimension(160, 20));
		comboBoxITEMNAME.setSize(200, (int) comboBoxITEMNAME.getPreferredSize().getHeight());
		//comboBoxITEMNAME.setMaximumSize( comboBoxITEMNAME.getPreferredSize() );
		add(comboBoxITEMNAME);

		comboBoxFIELDNAME = new JComboBox<Object>();
		springLayout.putConstraint(SpringLayout.WEST, comboBoxITEMNAME, 0, SpringLayout.WEST, comboBoxFIELDNAME);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxITEMNAME, 0, SpringLayout.EAST, comboBoxFIELDNAME);
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxFIELDNAME, 0, SpringLayout.NORTH, lblFieldName);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxFIELDNAME, 6, SpringLayout.EAST, lblFieldName);
		comboBoxFIELDNAME.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxFIELDNAME.setPreferredSize(new Dimension(160, 20));
		add(comboBoxFIELDNAME);

		comboBoxFIELDNAME.setModel(new DefaultComboBoxModel<Object>( dayNames));
		comboBoxFIELDNAME.setEditable(false);
		comboBoxITEMNAME.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub


				switch( comboBoxITEMNAME.getSelectedIndex()) {
				case 0:	comboBoxFIELDNAME.setModel(new DefaultComboBoxModel<Object>( dayNames));
				comboBoxFIELDNAME.setEditable(false);
				break;
				case 1:	comboBoxFIELDNAME.setModel(new DefaultComboBoxModel<Object>( eventNames));
				comboBoxFIELDNAME.setEditable(false);
				break;	
				case 2:comboBoxFIELDNAME.setModel(new DefaultComboBoxModel<Object>( eventNumbers));	
				comboBoxFIELDNAME.setEditable(false);
				break;
				default:comboBoxFIELDNAME.setModel(new DefaultComboBoxModel<Object>());
				comboBoxFIELDNAME.setEditable(true);
				}
			}

		});
		System.out.println("INDEX = " +  comboBoxITEMNAME.getSelectedIndex() );


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		QueryPanel queryPanel = new QueryPanel();


		JFrame myFrame = new JFrame();
		myFrame.setResizable(true);
		myFrame.setTitle("My Query Results");
		myFrame.getContentPane().setLayout(new BorderLayout());
		myFrame.setSize(320,250);
		myFrame.getContentPane().add(queryPanel);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);

	}

}
