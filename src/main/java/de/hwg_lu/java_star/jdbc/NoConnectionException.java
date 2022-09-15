package de.hwg_lu.java_star.jdbc;


import java.sql.SQLException;

@SuppressWarnings("serial")
public class NoConnectionException extends SQLException {
	
	public NoConnectionException(){
		super("JDBC-Connection was not succesful: ");
	}
	public NoConnectionException(String msg){
		super("JDBC-Connection was not succesful: " + msg);
	}
	
}
