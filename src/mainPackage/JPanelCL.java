package mainPackage;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

public class JPanelCL extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tLog;
	String tableID;
	JLabel panelHeader;
	JButton buttonOne;
	JButton buttonTwo;
	JButton buttonThree;
	JButton buttonFour;

	/**
	 * JPanelCL
	 * Author:	Pieter du Plessis
	 * Date: 	2018/09/30
	 * Description:
	 * This is a JPanel object that extends JPanel class from javax.swing. This panel consists of one header, four buttons and a table. The names for the 
	 * label and buttons can bet set with the setter methods. The action listener and mouse listeners will have to be set for the buttons and the tables.
	 * Query needs to be passed with the update table method (With a limit for amount of queries being pulled for performance and safeguard reasons. 
	 * 
	 * Defaults for Buttons:
	 * Header - panelHeader = Log
	 * Button 1 - buttonOne = Refresh
	 * Button 2 - buttonTwo = Add Entry
	 * BUtton 3 - buttonThree = Remove Entry
	 * Button 4 - buttonFour = Main Menu (Back to Main Menu)
	 * Table = getter to be used (getTable)
	 * 
	 */
	JPanelCL() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190, 190, 190, 190};
		gridBagLayout.rowHeights = new int[]{0, 40, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panelHeader = new JLabel("Log");
		panelHeader.setFont(new Font("Tahoma", Font.PLAIN, 38));
		GridBagConstraints gbc_panelHeader = new GridBagConstraints();
		gbc_panelHeader.gridwidth = 4;
		gbc_panelHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeader.gridx = 0;
		gbc_panelHeader.gridy = 0;
		add(panelHeader, gbc_panelHeader);
		
		buttonOne = new JButton("Refresh");
		GridBagConstraints gbc_buttonOne = new GridBagConstraints();
		gbc_buttonOne.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonOne.insets = new Insets(0, 0, 5, 5);
		gbc_buttonOne.gridx = 0;
		gbc_buttonOne.gridy = 2;
		add(buttonOne, gbc_buttonOne);
		
		buttonTwo = new JButton("Add Entry");
		GridBagConstraints gbc_buttonTwo = new GridBagConstraints();
		gbc_buttonTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonTwo.insets = new Insets(0, 0, 5, 5);
		gbc_buttonTwo.gridx = 1;
		gbc_buttonTwo.gridy = 2;
		add(buttonTwo, gbc_buttonTwo);
		
		buttonThree = new JButton("Remove Entry");
		GridBagConstraints gbc_buttonThree = new GridBagConstraints();
		gbc_buttonThree.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonThree.insets = new Insets(0, 0, 5, 5);
		gbc_buttonThree.gridx = 2;
		gbc_buttonThree.gridy = 2;
		add(buttonThree, gbc_buttonThree);
		
		buttonFour = new JButton("Main Menu");
		GridBagConstraints gbc_buttonFour = new GridBagConstraints();
		gbc_buttonFour.insets = new Insets(0, 0, 5, 0);
		gbc_buttonFour.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonFour.gridx = 3;
		gbc_buttonFour.gridy = 2;
		add(buttonFour, gbc_buttonFour);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		tLog = new JTable();
		scrollPane.setViewportView(tLog);
		tLog.setCellSelectionEnabled(true);
		
	}
	
	void makeHide() {
		this.setVisible(false);
		Logger.pMainMenu.setVisible(true);
	}
	
	void setHeader(String name) {
		panelHeader.setText(name);
	}
	
	void setButtonName1(String name) {
		buttonOne.setText(name);
	}
	
	void setButtonName2(String name) {
		buttonTwo.setText(name);
	}
	
	void setButtonName3(String name) {
		buttonThree.setText(name);
	}
	
	void setButtonName4(String name) {
		buttonFour.setText(name);
	}

	JTable getTable() {
		return tLog;
	}
	
	@SuppressWarnings("serial")
	void updateTable(String query) {
		try {
			Connection conn1 = Data.dbConn();
			Statement stmt1 = conn1.createStatement();
			ResultSet rs1 = stmt1.executeQuery(query);
			ResultSetMetaData rsmd1 = rs1.getMetaData();
			int columns1 = rsmd1.getColumnCount();
			DefaultTableModel dtm1 = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			Vector<String> colname1 = new Vector<String>();
			Vector<String> rowdata1 = new Vector<String>();
			
			for (int i=1; i<=columns1; i++) {
				colname1.addElement(rsmd1.getColumnName(i));
			}
			dtm1.setColumnIdentifiers(colname1);
			
			while (rs1.next()) {
				rowdata1 = new Vector<String>();
				for (int j=1; j<=columns1; j++) {
					rowdata1.addElement(rs1.getString(j));
				}
				dtm1.addRow(rowdata1);
			}
			tLog.setModel(dtm1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: M0001\n"+e);
		}
	}

}
