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
<jsp:useBean id="exerciseResultBean" class="de.hwg_lu.java_star.beans.ExerciseResultBean"
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
	boolean compilationError = exerciseResultBean.isCompilationError();
	boolean testError = exerciseResultBean.isTestError();
	String sourceCode = exerciseResultBean.getSourceCode();
	String output = exerciseResultBean.getOutput();
	int exerciseNumber = exerciseResultBean.getExerciseId();
	String testClass = exerciseResultBean.getTestCode();
	
	out.println(GuiBean.getExerciseResult(compilationError, testError));
	
	String pattern = ".{1,80}(?!\\S)";
	Pattern r = Pattern.compile(pattern);
	Matcher m = r.matcher(output);

	out.println("<a onclick=\"toggleExcericeSideBar('output')\" href='#'>show output</a>");
	out.print("<pre id = 'output' style='background-color: #FFFF; margin-left:3% ; margin-right:3%; display:none'><code>");
	while (m.find()) {
		out.println(m.group(0).trim());
	}
	out.println("</code></pre>");
	
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
			out.println("<input type='hidden' name='exerciseNum' value='" + (exerciseNumber - 1) + "'/>");
			%>
		</form>

		<%
out.println("<form action='./ExerciseView.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + exerciseNumber + " />");
out.println("<input type='hidden' name='sourceCode' value='" + sourceCode + "'/>");
out.println("<input type='image' alt='Submit' value='home' src='../images/retry.png' width='48' height='48'/>");
out.println("</form>");
int next = exerciseNumber + 1;
out.println("<form action='./ExerciseView.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + next + " />");
out.println("<input type='image' alt='Submit' value='home' src='../images/next.png' width='48' height='48'/>");
out.println("</form>");

%>
	</div>
	</div>
</body>
</html>