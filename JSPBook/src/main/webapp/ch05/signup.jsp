<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
</head>
<body>
	<h3>회원가입</h3>
	<form name="signup" method="post" action="signup_process.jsp">
		아이디 : <input type="text" name="id"> <br>
		비밀번호 : <input type="password" name="pwd"> <br>
		이름 : <input type="text" name="name"> <br>
		연락처 : <input type="tel" name="tel1"> - <input type="tel" name="tel2"> - <input type="tel" name="tel3"> <br> 
		<input type="submit" value="가입하기">
		<input type="reset" value="취소">
	</form>
</body>
</html>