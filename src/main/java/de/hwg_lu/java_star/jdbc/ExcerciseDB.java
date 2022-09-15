package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcerciseDB {
	
	public String getExcerciseText(Integer exerciseNumber) throws SQLException {
		
		String sql = "SELECT exercise_text FROM bwi420_6xxxxx.excercise WHERE id = ?";
		System.out.println(sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			return dbRes.getString(1);
		}
		
		return "";
	}
	
	public String getExcericeSolution(Integer exerciseNumber) throws SQLException {
		String sql = "SELECT exercise_out FROM bwi420_6xxxxx.excercise WHERE id = ?";
		System.out.println(sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setInt(1, exerciseNumber);
		prep.execute();
		
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			return dbRes.getString(1).trim();
		}
		
		return "";
	}
}
