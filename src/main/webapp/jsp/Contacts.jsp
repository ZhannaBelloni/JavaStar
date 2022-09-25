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


<title>More</title>
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
	<div class="main" style="color:white;font-size:22px">
	
	<img style="float: right; margin-top: 70px" src="../images/javaBooks.jpeg" alt="Java Books">
	<br>
	
	<h3>3 Must-Read Books for Java Developers</h3>
	
	<p>
	These Java books cover various programming areas, including core Java fundamentals, 
	frameworks, design patterns, and so much more. These books are excellent tools for all Java developers, 
	from beginners to advanced users.
	</p>
	
	<ul>
	<li>
	<a style="color:#0008ff" href="http://www.headfirstlabs.com/books/hfjava/"">
	     Head First Java, 2nd Edition</a> by Kathy Sierra
	</li>
	<li>
	<a style="color:#0008ff" href="http://www.amazon.com/Introduction-Programming-Java-Interdisciplinary-Approach/dp/0321498054">
	Introduction to Programming in Java:<br>An Interdisciplinary Approach
	</a> by Robert Sedgewick
	</li>
	<li>
	<a style="color:#0008ff" href="http://www.amazon.com/Thinking-Java-Edition-Bruce-Eckel/dp/0131872486">
	Thinking in Java</a> by Bruce Eckel
	</li>
</ul>
	<br>
	<hr>
	<!-- div style="position: absolute; bottom: 5px;float: right"-->
	<address style='font-size:12px'>
Developed by <a href="mailto:Zhanna.Belloni@studmail.hwg-lu.de">Zhanna Belloni</a> and 
<a href="mailto:Dmytro.Poliskyi@studmail.hwg-lu.de">Dmytro Poliskyi</a>.<br>
Hochschule für Wirtschaft und Gesellschaft Ludwigshafen<br>
<br>
Ernst-Boehe-Straße 4<br> 
67059 Ludwigshafen am Rhein <br>
Germany
</address>
<!--  /div-->
	
	</div>

</body>
</html>