<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String mail = request.getParameter("mail");
		String news = request.getParameter("news");
		String[] irinfo = request.getParameterValues("irinfo");
		String memo = request.getParameter("memo"); 
	%>
	
	<p> 아이디	: <%= id %></p>
	<p> 비밀번호	: <%= pw %></p>
	<p> 이메일	: <%= email %> @ <%= mail %>
	<p> 뉴스수신	: <%= news %>
	<p> 관심정보	: <%
					if (irinfo != null) {
						for(int i=0; i < irinfo.length; i++) {
							out.println(" " + irinfo[i]);
						}
					}
					%>
	</p>
	<p> 메모		: <%= memo %></p>
</body>
</html>