<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation02</title>
</head>
<script>
    function checkPasswd() {
        var id = document.getElementById("id").value;
        var passwd = document.getElementById("passwd").value; 
        var passwdConfirm = document.getElementById("passwdConfirm").value; 
        
        pw_passed = true;
        
        var pattern1 = /^[0-9]$/;
        var pattern2 = /^[a-zA-Z]$/;
        var pattern3 = /^[~!@\#$%<>^&*]$/;   

        var pw_msg = "";

        if(id == "") {
               alert("아이디를 입력해주세요");
               return false;
        } 
        if(passwd == "") {
               alert("비밀번호를 입력해주세요");
               return false; 
        }
        if( passwd  !=  passwdConfirm) {
               alert("비밀번호가 일치하지 않습니다.");
               return false;                    
         }

       if(!pattern1.test(passwd)||!pattern2.test(passwd)||!pattern3.test(passwd)||passwd.length<8||passwd.length>50){
            alert("영문+숫자+특수기호 8자리 이상으로 구성하여야 합니다.");
            return false;
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