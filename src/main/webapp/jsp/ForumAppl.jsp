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
<jsp:useBean id="messageBean" class="de.hwg_lu.java_star.beans.MessageBean"
	scope="session" />

<title>Comments - Send Comment</title>
</head>
<body>

	<%
	String idToDelete = request.getParameter("idToDelete");
	if (idToDelete != null) {
		CommentsDB commentsDB = new CommentsDB();
		commentsDB.deleteComment(Integer.parseInt(idToDelete));
		response.sendRedirect("./ForumView.jsp");
	} else {
		String comment = request.getParameter("commentArea");

		if (comment == null || comment.isEmpty()) {
			messageBean.setCommentIsEmpty();
			response.sendRedirect("./ForumView.jsp");
		} else if (comment.length() > 512) {
			messageBean.setCommentIsTooLong();
			response.sendRedirect("./ForumView.jsp");
		} else {
			// Insert comment into database
			CommentsDB commentsDB = new CommentsDB();
			try {
				commentsDB.insertComment(loginBean.getUserid(), comment);
				response.sendRedirect("./ForumView.jsp");
			} catch (SQLException e) {
				response.sendRedirect("./ForumView.jsp");
				// out.println("exception: " + e.toString());
			}
		}
	}
	%>

</body>
</html>