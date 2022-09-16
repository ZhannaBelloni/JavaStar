<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    
    <%
    String exerciseNumber = request.getParameter("exerciseNum");
    Integer numEx = Integer.parseInt(exerciseNumber);
    ExcerciseDB ex = new ExcerciseDB();
    out.println("Excercise<br>");
    String textEx = ex.getExcerciseText(numEx);
    if (textEx==null) {
    	response.sendRedirect("./HomePageView.jsp");
    } else {
    	out.println(textEx);
    }
    %>
    
    <form action='compileCode.jsp'>
    <textarea id="sourceCode" name="sourceCode" rows="10" cols="80"><%
    String sourceCode = request.getParameter("sourceCode");
    if (sourceCode != null) {
out.println(sourceCode);
    } else {
// Insert Code
    }
    %></textarea><br>
    <%
    out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
    %>
    
    <input type="submit" value="verify">  
    </form> 
    
    <form action='./HomePageView.jsp' method='get'>
	<input type='submit' value='home'/>
	</form>


</body>
</html>