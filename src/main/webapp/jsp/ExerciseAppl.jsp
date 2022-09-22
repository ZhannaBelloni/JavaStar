<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.jdbc.StatisticsDB"%>
<%@page import="java.util.regex.*"%>
	
<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>

<link rel="stylesheet" href="../css/excercise.css?1">
<link rel='stylesheet' href='../css/sidebar.css'>
<link rel='stylesheet' href='../css/topnav.css'>
<script type="text/javascript" src="../js/helper.js"></script>

<meta charset="UTF-8">
<title>Exercise: Results</title>
</head>

<jsp:useBean id="tester" class="de.hwg_lu.java_star.excercises.Tester" />
<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<form action='./HomePageView.jsp' method='get'>
	<input type='image' alt='Submit' value='home' src='../images/home.png'
		width='48' height='48' />
</form>

	<%
	// =========================================================================== //
	//                        SIDE AND TOP NAVIGATION BARS                         //
	// =========================================================================== //
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
	// =========================================================================== //
	%>

<%
String exerciseNumber = request.getParameter("exerciseNum");
Integer numEx = Integer.parseInt(exerciseNumber);
ExcerciseDB ex = new ExcerciseDB();
StatisticsDB stat = new StatisticsDB();

String expectedOut = ex.getExcericeSolution(numEx);

String sourceCode = request.getParameter("sourceCode");
//out.println("v1<br>");
// out.println("##################################<br>");
// out.println(sourceCode + "<br>");
// out.println("request:<br>");
// 

String executionOut = "";
String compilationOutput = "";
boolean compilationError = false;

/*
if (sourceCode.contains("main(String[] ")) {
	// out.println("main found<br>");
	// out.println(obj.prepareRequest(sourceCode) + "<br>");

	executionOut = tester.execute(sourceCode);
	// out.print("##################################<br>");
	// out.print(executionOut + "<br>");
	if (executionOut.contains("Compiler exited with result code")) {
		out.print("Try again there was an error<br>");
	} else {
		out.print("Compiled: Very good!<br>");
	}

} else {
	*/
// out.println("main NOT found<br>");
/* 
out.println("preparedSourceCode<br>");
String preparedSourceCode = tester.prepareSourceCode(sourceCode, numEx);
out.println(preparedSourceCode + "<br>");
out.println("preparedRequest<br>");
out.println(tester.prepareRequest(preparedSourceCode) + "<br>");
*/
compilationOutput = tester.compileExcercise(sourceCode, numEx);

if (compilationOutput.contains("Compiler exited with result code") && !compilationOutput.contains("should be declared in a file named")) {
	out.print("Try again there was an error<br>");
	compilationError = true;
	stat.updateExcerciseForUser(loginBean.getUserid(), numEx, StatisticsDB.ExerciseTag.COMPILE_ERROR);
	compilationOutput = compilationOutput.replace("error:", "\nerror:");
	String pattern = ".{1,80}(?!\\S)";
	Pattern r = Pattern.compile(pattern);
	Matcher m = r.matcher(compilationOutput);

	out.print("<pre style='background-color: #FFFF; margin-left:15% ; margin-right:15%'><code>");
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

	out.print("<pre><code>");
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

//out.print("Expected " + expectedOut + ", got " + executionOut.substring(executionOut.isEmpty() ? 0 : (indexOut + 4)));
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

<!-- iframe src="../html/tmp.html" seamless></iframe -->


</div>

