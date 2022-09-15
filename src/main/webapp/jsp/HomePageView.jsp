<!-- %@page import="de.hwg_lu.bw4s.beans.GUIBean"%-->
<!--  %@page import="de.hwg_lu.bw4s.beans.MessageBean"%-->
<!--%@page import="de.hwg_lu.bw4s.beans.LoginBean"%-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- jsp:getProperty name="loginBean" property="checkLoggedIn" /-->

<title>Java Star</title>
</head>
<body>

<!--jsp:useBean id="messageBean" class="de.hwg_lu.bw4s.beans.MessageBean" scope="session" /-->
<!--jsp:useBean id="guiBean" class="de.hwg_lu.bw4s.beans.GUIBean" scope="session" /-->


<!--jsp:getProperty name="guiBean" property="htmlHeadLine" /-->

<h2>
	<!--jsp:getProperty name="messageBean" property="infoMessage" /-->
</h2>
<!-- property="infoMessage" wird zu ...getInfoMessage() -->
<h4>
	<!--jsp:getProperty property="actionMessage" name="messageBean" /-->
</h4>

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

<h1>JAVA STAR</h1>
<h2>Complete Java Course </h2>

<p>This website will help you to ...</p>
<p>Register or login to take the course and solve exercises</p>

<%
if (!loginBean.isLoggedIn()) {
	
	out.println("<form action=\"./RegisterView.jsp\" method=\"get\">");
	out.println("<input type=\"submit\" name=\"register\" value=\"register\" />");
	out.println("</form>");

	out.println("<form action='./LoginView.jsp' method='get'>");
	out.println("<input type='submit' name='login' value='login' />");
	out.println("</form>");
}
%>

<form action="./ExerciseCode.jsp" method="get">
    <input type="submit" name="exerciseNum" value="1" />
</form>


</html>