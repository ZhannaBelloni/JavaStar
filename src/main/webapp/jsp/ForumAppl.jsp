<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="de.hwg_lu.java_star.jdbc.CommentsDB"%>
<%@page import="java.sql.SQLException"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:useBean id="loginBean" class="de.hwg_lu.java_star.beans.LoginBean"
	scope="session" />

<title>Comments - Send Comment</title>
</head>
<body>

<%

String comment = request.getParameter("commentArea");
if (comment == null) {
    // TODO
    out.println("comment is null!");
} else if (comment.length() > 512) {
    // TODO
    out.println("comment is too long!");
} else {
    // Insert comment into database
    CommentsDB commentsDB = new CommentsDB();
    try {
    	commentsDB.insertComment(loginBean.getUserid(), comment);
    	response.sendRedirect("./ForumView.jsp");
    } catch (SQLException e) {
        // TODO
        out.println("exception: " + e.toString());
    }
}


%>

</body>
</html>