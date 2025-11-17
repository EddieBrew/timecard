package com.timecard.timecard;

import java.awt.EventQueue;

public class App {
  public static void main(String[] args) {
	  
  	
  	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					 new TimeCardSU();
					//window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
  	
  }
}
