package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 *  The file has been created to be able to use SQLite3 or MySQL databases. Comment out as required. SQLServer to be added in the future
 *  SQLite3 - Database file is available in the Required Files folder.
 *  MySQL - Script file is available in the Resource Folder. The script can be run on you server and updated the connection details as required.
 *  SQLServer - Script file still needs to be implemented.
 *  
 */

class Data {
	static final String DATABASE = "SQLite3";
	//static final String DATABASE = "MySQL";
	//static final String DATABASE = "SQLServer"; // Still needs to be implemented
	
	@SuppressWarnings("unused")
	private static final String mysqlHost = "MySQL";
	private static final String mysqlURL = "jdbc:mysql://127.0.0.1/CARLOGGER";
	private static final String mysqlUsername = "carloggeruser";
	private static final String mysqlPassword = "thepassword";
	
	@SuppressWarnings("unused")
	private static final String mssqlHost = "MSSQL";
	private static final String mssqlURL = "";
	private static final String mssqlUsername = "carloggeruser";
	private static final String mssqlPassword = "thepassword";
	
	static Connection dbConn() {
		try {
			Connection conn = null;
			
			if (DATABASE.equals("SQLite3")) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:CarLogger.db");
			} else if (DATABASE.equals("MySQL")) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(mysqlURL, mysqlUsername, mysqlPassword);
			} else if (DATABASE.equals("SQLServer")) {
				Class.forName("");
				conn = DriverManager.getConnection(mssqlURL, mssqlUsername, mssqlPassword);
			}
			
			return conn;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "D001\n"+e, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	}
	
	static String queryLimit() {
		String limit = " LIMIT 0, 5000";
		return limit;
	}
	
}
