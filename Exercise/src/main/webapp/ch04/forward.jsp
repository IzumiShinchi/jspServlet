<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>구구단 출력하기</h4>
	<jsp:forward page="forward_data.jsp">
		<jsp:param name="catesian" value="5" />
	</jsp:forward>
</body>
</html>
<!-- forward는 지정한 페이지를 실행! -->