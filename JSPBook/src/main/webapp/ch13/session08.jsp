<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
	<%
		String session_id = session.getId();
	
		long last_time = session.getLastAccessedTime();
		
		long start_time = session.getCreationTime();
		
		long used_time = (last_time - start_time) / 60000;	//밀리세컨(1/1000초)이라서 1000이 더 필요한거야
		
		out.println(" 세션 아이디 : " + session_id + "<br>");
		out.println(" 요청 시작 시간 : " + start_time + "<br>");
		out.println(" 요청 끝난 시간 : " + last_time + "<br>");
		out.println(" 웹 사이트의 경과 시간 : " + used_time + "<br>");
	%>
</body>
</html>