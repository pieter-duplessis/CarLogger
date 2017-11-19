package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Data {
	static String ConnectionData(String what) {
		/**/	//Connect via Localhost WAMP (on this computer).
		String host = "localWAMP";
		String url = "jdbc:mysql://127.0.0.1/carlogger";
		String username = "carloggeruser";
		String password = "thepassword";
		/**/
		
		if (what.equals("stringurl")) {
			return url;
		} else if (what.equals("stringusername")) {
			return username;
		} else if (what.equals("stringpassword")) {
			return password;
		} else if (what.equals("host")) {
			return host;
		}
		return null;
	}
	
	static Statement dbStmt() {
		try {
			
			/*
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:CarLogger.db");
			Statement stmt = conn.createStatement();
			
			return stmt;
			/**/
			
			/**/
			String url = ConnectionData("stringurl");
			String username = ConnectionData("stringusername");
			String password = ConnectionData("stringpassword");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn1 = DriverManager.getConnection(url, username, password);
				Statement stmt1 = conn1.createStatement();
				return stmt1;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}/**/
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
