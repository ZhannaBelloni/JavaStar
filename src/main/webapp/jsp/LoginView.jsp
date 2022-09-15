<%@page import="de.hwg_lu.java_star.beans.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JAVA STAR-Login</title>
</head>
<body>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />

<form action="./LoginAppl.jsp" method="get">
	<table>
		<tr>
			<td>Nickname:</td>
			<td>
				<input type="text" name="userid" 
					value='<jsp:getProperty name="loginBean" property="userid" />' 
				/>
			</td>
		</tr>
		<tr>
			<td>Passwort:</td>
			<td>
				<input type="password" name="password" 
					value="" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" name="btnLogin" value="login" />
			</td>
		</tr>
	</table>
</form>
<p>Noch kein Nickname? Registrieren Sie sich
<a href="./RegisterView.jsp">hier</a>!

</body>
</html>