package mainPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

public class Logger {

	private JFrame frame;
	static JPanel pMainMenu; 

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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR\n"+e);
			e.printStackTrace();
		}
		
		pMainMenu = new JPanel();
		frame.getContentPane().add(pMainMenu, "name_133416811405546");
		GridBagLayout gbl_pMainMenu = new GridBagLayout();
		gbl_pMainMenu.columnWidths = new int[] {0};
		gbl_pMainMenu.rowHeights = new int[] {0, 50, 0, 0, 0, 0};
		gbl_pMainMenu.columnWeights = new double[]{0.0};
		gbl_pMainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pMainMenu.setLayout(gbl_pMainMenu);

		JPanelCL pLog = new JPanelCL();
		frame.getContentPane().add(pLog, "name_7019371194259");
		pLog.buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pLog.updateTable("SELECT id AS 'ID', carName AS 'Car', date AS 'Date', kmReading AS 'Odometer', docNo AS 'Document', typeEvent AS 'Event' FROM log ORDER BY date DESC " + Data.queryLimit());
				TableColumn column1 = null;
				for (int i = 0; i < 6; i++) {
				    column1 = pLog.getTable().getColumnModel().getColumn(i);
				    if (i == 0) {
				    	column1.setPreferredWidth(50);
				    } else if (i == 2 || i == 3) {
				    	column1.setPreferredWidth(100);
				    } else if (i == 1 || i == 4 || i == 5) {
				    	column1.setPreferredWidth(150);
				    } 
				}
			}
		});
		
		pLog.buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataLog.popupAddToLog();;
			}
		});
		
		pLog.buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not enabled, yet");
			}
		});
		
		pLog.buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pMainMenu.setVisible(true);
				pLog.setVisible(false);
			}
		});
		
		pLog.getTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent e) {
				if (e.getClickCount() == 2) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataLog.popupEditLogEntry(id);
				}
			}
		});
		
		JPanelCL pCars = new JPanelCL();
		frame.getContentPane().add(pCars, "name_7026129106005");
		pCars.setHeader("Car");
		pCars.buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pCars.updateTable("SELECT id AS 'ID', carName AS 'Car Name', manufacturer AS 'Manufacturer', model AS 'Model', yearMade AS 'Year', regNo AS 'Reg. No', vinNo AS 'VIN Number' FROM car WHERE active = 'Yes' ORDER BY carName " + Data.queryLimit());
				TableColumn column2 = null;
				for (int i = 0; i < 7; i++) {
				    column2 = pCars.getTable().getColumnModel().getColumn(i);
				    if (i == 0 || i == 4) {
				    	column2.setPreferredWidth(50);
				    } else if (i == 5) {
				    	column2.setPreferredWidth(80);
				    } else if (i == 1 || i == 2 || i == 3 || i == 6) {
				    	column2.setPreferredWidth(120);
				    } 
				}
			}
		});
		
		pCars.setButtonName2("Add Car");
		pCars.buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataCars.popupAddCar();
			}
		});
		
		pCars.setButtonName3("Remove Car");
		pCars.buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataCars.popupRemoveCar();
			}
		});
		
		pCars.buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pMainMenu.setVisible(true);
				pCars.setVisible(false);
			}
		});
		
		pCars.getTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent e) {
				if (e.getClickCount() == 2) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataCars.popupEditCar(id);
				}
			}
		});
		
		JPanelCL pEvent = new JPanelCL();
		frame.getContentPane().add(pEvent, "name_7029079578505");
		pEvent.setHeader("Event Types");
		
		pEvent.buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pEvent.updateTable("SELECT id AS 'ID', typeEvent AS 'Event Type', typeDescr AS 'Description' FROM typeevent WHERE active = 'Yes' ORDER BY typeEvent " + Data.queryLimit());
				TableColumn column3 = null;
				for (int i = 0; i < 3; i++) {
				    column3 = pEvent.getTable().getColumnModel().getColumn(i);
				    if (i == 0) {
				    	column3.setPreferredWidth(30);
				    } else if (i == 1) {
				    	column3.setPreferredWidth(150);
				    } else if (i == 2) {
				    	column3.setPreferredWidth(400);
				    }
				}
			}
		});
		
		pEvent.setButtonName2("Add Event Type");
		pEvent.buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataEvents.popupEventTypeAdd();
			}
		});
		
		pEvent.setButtonName3("Remove Event Type");
		pEvent.buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataEvents.popupEventTypeRemove();
			}
		});
		
		pEvent.buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pMainMenu.setVisible(true);
				pEvent.setVisible(false);
			}
		});
		
		pEvent.getTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent e) {
				if (e.getClickCount() == 2) {
					final JTable jtable1 = (JTable)e.getSource();
					final int row = jtable1.getSelectedRow();
					final String id = (String)jtable1.getValueAt(row, 0);
					DataEvents.popupEventTypeEdit(id);
				}
			}
		});
		
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
				pCars.setVisible(true);
				pMainMenu.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCars = new GridBagConstraints();
		gbc_btnCars.insets = new Insets(0, 0, 5, 0);
		gbc_btnCars.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCars.gridx = 0;
		gbc_btnCars.gridy = 3;
		pMainMenu.add(btnCars, gbc_btnCars);
		
		JButton btnEvents = new JButton("Event Types");
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
		
	}
	
	static void testPanel(Boolean visible) {
		JTextField num = new JTextField();
		JTextField nam = new JTextField();
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		panel.setVisible(visible);
		panel.add(new JLabel("Branch Number:"));
		panel.add(num);
		panel.add(new JLabel("Branch Name:"));
		panel.add(nam);
	}
	

}
