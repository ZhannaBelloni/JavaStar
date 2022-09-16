package de.hwg_lu.java_star.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

public class AdminDBCleaner {
		
	private void deleteSchema(Connection conn, String schemaName) throws SQLException {
		System.out.println("dropping schema " + schemaName);
		String sql = "DROP SCHEMA \"" + schemaName + "\" CASCADE"; 
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.executeUpdate();
	}
	
	public void cleanupJavaStarSchema(PostgreSQLAccess jdbcAccess) throws SQLException {
		System.out.println("Creating schema for 'JAVA_STAR' application");
		this.deleteSchema(jdbcAccess.createConnection(), jdbcAccess.getSchema());
	}
	
	
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
