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

class DataSpareParts {
	static void popupAddSparePart() {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextFieldOne supp = new JTextFieldOne();
			JTextFieldOne doc = new JTextFieldOne();
			JTextFieldOne part = new JTextFieldOne();
			JComboBox<String> used = new JComboBox<String>(new String[]{"Not Used", "Used"});
			JTextPaneOne comment = new JTextPaneOne();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Date: "));
				panel.add(date);
				panel.add(new JLabel("Supplier/Service Provider: "));
				panel.add(supp);
				panel.add(new JLabel("Document No.: "));
				panel.add(doc);
				panel.add(new JLabel("Part"));
				panel.add(part);
				panel.add(new JLabel("Used: "));
				panel.add(used);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Add To Log", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				try {
					if (result == JOptionPane.OK_OPTION) {
						if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 && part.getText().length() > 0) {
							Connection conn = Data.dbConn();
							Statement stmt = conn.createStatement();
							stmt.executeUpdate(DataQueries.partAdd((String)carName.getSelectedItem(), date.getText(), supp.getText(), doc.getText(), part.getText(), (String)used.getSelectedItem(), comment.getText()));
							stmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, "Successfully added spare part", "Successful...", JOptionPane.INFORMATION_MESSAGE);
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name, Date and Part", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
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
	
	static void popupEditSparePart(String id) {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextFieldOne supp = new JTextFieldOne();
			JTextFieldOne doc = new JTextFieldOne();
			JTextFieldOne part = new JTextFieldOne();
			JComboBox<String> used = new JComboBox<String>(new String[]{"Not Used", "Used"});
			JTextPaneOne comment = new JTextPaneOne();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			Connection conn0 = Data.dbConn();
			Statement stmt0 = conn0.createStatement();
			ResultSet rs0 = stmt0.executeQuery(DataQueries.partEditQuery(id));
			while (rs0.next()) {
				carName.setSelectedItem(rs0.getString("carName"));
				date.setText(rs0.getString("date"));
				doc.setText(rs0.getString("docNo"));
				part.setText(rs0.getString("part"));
				used.setSelectedItem(rs0.getString("used"));
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
				panel.add(new JLabel("Supplier/Service Provider: "));
				panel.add(supp);
				panel.add(new JLabel("Document No.: "));
				panel.add(doc);
				panel.add(new JLabel("Part: "));
				panel.add(part);
				panel.add(new JLabel("Used: "));
				panel.add(used);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Edit Spare Part", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 && part.getText().length() > 0) {
						Connection conn = Data.dbConn();
						Statement stmt = conn.createStatement();
						stmt.executeUpdate(DataQueries.partEditUpdate(id, (String)carName.getSelectedItem(), date.getText(), supp.getText(), doc.getText(), part.getText(), (String)used.getSelectedItem(), comment.getText()));
						stmt.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "Spare part updated successfully", "Successful...", JOptionPane.INFORMATION_MESSAGE);
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name, Date and Part", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
					}
				} 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L002\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
}
