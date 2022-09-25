package de.hwg_lu.java_star.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

/**
 * Utility to inspect the content of all the tables used by Java Star.
 *
 */
public class AdminDBViewer {
	
	private void printTable(Connection conn, String tableName) throws SQLException {
		System.out.println("============================================================================");
		System.out.println("Dumping TABLE " + tableName);
		System.out.println("============================================================================");

		String sql = "SELECT * FROM " + tableName + ";"; 
		java.sql.Statement  stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber; ++i) {
    		System.out.print("| " + rsmd.getColumnName(i) + "|");
    	}
        System.out.println("");
        while (rs.next()) {
        	for (int i = 1; i <= columnsNumber; ++i) {
        		System.out.print("| " + rs.getString(i).replace("\n", " ") + "|");
        	}
            System.out.println("");

        }
        System.out.println("");
        System.out.println("============================================================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");


	}
	
	/**
	 * Application: no argument is needed.
	 * @param args
	 */
	public static void main(String[] args) {
		
		AdminDBViewer adminDB = new AdminDBViewer();
		try {
			PostgreSQLAccess access = new PostgreSQLAccess();
			Connection conn = access.getConnection();
			for (String tableName : tablesNames) {
				adminDB.printTable(conn, tableName);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========================================================= //
	// SQL statements
	static private String[] tablesNames = { "excercise", "account", "comments", "statistics"}; 

	
}
