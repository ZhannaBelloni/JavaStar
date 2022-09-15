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
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");

	loginBean.setUserid(userid);
	loginBean.setPassword(password);
	try {
		boolean accountGefunden = loginBean.checkUseridPassword();
		if (accountGefunden) {
			loginBean.setLoggedIn(true);
			messageBean.setLoginSuccessful(userid);
			response.sendRedirect("./HomePageView.jsp");
		} else {
			loginBean.setLoggedIn(false);
			messageBean.setLoginFailed();
			response.sendRedirect("./LoginView.jsp");
		}
	} catch (NoConnectionException e) {
		messageBean.setNoJDBCConnection();
		e.printStackTrace();
		response.sendRedirect("./LoginView.jsp");
	} catch (SQLException e) {
		messageBean.setDBError();
		e.printStackTrace();
		response.sendRedirect("./LoginView.jsp");
	}
	%>
</body>
</html>