<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/Lesson.css?2">
<link rel='stylesheet' href='../css/sidebar.css?2'>
<link rel='stylesheet' href='../css/topnav.css?2'>
<link rel='stylesheet' href='../css/default.css?2'>
<script type="text/javascript" src="../js/helper.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<title>Java Basic</title>
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

	<div class='main'>
	<% out.println(GuiBean.getNavigationCourse());%>
	<%@include  file="../html/JavaBasic.html"%>
	
</div>
</body>
</html>