<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation</title>
</head>
<script type="text/javascript">
	function checkLogin() {
		var form = document.loginForm;
		if(form.id.value=="") {
			alert("아이디를 입력해주세요! @o@ ");
			form.id.focus();
			return false;
		} else if(form.passwd.value=="") {
			alert("비밀번호를 입력해주세요! @_@ ");
			form.passwd.focus();
			return false;
		}
		form.submit();
	}
</script>
<body onload="document.loginForm.id.focus()">
	<form name="loginForm" method="post" action="validation02_process.jsp">
		<p> 아 이 디 : <input type="text" name="id"></p>
		<p> 비밀번호 : <input type="password" name="passwd"></p>
		<p> <input type="button" value="전송" onclick="checkLogin()"></p>
	</form>
</body>
</html>