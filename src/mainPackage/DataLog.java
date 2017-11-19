package mainPackage;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class DataLog {
	static void popupAddToLog() {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextField km = new JTextField();
			JTextField doc = new JTextField();
			JComboBox event = new JComboBox(DataEvents.combo());
			JTextPane comment = new JTextPane();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Date: "));
				panel.add(date);
				panel.add(new JLabel("km Reading: "));
				panel.add(km);
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
				
				if (result == JOptionPane.OK_OPTION) {
					if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 && !"--Select--".equals(event.getSelectedItem())) {
						Statement stmt = Data.dbStmt();
						stmt.executeUpdate("INSERT INTO log(carName, date, kmReading, docNo, typeEvent, comment) VALUES ('"+carName.getSelectedItem()+"', '"+date.getText()+"', '"+km.getText()+"', '"+doc.getText()+"', '"+event.getSelectedItem()+"', '"+comment.getText()+"') ");
						stmt.close();
						JOptionPane.showMessageDialog(null, "Successfully added to log");
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name, Date and Type Event");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L001\n"+e);
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
			JTextField km = new JTextField();
			JTextField doc = new JTextField();
			JComboBox event = new JComboBox(DataEvents.combo());
			JTextPane comment = new JTextPane();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			Statement stmt0 = Data.dbStmt();
			ResultSet rs0 = stmt0.executeQuery("SELECT carName, date, kmReading, docNo, typeEvent, comment FROM log WHERE id = '"+id+"';");
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
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Date: "));
				panel.add(date);
				panel.add(new JLabel("km Reading: "));
				panel.add(km);
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
						Statement stmt = Data.dbStmt();
						stmt.executeUpdate("UPDATE log SET carName = '"+carName.getSelectedItem()+"', date = '"+date.getText()+"', kmReading = '"+km.getText()+"', docNo = '"+doc.getText()+"', typeEvent = '"+event.getSelectedItem()+"', comment = '"+comment.getText()+"' WHERE id = '"+id+"';");
						stmt.close();
						JOptionPane.showMessageDialog(null, "Log entry updated successfully");
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name, Date and Type Event");
					}
				} 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L002\n"+e);
		}
	}
}
