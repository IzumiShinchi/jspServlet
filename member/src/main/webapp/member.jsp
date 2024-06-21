<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<form name="member" action="member_ok.jsp" method="post">
		아이디 : <input type="text" name="mId"><input type="button" value="아이디중복조회" name="idbtn"><br>
		<p></p>
		비밀번호 : <input type="password" name="mPasswd"><br>
		<p></p> 
		비밀번호확인 : <input type="password" name="mPasswd2"><br>
		<p></p>
		이름:<input type="text" name="mName"><br>
		<p></p>
		전화번호 : <select name="mTel1">
				<option value="선택하세요"></option>
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="012">012</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="019">019</option>
		</select>-<input type="text" name="mTel2">-<input type="text" name="mTel3">
		<p></p>
		이메일 : <input type="text" name="mEmail1">@<select name="mEmail2">
				<option value="선택하세요">선택하세요</option>
				<option value="gmail.com">gmail.com</option>
				<option value="kakao.com">kakao.com</option>
				<option value="nate.com">nate.com</option>
				<option value="naver.com">naver.com</option>
		</select><br>
		<p></p>
		우편번호 : <input type="text" name="zipNo"> <input type="button" value="우편번호검색" name="zipbtn"><br>
		<p></p>
		주소 : <input type="text" name="roadAddrPart1" ><br>
		<p></p>
		상세주소 : <input type="text" name="addrDetail" ><br>
		<p></p>
		생년월일 : <input type="date" name="mDate"><br>
		<p></p>
		<input type="submit" value="가입하기">
		<input type="reset" value="취소">
	</form>
</body>
</html>