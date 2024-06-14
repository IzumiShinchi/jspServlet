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
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("id");
		String userPW = request.getParameter("passwd");
		
		if(userID.equals("admin") && userPW.equals("admin")) {
			Cookie cookieId = new Cookie("userID", userID);
			cookieId.setMaxAge(60*60); 	//60분
			response.addCookie(cookieId);
			response.sendRedirect("welcome.jsp");
			//out.println("<script>location.href='welcome.jsp'</script>");
		} else {
			out.println("<script>alert('아이디와 비밀번호를 확인해주세요.');history.back();</script>");
		}
	%>
</body>
</html>