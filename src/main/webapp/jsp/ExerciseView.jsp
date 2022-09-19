<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="java.sql.SQLException"%>

<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>
<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
	
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="../css/excercise.css">
<link rel='stylesheet' href='../css/sidebar.css'>
<link rel='stylesheet' href='../css/topnav.css'>
<script type="text/javascript" src="../js/helper.js"></script>


<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<meta charset="UTF-8">
<title>Exercise: Coding</title>
</head>


<body>

	<%
	// =========================================================================== //
	//                        SIDE AND TOP NAVIGATION BARS                         //
	// =========================================================================== //
	// Force login for this page.
	if (!loginBean.isLoggedIn()) {
	    response.sendRedirect("./LoginView.jsp");
	}
	// ===================================================== //
	// Side navigation
	try {
	    out.println(GuiBean.getSideNavigation());
	} catch (SQLException e) {
	    response.sendRedirect("./LoginView.jsp");
	}
	// ===================================================== //
	// top navigation
	out.println(GuiBean.getTopNavigation(loginBean.isLoggedIn(), loginBean.getUserid()));

	// =========================================================================== //
	%>



	<div class="main">
		<%
		String exerciseNumber = request.getParameter("exerciseNum");
		Integer numEx = Integer.parseInt(exerciseNumber);
		// ExcerciseDB ex = new ExcerciseDB();
		out.println("<p class='titleExcercise'> Excercise " + exerciseNumber + "</p>");
	    ExcerciseDB ex = new ExcerciseDB();
		String textEx = ex.getExcerciseText(numEx);
		if (textEx == null) {
			response.sendRedirect("./HomePageView.jsp");
		} else {
			out.println("<p class='textExcercise'>");
			out.println(textEx);
			out.println("</p>");
		}
		%>

		<form action='ExerciseAppl.jsp'>
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
			<form action='./ExerciseView.jsp' method='get'>
				<!-- input type='submit' value='solution'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/solution.jpg' width='48' height='48' />
				<%
				out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
				out.println("<input type='hidden' name='withSolution' value='true'/>");
				%>
			</form>
			<form action='./ExerciseView.jsp' method='get'>
				<!-- input type='submit' value='previous'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/previous.png' width='48' height='48' />
				<%
				out.println("<input type='hidden' name='exerciseNum' value='" + (numEx - 1) + "'/>");
				%>
			</form>

			<form action='./ExerciseView.jsp' method='get'>
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