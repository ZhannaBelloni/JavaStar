package de.hwg_lu.java_star.utils;

import java.util.HashMap;

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
