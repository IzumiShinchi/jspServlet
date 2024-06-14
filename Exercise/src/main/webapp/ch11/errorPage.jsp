<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="isErrorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
	<%
		int x = 1;
	
		if(x==1) {
			throw new RuntimeException("오류 발생!!!");
		}
	%>
</body>
</html>