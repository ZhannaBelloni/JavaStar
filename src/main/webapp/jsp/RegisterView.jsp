
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->
<title>JavaStar-Registration</title>
<!-- link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/Register.css" -->
<link type="text/css" rel="stylesheet" href="../css/Register.css">

</head>
<body>

	<form action='./HomePageView.jsp' method='get'>
		<input type='image' alt='Submit' value='home' src='../images/home.png'
			width='48' height='48' />
	</form>

	<div class="register">
		<p class="join-us-now">Join us now!</p>
		<form action="./RegisterAppl.jsp" method="get"
			onsubmit="return checkInput(this)">
			<table>
				<tr>
					<td>
						<div class="flex-wrapper-one">
							<p class="label">Name:</p>
						</div>
					</td>
					<td><input class="rectangle-2" type="text" name="userid"
						value=''
						onkeyup="checkValueAndSetFehlerMsg(this.name, this.value)" /></td>
					<td id="useridMsg"></td>
				</tr>
				<tr>
					<td>
						<div class="flex-wrapper-one">
							<p class="label">Password:</p>
						</div>
					</td>
					<td><input class="rectangle-2" type="password" name="password"
						value=""
						onchange="checkValueAndSetFehlerMsg('password', this.value)"
						onkeyup="checkValueAndSetFehlerMsg('password', this.value)" /></td>
					<td id="passwordMsg"></td>
				</tr>
				<tr>
					<td>
						<div class="flex-wrapper-one">
							<p class="label">e-mail:</p>
						</div>
					</td>
					<td><input class="rectangle-2" type="text" name="email"
						value=''
						onkeyup="checkValueAndSetFehlerMsg(this.name, this.value)" /></td>
					<td id="emailMsg"></td>
				</tr>
				<tr>
					<td></td>
					<td><div class='relative-wrapper-one'>
							<input class="rectangle-5" type="submit" name="logon"
								value="logon" onclick="setButtonClicked(this.name)" />
						</div></td>
					<td></td>
				</tr>
			</table>


		</form>
	</div>
</body>
</html>