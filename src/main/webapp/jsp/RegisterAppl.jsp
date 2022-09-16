<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.NoConnectionException"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register in JavaStar</title>
<link rel="stylesheet" href="../css/JavaStarBasic.css">
</head>


<body>
<jsp:useBean id="accountBean" class="de.hwg_lu.java_star.beans.AccountBean" scope="session" />

<%!
	public String denullify(String s){
		if (s == null) return ""; else return s;
		//return (s == null)?"":s;
	}
	public String[] denullify(String[] sA){
		return (sA == null)?new String[0]:sA;
	}
%>
<%
	String userid      = request.getParameter("userid"); 
	String password    = request.getParameter("password"); 
	String email       = request.getParameter("email"); 
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
		accountBean.insertAccountNoCheck();
		response.sendRedirect("./LoginView.jsp");
	} catch (SQLException e) {
		out.println("Error: " + e.toString());
	}
	
%>
</body>
</html>