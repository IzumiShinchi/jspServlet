<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 내장객체</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지!!!
	
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	%>
	
	아이디 : <%= id %> <br>
	비밀번호 : <%= pwd %>
</body>
</html>