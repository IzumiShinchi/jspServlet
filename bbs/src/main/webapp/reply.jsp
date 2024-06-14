<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변글 쓰기 화면</title>
</head>
<body>
	<h1>답변글 쓰기 화면</h1>
	<form action="reply.do" method="post">
		<input type="text" name="num" value="${retrieve.num}">
		<input type="text" name="reproot" value="${replyui.reproot}">
		<input type="text" name="repstep" value="${replyui.repstep}">
		<input type="text" name="repindent" value="${replyui.repindent}">
		
		원래 글 번호 : ${replyui.num}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		조회수 : ${replyui.readcnt}<br>
		제목 : <input type="text" name="title" value="${replyui.title}"><br>
		작성자 : <input type="text" name="author"><br>
		내용 : <textarea rows="10" cols="30" name="content">${replyui.content}</textarea><br>
		<input type="submit" value="답변 달기">
	</form>
	
	<a href="list.do">목록 보기</a>
</body>
</html>