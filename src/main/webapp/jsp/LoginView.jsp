<%@page import="de.hwg_lu.java_star.beans.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/Login.css">
<title>JAVA STAR-Login</title>
</head>
<body>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />



<div>
<form action='./HomePageView.jsp' method='get'>
<input type='image' alt='Submit' value='home' src='../images/home.png' width='48' height='48'/>
</form>
<div class="login-two">
  <p class="login-if-you-already-a-student-or-regist">
    Login if you already a student or register if you a new
    one<br>
  </p>

<form action="./LoginAppl.jsp" method="get">
	<table>
		<tr>
			<td><div class="flex-wrapper-four">
      <p class="name">Name:</p>
          </div>:</td>
			<td>
				<input class="rectangle-2" type="text" name="userid" 
					value='<jsp:getProperty name="loginBean" property="userid" />' 
				/>
			</td>
		</tr>
		<tr>
			<td><div class="flex-wrapper-four">
      <p class="name">Password:</p></div></td>
			<td>
				<input class="rectangle-2" type="password" name="password" 
					value="" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input class="flex-wrapper-two" type="submit" name="btnLogin" value="login" />
			</td>
		</tr>
	</table>
</form>

</div></div>

<div class="flex-wrapper-three">
<a class="login" href="./RegisterView.jsp">I am a new one here</a>!
</div>

</body>
</html>