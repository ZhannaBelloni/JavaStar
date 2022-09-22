package de.hwg_lu.java_star.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatisticsDB {
    
    public static enum ExerciseTag {
        NO_ERROR,
        NOT_DONE,
        COMPILE_ERROR,
        TEST_ERROR
    }
    
    public static String WHITE = "#FFFFFF";

    public String getColorForExcercise(String userid, Integer exerciseId) {
        try {
            String sql = "SELECT tried_to_solved, compile_error, test_error  FROM bwi420_6xxxxx.statistics WHERE userid = ? AND exerciseid = ?";
            System.out.println(sql + ", id = " + exerciseId + ", userid = " + userid);
            Connection dbConn = new PostgreSQLAccess().getConnection();
            PreparedStatement prep = dbConn.prepareStatement(sql);
            prep.setString(1, userid);
            prep.setInt(2, exerciseId);
            prep.execute();

            ResultSet dbRes = prep.executeQuery();
            if (dbRes.next()) {
                dbConn.close();
                if (!dbRes.getBoolean(1)) {
                    return "#FFFFFF"; // white
                } else if (dbRes.getBoolean(2)) {
                    return "#ff8000"; // orange
                } else if (dbRes.getBoolean(3)) {
                    return "#ff0000"; // red
                } else {
                    return "#00ff04"; // green
                }
            }
            dbConn.close();
        } catch (SQLException e) {
            return "#FFFFFF";
        }
        return "#FFFFFF";

    }
    
    public ArrayList<String> getColorForAllExcercise(String userid, Integer totalNumberExcercises) {
        ArrayList<String> colors = new ArrayList<String>();
        String color = WHITE;
        for (int i = 0; i <= totalNumberExcercises; ++i ) {colors.add(color);}
    	try {
            String sql = "SELECT exerciseid, tried_to_solved, compile_error, test_error  FROM bwi420_6xxxxx.statistics WHERE userid = ?";
            System.out.println(sql + " userid = " + userid);
            Connection dbConn = new PostgreSQLAccess().getConnection();
            PreparedStatement prep = dbConn.prepareStatement(sql);
            prep.setString(1, userid);
            prep.execute();

            ResultSet dbRes = prep.executeQuery();
            while (dbRes.next()) {
                dbConn.close();
                int exNum = dbRes.getInt(1);
                if (!dbRes.getBoolean(2)) {
                    color = "#FFFFFF"; // white
                } else if (dbRes.getBoolean(3)) {
                	color = "#ff8000"; // orange
                } else if (dbRes.getBoolean(4)) {
                	color = "#ff0000"; // red
                } else {
                	color = "#00ff04"; // green
                }
                colors.add(exNum, color);
            }
            dbConn.close();
        } catch (SQLException e) {
            return colors;
        }
        return colors;

    }

    public void updateExcerciseForUser(String userid, Integer exerciseId, ExerciseTag tag) throws SQLException {
        String sql = "SELECT tried_to_solved, compile_error, test_error  FROM bwi420_6xxxxx.statistics WHERE userid = ? AND exerciseid = ?";

        Connection dbConn = new PostgreSQLAccess().getConnection();
        PreparedStatement prepSelect = dbConn.prepareStatement(sql);
        prepSelect.setString(1, userid);
        prepSelect.setInt(2, exerciseId);
        prepSelect.execute();

        ResultSet dbRes = prepSelect.executeQuery();
        
        boolean tried_to_solved = true;
        boolean compile_error;
        boolean test_error;
        
        if (tag == ExerciseTag.COMPILE_ERROR) {
            compile_error = true;
            test_error = true;
        } else if (tag == ExerciseTag.TEST_ERROR) {
            compile_error = false;
            test_error = true;
        } else if (tag == ExerciseTag.NO_ERROR) {
            compile_error = false;
            test_error = false;
        } else {
            tried_to_solved = false;
            compile_error = false;
            test_error = false;
        }
        

        if (dbRes.next()) {  // Exercise already saved: update it
            String updateSQL = "UPDATE statistics SET tried_to_solved = ?, compile_error = ?, test_error = ? WHERE userid = ? AND exerciseid = ?";
            PreparedStatement prepUpdate = dbConn.prepareStatement(updateSQL);
            prepUpdate.setBoolean(1, tried_to_solved);
            prepUpdate.setBoolean(2, compile_error);
            prepUpdate.setBoolean(3, test_error);
            prepUpdate.setString(4, userid);
            prepUpdate.setInt(5, exerciseId);
            prepUpdate.execute();
        } else {   // insert it
            String insertSQL = "INSERT INTO statistics (userid, exerciseid, tried_to_solved, compile_error, test_error) VALUES (?, ? ,?, ?, ?)";
            PreparedStatement prepInsert = dbConn.prepareStatement(insertSQL);
            prepInsert.setString(1, userid);
            prepInsert.setInt(2, exerciseId);
            prepInsert.setBoolean(3, tried_to_solved);
            prepInsert.setBoolean(4, compile_error);
            prepInsert.setBoolean(5, test_error);
            prepInsert.execute();
        }

    }

}
