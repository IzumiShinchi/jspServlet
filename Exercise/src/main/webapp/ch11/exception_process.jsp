<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="exception_error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exception_process</title>
</head>
<body>
	<%
		String userID = request.getParameter("id");
		String userPW = request.getParameter("password");
		
		if(userID==null || userID.equals("") || userPW==null || userPW.equals("")) {
			throw new ServletException("요청 파라미터가 없습니다.");
		}
	%>
	Welcome to <%=userID %>
</body>
</html>