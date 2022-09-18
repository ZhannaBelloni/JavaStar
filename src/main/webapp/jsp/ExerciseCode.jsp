<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="../css/excercise.css">

<script type="text/javascript" src="../js/helper.js"></script>


<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<meta charset="UTF-8">
<title>Exercise: Coding</title>
</head>


<body>

	<span class="closedSideNav" onclick="openNav()">&#9776; JAVA
		STAR</span>

	<div class="sidenav" id="mySidenav" style="width: 0">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

		<a href="#">Course</a> <a onclick="toggleExcericeSideBar('exSideBar')"
			href="#">Exercises</a>
		<div id='exSideBar' style="display: none">
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
		</div>

		<a href="#">Forum</a> <a href="#">Contact</a>
	</div>

	<!-- ======================================== -->

	<div class='topnav'>
		<div class='login-container'>
			<%
			if (!loginBean.isLoggedIn()) {
				response.sendRedirect("./LoginView.jsp");
			}
			%>
			<div style='display: flex;'>
				<img src='../images/user_0.png' width='48' height='48' />
				<%
				out.println("<p class='hallo_user'>Hello, " + loginBean.getUserid() + "!!</p>");
				%>
				<form class='logout' action='./LogoutAppl.jsp' method='get'>
					<input type='image' alt='Submit' name='logout' value='logout'
						src='../images/logout.jpg' width='48' height='48' />
				</form>
				<form class="homeBtn" action='./HomePageView.jsp' method='get'>
					<input type='image' alt='Submit' value='home'
						src='../images/home.png' width='48' height='48' />
				</form>
			</div>
		</div>
	</div>



	<div class="main">
		<%
		String exerciseNumber = request.getParameter("exerciseNum");
		Integer numEx = Integer.parseInt(exerciseNumber);
		// ExcerciseDB ex = new ExcerciseDB();
		out.println("<p class='titleExcercise'> Excercise " + exerciseNumber + "</p>");
		String textEx = ex.getExcerciseText(numEx);
		if (textEx == null) {
			response.sendRedirect("./HomePageView.jsp");
		} else {
			out.println("<p class='textExcercise'>");
			out.println(textEx);
			out.println("</p>");
		}
		%>

		<form action='compileCode.jsp'>
			<%
			out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
			%>
			<div style="display: flex;">
				<input type='image' alt='Submit' value='home'
					src='../images/verify.png' height='48' />
			</div>
			<br>
			<textarea id="sourceCode" name="sourceCode" rows="10" cols="80">
				<%
				String sourceCode = request.getParameter("sourceCode");
				String withSolution = request.getParameter("withSolution");
				if (withSolution != null) {
					String expectedOut = ex.getExcericeSourceCodeSolution(numEx);
					out.println(expectedOut);
				} else if (sourceCode != null) {
					out.println(sourceCode);
				} else {
					// Insert Code
				}
				%>
			</textarea>
			<br>
		</form>

		<div style="display: flex;">
			<form action='./ExerciseCode.jsp' method='get'>
				<!-- input type='submit' value='solution'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/solution.jpg' width='48' height='48' />
				<%
				out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
				out.println("<input type='hidden' name='withSolution' value='true'/>");
				%>
			</form>
			<form action='./ExerciseCode.jsp' method='get'>
				<!-- input type='submit' value='previous'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/previous.png' width='48' height='48' />
				<%
				out.println("<input type='hidden' name='exerciseNum' value='" + (numEx - 1) + "'/>");
				%>
			</form>

			<form action='./ExerciseCode.jsp' method='get'>
				<!-- input type='submit' value='next'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/next.png' width='48' height='48' />
				<%
				out.println("<input type='hidden' name='exerciseNum' value='" + (numEx + 1) + "'/>");
				%>
			</form>
		</div>
	</div>

</body>
</html>