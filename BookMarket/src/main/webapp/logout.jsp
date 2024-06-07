<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOG OUT</title>
</head>
<body>
	<%
		session.invalidate(); //logout, 세션 모두 삭제
		response.sendRedirect("addBook.jsp");
	%>
</body>
</html>