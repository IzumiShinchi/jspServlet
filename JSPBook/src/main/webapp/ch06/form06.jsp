<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>
</head>
<body>
	<h3>회원가입</h3>
	<form name="member" method="post" action="form06_process.jsp">
		<p>아이디 : <input type="text" name="id" autofocus required>  <input type="button" value="아이디 중복 검사"></p>
		<p>비밀번호 : <input type="password" name="passwd" required></p>
		<p>이름 : <input type="text" name="name" placeholder="이름을 한글로 입력해주세요." required></p>
		<p>연락처 : <select name="phone1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="019">019</option>
		</select> - <input type="text" maxlength="4" name="phone2"> 
		- <input type="text" maxlength="4" name="phone3"></p>
		<p>성별 : <input type="radio" name="sex" value="남성">남성
		<input type="radio" name="sex" value="여성" checked> 여성</p>
		<p>취미 : 독서<input type="checkbox" name="hobby" value="독서" checked>
		운동<input type="checkbox" name="hobby" value="운동">
		영화<input type="checkbox" name="hobby" value="영화">
				<p><textarea name="comment" cols="30" rows="3" placeholder="가입 인사를 입력해주세요." wrap="soft"></textarea></p>
		<p><input type="submit" value="가입하기"> <input type="reset" value="취소"></p>
	</form>
</body>
</html>