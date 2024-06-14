<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		if(id.equals("admin") && passwd.equals("admin1234")) {
			session.setAttribute("userID", id);
			response.sendRedirect("welcome.jsp");
			//out.println("<script>location.href='welcome.jsp'</script>");
		} else {
			out.println("<script>alert('아이디와 비밀번호를 확인해주세요.'); history.back();</script>");
		}
	%>
</body>
</html>