package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Data {
	static Connection dbConn() {
		try {
			
			/*
			 *  The file has been created to be able to use SQLite3 or MySQL databases. Comment out as required.
			 *  SQLite3 - Database file is available in the Required Files folder.
			 *  MySQL - Script file is available in the Resource Folder. The script can be run on you server and updated the connection details as required.
			 *  
			 */
			
			
			/* SQLite3: */
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:CarLogger.db");
			return conn;
			/**/
			
			/* MySQL: *
			String host = "localWAMP";
			String url = "jdbc:mysql://127.0.0.1/carlogger";
			String username = "carloggeruser";
			String password = "thepassword";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				return conn;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
			/**/
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "D001\n"+e);
			return null;
		}
	}
	
	static String queryLimit() {
		String limit = " LIMIT 0, 5000";
		return limit;
	}
	
}
