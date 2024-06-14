<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
	
		if(request.getCookies()==null) {
			response.sendRedirect("cookie.jsp");
		}
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("userID")) {
				%>
				<h4><%=cookies[i].getValue() %>님, 반갑습니다.</h4>
				<%
				i=cookies.length;
			}
		}
	%>
	<a href="cookie_out.jsp">로그아웃</a>
</body>
</html>