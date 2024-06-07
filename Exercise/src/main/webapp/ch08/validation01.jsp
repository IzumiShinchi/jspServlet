<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation01</title>
</head>
<script type="text/javascript">
	function checkForm() {
		//var id = document.form.id.value;
		var id = document.getElementbyId("id").value;
		//var passwd = document.passwd.value;
		var passwd = document.getElementbyId("passwd").value;	

		if (id == "") {
			alert("아이디를 입력해주세요");
			return false;
		}
		if (passwd == "") {
			alert("비밀번호를 입력해주세요");
			return false;
		}
		
		if (passwd.indexOf(id) > -1) {
			alert("비밀번호는 ID를 포함할 수 없습니다.");
			return false;
		}
	
		//document.form.submit();
		document.getElementbyId("form").submit();
	}
</script>
<body>
	<h3>LOGIN</h3>
	<form name="form" action="validation_process.jsp" method="post">
		아이디 : <input type="text" id="id"> <br>
		비밀번호 : <input type="text" id="passwd"> <br>
		<input type="button" value="전송" onclick="checkForm()">
	</form>
</body>
</html>