<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.jdbc.StatisticsDB"%>
<%@page import="java.util.regex.*"%>

<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
<%@page import="de.hwg_lu.java_star.beans.ExerciseResultBean"%>
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
	
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="../css/excercise.css?2">
<link rel='stylesheet' href='../css/sidebar.css'>
<link rel='stylesheet' href='../css/topnav.css'>
<script type="text/javascript" src="../js/helper.js"></script>

<title>Exercise: Results</title>
</head>

<jsp:useBean id="tester" class="de.hwg_lu.java_star.excercises.Tester" />
<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />
<jsp:useBean id="exerciseResultBean" class="de.hwg_lu.java_star.beans.ExerciseResultBean"
	scope="session" />

<body>

	<%
	String exerciseNumber = request.getParameter("exerciseNum");
	Integer numEx = Integer.parseInt(exerciseNumber);
	ExcerciseDB ex = new ExcerciseDB();
	StatisticsDB stat = new StatisticsDB();
	String expectedOut = ex.getExcericeSolution(numEx);
	String sourceCode = request.getParameter("sourceCode");
	String testClass = tester.getTextCodeForSource(sourceCode, numEx);
	String executionOut = "";
	String compilationOutput = "";
	String output = "";
	boolean compilationError = false;
	boolean testError = false;


	compilationOutput = tester.compileExcercise(sourceCode, numEx);

	if (compilationOutput.contains("Compiler exited with result code")
			&& !compilationOutput.contains("should be declared in a file named")) {
		compilationError = true;
		testError = true;
		stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.COMPILE_ERROR);
		output = compilationOutput.replace("Compiler exited with result code", "\nCompiler exited with result code\n").replace("error:", "\nerror:\n");

	} else {
		compilationError = false;
		executionOut = tester.testExcerciseSourceCodeWithtesterClass(testClass);

		// out.print("##################################<br>");
		output = executionOut.replace("error:", "\nerror:\n");
		
		int indexOut = executionOut.indexOf("out:");

		if (!compilationError && !executionOut.isEmpty() && executionOut.substring(indexOut + 4).equals(expectedOut)) {
			testError = false;
			stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.NO_ERROR);

		} else {
			testError = true;
			stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.TEST_ERROR);
		}

	}

	exerciseResultBean.setCompilationError(compilationError);
	exerciseResultBean.setTestError(testError);
	exerciseResultBean.setTestCode(testClass);
	exerciseResultBean.setOutput(output);
	exerciseResultBean.setSourceCode(sourceCode);
	exerciseResultBean.setExerciseId(numEx);
	
	response.sendRedirect("./ExerciseResultView.jsp");

	
	%>

</body>
</html>