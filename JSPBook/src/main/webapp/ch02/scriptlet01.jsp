<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Script Tag</title>
</head>
<body>
	<%
	int a = 2;
	int b = 3;
	int sum = a + b;
	out.println("2 + 3 = " + sum);
	%>
	<br>							//태그가 메인이라서 '\n'을 인식 못함.
	<%
	int c = 9;
	int d = 8;
	int sume = c + d;
	%>
	9+8=<%=sum%>
</body>
</html>