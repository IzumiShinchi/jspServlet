<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script language="javascript">
function goPopup(){
	var pop = window.open("/rum/jusopopup.do","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
		document.member.roadAddrPart1.value = roadAddrPart1;
		document.member.addrDetail.value = addrDetail;
		document.member.zipNo.value = zipNo;
}

function idCheck(){
	var pop = window.open("/rum/idCheck.do","pop","width=570,height=220, scrollbars=yes, resizable=yes"); 
}
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form name="member" action="/rum/memberok.do" method="post">
		아이디 : <input type="text" name="userId"> <input type="button" value="아이디중복조회" name="idbtn" onclick="idCheck()"><br>
		<p></p>
		비밀번호 : <input type="password" name="userPw"><br>
		<p></p> 
		비밀번호 확인 : <input type="password" name="userPwCheck"><br>
		<p></p>
		이름 : <input type="text" name="userName"><br>
		<p></p>
		생년월일 : <input type="date" name="userBirth"><br>
		<p></p>
		성별 : <input type="radio" name="userGender">남	<input type="radio" name="userGender">여<br>
		<p></p>
		전화번호 : <input type="text" name="userPhone" value="010"> - <input type="text" name="userPhone" placeholder="숫자만 입력해주세요~">
		<p></p>
		닉네임 : <input type="text" name="userNick"><br>
		<p></p>
<!-- 		이메일 : <input type="text" name="userEmail">@<select name="userEmail2"> -->
<!-- 				<option value="선택하세요">선택하세요</option> -->
<!-- 				<option value="gmail.com">gmail.com</option> -->
<!-- 				<option value="kakao.com">kakao.com</option> -->
<!-- 				<option value="nate.com">nate.com</option> -->
<!-- 				<option value="naver.com">naver.com</option> -->
<!-- 		</select><br> -->
<!-- 		<p></p> -->
		우편번호 : <input type="text" name="zipNo" readonly onclick="goPopup()"> <input type="button" value="우편번호검색" name="zipbtn" onClick="goPopup();"><br>
		<p></p>
		주소 : <input type="text" name="roadAddrPart1" readonly onclick="goPopup()"><br>
		<p></p>
		상세주소 : <input type="text" name="addrDetail" readonly onclick="goPopup()"><br>
		<p></p>
		<input type="hidden" name="userIp" value="<%=request.getRemoteAddr()%>">
		<input type="submit" value="가입하기">
		<input type="reset" value="취소">
	</form>
</body>
</html>