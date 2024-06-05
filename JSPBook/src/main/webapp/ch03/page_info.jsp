<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page info="Date 클래스를 이용한 날짜 출력하기" import="java.util.Date" %>	<%-- "" 꼭 붙이기 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Directives Tag</title>
</head>
<body>
	<%-- Date 클래스를 이용한 날짜 출력하기 --%>
	Today is <%=new Date().toLocaleString() %>
</body>
</html>