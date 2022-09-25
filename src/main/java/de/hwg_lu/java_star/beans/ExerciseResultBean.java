package de.hwg_lu.java_star.beans;

/**
 * Class to store the result of the verification of an Exercise.
 * 
 * <br>
 * It stores all the required information to be displayed by ExerciseResultView.jsp
 *
 */
public class ExerciseResultBean {
	/**
	 *  output of the request sent to compiler-explorer
	 */
	String output = "";
	/**
	 * input source code
	 */
	String sourceCode = "";
	/**
	 * source code used to the the class written by the user.
	 */
	String testCode = "";
	
	/**
	 * flag to indicate there was a compilation error
	 */
	boolean compilationError = false;
	/**
	 * flag to indicate there was an error running the tests.
	 */
	boolean testError = false;
	
	/**
	 * Id of the exercise: it is unique.
	 */
	int exerciseId = 0;
	
	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}
	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}
	/**
	 * @return the sourceCode
	 */
	public String getSourceCode() {
		return sourceCode;
	}
	/**
	 * @param sourceCode the sourceCode to set
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	/**
	 * @return the testCode
	 */
	public String getTestCode() {
		return testCode;
	}
	/**
	 * @param testCode the testCode to set
	 */
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	/**
	 * @return the compilationError
	 */
	public boolean isCompilationError() {
		return compilationError;
	}
	/**
	 * @param compilationError the compilationError to set
	 */
	public void setCompilationError(boolean compilationError) {
		this.compilationError = compilationError;
	}
	/**
	 * @return the testError
	 */
	public boolean isTestError() {
		return testError;
	}
	/**
	 * @param testError the testError to set
	 */
	public void setTestError(boolean testError) {
		this.testError = testError;
	}
	/**
	 * @return the exerciseId
	 */
	public int getExerciseId() {
		return exerciseId;
	}
	/**
	 * @param exerciseId the exerciseId to set
	 */
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	

	
}
