<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 접근불가</title>
</head>
<body>
	<%
		//관리자페이지 접근불가
	
		String mId = "";
		
		if(session.getAttribute("mId")!= null){
			mId=(String)session.getAttribute("mId");
		}
		
		if(!mId.equals("admin")){
			response.sendRedirect("login.do");
		}
	%>
</body>
</html>