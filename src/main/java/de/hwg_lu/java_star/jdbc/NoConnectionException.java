package de.hwg_lu.java_star.jdbc;


import java.sql.SQLException;

@SuppressWarnings("serial")
/**
 * Utility to identify when a Database is not available.
 *
 */
public class NoConnectionException extends SQLException {
	
	public NoConnectionException(){
		super("JDBC-Connection was not succesful: ");
	}
	public NoConnectionException(String msg){
		super("JDBC-Connection was not succesful: " + msg);
	}
	
}
