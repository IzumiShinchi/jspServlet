<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>오류 발생</h4>
	<table border="1">
		<tr>
			<td width="20%"><b>Error : </b></td>
			<td>${pageContext.exception}</td>
		</tr>
		<tr>
			<td><b>URI :</b></td>
			<td>${pageContext.errorData.requestURI}</td>
		</tr>
		<tr>
			<td><b>Status Code : </b></td>
			<td>${pageContext.errorData.statusCode}</td>
		</tr>
	</table>
</body>
</html>