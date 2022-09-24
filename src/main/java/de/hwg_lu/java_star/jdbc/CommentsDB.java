package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.hwg_lu.java_star.utils.Comment;

public class CommentsDB {
	
	static public ArrayList<Comment> getComments(Integer last) throws SQLException, NoConnectionException {
	    ArrayList<Comment> comments = new ArrayList<>();
	    String sql = "SELECT userid, comment, time, id FROM comments ORDER BY time DESC ";
	    if (last != null && last > 0) {
	        sql += " LIMIT " + last;
	    }
	    System.out.println(sql);
	    Connection dbConn = new PostgreSQLAccess().getConnection();
	    PreparedStatement prep = dbConn.prepareStatement(sql);
	    prep.execute();
	        
	    ResultSet dbRes = prep.executeQuery();
	    while (dbRes.next()) {
	        comments.add(new Comment(dbRes.getInt(4), dbRes.getString(1), dbRes.getString(2), dbRes.getTimestamp(3).toString()));
	    }
        dbConn.close();
	    return comments;
	}
	
	public void insertComment(String userid, String comment) throws SQLException {
	    String sql = "INSERT INTO comments (userid, comment) VALUES (?, ?)";
	    System.out.println(sql + ": " + userid + ", " + comment);
        Connection dbConn = new PostgreSQLAccess().getConnection();
        PreparedStatement prep = dbConn.prepareStatement(sql);
        prep.setString(1, userid);
        prep.setString(2, comment);
        prep.executeUpdate();
        System.out.println(" ... DONE");
	}
	
	// delete comment replace the text with 'comment delete by the admin'
	public void deleteComment(int commentId) throws SQLException {
	    String sql = "UPDATE comments SET comment = 'comment delete by the admin' WHERE id = ?";
	    System.out.println(sql + ": " + commentId);
        Connection dbConn = new PostgreSQLAccess().getConnection();
        PreparedStatement prep = dbConn.prepareStatement(sql);
        prep.setInt(1, commentId);
        prep.executeUpdate();
        System.out.println(" ... DONE");
	}
	
}
