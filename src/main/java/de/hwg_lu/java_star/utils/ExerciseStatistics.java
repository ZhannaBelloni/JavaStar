package de.hwg_lu.java_star.utils;

public class ExerciseStatistics {

    private int exerciseID;
    private boolean attempted = false;
    private boolean compile_error = false;
    private boolean test_error = false;
    
    public ExerciseStatistics(int exerciseId) {
        this.exerciseID = exerciseId;
    }
    
    public int getExerciseID() {
        return exerciseID;
    }
    public boolean isAttempted() {
        return attempted;
    }
    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }
    public boolean isCompileError() {
        return compile_error;
    }
    public void setCompileError(boolean compile_error) {
        this.compile_error = compile_error;
    }
    public boolean isTestError() {
        return test_error;
    }
    public void setTestError(boolean test_error) {
        this.test_error = test_error;
    }
    
    
    
}
