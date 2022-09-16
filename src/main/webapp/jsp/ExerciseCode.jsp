<%@page import="de.hwg_lu.java_star.jdbc.ExcerciseDB"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="../css/JavaStarBasic.css">

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean" scope="session" />

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <p style="border-width:3px; border-style:solid; border-color:#FF0000; padding: 1em;">
    <%
    String exerciseNumber = request.getParameter("exerciseNum");
    Integer numEx = Integer.parseInt(exerciseNumber);
    ExcerciseDB ex = new ExcerciseDB();
    out.println("Excercise " + exerciseNumber + "<br>");
    String textEx = ex.getExcerciseText(numEx);
    if (textEx==null) {
    	response.sendRedirect("./HomePageView.jsp");
    } else {
       	out.println(textEx);
    }
    %>
    </p>
    
    <form action='compileCode.jsp'>
    <%
    out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
    %>
    <!--input type="submit" value="verify"-->
    <input type='image' alt='Submit' value='home' src='../images/verify.png' width='48' height='48'/><br>  
    <textarea id="sourceCode" name="sourceCode" rows="10" cols="80"><%
    String sourceCode = request.getParameter("sourceCode");
    String withSolution = request.getParameter("withSolution");    
    if (withSolution != null) {
    	String expectedOut = ex.getExcericeSourceCodeSolution(numEx);
out.println(expectedOut);
    } else if (sourceCode != null) {
out.println(sourceCode);
    } else {
// Insert Code
    }
    %></textarea><br>
    </form> 
    
  	<div style="display: flex;">
    <form action='./ExerciseCode.jsp' method='get'>
	<!-- input type='submit' value='solution'/-->
	<input type='image' alt='Submit' value='home' src='../images/solution.jpg' width='48' height='48'/>  
	<%
    out.println("<input type='hidden' name='exerciseNum' value='" + exerciseNumber + "'/>");
    out.println("<input type='hidden' name='withSolution' value='true'/>");
    %>
	</form>
	<form action='./ExerciseCode.jsp' method='get'>
	<!-- input type='submit' value='previous'/-->
	<input type='image' alt='Submit' value='home' src='../images/previous.png' width='48' height='48'/> 
	<%
    out.println("<input type='hidden' name='exerciseNum' value='" + (numEx - 1) + "'/>");
    %>
	</form>
	
	<form action='./ExerciseCode.jsp' method='get'>
	<!-- input type='submit' value='next'/-->
	<input type='image' alt='Submit' value='home' src='../images/next.png' width='48' height='48'/> 
	<%
    out.println("<input type='hidden' name='exerciseNum' value='" + (numEx + 1) + "'/>");
    %>
	</form>
    
    <form action='./HomePageView.jsp' method='get'>
		<input type='image' alt='Submit' value='home' src='../images/home.png' width='48' height='48'/>
	</form>
	</div>

</body>
</html>