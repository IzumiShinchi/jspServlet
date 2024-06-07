<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation Process</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	
	<p> 아이디 : <%= request.getParameter("id") %></p>
	<p> 비밀번호 : <%= request.getParameter("passwd") %></p>
</body>
</html>