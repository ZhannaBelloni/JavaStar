package de.hwg_lu.java_star.beans;

public class CodeExcerciseBean {
	
	String sourceCode;
	int exerciseNumber;
	
	CodeExcerciseBean() {
		this.sourceCode = "";
		this.exerciseNumber = -1;
	}
	
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public int getExerciseNumber() {
		return exerciseNumber;
	}
	public void setExerciseNumber(int exerciseNumber) {
		this.exerciseNumber = exerciseNumber;
	}
	
	public boolean isExerciseValid() {
		return this.exerciseNumber > 0;
	}

}
