<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 내장객체</title>
</head>
<body>
	<h3>로그인</h3>
	<form name="Login" method="post" action="process.jsp">
		아이디 : <input type="text" name="id"> <br>
		비밀번호 : <input type="password" name="pwd"> <br>
		<input type="submit" value="로그인">
		<input type="reset" value="취소">
	</form>
</body>
</html>