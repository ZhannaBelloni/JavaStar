package de.hwg_lu.java_star.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

/**
 * Utility to cleanup the Database used by Java Star.
 * 
 * <br>
 * This will delete all data from all tables used. If you do not make a backup, all data are lost.
 * @author
 *
 */
public class AdminDBCleaner {
		
	/**
	 * Delete the schema with all dependencies.
	 * @param conn connection to the Database
	 * @param schemaName schema to remove
	 * @throws SQLException
	 */
	private void deleteSchema(Connection conn, String schemaName) throws SQLException {
		System.out.println("dropping schema " + schemaName);
		String sql = "DROP SCHEMA \"" + schemaName + "\" CASCADE"; 
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.executeUpdate();
	}
	
	/**
	 * Delete schema configured for Java Star in @see de.hwg_lu.java_star.jdbc.PostgreSQLAccess PostgreSQLAccess
	 * @param jdbcAccess class with all information to access the Database
	 * @throws SQLException
	 */
	public void cleanupJavaStarSchema(PostgreSQLAccess jdbcAccess) throws SQLException {
		System.out.println("Creating schema for 'JAVA_STAR' application");
		Connection conn = jdbcAccess.createConnection();
		this.deleteSchema(conn, jdbcAccess.getSchema());
		conn.close();
	}
	
	
	/**
	 * Application: no argument is needed.
	 * @param args
	 */
	public static void main(String[] args) {
		
		AdminDBCleaner adminDB = new AdminDBCleaner();
		try {
			PostgreSQLAccess access = new PostgreSQLAccess();
			adminDB.cleanupJavaStarSchema(access);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
