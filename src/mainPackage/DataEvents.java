package mainPackage;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataEvents {
	static void popupEventTypeAdd() {
		try {
			int result = 10;
			JTextField event = new JTextField();
			JTextField descr = new JTextField();
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(2,2));
				panel.add(new JLabel("Type Event: "));
				panel.add(event);
				panel.add(new JLabel("Description: "));
				panel.add(descr);
				
				result = JOptionPane.showConfirmDialog(null, panel, "Add Type Event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (event.getText().length() > 0) {
						Boolean flag = doesEventAlreadyExist(event.getText());
						if (!flag) {
							Connection conn = Data.dbConn();
							Statement stmt = conn.createStatement();
							stmt.executeUpdate(DataQueries.eventAdd(event.getText(), descr.getText()));
							stmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, "Event type added successfully");
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Event already exists.");
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nType Event");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E001\n"+e);
		}
	}
	
	static void popupEventTypeEdit(String id) {
		try {
			int result = 10;
			JTextField event = new JTextField();
			JTextField descr = new JTextField();
			event.setColumns(20);
			descr.setColumns(20);
			
			Connection conn0 = Data.dbConn();
			Statement stmt0 = conn0.createStatement();
			ResultSet rs0 = stmt0.executeQuery(DataQueries.eventEditQuery(id));
			while (rs0.next()) {
				event.setText(rs0.getString("typeEvent"));
				descr.setText(rs0.getString("typeDescr"));
			}
			rs0.close();
			stmt0.close();
			conn0.close();
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(3,2));
				panel.add(new JLabel("ID: "));
				panel.add(new JLabel(id));
				panel.add(new JLabel("Type Event: "));
				panel.add(event);
				panel.add(new JLabel("Description: "));
				panel.add(descr);
				
				result = JOptionPane.showConfirmDialog(null, panel, "Edit Type Event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (event.getText().length() > 0) {
						Boolean flag = doesEventAlreadyExistOnEdit(event.getText(), id);
						if (!flag) {
							Connection conn = Data.dbConn();
							Statement stmt = conn.createStatement();
							stmt.executeUpdate(DataQueries.eventEditUpdate(id, event.getText(), descr.getText()));
							stmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, "Event type updated successfully");
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Event already exists");
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nType Event");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E002\n"+e);
		}
	}
	
	static void popupEventTypeRemove() {
		try {
			JComboBox event = new JComboBox(combo());
			
			JPanel panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("Event type to remove: "));
			panel.add(event);
			
			int result = JOptionPane.showConfirmDialog(null, panel, "Remove Event Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				if (event.getSelectedItem().equals("--Select--")) {
					JOptionPane.showMessageDialog(null, "Nothing selected\nAction cancelled");
				} else {
					Connection conn = Data.dbConn();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(DataQueries.eventRemove((String)event.getSelectedItem()));
					stmt.close();
					conn.close();
					JOptionPane.showMessageDialog(null, "Event removed successfully");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E003\n"+e);
		}
	}
	
	static String[] combo() {
		ArrayList<String> event1 = new ArrayList<String>();
		try {
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			String query = DataQueries.eventCombo();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String event = rs.getString("typeEvent");
				event1.add(event);
			}
			event1.add("--Select--");
			Collections.sort(event1);
			rs.close();
			stmt.close();
			conn.close();
			
			String[] event2 = event1.toArray(new String[event1.size()]);
			return event2;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E004\n"+e);
			return null;
		}
	}
	
	static Boolean doesEventAlreadyExist(String newEventName) {
		try {
			Boolean flag = false;
			
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataQueries.eventAlreadyExistNew());
			
			while (rs.next()) {
				if (newEventName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E005\n"+e);
			return false;
		}
	}
	
	static Boolean doesEventAlreadyExistOnEdit(String newEventName, String id) {
		try {
			Boolean flag = false;
			
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataQueries.eventAlreadyExistUpdate(id));
			
			while (rs.next()) {
				if (newEventName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E005\n"+e);
			return false;
		}
	}
}
