<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
	var mNo = "";
	var mId = "";
// 	var sessionId = "";
	
	if(session.getAttribute("mId") != null) {
		mNo = session.getAttribute("mNo");
		mId = session.getAttribute("mId");
	}
// 	if(session.getId() != null) {
// 		sessionId = session.getId();
// 	}
</script>
</head>
<body>
<!-- <input type="hidden" name="sessionId" value="sessionId">	 -->
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
			<a href="/rum/list.do">회원목록 보기</a>
			<a href="/rum/member.do">회원가입</a>
			<a href="/rum/logout.do">로그아웃</a>
			<br>
			<a href="/rum/registContent.do">콘텐츠 등록</a>
	<%		//관리자 페이지
		}
		
		//일반 사용자로 로그인했다면
		else if(!mId.equals("")) {
	%>
			<h3><%=mId%>님, 환영합니다.</h3>
<%-- 			<a href="/rum/main.do?sessionId=<%=session.getId()%>">메인 페이지</a> --%>
			<a href="/rum/main.do">메인 페이지</a>
			<br>
			<a href="/rum/edit.do?no=<%=mNo%>">회원정보수정</a>
			<a href="/rum/delete.do?no=<%=mNo%>">회원탈퇴</a>
			<a href="/rum/logout.do">로그아웃</a>
	<%		
		}
		
		//로그인하지 않았다면
		else {
	%>
		<h3>로그인</h3>
			<form action="/rum/loginok.do" name="login" method="post">
			아이디 : <input type="text" name="mId"><br>
			비밀번호 : <input type="password" name="mPasswd"><br>
			<input type="submit" value="로그인">
			<input type="reset" value="취소" onclick="history.back()">
			<input type="button" value="회원가입" onclick="location.href='/rum/member.do'">
		</form>
	<%		
		}
	%>
	<!-- 관리자로 로그인했다면 -->
	
	<!-- 일반 사용자로 로그인했다면 -->
	
	<!-- 로그인하지 않았다면 -->
</body>
</html>