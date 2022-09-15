package de.hwg_lu.java_star.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.NoConnectionException;
import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;

public class LoginBean {

	String userid;
	String password;
	int numberExDone;
	int correctEx;
	boolean isLoggedIn;

	public LoginBean() {
		this.userid = "";
		this.password = "";
		this.numberExDone = 0;
		this.correctEx = 0;
		this.isLoggedIn = false;
	}
	
	public boolean checkUseridPassword() throws NoConnectionException, SQLException{
		//true  - ein Datensatz mit this.userid und this.password existiert in DB in table account
		//false - ein Datensatz mit this.userid existiert nicht in DB in table account
		String sql = "SELECT totalExcercises, correctExcercise FROM account where userid = ? and password = ?";
		System.out.println(sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.userid);
		prep.setString(2, this.password);
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
			this.numberExDone = dbRes.getInt(1);
			this.correctEx = dbRes.getInt(2);
			return true;
		}
		
		return false;
	}
	
	public String getCheckLoggedIn(){
		if (!this.isLoggedIn())
			return "<meta http-equiv='refresh' content='0; URL=./HomePageView.jsp'>\n";
		else return "";
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public int getNumberExDone() {
		return numberExDone;
	}

	public void setNumberExDone(int numberExDone) {
		this.numberExDone = numberExDone;
	}

	public int getCorrectEx() {
		return correctEx;
	}

	public void setCorrectEx(int correctEx) {
		this.correctEx = correctEx;
	}
	
}
