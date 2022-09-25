package de.hwg_lu.java_star.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.NoConnectionException;
import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;
import de.hwg_lu.java_star.utils.UserStatistics;

/**
 * Bean to store information about logged in user.
 * 
 * <br>
 * It store the username and password and a flag to indicate if the user is an administrator.
 *
 */
public class LoginBean {

	String userid;
	String password;
	boolean isLoggedIn;
	boolean isAdmin = false;
	
	/* it the application is slow, it might be better to store more information,
	 * instead of sending continuously requests the database.
	UserStatistics userStatistics;

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }
    */


	/**
	 * default constructor for jsp files.
	 */
	public LoginBean() {
		this.userid = "";
		this.password = "";
		this.isLoggedIn = false;
		this.isAdmin = false;

	}
	
	/**
	 * check if a user is registered in the account table.
	 * 
	 * This function will also set the flag isAdmin to the retrieved value.
	 * @return true if username + password match.
	 * @throws NoConnectionException
	 * @throws SQLException
	 */
	public boolean checkUseridPassword() throws NoConnectionException, SQLException{
		//true  - ein Datensatz mit this.userid und this.password existiert in DB in table account
		//false - ein Datensatz mit this.userid existiert nicht in DB in table account
		String sql = "SELECT userid, password, admin FROM account where userid = ? and password = ?";
		System.out.println("[INFO] " + sql);
		Connection dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = dbConn.prepareStatement(sql);
		prep.setString(1, this.userid);
		prep.setString(2, this.password);
		ResultSet dbRes = prep.executeQuery();
		dbConn.close();
		if (dbRes.next()) {
			this.userid = dbRes.getString(1);
			this.isLoggedIn = true;
			String resultAdminColumn = dbRes.getString(3);
			this.isAdmin = "Y".equals(resultAdminColumn);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if a user is an admin - this is similar to the function checkUseridPassword<br>
	 * TODO: maybe might be deleted.
	 * @return true if user+ password corresponds to an administrator.
	 * @throws NoConnectionException
	 * @throws SQLException
	 */
	public boolean checkUseridIsAdmin() throws NoConnectionException, SQLException{
		String sql = "SELECT userid, password, admin FROM account where userid = ? and password = ? and admin == 'Y'";
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

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	
}
