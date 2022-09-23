<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel='stylesheet' href='../css/HomePage.css?2'>
<link rel='stylesheet' href='../css/sidebar.css?2'>
<link rel='stylesheet' href='../css/topnav.css?2'>
<script type="text/javascript" src="../js/helper.js"></script>
 <script src="jquery.js"></script> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>


<title>Java Star</title>
</head>
<body>

	<%
	// =========================================================================== //
	//                        SIDE AND TOP NAVIGATION BARS                         //
	try {
	    out.println(GuiBean.getNavigationElements(loginBean.isLoggedIn(), loginBean.getUserid()));
	} catch (SQLException e) {
	    response.sendRedirect("./LoginView.jsp");
	}
	%>

	<p class="welcome">Welcome to our Online school of JAVA
		Programming!</p>
	<%
	if (!loginBean.isLoggedIn()) {
	    out.println("<div class='start-study-with-us'>");
	    out.println("  <form action=\"./RegisterView.jsp\" method=\"get\">");
	    out.println(
	    "    <input class='start-study-with-us' type=\"submit\" name='register' value='Start Study with us!' />");
	    out.println("  </form>");
	    out.println("</div>");
	}
	%>
	

</body>
</html>