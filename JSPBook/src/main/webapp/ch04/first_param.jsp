<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag</title>
</head>
<body>
	<h3>이 파일은 first.jsp 입니다.</h3>
	<jsp:include page="second_param.jsp">
		<jsp:param name="date" value="<%=new Date() %>" />
	</jsp:include>
	<p>Jakarta Server Page</p>
</body>
</html>