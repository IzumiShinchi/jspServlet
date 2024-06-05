<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과제같은 예제</title>
</head>
<link rel="stylesheet" href="./test.css">
<script type="text/javascript">
	function CheckIt() {
		var form = document.fill;
		
		var regId = /^[a-z|0-9]$/;
		var regPasswd = /^{6,12}$/;
		var regName = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]$/;
		
		var id = form.id.value;
		var passwd1 = form.passwd1.value;
		var passwd2 = form.passwd2.value;
		var name = form.name.value;
		
		if(id=="") {
			alert("아이디를 입력하세요!");
			id.focus();
			return false;
		} 
		
		if(!id.match(regId)) {
			alert("아이디는 영문 소문자와 숫자로만 입력하세요!");
			id.focus();
			return false;
		}
		if(!passwd1.match(regPasswd)) {
			alert("비밀번호는 6자 이상 12자 이하로 입력해주세요!");
			passwd1.focus();
			return false;
		}
		if(passwd1!=passwd2) {
			alert("비밀번호와 확인 비밀번호가 일지하지 않습니다.");
			passwd2.focus();
			return false;
		}
		form.submit();
	}
</script>
<body onload="document.fill.id.focus()">
	<form name="fill" method="post" action="#">
		<fieldset>
			<legend>회원정보입력</legend>
			<div class="wrap">
				<ul>
					<li><label>아이디</label></li>
					<li><label>패스워드</label></li>
					<li><label>패스워드 확인</label></li>
					<li><label>이름</label></li>
					<li><label>휴대폰 번호</label></li>
					<li><label>이메일</label></li>
					<li><label>생일</label></li>
					<li><label>SNS</label></li>
				</ul>
							
				<ul class="twoUl">
					<li><input type="text" id="id" name="id"></li>
					<li><input type="password" id="passwd1" name="passwd1"></li>
					<li><input type="password" id="passwd2" name="passwd2"></li>
					<li><input type="text" id="name" name="name"></li>
					<li><input type="text" id="phone" name="phone"></li>
					<li><input type="text" id="email" name="email"></li>
					<li><input type="date" id="birth" name="birth"></li>
					<li><input type="text" id="sns" name="sns"></li>
				</ul>
				<div class="ckWrap">
					<input type="button" value="중복검사">
				</div>
			</div>
			<div class="radioWrap">
				<div>
					<input type="radio" id="sex" name="sex">
					<label class="position">남성</label>
				</div>
				<div>
					<input type="radio" id="sex" name="sex" checked>
					<label class="position">여성</label>
				</div>
			</div>
		</fieldset>
		<input type="button" value="확인" onclick="checkIt()">
		<input type="reset" value="취소">
	</form>
</body>
</html>