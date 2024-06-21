<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<%
		String mNo = "";
		String mId = "";
	
		if(session.getAttribute("mId") != null) {
			mNo = (String) session.getAttribute("mNo");
			mId = (String) session.getAttribute("mId");
		}
		
		//관리자로 로그인했다면
		if(mId.equals("admin")) {
	%>
			<h3><%=mId%>님은 관리자입니다.</h3>
			<a href="list.jsp">회원목록 보기</a>
			<a href="member.jsp">회원가입</a>
			<a href="logout.jsp">로그아웃</a>
	<%		//관리자 페이지
		}
		
		//일반 사용자로 로그인했다면
		else if(!mId.equals("")) {
	%>
			<h3><%=mId%>님, 환영합니다.</h3>
			<a href="edit.jsp?no=<%=mNo%>">회원정보수정</a>
			<a href="delete.jsp?no<%=mNo%>">회원탈퇴</a>
			<a href="logout.jsp">로그아웃</a>
	<%		
		}
		
		//로그인하지 않았다면
		else {
	%>
		<h3>로그인</h3>
			<form action="login_ok.jsp" name="login" method="post">
			아이디 : <input type="text" name="mId"><br>
			비밀번호 : <input type="password" name="mPasswd"><br>
			<input type="submit" value="로그인">
			<input type="reset" value="취소">
			<input type="button" value="회원가입" onclick="location.href='member.jsp'">
		</form>
	<%		
		}
	%>
	<!-- 관리자로 로그인했다면 -->
	
	<!-- 일반 사용자로 로그인했다면 -->
	
	<!-- 로그인하지 않았다면 -->
</body>
</html>