<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.beans.GuiBean"%>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />
<!-- web site to color source code: http://hilite.me/ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/Lesson.css?5">
<link rel='stylesheet' href='../css/sidebar.css?5'>
<link rel='stylesheet' href='../css/topnav.css?5'>
<script type="text/javascript" src="../js/helper.js"></script>

<title>Variable and Data Type</title>
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
	<div class='main'>

		<div style="display: flex;">
			<form action='../jsp/JavaBasic.jsp' method='get'>

				<input type='image' alt='Submit' value='home'
					src='../images/previous.png' width='48' height='48' />
			</form>
			<form action='../jsp/HomePageView.jsp' method='get'>
				<input type='image' alt='Submit' value='home'
					src='../images/home.png' width='48' height='48' />
			</form>
			<form action='../jsp/HomePageView.jsp' method='get'>
				<!-- input type='submit' value='next'/-->
				<input type='image' alt='Submit' value='home'
					src='../images/next.png' width='48' height='48' />
			</form>
		</div>
		<p class='text'>Java divides the operators into the following
			groups:
		<ul class='text'>
			<li>Arithmetic operators</li>
			<li>Assignment operators</li>
			<li>Comparison operators</li>
			<li>Logical operators</li>
			<li>Bitwise operators</li>
		</ul>
		<br>
		<h2 class='text'>Arithmetic Operators</h2>
		<p class='text'>These operators involve the mathematical operators
			that can be used to perform various simple or advanced arithmetic
			operations on the primitive data types referred to as the operands.
			These operators consist of various unary and binary operators that
			can be applied on a single or two operands. Let’s look at the various
			operators that Java has to provide under the arithmetic operators.</p>


		<table>
			<tbody>
				<tr>
					<th style="width: 20%">Operator</th>
					<th style="width: 20%">Name</th>
					<th style="width: 40%">Description</th>
					<th style="width: 20%;">Example</th>
				</tr>
				<tr>
					<td>+</td>
					<td>Addition</td>
					<td>Adds together two values</td>
					<td>x + y</td>
				</tr>
				<tr>
					<td>-</td>
					<td>Subtraction</td>
					<td>Subtracts one value from another</td>
					<td>x - y</td>
				</tr>
				<tr>
					<td>*</td>
					<td>Multiplication</td>
					<td>Multiplies two values</td>
					<td>x * y</td>
				</tr>
				<tr>
					<td>/</td>
					<td>Division</td>
					<td>Divides one value by another</td>
					<td>x / y</td>
				</tr>
				<tr>
					<td>%</td>
					<td>Modulus</td>
					<td>Returns the division remainder</td>
					<td>x % y</td>
				</tr>
				<tr>
					<td>++</td>
					<td>Increment</td>
					<td>Increases the value of a variable by 1</td>
					<td>++x</td>
				</tr>
				<tr>
					<td>--</td>
					<td>Decrement</td>
					<td>Decreases the value of a variable by 1</td>
					<td>--x</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>