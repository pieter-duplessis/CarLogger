package mainPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class Logger {

	private JFrame frame;
	private JTable tLog;
	private JTable tCar;
	private JTable tEvent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logger window = new Logger();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logger() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Car Logger");
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel pMainMenu = new JPanel();
		frame.getContentPane().add(pMainMenu, "name_133416811405546");
		GridBagLayout gbl_pMainMenu = new GridBagLayout();
		gbl_pMainMenu.columnWidths = new int[] {0};
		gbl_pMainMenu.rowHeights = new int[] {0, 50, 0, 0, 0, 0};
		gbl_pMainMenu.columnWeights = new double[]{0.0};
		gbl_pMainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pMainMenu.setLayout(gbl_pMainMenu);

		JPanel pLog = new JPanel();
		frame.getContentPane().add(pLog, "name_7019371194259");
		GridBagLayout gbl_pLog = new GridBagLayout();
		gbl_pLog.columnWidths = new int[] {185, 185, 185, 185};
		gbl_pLog.rowHeights = new int[] {0, 50, 0, 0, 0};
		gbl_pLog.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_pLog.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pLog.setLayout(gbl_pLog);

		JPanel pCar = new JPanel();
		frame.getContentPane().add(pCar, "name_7026129106005");
		GridBagLayout gbl_pCar = new GridBagLayout();
		gbl_pCar.columnWidths = new int[] {185, 185, 185, 185};
		gbl_pCar.rowHeights = new int[] {0, 50, 0, 0, 0};
		gbl_pCar.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_pCar.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pCar.setLayout(gbl_pCar);

		JPanel pEvent = new JPanel();
		frame.getContentPane().add(pEvent, "name_7029079578505");
		GridBagLayout gbl_pEvent = new GridBagLayout();
		gbl_pEvent.columnWidths = new int[] {185, 185, 185, 185};
		gbl_pEvent.rowHeights = new int[] {0, 50, 0, 0, 0};
		gbl_pEvent.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_pEvent.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pEvent.setLayout(gbl_pEvent);
		
		JLabel lblCarLogger = new JLabel("Car Logger");
		lblCarLogger.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblCarLogger = new GridBagConstraints();
		gbc_lblCarLogger.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarLogger.gridx = 0;
		gbc_lblCarLogger.gridy = 0;
		pMainMenu.add(lblCarLogger, gbc_lblCarLogger);
		
		JButton btnLog = new JButton("Log");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Go to Log panel
				pLog.setVisible(true);
				pMainMenu.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnLog = new GridBagConstraints();
		gbc_btnLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLog.gridx = 0;
		gbc_btnLog.gridy = 2;
		pMainMenu.add(btnLog, gbc_btnLog);
		
		JButton btnCars = new JButton("Cars");
		btnCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Go to Cars panel
				pCar.setVisible(true);
				pMainMenu.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCars = new GridBagConstraints();
		gbc_btnCars.insets = new Insets(0, 0, 5, 0);
		gbc_btnCars.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCars.gridx = 0;
		gbc_btnCars.gridy = 3;
		pMainMenu.add(btnCars, gbc_btnCars);
		
		JButton btnEvents = new JButton("Type of Events");
		btnEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Go to Events panel
				pEvent.setVisible(true);
				pMainMenu.setVisible(false);
				
			}
		});
		GridBagConstraints gbc_btnEvents = new GridBagConstraints();
		gbc_btnEvents.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEvents.gridx = 0;
		gbc_btnEvents.gridy = 4;
		pMainMenu.add(btnEvents, gbc_btnEvents);
		
		JLabel lblLogOfEvents = new JLabel("Log of Events");
		lblLogOfEvents.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblLogOfEvents = new GridBagConstraints();
		gbc_lblLogOfEvents.gridwidth = 4;
		gbc_lblLogOfEvents.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogOfEvents.gridx = 0;
		gbc_lblLogOfEvents.gridy = 0;
		pLog.add(lblLogOfEvents, gbc_lblLogOfEvents);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Refresh Log Table
				try {
					Statement stmt1 = Data.dbStmt();
					ResultSet rs1 = stmt1.executeQuery("SELECT id, carName, date, kmReading, docNo, typeEvent FROM log ORDER BY date DESC " + Data.queryLimit());
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					int columns1 = rsmd1.getColumnCount();
					DefaultTableModel dtm1 = new DefaultTableModel();
					Vector colname1 = new Vector();
					Vector rowdata1 = new Vector();
					
					String[] eheaders1 = {"null", "ID", "Car", "Date", "km Reading", "Doc. No.", "Type Event"};
					for (int i=1; i<=columns1; i++) {
						colname1.addElement(eheaders1[i]);
					}
					dtm1.setColumnIdentifiers(colname1);
					
					while (rs1.next()) {
						rowdata1 = new Vector();
						for (int j=1; j<=columns1; j++) {
							rowdata1.addElement(rs1.getString(j));
						}
						dtm1.addRow(rowdata1);
					}
					tLog.setModel(dtm1);
					TableColumn column = null;
					for (int i = 0; i < 6; i++) {
					    column = tLog.getColumnModel().getColumn(i);
					    if (i == 0) {
					    	column.setPreferredWidth(50);
					    } else if (i == 2 || i == 3) {
					    	column.setPreferredWidth(100);
					    } else if (i == 1 || i == 4 || i == 5) {
					    	column.setPreferredWidth(150);
					    } 
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: M001\n"+e);
				}
			}
		});
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefresh.gridx = 0;
		gbc_btnRefresh.gridy = 2;
		pLog.add(btnRefresh, gbc_btnRefresh);
		
		JButton btnAddToLog = new JButton("Add to Log");
		btnAddToLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Add to Log Popup
				DataLog.popupAddToLog();;
			}
		});
		GridBagConstraints gbc_btnAddToLog = new GridBagConstraints();
		gbc_btnAddToLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddToLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToLog.gridx = 1;
		gbc_btnAddToLog.gridy = 2;
		pLog.add(btnAddToLog, gbc_btnAddToLog);
		
		JButton btnRemoveFromLog = new JButton("Remove from Log");
		btnRemoveFromLog.setEnabled(false);
		btnRemoveFromLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remove from Log Popup
				
			}
		});
		GridBagConstraints gbc_btnRemoveFromLog = new GridBagConstraints();
		gbc_btnRemoveFromLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveFromLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveFromLog.gridx = 2;
		gbc_btnRemoveFromLog.gridy = 2;
		pLog.add(btnRemoveFromLog, gbc_btnRemoveFromLog);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Back to Main Menu from Log Panel
				pMainMenu.setVisible(true);
				pLog.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBackToMain = new GridBagConstraints();
		gbc_btnBackToMain.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBackToMain.insets = new Insets(0, 0, 5, 0);
		gbc_btnBackToMain.gridx = 3;
		gbc_btnBackToMain.gridy = 2;
		pLog.add(btnBackToMain, gbc_btnBackToMain);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		pLog.add(scrollPane, gbc_scrollPane);
		
		tLog = new JTable();
		scrollPane.setViewportView(tLog);
		tLog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataLog.popupEditLogEntry(id);
				}
			}
		});
		
		JLabel lblCars = new JLabel("Cars");
		lblCars.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblCars = new GridBagConstraints();
		gbc_lblCars.gridwidth = 4;
		gbc_lblCars.insets = new Insets(0, 0, 5, 0);
		gbc_lblCars.gridx = 0;
		gbc_lblCars.gridy = 0;
		pCar.add(lblCars, gbc_lblCars);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Refresh Car Table
				try {
					Statement stmt2 = Data.dbStmt();
					ResultSet rs2 = stmt2.executeQuery("SELECT id, carName, manufacturer, model, yearMade, regNo, vinNo FROM car WHERE active = 'Yes' ORDER BY carName " + Data.queryLimit());
					ResultSetMetaData rsmd2 = rs2.getMetaData();
					int columns2 = rsmd2.getColumnCount();
					DefaultTableModel dtm2 = new DefaultTableModel();
					Vector colname2 = new Vector();
					Vector rowdata2 = new Vector();
					
					String[] eheaders2 = {"null", "ID", "Car Name", "Manufacturer", "Model", "Year", "Reg. No", "VIN No."};
					for (int i=1; i<=columns2; i++) {
						colname2.addElement(eheaders2[i]);
					}
					dtm2.setColumnIdentifiers(colname2);
					
					while (rs2.next()) {
						rowdata2 = new Vector();
						for (int j=1; j<=columns2; j++) {
							rowdata2.addElement(rs2.getString(j));
						}
						dtm2.addRow(rowdata2);
					}
					tCar.setModel(dtm2);
					TableColumn column = null;
					for (int i = 0; i < 7; i++) {
					    column = tCar.getColumnModel().getColumn(i);
					    if (i == 0 || i == 4) {
					    	column.setPreferredWidth(70);
					    } else if (i == 1 || i == 2 || i == 3 || i == 5 || i == 6) {
					    	column.setPreferredWidth(250);
					    } 
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: M002\n"+e);
				}
			}
		});
		GridBagConstraints gbc_btnRefresh_1 = new GridBagConstraints();
		gbc_btnRefresh_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRefresh_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefresh_1.gridx = 0;
		gbc_btnRefresh_1.gridy = 2;
		pCar.add(btnRefresh_1, gbc_btnRefresh_1);
		
		JButton btnAddCar = new JButton("Add Car");
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Add Car Popup
				DataCars.popupAddCar();
			}
		});
		GridBagConstraints gbc_btnAddCar = new GridBagConstraints();
		gbc_btnAddCar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddCar.gridx = 1;
		gbc_btnAddCar.gridy = 2;
		pCar.add(btnAddCar, gbc_btnAddCar);
		
		JButton btnRemoveCar = new JButton("Remove Car");
		btnRemoveCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remove Car Popup
				DataCars.popupRemoveCar();
			}
		});
		GridBagConstraints gbc_btnRemoveCar = new GridBagConstraints();
		gbc_btnRemoveCar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveCar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveCar.gridx = 2;
		gbc_btnRemoveCar.gridy = 2;
		pCar.add(btnRemoveCar, gbc_btnRemoveCar);
		
		JButton btnBackToMain_1 = new JButton("Back to Main Menu");
		btnBackToMain_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Back to Main Menu from Car Panel
				pMainMenu.setVisible(true);
				pCar.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBackToMain_1 = new GridBagConstraints();
		gbc_btnBackToMain_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBackToMain_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnBackToMain_1.gridx = 3;
		gbc_btnBackToMain_1.gridy = 2;
		pCar.add(btnBackToMain_1, gbc_btnBackToMain_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		pCar.add(scrollPane_1, gbc_scrollPane_1);
		
		tCar = new JTable();
		scrollPane_1.setViewportView(tCar);
		tCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataCars.popupEditCar(id);
				}
			}
		});
		
		JLabel lblTypeOfEvents = new JLabel("Type of Events");
		lblTypeOfEvents.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GridBagConstraints gbc_lblTypeOfEvents = new GridBagConstraints();
		gbc_lblTypeOfEvents.gridwidth = 4;
		gbc_lblTypeOfEvents.insets = new Insets(0, 0, 5, 0);
		gbc_lblTypeOfEvents.gridx = 0;
		gbc_lblTypeOfEvents.gridy = 0;
		pEvent.add(lblTypeOfEvents, gbc_lblTypeOfEvents);
		
		JButton btnRefresh_2 = new JButton("Refresh");
		btnRefresh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Refresh the Event Table
				try {
					Statement stmt3 = Data.dbStmt();
					ResultSet rs3 = stmt3.executeQuery("SELECT id, typeEvent, typeDescr FROM typeevent WHERE active = 'Yes' ORDER BY typeEvent " + Data.queryLimit());
					ResultSetMetaData rsmd3 = rs3.getMetaData();
					int columns3 = rsmd3.getColumnCount();
					DefaultTableModel dtm3 = new DefaultTableModel();
					Vector colname3 = new Vector();
					Vector rowdata3 = new Vector();
					
					String[] eheaders3 = {"null", "ID", "Type Event", "Description"};
					for (int i=1; i<=columns3; i++) {
						colname3.addElement(eheaders3[i]);
					}
					dtm3.setColumnIdentifiers(colname3);
					
					while (rs3.next()) {
						rowdata3 = new Vector();
						for (int j=1; j<=columns3; j++) {
							rowdata3.addElement(rs3.getString(j));
						}
						dtm3.addRow(rowdata3);
					}
					tEvent.setModel(dtm3);
					TableColumn column = null;
					for (int i = 0; i < 3; i++) {
					    column = tEvent.getColumnModel().getColumn(i);
					    if (i == 0) {
					    	column.setPreferredWidth(30);
					    } else if (i == 1) {
					    	column.setPreferredWidth(150);
					    } else if (i == 2) {
					    	column.setPreferredWidth(400);
					    }
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR: M003\n"+e);
				}
			}
		});
		GridBagConstraints gbc_btnRefresh_2 = new GridBagConstraints();
		gbc_btnRefresh_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRefresh_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefresh_2.gridx = 0;
		gbc_btnRefresh_2.gridy = 2;
		pEvent.add(btnRefresh_2, gbc_btnRefresh_2);
		
		JButton btnAddTypeOf = new JButton("Add Type of Event");
		btnAddTypeOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Add Type of Event Popup
				DataEvents.popupEventTypeAdd();
			}
		});
		GridBagConstraints gbc_btnAddTypeOf = new GridBagConstraints();
		gbc_btnAddTypeOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddTypeOf.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddTypeOf.gridx = 1;
		gbc_btnAddTypeOf.gridy = 2;
		pEvent.add(btnAddTypeOf, gbc_btnAddTypeOf);
		
		JButton btnRemoveTypeOf = new JButton("Remove Type of Event");
		btnRemoveTypeOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remove Type Event
				DataEvents.popupEventTypeRemove();
			}
		});
		GridBagConstraints gbc_btnRemoveTypeOf = new GridBagConstraints();
		gbc_btnRemoveTypeOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveTypeOf.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveTypeOf.gridx = 2;
		gbc_btnRemoveTypeOf.gridy = 2;
		pEvent.add(btnRemoveTypeOf, gbc_btnRemoveTypeOf);
		
		JButton btnBackToMain_2 = new JButton("Back to Main Menu");
		btnBackToMain_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Back to Main Menu from Event Panel
				pMainMenu.setVisible(true);
				pEvent.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBackToMain_2 = new GridBagConstraints();
		gbc_btnBackToMain_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBackToMain_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnBackToMain_2.gridx = 3;
		gbc_btnBackToMain_2.gridy = 2;
		pEvent.add(btnBackToMain_2, gbc_btnBackToMain_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 4;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 3;
		pEvent.add(scrollPane_2, gbc_scrollPane_2);
		
		tEvent = new JTable();
		scrollPane_2.setViewportView(tEvent);
		tEvent.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataEvents.popupEventTypeEdit(id);
				}
			}
		});
		
	}

}
