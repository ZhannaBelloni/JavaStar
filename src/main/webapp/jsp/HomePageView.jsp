<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel='stylesheet' href='../css/HomePage.css?1'>
<link rel='stylesheet' href='../css/sidebar.css?0'>
<link rel='stylesheet' href='../css/topnav.css?0'>
<script type="text/javascript" src="../js/helper.js"></script>

<title>Java Star</title>
</head>
<body>

	<span class="closedSideNav" onclick="openNav()">&#9776; JAVA
		STAR</span>

	<div class="sidenav" id="mySidenav" style="width: 0">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="./HomePageView.jsp">Home</a> <a
			onclick="toggleExcericeSideBar('courseSideBar')" href="#">Course</a>
		<div id='courseSideBar' style="display: none">
			<!-- ============================================= -->
			<form action="./JavaBasic.jsp" method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Java Basics' />
			</form>
			<form method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Variables and Data Types' />;
			</form>
			<form method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Input and Output in Java' />
			</form>
					<form method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Operators in Java' />
			</form>
					<form method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Flow Control in Java'/>
					</form>
					<form method='get'>
				<input type='hidden' name='exerciseNum' value="1"> <input
					type='submit' value='Arrays in Java' />
			</form>
			<!-- ============================================= -->
		</div>
		<a onclick="toggleExcericeSideBar('exSideBar')" href="#">Exercises</a>
		<div id='exSideBar' style="display: none">
			<%
			try {
			ExcerciseDB ex = new ExcerciseDB();
			int numTot = ex.getNumberExcerice();
			for (int num = 1; num <= numTot; ++num) {
			    out.println("<form action='./ExerciseView.jsp' method='get'>");
			    out.println("    <input type='hidden' name='exerciseNum' value=" + num + " />");
			    out.println("	 <input type='submit' value='Exercise " + num + "' />");
			    out.println("</form>");
			}
			} catch (SQLException e ) {
				out.println("<p>Contact the webmaster</p>");
			}
			%>
		</div>

		<a href="./ForumView.jsp">Comments</a> <a href="#">Contact</a>
	</div>
	<div class='topnav'>
		<div class='login-container'>
			<%
			if (loginBean.isLoggedIn()) {
			    out.println("<div style='display: flex;'>");
			    out.println("  <img src='../images/user_0.png' width='48' height='48'/>");
			    out.println("  <p class='hallo_user'>Hello, " + loginBean.getUserid() + "!!</p>");
			    out.println("  <form class='logout' action='./LogoutAppl.jsp' method='get'>");
			    out.println(
			    "    <input type='image' alt='Submit' name='logout' value='logout' src='../images/logout.jpg'  width='48' height='48'/>");
			    out.println("    </form>");
			    out.println("</div>");
			} else {
			    out.println("<form action='LoginAppl.jsp'>");
			    out.println("   <input type='text' placeholder='Username' name='userid'>");
			    out.println("   <input type='text' placeholder='Password' name='password'>");
			    out.println("<button type='submit'>Login</button>");
			    out.println("</form>");
			}
			%>
		</div>
	</div>

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