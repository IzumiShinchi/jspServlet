<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
	%>
	
	아이디	:	<%= id %><br>
	비밀번호	:	<%= pwd %><br>
	이름		:	<%= name %><br>
	전화번호	: 	<%= tel1 %> - <%= tel2 %> - <%= tel3 %>
</body>
</html>