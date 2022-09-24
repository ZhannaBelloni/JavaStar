<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.jdbc.CommentsDB"%>
<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
<%@page import="de.hwg_lu.java_star.utils.Comment"%>
<%@page import="de.hwg_lu.java_star.jdbc.NoConnectionException" %>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />
<jsp:useBean id="messageBean"
	class="de.hwg_lu.java_star.beans.MessageBean" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='../css/ForumPage.css?0'>
<link rel='stylesheet' href='../css/sidebar.css?0'>
<link rel='stylesheet' href='../css/topnav.css?0'>
<link rel='stylesheet' href='../css/default.css?2'>
<script type="text/javascript" src="../js/helper.js"></script>

<jsp:getProperty name="loginBean" property="checkLoggedIn" />

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
	if (loginBean.isLoggedIn()) {
	// TODO: make the number of comments retrieved dynamic
	try {
		ArrayList<Comment> listOfComments = CommentsDB.getComments(0);
		for (Comment comment : listOfComments) {
		    out.println("<hr><p class='textForum'>" + comment.getComment().replace("\n", "<br>\n") + "</p>");
		    out.println("<p class='byUserForum'> by " + comment.getUser() + " on " + comment.getTime() + "</p>");
		    if (loginBean.isAdmin()) {
		    	out.println("<form action='ForumAppl.jsp'>");
		    	out.println("<input alt='Submit' type='image' src='../images/trashbin.jpg' height='28' name='send' >");
		    	out.println("<input type='hidden' name='idToDelete' value='" + comment.getId() +"'/>");
		    	out.println("</form>");
	
		    }
		} 
	} catch (SQLException e) {
		out.println("<hr><p style='font-size:25px' class='default_error_text'> Contact the webmaster: something went wrong!</p>");
		out.println("<a style='font-size:15px' onclick=\"toggleExcericeSideBar('errormessage')\" href='#'>show output</a>");
		out.println("<hr><p id = 'errormessage' style='font-size:15px;display:none'> " + e.toString() + "</p>");
	    out.println("<p class='byUserForum'> by webmaster </p>");
	}
	}
	%>
	<br><br>
	<h2 class='subtitleForum'>Leave a comment</h2>
	<form action='ForumAppl.jsp'>
	<textarea id="commentArea" name="commentArea" rows="10" cols="80"></textarea><br>
	<input class='send' name='send' type='image' alt='Submit' value='send' src='../images/send.png' height='48' />
	</form>
	
	<%
		if (messageBean.isWithError()) {
	    out.println("<p class='default_error_text'>"+  messageBean.getInfoMessage() + "<br> " + messageBean.getActionMessage() + "</p>");;
	    messageBean.setWithError(false);
	}
	%>
	
	
	</div>
	
	
	




</body>
</html>