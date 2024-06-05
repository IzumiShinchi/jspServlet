<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정규표현식</title>
</head>
<script type="text/javascript" src="checkForm.js"></script>
<body onload="document.member.id.focus();">
<h3>회원가입</h3>
	<form name="member" method="post" action="#">
		아이디 : <input type="text" name="id"> <br> 
		비밀번호 : <input type="password" name="passwd1"> <br>
		비밀번호 확인 : <input type="password" name="passwd2"> <br>
		이름 :  <input type="text" name="name"> <br>
		생년월일 : <input type="text" name="birth"> <br>
		연락처 : <input type="text" name="tel"> <br>
		<input type="button" value="회원가입" onclick="checkForm()">
		<input type="reset" value="가입취소">
	</form>
</body>
</html>