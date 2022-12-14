
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->
<title>JavaStar-Registration</title>
<!-- link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/Register.css" -->

<link type="text/css" rel="stylesheet" href="../css/Register.css">
<link type="text/css" rel="stylesheet" href="../css/sidebar.css">
<link rel="stylesheet" href="../css/default.css?1">

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
		scope="session" />
<jsp:useBean id="messageBean"
	class="de.hwg_lu.java_star.beans.MessageBean" scope="session" />

</head>
<body>

	<div class='topnav'>
		<div class='login-container'>
			<form action='./HomePageView.jsp' method='get'>
				<input type='image' alt='Submit' value='home'
					src='../images/home.png' width='48' height='48' />
			</form>
		</div>
	</div>

	<div class="signup__container">
		<div class="container__child signup__thumbnail">
			<div class="thumbnail__logo"></div>
			<div class="thumbnail__content text-center">
				<h1 class="heading--primary">Welcome to Java Star.</h1>
				<h2 class="heading--secondary">Are you ready to join?</h2>
			</div>
			<div class="signup__overlay"></div>
		</div>
		<div class="container__child signup__form">
			<form action="./RegisterAppl.jsp">
				<div class="form-group">
					<label for="username">Username</label> <input class="form-control"
						type="text" name="userid" id="username" placeholder="username"
						required />
				</div>
				<div class="form-group">
					<label for="email">Email</label> <input class="form-control"
						type="text" name="email" id="email" placeholder="user@name.de"
						required />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input class="form-control"
						type="password" name="password" id="password"
						placeholder="********" required />
				</div>
				<div class="form-group">
					<label for="passwordRepeat">Repeat Password</label> <input
						class="form-control" type="password" name="passwordRepeat"
						id="passwordRepeat" placeholder="********" required />
				</div>
				<div class="m-t-lg">
					<ul class="list-inline">
						<li><input class="btn btn--form" type="submit"
							value="Register" /></li>
						<li><a class="signup__link" href="./LoginView.jsp">I am
								already a register</a></li>
					</ul>
				</div>
				<%
					if (messageBean.isWithError()) {
	    out.println("<p class='default_error_text'>"+  messageBean.getInfoMessage() + ": " + messageBean.getActionMessage() + "</p>");;
	    messageBean.setWithError(false);
	}
	%>
			</form>
		</div>
	</div>
</html>