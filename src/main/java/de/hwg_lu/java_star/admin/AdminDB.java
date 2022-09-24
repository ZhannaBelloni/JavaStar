package de.hwg_lu.java_star.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hwg_lu.java_star.beans.LoginBean;
import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

public class AdminDB {

	static String pathToData = "";

	private void createSchema(Connection conn, String schemaName) throws SQLException {
		System.out.println("[INFO] Creating schema " + schemaName);
		String sql = "CREATE SCHEMA IF NOT EXISTS \"" + schemaName + "\"";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.executeUpdate();
	}

	public void createJavaStarSchema(PostgreSQLAccess jdbcAccess) throws SQLException {
		System.out.println("[INFO] Creating schema for 'JAVA_STAR' application");
		this.createSchema(jdbcAccess.createConnection(), jdbcAccess.getSchema());

	}

	public void createTableAccounts(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application accounts");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_ACCOUNT_TABLE);
		prep.executeUpdate();
	}

	public void createTableComments(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application comments");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_COMMENTS_TABLE);
		prep.executeUpdate();
	}

	public void createTableExercises(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application excercises");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_EXCERCISE_TABLE);
		prep.executeUpdate();
	}

	public void createStatisticsExercises(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application statistics");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_STATISTICS_TABLE);
		prep.executeUpdate();
	}

	private void insertAdmin(Connection connection) {
		
		LoginBean login = new LoginBean();
		login.setUserid("admin");
		login.setPassword("admin");
		try {
			if (login.checkUseridPassword()) {
				System.out.println("[WARN]admin user already present");
				return;
			}
		} catch (SQLException e1) {
			System.out.println("[ERROR] inserting admin user.");
			return;
		}
		
		String sql = "INSERT INTO account (" + "userid, " + "email, " + "password,  " + "admin, " + "active) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, "admin");
			prep.setString(2, "admin@admin.lu");
			prep.setString(3, "admin");
			prep.setString(4, "Y");
			prep.setString(5, "Y");
			prep.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[ERROR] inserting admin user.");
		}
		System.out.println("[INFO] DONE");

	}

	private void insertExercise(Connection connection, int number) throws SQLException {
		String string_instructions = this
				.readFile(AdminDB.pathToData + File.separator + "exercise_" + number + "_instructions.txt");
		String string_out = this.readFile(AdminDB.pathToData + File.separator + "exercise_" + number + "_out.txt");
		String string_test = this.readFile(AdminDB.pathToData + File.separator + "exercise_" + number + "_test.txt");
		String string_solution = this
				.readFile(AdminDB.pathToData + File.separator + "exercise_" + number + "_solution.txt");

		System.out.println("[INFO] INSERT data for excercise " + number + ": ");
		String sql = "INSERT INTO excercise (" + "id, " + "exercise_text, " + "exercise_out,  " + "exercise_solution, "
				+ "exercise_test) " + "VALUES (?, ?, ?, ?, ?)";

		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setInt(1, number);
		prep.setString(2, string_instructions);
		prep.setString(3, string_out);
		prep.setString(4, string_solution);
		prep.setString(5, string_test);
		prep.executeUpdate();
		System.out.println("[INFO] DONE");

	}

	public void insertExcercise(Connection connection) throws SQLException {
		System.out.println("[INFO] insering exercise ");
		int numExercises = 1;
		while (true) {
			String path = AdminDB.pathToData + File.separator + "exercise_" + numExercises + "_instructions.txt";
			File f = new File(path);
			if(f.exists()) { ++numExercises; } 
			else {
				numExercises--;
				if (numExercises == 0) {
					System.out.println("[WARN] Directory data is empty: " + path + " does not exists");
				}
			break; }
		}
		System.out.println("[INFO]   * found " + numExercises + " exercise.");
		
		for (int i = 1; i <= numExercises; ++i) {
			try {
				insertExercise(connection, i);
			} catch (SQLException e) {
				System.out.println("[ERROR] insering exercise number " + i + ": " + e.toString());
			}
		}

	}

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("usage: give the 'path/to/data' als first parameter.");
			System.exit(1);
		}

		pathToData = args[0];
		System.out.println("[INFO] data directory is " + pathToData);

		AdminDB adminDB = new AdminDB();
		try {
			PostgreSQLAccess access = new PostgreSQLAccess();
			adminDB.createJavaStarSchema(access);
			Connection conn = access.getConnection();
			adminDB.createTableAccounts(conn);
			adminDB.createTableComments(conn);
			adminDB.createTableExercises(conn);
			adminDB.insertExcercise(conn);
			adminDB.insertAdmin(conn);
			adminDB.createStatisticsExercises(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String readFile(String filename) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	// ========================================================= //
	// SQL statements

	String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS \"account\" (" + "userid CHAR(32) PRIMARY KEY NOT NULL, "
			+ "email  CHAR(32) NOT NULL, " + "password CHAR(16) NOT NULL, " + "active CHAR(1) NOT NULL DEFAULT 'Y',"
			+ "admin CHAR(1) NOT NULL DEFAULT 'N'" + ")";

	String CREATE_COMMENTS_TABLE = "CREATE TABLE IF NOT EXISTS \"comments\" (" + "id        SERIAL PRIMARY KEY,"
			+ "time      TIMESTAMP  WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, " + "userid    CHAR(32)   NOT NULL, "
			+ "comment   CHAR(512)  NOT NULL  " + ")";

	String CREATE_EXCERCISE_TABLE = "CREATE TABLE IF NOT EXISTS \"excercise\" ("
			+ "id                  INTEGER       PRIMARY KEY NOT NULL, "
			+ "exercise_text       VARCHAR(1024)             NOT NULL, "
			+ "exercise_out        VARCHAR(1024)             NOT NULL,"
			+ "exercise_solution   VARCHAR(2048)             NOT NULL,"
			+ "exercise_test       VARCHAR(2048)             NOT NULL" + ")";

	String CREATE_STATISTICS_TABLE = "CREATE TABLE IF NOT EXISTS \"statistics\" ("
			+ "userid              CHAR(32)  NOT NULL,  " + "exerciseid          INTEGER   NOT NULL, "
			+ "tried_to_solved     BOOL      NOT NULL DEFAULT FALSE, "
			+ "compile_error       BOOL      NOT NULL DEFAULT FALSE,"
			+ "test_error          BOOL      NOT NULL DEFAULT FALSE,"
			+ "FOREIGN KEY (userid) REFERENCES account(userid)" + ")";

}
