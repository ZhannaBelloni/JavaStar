<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.NoConnectionException"%>
<%@page import="de.hwg_lu.java_star.beans.MessageBean"%>
<%@page import="de.hwg_lu.java_star.beans.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
		scope="session" />
	<jsp:useBean id="messageBean"
		class="de.hwg_lu.java_star.beans.MessageBean" scope="session" />
	<%
	
	loginBean.setUserid("");
	loginBean.setPassword("");
	loginBean.setLoggedIn(false);
	response.sendRedirect("./HomePageView.jsp");
	%>
</body>
</html>