<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="obj" class="de.hwg_lu.java_star.excercises.EvilExecutor" />
<jsp:useBean id="tester" class="de.hwg_lu.java_star.excercises.Tester" />
<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />

<jsp:setProperty property="*" name="obj" />
    
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

if (sourceCode.contains("main(String[] ")) {
	// out.println("main found<br>");
	// out.println(obj.prepareRequest(sourceCode) + "<br>");

	executionOut = obj.execute(sourceCode);
	// out.print("##################################<br>");
	// out.print(executionOut + "<br>");
	if (executionOut.contains("Compiler exited with result code")) {
		out.print("Try again there was an error<br>");
	} else {
		out.print("Compiled: Very good!<br>");
	}

} else {
	// out.println("main NOT found<br>");
	// out.println(tester.prepareRequest(sourceCode) + "<br>");
	
	executionOut = tester.testExcercise(sourceCode);

	// out.print("##################################<br>");
	// out.print(executionOut + "<br>");
	if (executionOut.contains("Compiler exited with result code")) {
		out.print("Try again there was an error<br>");
	} else {
		out.print("Compiled: Very good!<br>");
	}
}


int indexOut = executionOut.indexOf("out:");

if (executionOut.substring(indexOut + 4).equals(expectedOut)) {
	out.print("Very good!<br>");
	out.print("Expected " + expectedOut + ", got " + executionOut.substring(indexOut + 4) + "<br>");

}
else {
	out.print("Error!<br>");
	out.print("Expected " + expectedOut + ", got " + executionOut.substring(indexOut + 4));
	
	out.print("<form action='./ExerciseCode.jsp' method='get'>");
	out.print("<input type='hidden' name='exerciseNum' value=" + exerciseNumber + " />");
	out.print("<input type='hidden' name='sourceCode' value='" + sourceCode + "'/>");
	out.print("<input type='submit' value='retry'/>");
	out.print("</form>");

}

%>



