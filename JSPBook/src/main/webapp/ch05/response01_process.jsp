<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Objects</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		if(id.equals("관리자") && pw.equals("1234")) {
			response.sendRedirect("response01_success.jsp");
		} else {
			response.sendRedirect("response01_failed.jsp");
		}
	%>
</body>
</html>