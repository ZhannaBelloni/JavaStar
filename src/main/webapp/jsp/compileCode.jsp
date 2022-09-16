<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="tester" class="de.hwg_lu.java_star.excercises.Tester" />
<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />
    
<%
if (loginBean.isLoggedIn()) {
	out.println("<p style=\"text-align:right;\"> hallo, " + loginBean.getUserid() + "</p>" );
	out.println("<p style=\"text-align:right;\"> execise done " + loginBean.getNumberExDone() + "</p>" );
	out.println("<p style=\"text-align:right;\"> execise correct " + loginBean.getCorrectEx() + "</p>" );
	out.println("<form action=\"./LogoutAppl.jsp\" method=\"get\">" +
			"<input style=\"float: right;\" type=\"submit\" name=\"logout\" value=\"logout\" />" +
	"</form>");
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
	out.println("preparedSourceCode<br>");
	String preparedSourceCode = tester.prepareSourceCode(sourceCode, numEx);
	out.println(preparedSourceCode + "<br>");
	out.println("preparedRequest<br>");
	out.println(tester.prepareRequest(preparedSourceCode) + "<br>");
	executionOut = tester.testExcercise(sourceCode, numEx);

	// out.print("##################################<br>");
	// out.print(executionOut + "<br>");
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
	// out.print("Expected " + expectedOut + ", got " + executionOut.substring(indexOut + 4) + "<br>");
	out.print("<form action='./ExerciseCode.jsp' method='get'>");
	int next = numEx + 1;
	out.print("<input type='hidden' name='exerciseNum' value=" + next + " />");
	out.print("<input type='submit' value='next'/>");
	out.print("</form>");
}
else {
	out.print("Error!<br>");
	// out.print("Expected " + expectedOut + ", got " + executionOut.substring(executionOut.isEmpty() ? 0 : (indexOut + 4)));
	
	out.print("<form action='./ExerciseCode.jsp' method='get'>");
	out.print("<input type='hidden' name='exerciseNum' value=" + exerciseNumber + " />");
	out.print("<input type='hidden' name='sourceCode' value='" + sourceCode + "'/>");
	out.print("<input type='submit' value='retry'/>");
	out.print("</form>");
	
	int next = numEx + 1;
	out.print("<form action='./ExerciseCode.jsp' method='get'>");
	out.print("<input type='hidden' name='exerciseNum' value=" + next + " />");
	out.print("<input type='submit' value='next'/>");
	out.print("</form>");

}

out.print("<form action='./HomePageView.jsp' method='get'>");
out.print("<input type='submit' value='home'/>");
out.print("</form>");

%>



