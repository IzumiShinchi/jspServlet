<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date"/>
	<p><%
		out.print("오늘의 날짜와 시각");
	%></p>
	<p><%=date %>
</body>
</html>