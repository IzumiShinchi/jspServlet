<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form02 Process</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		Enumeration paraNames = request.getParameterNames();
		while(paraNames.hasMoreElements()) {
			String name = (String) paraNames.nextElement();
			out.print(name + " : ");
			String paraValue = request.getParameter(name);
			out.println(paraValue + "<br>");
		}
	%>
</body>
</html>