<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.jdbc.StatisticsDB"%>
<%@page import="java.util.regex.*"%>

<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
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

<body>

	<%
	// Force login for this page.
	if (!loginBean.isLoggedIn()) {
		response.sendRedirect("./LoginView.jsp");
	} else {
		try {
			out.println(GuiBean.getNavigationElements(loginBean.isLoggedIn(), loginBean.getUserid()));
		} catch (SQLException e) {
			response.sendRedirect("./LoginView.jsp");
		}
	}
	%>

	<div class='main'>
	<%
	String exerciseNumber = request.getParameter("exerciseNum");
	Integer numEx = Integer.parseInt(exerciseNumber);
	ExcerciseDB ex = new ExcerciseDB();
	StatisticsDB stat = new StatisticsDB();
	String expectedOut = ex.getExcericeSolution(numEx);
	String sourceCode = request.getParameter("sourceCode");
	String executionOut = "";
	String compilationOutput = "";
	boolean compilationError = false;

	compilationOutput = tester.compileExcercise(sourceCode, numEx);

	if (compilationOutput.contains("Compiler exited with result code")
			&& !compilationOutput.contains("should be declared in a file named")) {
		out.print("Try again there was an error<br>");
		compilationError = true;
		stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.COMPILE_ERROR);
		compilationOutput = compilationOutput.replace("error:", "\nerror:");
		String pattern = ".{1,80}(?!\\S)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(compilationOutput);

		out.print("<pre style='background-color: #FFFF; margin-left:3% ; margin-right:3%'><code>");
		while (m.find()) {
			out.println(m.group(0).trim());
		}
		out.println("</code></pre>");

	} else {
		out.print("Compiled: Very good!<br>");

		executionOut = tester.testExcercise(sourceCode, numEx);

		// out.print("##################################<br>");
		executionOut = executionOut.replace("error:", "\nerror:");
		String pattern = ".{1,80}(?!\\S)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(executionOut);

		out.print("<pre style='background-color: #FFFF; margin-left:3% ; margin-right:3%'><code>");
		while (m.find()) {
			out.println(m.group(0).trim());
		}
		out.println("</code></pre>");

		int indexOut = executionOut.indexOf("out:");

		if (!compilationError && !executionOut.isEmpty() && executionOut.substring(indexOut + 4).equals(expectedOut)) {
			out.print("Very good!<br>");
			stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.NO_ERROR);

		} else {
			out.print("Error!<br>");
			stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.TEST_ERROR);

		}

	}
	%>

	<br>
	<br>
	<br>
	<div style="display: flex;">

		<form action='./ExerciseView.jsp' method='get'>
			<!-- input type='submit' value='previous'/-->
			<input type='image' alt='Submit' value='home'
				src='../images/previous.png' width='48' height='48' />
			<%
			out.println("<input type='hidden' name='exerciseNum' value='" + (numEx - 1) + "'/>");
			%>
		</form>

		<%
out.println("<form action='./ExerciseView.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + exerciseNumber + " />");
out.println("<input type='hidden' name='sourceCode' value='" + sourceCode + "'/>");
out.println("<input type='image' alt='Submit' value='home' src='../images/retry.png' width='48' height='48'/>");
out.println("</form>");
int next = numEx + 1;
out.println("<form action='./ExerciseView.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + next + " />");
out.println("<input type='image' alt='Submit' value='home' src='../images/next.png' width='48' height='48'/>");
out.println("</form>");

%>
	</div>
	</div>
</body>
</html>