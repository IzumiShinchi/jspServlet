<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
	<p>오류가 발생했습니다.</p>
	<p>예외 유형 : <%=exception.getClass().getName() %></p>
	<p>오류 메시지 : <%=exception.getMessage() %></p>
	<p>오류메시지2 : <%=exception.printStackTrace() %></p>
</body>
</html>