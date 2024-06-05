<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유효성 검사</title>
</head>
<script type="text/javascript">
	function checkForm() {
		alert(document.getElementById("id").value + "\n" + document.getElementById("passwd").value);
	}
</script>
<body>
<h3>로그인</h3>
	<form id="login" method="post" action="#">
		아이디 : <input type="text" id="id"> <br>
		비밀번호 : <input type="password" id="passwd"> <br>
		<input type="button" value="전송" onclick="checkForm()">
	</form>
</body>
</html>