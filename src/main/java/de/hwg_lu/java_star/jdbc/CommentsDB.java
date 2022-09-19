package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.hwg_lu.java_star.utils.Comment;

public class CommentsDB {
	
	static public ArrayList<Comment> getComments(Integer last) throws SQLException {
	    ArrayList<Comment> comments = new ArrayList<>();
	    String sql = "SELECT userid, comment, time FROM bwi420_6xxxxx.comments ORDER BY time DESC ";
	    if (last != null && last > 0) {
	        sql += " LIMIT " + last;
	    }
	    System.out.println(sql);
	    Connection dbConn = new PostgreSQLAccess().getConnection();
	    PreparedStatement prep = dbConn.prepareStatement(sql);
	    prep.execute();
	        
	    ResultSet dbRes = prep.executeQuery();
	    while (dbRes.next()) {
	        comments.add(new Comment(dbRes.getString(1), dbRes.getString(2), dbRes.getTimestamp(3).toString()));
	    }
        dbConn.close();
	    return comments;
	}
	
	public void insertComment(String userid, String comment) throws SQLException {
	    String sql = "INSERT INTO bwi420_6xxxxx.comments (userid, comment) VALUES (?, ?)";
	    System.out.println(sql + ": " + userid + ", " + comment);
        Connection dbConn = new PostgreSQLAccess().getConnection();
        PreparedStatement prep = dbConn.prepareStatement(sql);
        prep.setString(1, userid);
        prep.setString(2, comment);
        prep.executeUpdate();
        System.out.println(" ... DONE");
	}
	
}
