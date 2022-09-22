<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.jdbc.CommentsDB"%>
<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
<%@page import="de.hwg_lu.java_star.utils.Comment"%>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='../css/ForumPage.css?0'>
<link rel='stylesheet' href='../css/sidebar.css?0'>
<link rel='stylesheet' href='../css/topnav.css?0'>
<link rel='stylesheet' href='../css/default.css?2'>
<script type="text/javascript" src="../js/helper.js"></script>

<title>Comments</title>
</head>
<body>

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
	<div class='main'>
	<h1 class='titleForum'>COMMENTS</h1>
	
	<p class='subtitleForum'>Comments by Users</p>
	<%
	try {
	ArrayList<Comment> listOfComments = CommentsDB.getComments(0);
	for (Comment comment : listOfComments) {
	    out.println("<hr><p class='textForum'>" + comment.getComment().replace("\n", "<br>\n") + "</p>");
	    out.println("<p class='byUserForum'> by " + comment.getUser() + " on " + comment.getTime() + "</p>");
	}
	} catch (SQLException e) {
		out.println("<hr><p class='default_error_text'> Contect the webmaster: something went wrong!</p>");
	    out.println("<p class='byUserForum'> by webmaster </p>");
	}
	%>
	<br><br>
	<h2 class='subtitleForum'>Leave a comment</h2>
	<form action='ForumAppl.jsp'>
	<textarea id="commentArea" name="commentArea" rows="10" cols="80"></textarea><br>
	<input class='send' type='image' alt='Submit' value='send' src='../images/send.png' height='48' />
	</form>
	
	
	</div>
	
	
	




</body>
</html>