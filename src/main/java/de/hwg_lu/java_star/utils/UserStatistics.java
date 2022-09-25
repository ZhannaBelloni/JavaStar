package de.hwg_lu.java_star.utils;

import java.util.HashMap;

/**
 * Class to store the statistics all all exercise for a given user.
 * 
 * NOTE: This class is not really used. It might be useful to avoid to ask the database at every generation of the Side Navigation bar.
 *
 */
public class UserStatistics {
    
    public UserStatistics(String id) {
        userid = id;
    }
    
    String userid = "";
    HashMap<Integer, ExerciseStatistics> statistics = new HashMap<Integer, ExerciseStatistics>();
    
    public ExerciseStatistics getExerciseStatistics(int exerciseId) {
        return statistics.get(exerciseId);
    }
    
    public void addStatistics(int exerciseId, ExerciseStatistics exerciseStatistics) {
        statistics.put(exerciseId, exerciseStatistics);
    }
    
}
