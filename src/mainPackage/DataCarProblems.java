package mainPackage;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

class DataCarProblems {
	static void popupAddCarProblem() {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextField km = new JTextField();
			JTextField probName = new JTextField();
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
				panel.add(new JLabel("Problem Name: "));
				panel.add(probName);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Add Car Problem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				try {
					if (result == JOptionPane.OK_OPTION) {
						if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10) {
							Connection conn = Data.dbConn();
							Statement stmt = conn.createStatement();
							stmt.executeUpdate(DataQueries.problemAdd((String)carName.getSelectedItem(), date.getText(), km.getText(), probName.getText(), comment.getText()));
							stmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, "Successfully added car problem");
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name and Date");
						}
					}
				} catch (SQLException a) {
					result = 10;
					JOptionPane.showMessageDialog(null, "Please ensure that you have captured and integer value in the km Reading field");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L001\n"+e);
		}
	}
	
	static void popupEditCarProblem(String id) {
		try {
			int result = 10;
			JComboBox carName = new JComboBox(DataCars.combo());
			DatePickerSettings date1 = new DatePickerSettings();
			date1.setFormatForDatesCommonEra("yyyy-MM-dd");
			date1.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
			DatePicker date = new DatePicker(date1);
			JTextField km = new JTextField();
			JComboBox<String> fixed = new JComboBox<String>(new String[]{"Not Fixed", "Fixed"});
			JTextField probName = new JTextField();
			JTextPane comment = new JTextPane();
			JScrollPane commentScroll = new JScrollPane(comment);
			
			Connection conn0 = Data.dbConn();
			Statement stmt0 = conn0.createStatement();
			ResultSet rs0 = stmt0.executeQuery(DataQueries.problemEditQuery(id));
			while (rs0.next()) {
				carName.setSelectedItem(rs0.getString("carName"));
				date.setText(rs0.getString("date"));
				km.setText(rs0.getString("kmReading"));
				fixed.setSelectedItem(rs0.getString("fixed"));
				probName.setText(rs0.getString("probName"));
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
				panel.add(new JLabel("Fixed: "));
				panel.add(fixed);
				panel.add(new JLabel("Problem Name: "));
				panel.add(probName);
				
				JPanel panelComment = new JPanel(new GridLayout(1,1));
				panelComment.add(commentScroll);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Details", null, panel, "The details");
				tabbedPane.addTab("Comment", null, panelComment, "The comment");
				
				result = JOptionPane.showConfirmDialog(null, tabbedPane, "Edit Car Problem", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (!"--Select--".equals(carName.getSelectedItem()) && date.getText().length() == 10 ) {
						Connection conn = Data.dbConn();
						Statement stmt = conn.createStatement();
						stmt.executeUpdate(DataQueries.problemEditUpdate(id, (String)carName.getSelectedItem(), date.getText(), km.getText(), (String)fixed.getSelectedItem(), probName.getText(), comment.getText()));
						stmt.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "Car problem updated successfully");
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name and Date");
					}
				} 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: L002\n"+e);
		}
	}
}
