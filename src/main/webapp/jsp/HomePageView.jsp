<!-- %@page import="de.hwg_lu.bw4s.beans.GUIBean"%-->
<!--  %@page import="de.hwg_lu.bw4s.beans.MessageBean"%-->
<!--%@page import="de.hwg_lu.bw4s.beans.LoginBean"%-->
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
body {
  font-family: "Lato", sans-serif;
}

.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav input {
  padding: 1px 1px 1px 12px;
  text-decoration: none;
  font-size: 15px;
  color: #818181;
  display: block;
  transition: 0.3s;
  margin-left: 50px;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>


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
	/*
	out.println("<p style=\"text-align:right;\"> execise done " + loginBean.getNumberExDone() + "</p>" );
	out.println("<p style=\"text-align:right;\"> execise correct " + loginBean.getCorrectEx() + "</p>" );
	*/
	out.println("<form action=\"./LogoutAppl.jsp\" method=\"get\">" +
			"<input style=\"float: right;\" type=\"submit\" name=\"logout\" value=\"logout\" />" +
	"</form>");
}
%>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#">Course</a>
  <a href="#">Exercises</a>
    
  <%
	ExcerciseDB ex = new ExcerciseDB();
	int numTot = ex.getNumberExcerice();
	for (int num = 1; num <= numTot; ++num) {
		out.println("<form action='./ExerciseCode.jsp' method='get'>");
		out.println("    <input type='hidden' name='exerciseNum' value=" + num + " />");
		out.println("	 <input type='submit' value='Exercise " + num + "' />");
		out.println("</form>");
	}
%>
  
  <a href="#">Forum</a>
  <a href="#">Contact</a>

  
</div>

<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; JAVA STAR</span>

<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>

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
<%
	for (int num = 1; num <= numTot; ++num) {
		out.println("<form action='./ExerciseCode.jsp' method='get'>");
		out.println("	    <input type='submit' name='exerciseNum' value='" + num + "' />");
		out.println("</form>");
	}
%>


</html>