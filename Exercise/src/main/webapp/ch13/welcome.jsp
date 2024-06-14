<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session</title>
</head>
<body>
	<%
		String userID = (String)session.getAttribute("userID");
		if(userID==null) {
			response.sendRedirect("session_out.jsp");
		}
	%>
	
	<p><%=userID%>님, 반갑습니다!</p>
	<a href="session_out.jsp">로그아웃</a>
</body>
</html>