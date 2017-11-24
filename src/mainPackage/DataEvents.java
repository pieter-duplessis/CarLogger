package mainPackage;

import java.awt.GridLayout;
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
							Statement stmt = Data.dbStmt();
							stmt.executeUpdate("INSERT INTO typeevent(typeEvent, typeDescr, active) VALUES ('"+event.getText()+"', '"+descr.getText()+"', 'Yes');");
							stmt.close();
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
			
			Statement stmt0 = Data.dbStmt();
			ResultSet rs0 = stmt0.executeQuery("SELECT typeEvent, typeDescr FROM typeevent WHERE id = '"+id+"';");
			while (rs0.next()) {
				event.setText(rs0.getString("typeEvent"));
				descr.setText(rs0.getString("typeDescr"));
			}
			rs0.close();
			stmt0.close();
			
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
							Statement stmt = Data.dbStmt();
							stmt.executeUpdate("UPDATE typeevent SET typeEvent = '"+event.getText()+"', typeDescr = '"+descr.getText()+"' WHERE id = '"+id+"';");
							stmt.close();
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
					Statement stmt = Data.dbStmt();
					stmt.executeUpdate("UPDATE typeevent SET active = 'No' WHERE typeEvent = '"+event.getSelectedItem()+"';");
					stmt.close();
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
			Statement stmt = Data.dbStmt();
			String query = "SELECT typeEvent FROM typeevent WHERE active = 'Yes';";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String event = rs.getString("typeEvent");
				event1.add(event);
			}
			event1.add("--Select--");
			Collections.sort(event1);
			rs.close();
			stmt.close();
			
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
			
			Statement stmt = Data.dbStmt();
			ResultSet rs = stmt.executeQuery("SELECT typeEvent FROM typeevent WHERE active = 'Yes';");
			
			while (rs.next()) {
				if (newEventName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E005\n"+e);
			return false;
		}
	}
	
	static Boolean doesEventAlreadyExistOnEdit(String newEventName, String id) {
		try {
			Boolean flag = false;
			
			Statement stmt = Data.dbStmt();
			ResultSet rs = stmt.executeQuery("SELECT typeEvent FROM typeevent WHERE id != '"+id+"' AND active = 'Yes';");
			
			while (rs.next()) {
				if (newEventName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: E005\n"+e);
			return false;
		}
	}
}
