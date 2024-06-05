<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration</title>
<style type="text/css">
	table, tr, td {border : 1px solid black; border-collapse:collapse;}
</style>
</head>
<body>
<form name="registraion" method="post" action="custregist_process.jsp">
	<table>
		<tr>
			<td>*아이디</td>
			<td><input type="text" name="id"> </td>
		</tr>
		<tr>
			<td>*비밀번호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td rowspan="2">*이메일</td>
			<td>
				<input type="text" name="email"> @ <select name="mail">
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="kakao.com">kakao.com</option>
					<option value="google.com">google.com</option>
					<option value="yahoo.co.kr">yahoo.co.kr</option>
				</select></td>
		</tr>
		<tr>
			<td>뉴스를 받으시겠습니까? <input type="radio" name="news" value="뉴스 수신">수신동의 <input type="radio" name="news" value="수신거부" checked>동의안함</td>
		</tr>
		<tr>
			<td>*관심 정보</td>
			<td><input type="checkbox" name="irinfo" value="피부관리">피부관리 <input type="checkbox" name="irinfo" value="천연화장품">천연화장품 
			<input type="checkbox" name="irinfo" value="피부나이측정">피부나이측정 </td>
		</tr>
		<tr>
			<td>*메모</td>
			<td><textarea name="memo" rows="3" cols="100" placeholder="하실 말씀이 있으시다면..요?" wrap="hard"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="확인"></td>
		</tr>
	</table>
</form>
</body>
</html>