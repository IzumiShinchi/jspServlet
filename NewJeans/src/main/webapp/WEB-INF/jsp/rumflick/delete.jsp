<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	<%
		int no = Integer.parseInt(request.getParameter("no"));
	%>
	<h3>회원탈퇴</h3>
	<form name="member" method="post" action="delete_ok.jsp">
		<input type="hidden" name="no" value="<%=no%>">
		* 회원탈퇴를 위해 비밀번호를 입력하세요 : <input type="password" name="mPasswd">
	<input type="submit" value="회원탈퇴">
	<input type="button" value="취소" onclick="history.back()">
	</form>
</body>
</html>