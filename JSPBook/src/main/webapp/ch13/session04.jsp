<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
	<h4>----- 세션을 삭제하기 전 -----</h4>
	<%
		String user_id = (String) session.getAttribute("userID");
		String user_pw = (String) session.getAttribute("userPW");
		
		out.println("설정된 세션의 이름 userID : " + user_id + "<br>");
		out.println("설정된 세션의 값 userPW : " + user_pw + "<br>");
		
		session.removeAttribute("userID");
		session.removeAttribute("userPW");
	%>

	<h4>----- 세션을 삭세하고나서 -----</h4>
	<%
		user_id = (String) session.getAttribute("userID");
		user_pw = (String) session.getAttribute("userPW");
		
		out.println("설정된 세션의 이름 userID : " + user_id + "<br>");
		out.println("설정된 세션의 값 userPW : " + user_pw + "<br>");
	%>
</body>
</html>