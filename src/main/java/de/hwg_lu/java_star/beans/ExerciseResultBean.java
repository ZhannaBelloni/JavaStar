package de.hwg_lu.java_star.beans;

public class ExerciseResultBean {
	String output = "";
	String sourceCode = "";
	String testCode = "";
	boolean compilationError = false;
	boolean testError = false;
	int exerciseId = 0;
	
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public boolean isCompilationError() {
		return compilationError;
	}
	public void setCompilationError(boolean compilationError) {
		this.compilationError = compilationError;
	}
	public boolean isTestError() {
		return testError;
	}
	public void setTestError(boolean testError) {
		this.testError = testError;
	}
	public int getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	
}
