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

public class DataCars {
	static void popupAddCar() {
		try {
			int result = 10;
			JTextField carName = new JTextField();
			JTextField man = new JTextField();
			JTextField model = new JTextField();
			JTextField year = new JTextField();
			JTextField reg = new JTextField();
			JTextField vin = new JTextField();
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(6,2));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Manufacturer: "));
				panel.add(man);
				panel.add(new JLabel("Model: "));
				panel.add(model);
				panel.add(new JLabel("Year Made: "));
				panel.add(year);
				panel.add(new JLabel("Registration No. (Plate No.): "));
				panel.add(reg);
				panel.add(new JLabel("VIN Number: "));
				panel.add(vin);
				
				result = JOptionPane.showConfirmDialog(null, panel, "Add Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (carName.getText().length() > 0 && man.getText().length() > 0 && model.getText().length() > 0) {
						if (year.getText().length() == 4) {
							Statement stmt = Data.dbStmt();
							stmt.executeUpdate("INSERT INTO car(carName, manufacturer, model, yearMade, regNo, vinNo, active) VALUES ('"+carName.getText()+"', '"+man.getText()+"', '"+model.getText()+"', '"+year.getText()+"', '"+reg.getText()+"', '"+vin.getText()+"', 'Yes');");
							stmt.close();
							JOptionPane.showMessageDialog(null, "Car added successfully");
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that the year is 4 digits");
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name, Manufacturer and Model");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C001\n"+e);
		}
	}
	
	static void popupEditCar(String id) {
		try {
			int result = 10;
			JTextField carName = new JTextField();
			JTextField man = new JTextField();
			JTextField model = new JTextField();
			JTextField year = new JTextField();
			JTextField reg = new JTextField();
			JTextField vin = new JTextField();
			
			Statement stmt0 = Data.dbStmt();
			ResultSet rs0 = stmt0.executeQuery("SELECT carName, manufacturer, model, yearMade, regNo, vinNo FROM car WHERE id = '"+id+"';");
			while (rs0.next()) {
				carName.setText(rs0.getString("carName"));
				man.setText(rs0.getString("manufacturer"));
				model.setText(rs0.getString("model"));
				year.setText(rs0.getString("yearMade"));
				reg.setText(rs0.getString("regNo"));
				vin.setText(rs0.getString("vinNo"));
			}
			rs0.close();
			stmt0.close();
			
			while (result == 10) {
				JPanel panel = new JPanel(new GridLayout(7,2));
				panel.add(new JLabel("ID: "));
				panel.add(new JLabel(id));
				panel.add(new JLabel("Car Name: "));
				panel.add(carName);
				panel.add(new JLabel("Manufacturer: "));
				panel.add(man);
				panel.add(new JLabel("Model: "));
				panel.add(model);
				panel.add(new JLabel("Year: "));
				panel.add(year);
				panel.add(new JLabel("Registration No. (Plate No.): "));
				panel.add(reg);
				panel.add(new JLabel("VIN Number: "));
				panel.add(vin);
				
				
				
				result = JOptionPane.showConfirmDialog(null, panel, "Edit Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					if (carName.getText().length() > 0 && man.getText().length() > 0 && model.getText().length() > 0) {
						if (year.getText().length() == 4) {
							Statement stmt = Data.dbStmt();
							stmt.executeUpdate("UPDATE car SET carName = '"+carName.getText()+"', manufacturer = '"+man.getText()+"', model = '"+model.getText()+"', yearMade = '"+year.getText()+"', regNo = '"+reg.getText()+"', vinNo = '"+vin.getText()+"' WHERE id = '"+id+"';");
							stmt.close();
							JOptionPane.showMessageDialog(null, "Car updated successfully");
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that the year is 4 digits");
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name, Manufacturer and Model");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C002\n"+e);
		}
	}
	
	static void popupRemoveCar() {
		try {
			JComboBox car = new JComboBox(combo());
			
			JPanel panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("Car to remove: "));
			panel.add(car);
			
			int result = JOptionPane.showConfirmDialog(null, panel, "Remove Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				if (car.getSelectedItem().equals("--Select--")) {
					JOptionPane.showMessageDialog(null, "Nothing selected\nAction cancelled");
				} else {
					Statement stmt = Data.dbStmt();
					stmt.executeUpdate("UPDATE car SET active = 'No' WHERE carName = '"+car.getSelectedItem()+"';");
					stmt.close();
					JOptionPane.showMessageDialog(null, "Car removed successfully");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C003\n"+e);
		}
	}
	
	static String[] combo() {
		ArrayList<String> car1 = new ArrayList<String>();
		try {
			Statement stmt = Data.dbStmt();
			String query = "SELECT carName FROM car WHERE active = 'Yes';";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String car = rs.getString("carName");
				car1.add(car);
			}
			car1.add("--Select--");
			Collections.sort(car1);
			rs.close();
			stmt.close();
			
			String[] car2 = car1.toArray(new String[car1.size()]);
			return car2;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "C004\n"+e);
			return null;
		}
	}

}
