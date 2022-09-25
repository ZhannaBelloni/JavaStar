package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class to retrieve data for a given exercise in order to test the solution of the user. 
 *
 */
public class ExcerciseDB {
	
	/**
	 * Get the text of the exercise.
	 * 
	 * @param exerciseNumber exercise id
	 * @return the text of the exercise
	 * @throws SQLException
	 */
	public String getExcerciseText(Integer exerciseNumber) throws SQLException {
		
		String sql = "SELECT exercise_text FROM excercise WHERE id = ?";
		System.out.println("[INFO] " +sql + ", id = " + exerciseNumber);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			dbConn.close();
			return dbRes.getString(1);
		}
		dbConn.close();
		return null;
	}
	
	/**
	 * Get the exercise expected output.
	 * @param exerciseNumber exercise id
	 * @return
	 * @throws SQLException
	 */
	public String getExcericeSolution(Integer exerciseNumber) throws SQLException {
		String sql = "SELECT exercise_out FROM excercise WHERE id = ?";
		System.out.println("[INFO] " +sql + ", id = " + exerciseNumber);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			dbConn.close();
			return dbRes.getString(1).trim();
		}
		
		dbConn.close();
		
		return "";
	}
	
	/**
	 * Get the sourceCode of the tester to test user solution.
	 * @param exerciseNumber exercise id
	 * @return
	 * @throws SQLException
	 */
	public String getExcericeTestSourceCode(Integer exerciseNumber) throws SQLException {
		String sql = "SELECT exercise_test FROM excercise WHERE id = ?";
		System.out.println(sql + ", id = " + exerciseNumber);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			dbConn.close();
			return dbRes.getString(1).trim();
		}
		
		dbConn.close();
		return "";
	}
	
	/**
	 * get the solution of the exercise saved in the database.
	 * 
	 * @param exerciseNumber exercise id
	 * @return solution of the exercise.
	 * @throws SQLException
	 */
	public String getExcericeSourceCodeSolution(Integer exerciseNumber) throws SQLException {
		String sql = "SELECT exercise_solution FROM excercise WHERE id = ?";
		System.out.println(sql + ", id = " + exerciseNumber);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			dbConn.close();
			return dbRes.getString(1).trim();
		}
		
		dbConn.close();
		return "";
	}
	
	/**
	 * Get the total number of exercises saved in the database.
	 * @return
	 * @throws SQLException
	 */
	public int getNumberExcerice() throws SQLException {
		String sql = "SELECT COUNT(*) FROM excercise";
		System.out.println(sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			dbConn.close();
			return dbRes.getInt(1);
		}
		
		dbConn.close();
		return 0;
	}
	
}
