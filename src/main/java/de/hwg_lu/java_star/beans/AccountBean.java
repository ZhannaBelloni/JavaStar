package de.hwg_lu.java_star.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.NoConnectionException;
import de.hwg_lu.java_star.jdbc.PostgreSQLAccess;
import de.hwg_lu.java_star.utils.UserStatistics;

/**
 * class to store information about an account, with functionalities.
 * 
 * <br>
 * <uL> 
 *    <li>to insert the account into the TABLE account.</li>
 *    <li>check if a combination of username and password can be found in the account table</li>
 * </ul> 
 */
public class AccountBean {

	String userid;
	String password;
	String active;
	String admin;
	String email;
	
	
	/**
	 * Default constructor, to be used in jsp files
	 */
    public AccountBean() {
		this.userid = "";
		this.password = "";
		this.active = "";
		this.admin = "";
		this.email = "";
	}
    
    /**
     * if a combination of username and password can be found in the account table, <br>
     * i.e. if the user can login.
     * @param connection connection to the database
     * @return true if the user is authorized, false otherwise
     * @throws NoConnectionException there are problem connecting with the database
     * @throws SQLException jdbc thrown an exception.
     */
    public boolean isEmailOrUseridPresent(Connection connection) throws NoConnectionException, SQLException {
        String sql = "SELECT * FROM account where email = ? or userid = ?";
        System.out.println("[INFO] " +sql);
        Connection dbConn = new PostgreSQLAccess().getConnection();
        PreparedStatement prep = dbConn.prepareStatement(sql);
        prep.setString(1, this.email);
        prep.setString(2, this.userid);
        ResultSet dbRes = prep.executeQuery();
        dbConn.close();
        if (dbRes.next()) {
            return true;
        }
        return false;
    }

    /**
     * insert the account into the database: no check if the user is already register is made.
     * @param connection connection to the database
     * @throws NoConnectionException there are problem connecting with the database
     * @throws SQLException jdbc thrown an exception.
     */
	public void insertAccount(Connection connection) throws NoConnectionException, SQLException {
		String sql = "insert into account " + "(userid, password, active, admin, email) " + "values (?,?,?,?,?)";
		System.out.println("[INFO] " +sql);
		// Connection connection = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setString(1, this.userid);
		prep.setString(2, this.password);
		prep.setString(3, this.active);
		prep.setString(4, this.admin);
		prep.setString(5, this.email);
		prep.executeUpdate();
		System.out.println("Account " + this.userid + " erfolgreich angelegt");
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
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * check if a user is the admin
	 * @return true if the user is the admin.
	 */
	public boolean isAdmin() {
		return "Y".equals(admin);
	}
	
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
