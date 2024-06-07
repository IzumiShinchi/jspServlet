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
		int catesian = Integer.parseInt(request.getParameter("catesian"));
	
		for(int i=1; i<10; i++) {
			out.println(catesian + " * " + i + " = " + catesian*i + "<br>");
		}
	%>
</body>
</html>