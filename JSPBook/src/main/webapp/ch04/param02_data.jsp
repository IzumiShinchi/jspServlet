<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
%>
<%-- 	<% --%>
<!-- 		String title = request.getParameter("title"); -->
<%-- 	%> --%>
	<h3><%= request.getParameter("title") %></h3>
<%-- 	<%=java.net.URLDecoder.decode(title) %> --%>
	Today is <%=request.getParameter("data") %>
</body>
</html>