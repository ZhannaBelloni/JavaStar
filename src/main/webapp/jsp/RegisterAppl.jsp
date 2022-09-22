<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.NoConnectionException"%>

<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.utils.ExerciseStatistics"%>
<%@page import="de.hwg_lu.java_star.utils.UserStatistics"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register in JavaStar</title>

</head>

<jsp:useBean id="accountBean"
		class="de.hwg_lu.java_star.beans.AccountBean" scope="session" />
	<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
		scope="session" />
		<jsp:useBean id="messageBean"
	class="de.hwg_lu.java_star.beans.MessageBean" scope="session" />

<body>

	<%!public String denullify(String s) {
		if (s == null)
			return "";
		else
			return s;
		//return (s == null)?"":s;
	}

	public String[] denullify(String[] sA) {
		return (sA == null) ? new String[0] : sA;
	}%>
	<%
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String btnRegister = this.denullify(request.getParameter("btnRegister"));
	String btnZumLogin = this.denullify(request.getParameter("btnZumLogin"));

	accountBean.setUserid(userid);
	accountBean.setPassword(password);
	accountBean.setEmail(email);
	accountBean.setActive("Y");
	accountBean.setAdmin("N");

	out.println("userid: " + userid + "<br>");
	out.println("password: " + password + "<br>");
	out.println("email: " + email + "<br>");
	out.println("btnRegister: " + btnRegister + "<br>");
	out.println("btnZumLogin: " + btnZumLogin + "<br>");

	try {
		
		 //TODO :do in one transaction!
		accountBean.insertAccountNoCheck();
		loginBean.setUserid(userid);
		loginBean.setPassword(password);
		loginBean.setLoggedIn(true);
		
		UserStatistics userstat = new UserStatistics(userid);
		
		ExcerciseDB ex = new ExcerciseDB();
		int numTot = ex.getNumberExcerice();
		for (int i = 1; i < numTot; ++i) {
			ExerciseStatistics stat = new ExerciseStatistics(i);
			userstat.addStatistics(i, stat);
		}
		loginBean.setUserStatistics(userstat);
		
		response.sendRedirect("./HomePageView.jsp");
	} catch (SQLException e) {
		loginBean.setLoggedIn(false);
		messageBean.setLoginOnFailed();
		response.sendRedirect("./RegisterView.jsp");
	}
	%>
</body>
</html>