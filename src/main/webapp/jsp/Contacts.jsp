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

<link rel='stylesheet' href='../css/sidebar.css?0'>
<link rel='stylesheet' href='../css/topnav.css?0'>
<link rel='stylesheet' href='../css/ForumPage.css?2'>
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
	<div class="main">
	
	
	<a href="http://www.headfirstlabs.com/books/hfjava/"">Head First Java, 2nd Edition</a>

	<br><br>
	<div style="position: absolute; bottom: 5px;">
	<address>
Developed by <a href="mailto:webmaster@example.com">Zhanna Belloni</a> and 
<a href="mailto:webmaster@example.com">Dmytro Poliskyi</a>.<br>
Hochschule für Wirtschaft und Gesellschaft Ludwigshafen<br>

Ernst-Boehe-Straße 4, 67059 Ludwigshafen am Rhein <br>
Germany
</address>
</div>
	
	</div>

</body>
</html>