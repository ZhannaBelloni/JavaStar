package de.hwg_lu.java_star.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

public class AdminDB {
	
	String pathToData = "/home/bau/Documents/Zhanna/website/JavaStarWebsite/data/";
	
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
	
	private void insertExercise(Connection connection, int number) {
		String string_instructions = this.readFile(
				this.pathToData + File.separator + 
				"exercise_" + number + "_instructions.txt"
		);
		String string_out = this.readFile(
				this.pathToData + File.separator + 
				"exercise_" + number + "_out.txt"
		);
		String string_test = this.readFile(
				this.pathToData + File.separator + 
				"exercise_" + number + "_test.txt"
		);
		String string_solution = this.readFile(
				this.pathToData + File.separator + 
				"exercise_" + number + "_solution.txt"
		);
		
		System.out.print("INSERT data for excercise " + number + ": ");
		String sql = "INSERT INTO excercise ("
				+ "id, "
				+ "exercise_text, "
				+ "exercise_out,  "
				+ "exercise_solution, "
				+ "exercise_test) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, number);
			prep.setString(2, string_instructions);
			prep.setString(3, string_out);
			prep.setString(4, string_solution);
			prep.setString(5, string_test);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
		
	}
	
	public void insertExcercise(Connection connection) throws SQLException {
		
		int numExercises = 1;
		
		while (true) {
			File f = new File(this.pathToData + "exercise_" + numExercises + "_instructions.txt");
			if(f.exists()) { ++numExercises; } 
			else { numExercises--; break; }
		}
		
		for (int i = 1; i <= numExercises; ++i) {
			insertExercise(connection, i);
		}

	}
	
	
	public static void main(String[] args) {
		
		AdminDBCleaner admin = new AdminDBCleaner();
		try {
			PostgreSQLAccess access = new PostgreSQLAccess();
			admin.cleanupJavaStarSchema(access);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
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
	
	String readFile(String filename) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
		{
		    String sCurrentLine;
		    while ((sCurrentLine = br.readLine()) != null) {
		        contentBuilder.append(sCurrentLine).append("\n");
		    }
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}

		return contentBuilder.toString();
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
	
	String _CREATE_EXCERCISE_TABLE = "CREATE TABLE IF NOT EXISTS \"excercise\" (" 
			+ "id INTEGER PRIMARY KEY NOT NULL, "
			+ "exercise_text  CHAR(1024) NOT NULL, "
			+ "exercise_out   CHAR(512) NOT NULL"
			+ ")";
	
	
	String CREATE_EXCERCISE_TABLE = "CREATE TABLE IF NOT EXISTS \"excercise\" (" 
			+ "id                  INTEGER       PRIMARY KEY NOT NULL, "
			+ "exercise_text       VARCHAR(1024)             NOT NULL, "
			+ "exercise_out        VARCHAR(1024)             NOT NULL,"
			+ "exercise_solution   VARCHAR(2048)             NOT NULL,"
			+ "exercise_test       VARCHAR(2048)             NOT NULL"
			+ ")";
	
	
}
