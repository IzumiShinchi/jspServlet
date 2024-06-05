<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		//변수, 메소드 선언
		int count = 3;
	
		String makeItLower(String data) {
			return data.toLowerCase();
		}
	%>
	<%
		for(int i=1; i<=count; i++) {
			out.println("Java Server Pages" + i + ".<br>");
		}
	%>
	<%= makeItLower("Hello World")%>
</body>
</html>