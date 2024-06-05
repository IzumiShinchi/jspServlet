<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form01_process</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	String email = request.getParameter("email");
	%>
	
<!-- 	String buffer = buffer -->
	<p> 이름 : <%= name %></p>
	<p> 주소 : <%= addr %></p>
	<p> 이메일 : <%= email %></p>
</body>
</html>