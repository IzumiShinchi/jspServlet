<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form Process</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		String message;
		
		if(id.equals("admin") && passwd.equals("admin1234")) {
			
			session.setAttribute("userID", id);
			
			message = "로그인 성공!";
		} else {
			message = "로그인 실패!";
		}
	%>
	<%=message %> <!-- ah...그대로 표현 -->
</body>
</html>