<%@page import="java.sql.SQLException"%>
<%@page import="de.hwg_lu.java_star.jdbc.NoConnectionException"%>

<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>
<%@page import="de.hwg_lu.java_star.utils.ExerciseStatistics"%>
<%@page import="de.hwg_lu.java_star.utils.UserStatistics"%>

<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>

<%@page import="java.sql.Connection"%>
<%@page import="de.hwg_lu.java_star.jdbc.PostgreSQLAccess"%>


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
	
	public boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

	public String[] denullify(String[] sA) {
		return (sA == null) ? new String[0] : sA;
	}
	
	public boolean isFieldTooLong(String value, int maxLength) {
	    return value.length() > maxLength;
	}
	
	%>
	<%
	String userid = request.getParameter("userid");
	String password = this.denullify(request.getParameter("password"));
	String email = this.denullify(request.getParameter("email"));
	String passwordRepeted = this.denullify(request.getParameter("passwordRepeat"));
	String btnRegister = this.denullify(request.getParameter("btnRegister"));
	String btnZumLogin = this.denullify(request.getParameter("btnZumLogin"));

	if (password.isEmpty()) {
		loginBean.setLoggedIn(false);
		messageBean.setFieldIsEmpty("password"); 
		response.sendRedirect("./RegisterView.jsp");
	} else if (userid.isEmpty()) {
		loginBean.setLoggedIn(false);
		messageBean.setFieldIsEmpty("username");
		response.sendRedirect("./RegisterView.jsp");
	} else if (email.isEmpty()) {
		loginBean.setLoggedIn(false);
		messageBean.setFieldIsEmpty("email");
		response.sendRedirect("./RegisterView.jsp");
	} else if (!password.equals(passwordRepeted)) {
		loginBean.setLoggedIn(false);
		messageBean.setRepeatedPasswordMismatch();
		response.sendRedirect("./RegisterView.jsp");
	} else if (!isValid(email)) {
		// https://www.geeksforgeeks.org/check-email-address-valid-not-java/
		loginBean.setLoggedIn(false);
		messageBean.emailIsInvalid();
		response.sendRedirect("./RegisterView.jsp");
	} else if (isFieldTooLong(userid, 32)) {
	    loginBean.setLoggedIn(false);
		messageBean.setAttributeTooLong("username", 32);
		response.sendRedirect("./RegisterView.jsp");
	} else if (isFieldTooLong(email, 64)) { 
	    loginBean.setLoggedIn(false);
		messageBean.setAttributeTooLong("email", 64);
		response.sendRedirect("./RegisterView.jsp");
	} else if (isFieldTooLong(password, 32)) { 
	    loginBean.setLoggedIn(false);
		messageBean.setAttributeTooLong("password", 32);
		response.sendRedirect("./RegisterView.jsp");
	} else {

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
		Connection connection = null;
		try {
			connection = new PostgreSQLAccess().getConnection();
			connection.setAutoCommit(false);
			if (accountBean.isEmailOrUseridPresent(connection)) {
				loginBean.setUserid("");
				loginBean.setPassword("");
				loginBean.setLoggedIn(false);
				messageBean.setLoginOnFailed();
				response.sendRedirect("./RegisterView.jsp");
			} else {
				accountBean.insertAccount(connection);
				loginBean.setUserid(userid);
				loginBean.setPassword(password);
				loginBean.setLoggedIn(true);
				connection.commit();
				response.sendRedirect("./HomePageView.jsp");
			}
		}catch (NoConnectionException e) {
			loginBean.setLoggedIn(false);
			messageBean.setDBError();
			response.sendRedirect("./RegisterView.jsp"); 
		} catch (SQLException e) {
			loginBean.setLoggedIn(false);
			messageBean.setLoginOnFailed();
			response.sendRedirect("./RegisterView.jsp");
		} finally {
			if (connection != null) {
				connection.rollback();
				connection.setAutoCommit(true);
				connection.close();
			}
		}
	}
	%>
</body>
</html>