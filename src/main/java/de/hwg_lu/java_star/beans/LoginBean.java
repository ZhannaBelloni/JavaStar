package de.hwg_lu.java_star.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.NoConnectionException;
import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;
import de.hwg_lu.java_star.utils.UserStatistics;

public class LoginBean {

	String userid;
	String password;
	boolean isLoggedIn;
	
	UserStatistics userStatistics;

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }


	public LoginBean() {
		this.userid = "";
		this.password = "";

		this.isLoggedIn = false;
	}
	
	public boolean checkUseridPassword() throws NoConnectionException, SQLException{
		//true  - ein Datensatz mit this.userid und this.password existiert in DB in table account
		//false - ein Datensatz mit this.userid existiert nicht in DB in table account
		String sql = "SELECT * FROM account where userid = ? and password = ?";
		System.out.println(sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.userid);
		prep.setString(2, this.password);
		ResultSet dbRes = prep.executeQuery();
		if (dbRes.next()) {
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
	
}
