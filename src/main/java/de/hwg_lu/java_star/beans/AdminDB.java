package de.hwg_lu.java_star.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

public class AdminDB {
	
	private void createSchema(Connection conn, String schemaName) throws SQLException {
		System.out.println("Creating schema " + schemaName);
		String sql = "CREATE SCHEMA IF NOT EXISTS \"" + schemaName + "\""; 
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.executeUpdate();
	}
	
	public void createJavaStarSchema(PostgreSQLAccess jdbcAccess) throws SQLException {
		System.out.println("Creating schema for 'JAVA_STAR' application");
		this.createSchema(jdbcAccess.createConnection(), jdbcAccess.getSchema());
	}
	
	public void createTableAccounts(Connection connection) throws SQLException {
		System.out.println("Creating table for 'JAVA_STAR' application accounts");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_ACCOUNT_TABLE);
		prep.executeUpdate();
	}
	
	public void createTableExercises(Connection connection) throws SQLException {
		System.out.println("Creating table for 'JAVA_STAR' application excercises");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_EXCERCISE_TABLE);
		prep.executeUpdate();
	}
	
	public void insertExcercise(Connection connection) throws SQLException {
		System.out.println("INSERT INTO excercises");
		String sql = "INSERT INTO excercise (id, exercise_text, exercise_out) VALUES (?, ?, ?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, 1);
			prep.setString(2, "Write a program that print to console \"Hello Java Star\"");
			prep.setString(3, "Hello Java Star");		
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DONE");

	}
	
	public static void main(String[] args) {
		
		AdminDB adminDB = new AdminDB();
		try {
			PostgreSQLAccess access = new PostgreSQLAccess();
			adminDB.createJavaStarSchema(access);
			adminDB.createTableAccounts(access.getConnection());
			adminDB.createTableExercises(access.getConnection());
			adminDB.insertExcercise(access.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// ========================================================= //
	// SQL statements
	
	String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS \"account\" (" 
			+ "userid CHAR(32) PRIMARY KEY NOT NULL, "
			+ "email  CHAR(32) NOT NULL, "
			+ "password CHAR(16) NOT NULL, "
			+ "active CHAR(1) NOT NULL DEFAULT 'Y',"
			+ "admin CHAR(1) NOT NULL DEFAULT 'N',"
			+ "totalExcercises INTEGER DEFAULT 0,"
			+ "correctExcercise INTEGER DEFAULT 0"
			+ ")";
	
	String CREATE_EXCERCISE_TABLE = "CREATE TABLE IF NOT EXISTS \"excercise\" (" 
			+ "id INTEGER PRIMARY KEY NOT NULL, "
			+ "exercise_text  CHAR(1024) NOT NULL, "
			+ "exercise_out   CHAR(512) NOT NULL"
			+ ")";
	
}
