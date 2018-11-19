package com.github.pieter_duplessis.carlogger;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.pieter_duplessis.carlogger.java.JTextFieldOne;
import com.github.pieter_duplessis.carlogger.java.JTextPaneOne;


public class DataLog {
	static void popupAddToLog() {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextFieldOne km = new JTextFieldOne();
			JTextFieldOne supp	= new JTextFieldOne();
			JTextFieldOne doc = new JTextFieldOne();
			JComboBox event = new JComboBox(DataEvents.combo());
			JTextPaneOne comment = new JTextPaneOne();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Date: "));
				panel.add(date);
				panel.add(new JLabel("km Reading: "));
				panel.add(km);
				panel.add(new JLabel("Supplier/Service Provider: "));
				panel.add(supp);
				panel.add(new JLabel("Document No.: "));
				panel.add(doc);
				panel.add(new JLabel("Type Event: "));
				panel.add(event);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Add To Log", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				try {
					if (result == JOptionPane.OK_OPTION) {
						if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 && !"--Select--".equals(event.getSelectedItem())) {
							Connection conn = Data.dbConn();
							Statement stmt = conn.createStatement();
							
																																	
							stmt.executeUpdate(DataQueries.logAdd((String)carName.getSelectedItem(), date.getText(), km.getText(), supp.getText(), doc.getText(), (String)event.getSelectedItem(), comment.getText()));
							stmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, "Successfully added to log", "Successful...", JOptionPane.INFORMATION_MESSAGE);
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name, Date and Type Event", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (SQLException a) {
					result = 10;
					JOptionPane.showMessageDialog(null, "Please ensure that you have captured and integer value in the km Reading field", "Characters out of place...", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L001\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void popupEditLogEntry(String id) {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextFieldOne km = new JTextFieldOne();
			JTextFieldOne supp = new JTextFieldOne();
			JTextFieldOne doc = new JTextFieldOne();
			JComboBox event = new JComboBox(DataEvents.combo());
			JTextPaneOne comment = new JTextPaneOne();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			Connection conn0 = Data.dbConn();
			Statement stmt0 = conn0.createStatement();
			ResultSet rs0 = stmt0.executeQuery(DataQueries.logEditQuery(id));
			while (rs0.next()) {
				carName.setSelectedItem(rs0.getString("carName"));
				date.setText(rs0.getString("date"));
				km.setText(rs0.getString("kmReading"));
				doc.setText(rs0.getString("docNo"));
				event.setSelectedItem(rs0.getString("typeEvent"));
				comment.setText(rs0.getString("comment"));
			}
			rs0.close();
			stmt0.close();
			conn0.close();
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Date: "));
				panel.add(date);
				panel.add(new JLabel("km Reading: "));
				panel.add(km);
				panel.add(new JLabel("Supplier/Service Provider: "));
				panel.add(supp);
				panel.add(new JLabel("Document No.: "));
				panel.add(doc);
				panel.add(new JLabel("Type Event: "));
				panel.add(event);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Edit Log Entry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 && !"--Select--".equals(event.getSelectedItem())) {
						Connection conn = Data.dbConn();
						Statement stmt = conn.createStatement();
						stmt.executeUpdate(DataQueries.logEditUpdate(id, (String)carName.getSelectedItem(), date.getText(), km.getText(), supp.getText(), doc.getText(), (String)event.getSelectedItem(), comment.getText()));
						stmt.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "Log entry updated successfully", "Successful...", JOptionPane.INFORMATION_MESSAGE);
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name, Date and Type Event", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
					}
				} 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L002\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
}
