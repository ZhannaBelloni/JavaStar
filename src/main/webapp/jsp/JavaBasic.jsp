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



<title>Java Basic</title>
</head>

<body>

	<%
	// =========================================================================== //
	//                        SIDE AND TOP NAVIGATION BARS                         //
	// =========================================================================== //
	// Force login for this page.
	try {
		// ===================================================== //
		// Side navigation
		out.println(GuiBean.getSideNavigation());
		// ===================================================== //
		// top navigation
		out.println(GuiBean.getTopNavigation(loginBean.isLoggedIn(), loginBean.getUserid()));
	} catch (SQLException e) {
		response.sendRedirect("./LoginView.jsp");
	}
	// =========================================================================== //
	%>
	<div class='main'>

		<div style="display: flex;">
			<form action='../jsp/HomePageView.jsp' method='get'>

				<input type='image' alt='Submit' value='home'
					src='../images/previous.png' width='48' height='48' />
			</form>
			<form action='../jsp/HomePageView.jsp' method='get'>
				<input type='image' alt='Submit' value='home'
					src='../images/home.png' width='48' height='48' />
			</form>
			<form action='../jsp/VariableAndDataType.jsp' method='get'>
				<input type='image' alt='Submit' value='home'
					src='../images/next.png' width='48' height='48' />
			</form>
		</div>

		<p class='text'>JAVA was developed by James Gosling at Sun
			Microsystems Inc in the year 1995, later acquired by Oracle
			Corporation. It is a simple programming language. Java makes writing,
			compiling, and debugging programming easy. It helps to create
			reusable code and modular programs. Java is a class-based,
			object-oriented programming language and is designed to have as few
			implementation dependencies as possible. A general-purpose
			programming language made for developers to write once run anywhere
			that is compiled Java code can run on all platforms that support
			Java. Java applications are compiled to byte code that can run on any
			Java Virtual Machine.</p>
		<b></b>
		<h2 class='title'>Java History</h2>

		<p class='text'>
			Java history is rich and vibrant. It all started with Patrick
			Naughton, James Gosling, and Mike Sheridan as they started working on
			a project in June 1991. The language is aimed to designed for
			interactive television. Initially, the language was named Oak because
			of the oak tree outside this office. With a name change of “Green,”
			it was finally named to Java. The final name is derived from Java
			coffee.<b></b> Java programming language is inspired by the C/C++
			style according to Gosling. The aim was to create a programming
			language independent of the underlying machine architecture.<b></b>
			The first public release of Java happened in 1995 as Java 1.0. New
			versions were released with time. Java also became the open source on
			November 13, 2006. It was released under the GNU General Public
			License (GPL) terms. Java is a powerful programming language and is
			used almost everywhere, including small microchips to powerful
			supercomputers.
		</p>
	</div>
</body>
</html>