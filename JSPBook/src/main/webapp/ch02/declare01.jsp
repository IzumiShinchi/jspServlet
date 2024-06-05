<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scripting Tag</title>
</head>
<%-- 선언문 태그 [메소드] 주석!--%>
<%! int count=0; %>
<body>
	Page Count is
	<%-- 스크립틀릿 태그 --%>
	<%
	out.println(++count);
	%>
	
	<%
	out.print(myMethod(0));
	%>
	<%-- 선언문 태그 [메소드] --%>
	<%! public int myMethod(int count) {
		return ++count;
	} %>
</body>
</html>