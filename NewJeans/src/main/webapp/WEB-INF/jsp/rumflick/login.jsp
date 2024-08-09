<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>
	<%
		String mNo ="";
		String mId ="";
	
		if(session.getAttribute("mId") != null){
			mNo = (String)session.getAttribute("mNo");
			mId = (String)session.getAttribute("mId");
		}
		
		String sessionId = session.getId();
		
		
		// 관리자로 로그인했다면
		if(mId.equals("admin")){
	%>		
			<h3><%=mId%>님은 관리자입니다.</h3>
			<a href="/rumflicklist.do">회원목록보기</a>
			<a href="/rumflickmember.do">회원가입</a> 
			<a href="/rumflicklogout.do">로그아웃</a>
			<br>
			<a href="/rum/registContent.do">콘텐츠 등록</a>
			<a href="/rumflick/openIndex.do">메인 페이지</a>
			<br>
	<%		//관리자 페이지
		}
		
		// 일반 사용자로 로그인했다면
		else if(!mId.equals("")){
	%>
			<h3><%=mId %>님 환영합니다.</h3>
			<a href="/rumflick/openIndex.do?sessionId=<%=sessionId%>">메인 페이지</a>
			<br>
			<a href="/rumflick/edit.do?no=<%=mNo%>">회원정보수정</a>
			<a href="/rumflick/delete.do?no=<%=mNo%>">회원탈퇴</a> 
			<a href="/rumflick/logout.do">로그아웃</a>
	<%
		}
		//로그인하지 않았다면
		else{
	%>
			<h3>로그인</h3>
			<form name="login" method="post" action="/rumflick/login_ok.do">
				아이디 : <input type="text" name="mId"> <br>
				비밀번호 : <input type="password" name="mPasswd"> <br>
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<input type="button" value="회원가입" onclick="location.href='/rumflick/member.do'">
			</form>
	<%
		}
	%>
	<!-- 관리자로 로그인했다면 -->
	
	<!-- 일반 사용자로 로그인했다면 -->
	
	<!-- 로그인하지 않았다면 -->
</body>
</html>
