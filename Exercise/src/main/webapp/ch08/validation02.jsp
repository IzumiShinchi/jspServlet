<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
var pw_passed = true;  
	function checkPasswd() {
		var id = document.getElementById("id").value;
		var passwd = document.getElementById("passwd").value;
		var passwdConfirm = document.getElementById("passwdConfirm").value;
		pw_passed = true;
		
		if (id == "") {
			alert("아이디를 입력해주세요");
			return false;
		}
		if (passwd == "") {
			alert("비밀번호를 입력해주세요");
			return false;

		}
		if (passwd != passwdConfirm) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}	

		var SameChar = 0; // 동일문자 카운트
		var sequenceCount1 = 0; // 연속성(+) 카운트
		var sequenceCount2 = 0; // 연속성(-) 카운트

		for (var i = 0; i < passwd.length; i++) {
			var char0;
			var char1;
			var char2;

			if (i >= 2) {
				char0 = passwd.charCodeAt(i - 2);
				char1 = passwd.charCodeAt(i - 1);
				char2 = passwd.charCodeAt(i);

				//동일문자 카운트
				if ((char0 == char1) && (char1 == char2)) 
					SameChar++;
				 else 
					SameChar = 0;

			
				//연속성(+) 카운트

				if (char0 - char1 == 1 && char1 - char2 == 1)
					sequenceCount1++;
				 else
					sequenceCount1 = 0;

				

				//연속성(-) 카운트

				if (char0 - char1 == -1 && char1 - char2 == -1)
					sequenceCount2++;
				else
					sequenceCount2 = 0;			

			}

			if (SameChar > 0) {
				alert("동일문자를 3자 이상 연속 입력할 수 없습니다.");
				pw_passed = false;
			}

			if (sequenceCount1 > 0 || sequenceCount2 > 0) {
				alert("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
				pw_passed = false;
			}

			if (!pw_passed) {
				return false;
				break;
			}

		}

		document.form.submit();

	}
</script>
<body>
	<form name="form" action="validation_process.jsp" method="post">
		<p>아이디 : <input type="text" id="id" name="id">
		<p>비밀번호 : <input type="password" id="passwd" name="passwd">
		<p>비밀번호 확인 : <input type="password" id="passwdConfirm"> 
		<p><input type="button" value="전송" onclick="checkPasswd()">
	</form>
</body>
</html>