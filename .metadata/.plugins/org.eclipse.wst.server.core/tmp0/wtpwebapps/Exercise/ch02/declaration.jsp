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
		String hello = "Hello, Java Server Pages"; 
		String getString(String hello) {
			return hello;
		}
	%>
	<%= getString(hello) %>
	
	<%!
		String str = "Hello, Java Server Pages";
	
		public String getString() {
			return str;
		}
	%>
	<%= getString() %>
</body>
</html>