package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.hwg_lu.java_star.utils.Comment;

public class CommentsDB {
	
	public ArrayList<Comment> getComments(Integer last) throws SQLException {
	    ArrayList<Comment> comments = new ArrayList<>();
	    String sql = "SELECT userid, comment FROM bwi420_6xxxxx.excercise ORDER BY time DESC ";
	    if (last != null && last > 0) {
	        sql += " LIMIT " + last;
	    }
	    System.out.println(sql);
	    Connection dbConn = new PostgreSQLAccess().getConnection();
	    PreparedStatement prep = dbConn.prepareStatement(sql);
	    prep.execute();
	        
	    ResultSet dbRes = prep.executeQuery();
	    if (dbRes.next()) {
	        comments.add(new Comment(dbRes.getString(1), dbRes.getString(2)));
	    }
        dbConn.close();
	    return comments;
	}
	
}
