package com.github.pieter_duplessis.carlogger;

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

import com.github.pieter_duplessis.carlogger.java.JTextFieldOne;


public class DataCars {
	static void popupAddCar() {
		try {
			int result = 10;
			JTextFieldOne carName = new JTextFieldOne();
			JTextFieldOne man = new JTextFieldOne();
			JTextFieldOne model = new JTextFieldOne();
			JTextFieldOne year = new JTextFieldOne();
			JTextFieldOne reg = new JTextFieldOne();
			JTextFieldOne vin = new JTextFieldOne();
			
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
							Boolean flag = doesNameAlreadyExist(carName.getText());
							if (!flag) {
								Connection conn = Data.dbConn();
								Statement stmt = conn.createStatement();
								stmt.executeUpdate(DataQueries.carAdd(carName.getText(), man.getText(), model.getText(), year.getText(), reg.getText(), vin.getText()));
								stmt.close();
								conn.close();
								JOptionPane.showMessageDialog(null, "Car added successfully", "Successful...", JOptionPane.INFORMATION_MESSAGE);
							} else {
								result = 10;
								JOptionPane.showMessageDialog(null, "The name already exists", "Oops, the name already exists....", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that the year is 4 digits", "Characters out of place...", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that you have captured the following fields:\nCar Name, Manufacturer and Model", "Something is amiss...", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C001\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void popupEditCar(String id) {
		try {
			int result = 10;
			JTextFieldOne carName = new JTextFieldOne();
			carName.setColumns(20);
			carName.setEditable(false);
			JTextFieldOne man = new JTextFieldOne();
			man.setColumns(20);
			JTextFieldOne model = new JTextFieldOne();
			model.setColumns(20);
			JTextFieldOne year = new JTextFieldOne();
			year.setColumns(20);
			JTextFieldOne reg = new JTextFieldOne();
			reg.setColumns(20);
			JTextFieldOne vin = new JTextFieldOne();
			vin.setColumns(20);
			
			Connection conn0 = Data.dbConn();
			Statement stmt0 = conn0.createStatement();
			ResultSet rs0 = stmt0.executeQuery(DataQueries.carEditQuery(id));
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
			conn0.close();
			
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
							Boolean flag = doesNameAlreadyExistOnEdit(carName.getText(), id);
							if (!flag) {
								Connection conn = Data.dbConn();
								Statement stmt = conn.createStatement();
								stmt.executeUpdate(DataQueries.carEditUpdate(id, carName.getText(), man.getText(), model.getText(), year.getText(), reg.getText(), vin.getText()));
								stmt.close();
								conn.close();
								JOptionPane.showMessageDialog(null, "Car updated successfully", "Successful...", JOptionPane.INFORMATION_MESSAGE);
							} else {
								result = 10;
								JOptionPane.showMessageDialog(null, "This name already exists", "Oops, the name already exists...", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							result = 10;
							JOptionPane.showMessageDialog(null, "Please ensure that the year is 4 digits", "Characters out of place...", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						result = 10;
						JOptionPane.showMessageDialog(null, "Please ensure that the following fields are captured:\nCar Name, Manufacturer and Model", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C002\n"+e, "Someting went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static void popupRemoveCar() {
		try {
			JComboBox carName = new JComboBox(combo());
			
			JPanel panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("Car to remove: "));
			panel.add(carName);
			
			int result = JOptionPane.showConfirmDialog(null, panel, "Remove Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				if (carName.getSelectedItem().equals("--Select--")) {
					JOptionPane.showMessageDialog(null, "Nothing selected\nAction cancelled", "Something looks amiss...", JOptionPane.WARNING_MESSAGE);
				} else {
					Connection conn = Data.dbConn();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(DataQueries.carRemove((String)carName.getSelectedItem()));
					stmt.close();
					conn.close();
					JOptionPane.showMessageDialog(null, "Car removed successfully", "Successful...", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C003\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	static String[] combo() {
		ArrayList<String> car1 = new ArrayList<String>();
		try {
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			String query = DataQueries.carCombo();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String car = rs.getString("carName");
				car1.add(car);
			}
			car1.add("--Select--");
			Collections.sort(car1);
			rs.close();
			stmt.close();
			conn.close();
			
			String[] car2 = car1.toArray(new String[car1.size()]);
			return car2;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C004\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	static Boolean doesNameAlreadyExist(String newCarName) {
		try {
			Boolean flag = false;
			
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataQueries.carNameAlreadyExistNew());
			
			while (rs.next()) {
				if (newCarName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			
			stmt.close();
			conn.close();
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "C005\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	static Boolean doesNameAlreadyExistOnEdit(String newCarName, String id) {
		try {
			Boolean flag = false;
			
			Connection conn = Data.dbConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataQueries.carNameAlreadyExistUpdate(id));
			
			while (rs.next()) {
				if (newCarName.equals(rs.getString(1))) {
					flag = true;
				}
			}
			
			stmt.close();
			conn.close();
			return flag;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: C006\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}


