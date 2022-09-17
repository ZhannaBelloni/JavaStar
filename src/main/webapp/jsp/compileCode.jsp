<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="java.util.regex.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../css/JavaStarBasic.css">

<jsp:useBean id="tester" class="de.hwg_lu.java_star.excercises.Tester" />
<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<%
if (loginBean.isLoggedIn()) {
	out.println("<p style=\"text-align:right;\"> hallo, " + loginBean.getUserid() + "</p>");
	/*
	out.println("<p style=\"text-align:right;\"> execise done " + loginBean.getNumberExDone() + "</p>" );
	out.println("<p style=\"text-align:right;\"> execise correct " + loginBean.getCorrectEx() + "</p>" );
	*/
	out.println("<form action=\"./LogoutAppl.jsp\" method=\"get\">"
	+ "<input style=\"float: right;\" type=\"submit\" name=\"logout\" value=\"logout\" />" + "</form>");
}
%>

<%
String exerciseNumber = request.getParameter("exerciseNum");
Integer numEx = Integer.parseInt(exerciseNumber);
ExcerciseDB ex = new ExcerciseDB();
String expectedOut = ex.getExcericeSolution(numEx);

String sourceCode = request.getParameter("sourceCode");
//out.println("v1<br>");
// out.println("##################################<br>");
// out.println(sourceCode + "<br>");
// out.println("request:<br>");
// 

String executionOut = "";
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

if (executionOut.contains("Compiler exited with result code")) {
	out.print("Try again there was an error<br>");
	compilationError = true;
} else {
	out.print("Compiled: Very good!<br>");
}
// }

int indexOut = executionOut.indexOf("out:");

if (!compilationError && !executionOut.isEmpty() && executionOut.substring(indexOut + 4).equals(expectedOut)) {
	out.print("Very good!<br>");
} else {
	out.print("Error!<br>");
}
//out.print("Expected " + expectedOut + ", got " + executionOut.substring(executionOut.isEmpty() ? 0 : (indexOut + 4)));
%>

<br>
<br>
<br>
<div style="display: flex;">

<form action='./ExerciseCode.jsp' method='get'>
	<!-- input type='submit' value='previous'/-->
	<input type='image' alt='Submit' value='home'
		src='../images/previous.png' width='48' height='48' />
	<%
	out.println("<input type='hidden' name='exerciseNum' value='" + (numEx - 1) + "'/>");
	%>
</form>

<%
out.println("<form action='./ExerciseCode.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + exerciseNumber + " />");
out.println("<input type='hidden' name='sourceCode' value='" + sourceCode + "'/>");
out.println("<input type='image' alt='Submit' value='home' src='../images/retry.png' width='48' height='48'/>");
out.println("</form>");
int next = numEx + 1;
out.println("<form action='./ExerciseCode.jsp' method='get'>");
out.println("<input type='hidden' name='exerciseNum' value=" + next + " />");
out.println("<input type='image' alt='Submit' value='home' src='../images/next.png' width='48' height='48'/>");
out.println("</form>");

out.print("<form action='./HomePageView.jsp' method='get'>");
out.print("<input type='image' alt='Submit' value='home' src='../images/home.png' width='48' height='48'/>");
out.print("</form>");
%>

</div>

