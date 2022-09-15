package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCAccess {

	Connection dbConn;
	String dbDrivername;
	String dbURL;
	String dbUser;
	String dbPassword;
	String dbSchema; 
	
	
	public JDBCAccess() throws NoConnectionException{
		this.setDBParms();
		this.createConnection();
		// this.setSchema();
	}
	public abstract void setDBParms();

	public void setSchema() throws NoConnectionException {
		try{

			String sql = "SET SCHEMA '" + dbSchema + "'";
			// System.out.println(sql);
			dbConn.createStatement().executeUpdate(sql);
			// System.out.println("Schema " + dbSchema + " erfolgreich gesetzt");
		}catch(SQLException se){
			se.printStackTrace();
			throw new NoConnectionException();
		}
	}
	
	public Connection createConnection() throws NoConnectionException{
		try{
			Class.forName(dbDrivername);
			System.out.println("jdbc driver has been loaded");
		
			dbConn = DriverManager.getConnection(
												dbURL,
												dbUser,
												dbPassword
												);
			System.out.println("  * connection was successful");
			return dbConn;
		}catch(Exception e){
			e.printStackTrace();
			throw new NoConnectionException(e.toString());
		}
	}
	
	public Connection getConnection() throws NoConnectionException {
		try{
			this.setSchema();
			return dbConn;
		}catch(SQLException se){
			se.printStackTrace();
			throw new NoConnectionException();
		}
	}

}
