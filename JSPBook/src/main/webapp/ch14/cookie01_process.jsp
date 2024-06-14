<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie</title>
</head>
<body>
	<%
		String userID = request.getParameter("id");
		String userPW = request.getParameter("passwd");
		
		if(userID.equals("admin") && userPW.equals("passwd")) {
			Cookie cookieID = new Cookie("userID", userID);
			Cookie cookiePW = new Cookie("userPW", userPW);
			response.addCookie(cookieID);
			response.addCookie(cookiePW);
			
			out.println("쿠키 생성이 성공했습니다.<br>");
			out.println(userID + "님, 들어오셨군요.<br>");
		} else {
			out.println("쿠키 생성에 실패했습니다.");
		}
	%>
</body>
</html>