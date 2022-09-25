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

/**
 * Class used to initialize the System.
 * 
 * <br>
 * This class can be run as java application and needs a command line argument, i.e.
 * the full path to the directory data, where are saved the files '.txt' for the exercises 
 * 
 * Create the needed TABLES:
 * <ul>
 *   <li> account</li>
 *   <li> comments</li>
 *   <li> exercises</li>
 *   <li> statistics for the exercises</li>
 * </ul>
 * Inserts:
 * <ul>
 *   <li> "admin" user</li>
 *   <li> exercises data</li>
 * </ul> 
 *
 * @author 
 *
 */
public class AdminDB {

	static String pathToData = "";

	/**
	 * Create the schema if exists
	 * @param conn connection to the Database
	 * @param schemaName schema name to create
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	private void createSchema(Connection conn, String schemaName) throws SQLException {
		System.out.println("[INFO] Creating schema " + schemaName);
		String sql = "CREATE SCHEMA IF NOT EXISTS \"" + schemaName + "\"";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.executeUpdate();
	}

	/**
	 * Create the schema for the application Java Star
	 * @param jdbcAccess class containing information for the connection alonge with the SCHEMA name to be used.
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	public void createJavaStarSchema(PostgreSQLAccess jdbcAccess) throws SQLException {
		System.out.println("[INFO] Creating schema for 'JAVA_STAR' application");
		this.createSchema(jdbcAccess.createConnection(), jdbcAccess.getSchema());

	}

	/**
	 * Create the table for the account: do not close the connection, it will be used by others
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	public void createTableAccounts(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application accounts");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_ACCOUNT_TABLE);
		prep.executeUpdate();
	}

	/**
	 * Create the table for the comments: do not close the connection, it will be used by others
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	public void createTableComments(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application comments");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_COMMENTS_TABLE);
		prep.executeUpdate();
	}

	/**
	 * Create the table for the exercises: do not close the connection, it will be used by others
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	public void createTableExercises(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application excercises");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_EXCERCISE_TABLE);
		prep.executeUpdate();
	}

	/**
	 * Create the table for the comments: do not close the connection, it will be used by others
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
	public void createStatisticsExercises(Connection connection) throws SQLException {
		System.out.println("[INFO] Creating table for 'JAVA_STAR' application statistics");
		PreparedStatement prep = connection.prepareStatement(this.CREATE_STATISTICS_TABLE);
		prep.executeUpdate();
	}

	/**
	 * Insert the admin user, if not present in the table account.<br>
	 * 
	 * It will print to the console warning, errors and information.
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
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

	/**
	 * Insert into the TABLE "exercises" all information for a given exercise.<br>
	 * 
	 * It reads the files from the directory AdminDB.pathToData and insert into the table.
	 * 
	 * @param connection connection to the Database
	 * @param number exercise ID: it must be unique. <br>
	 *        NOTE: if an exercise with the same id is already present, it will not be updated
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
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

	/**
	 * Inserts all exercises in the directory AdminDB.pathToData in the Database.
	 * @param connection connection to the Database
	 * @throws SQLException thrown by JDBC driver, if something went wrong
	 */
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

	/**
	 * Application to initialize Java Star.
	 * 
	 * 
	 * @param args: it needs the first parameter to be set to 'path/to/data'
	 */
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

	/**
	 * Utility to read a file into a String.
	 * @param filename full path to the File to be read.
	 * @return Content of the File.
	 */
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

	/**
	 * SQL statement to create TABLE account:
	 * the table saves registration data
	 */
	String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS \"account\" (" + "userid CHAR(32) PRIMARY KEY NOT NULL, "
			+ "email  CHAR(32) NOT NULL, " + "password CHAR(16) NOT NULL, " + "active CHAR(1) NOT NULL DEFAULT 'Y',"
			+ "admin CHAR(1) NOT NULL DEFAULT 'N'" + ")";

	/**
	 * SQL statement to create TABLE comments
	 * the table saves comments sent by users
	 */
	String CREATE_COMMENTS_TABLE = "CREATE TABLE IF NOT EXISTS \"comments\" (" + "id        SERIAL PRIMARY KEY,"
			+ "time      TIMESTAMP  WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, " + "userid    CHAR(32)   NOT NULL, "
			+ "comment   CHAR(512)  NOT NULL  " + ")";

	/**
	 * SQL statement to create TABLE excercise
	 * the table save information about the exercises in the Web Application
	 */
	String CREATE_EXCERCISE_TABLE = "CREATE TABLE IF NOT EXISTS \"excercise\" ("
			+ "id                  INTEGER       PRIMARY KEY NOT NULL, "
			+ "exercise_text       VARCHAR(1024)             NOT NULL, "
			+ "exercise_out        VARCHAR(1024)             NOT NULL,"
			+ "exercise_solution   VARCHAR(2048)             NOT NULL,"
			+ "exercise_test       VARCHAR(2048)             NOT NULL" + ")";

	/**
	 * SQL statement to create TABLE statistics
	 * the table saves the statistics about the individual exercises for the registered users.
	 */
	String CREATE_STATISTICS_TABLE = "CREATE TABLE IF NOT EXISTS \"statistics\" ("
			+ "userid              CHAR(32)  NOT NULL,  " 
			+ "exerciseid          INTEGER   NOT NULL, "
			+ "tried_to_solved     BOOL      NOT NULL DEFAULT FALSE, "
			+ "compile_error       BOOL      NOT NULL DEFAULT FALSE,"
			+ "test_error          BOOL      NOT NULL DEFAULT FALSE,"
			+ "FOREIGN KEY (userid) REFERENCES account(userid)" + ")";

}
