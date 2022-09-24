package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcerciseDB {
	
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
