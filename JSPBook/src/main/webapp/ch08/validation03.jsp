<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation</title>
</head>
<script>
	function checkLogin() {
		var form = document.loginForm;
		
		if(form.id.value.length < 4 || form.id.value.length > 12) {
			alert("아이디는 4~12자 이내로 입력 가능합니다!");
			form.id.focus();
			return false;
		}
		
		if(form.passwd.value.length < 4) {
			alert("비밀번호는 4자 이상으로 입력해야 합니다.")
			form.passwd.focus();
			return false;
		}
		form.submit();
	}
</script>
<body>
	<form name="loginForm" method="post" action="validation03_process.jsp">
		<p> 아 이 디 : <input type="text" name="id"></p>
		<p> 비밀번호 : <input type="password" name="passwd"></p>
		<p> <input type="button" value="전송" onclick="checkLogin()"></p>
	</form>
</body>
</html>