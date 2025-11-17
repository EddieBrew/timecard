package com.timecard.timecard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class QueryFrame extends JFrame{

	private  JFrame myFrame;
	private JPanel contentPane;
	private QueryPanel myQueryPanel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QueryFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setupFrame();
	}
	
	public void setupFrame() {
		myFrame = new JFrame();//instantiate Frame
		myQueryPanel = new QueryPanel(); // instantiate panel
		
		//set Frame values
		myFrame.setResizable(false);
		myFrame.setTitle("My QUERY REQUESTS ");
		myFrame.getContentPane().setLayout(new GridLayout(2,1));
		myFrame.setSize(420,);
		myFrame.getContentPane().add(myQueryPanel);

	
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryFrame frame = new QueryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
