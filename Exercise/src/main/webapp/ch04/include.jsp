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
	<jsp:include page="include_data.jsp" flush="false">
		<jsp:param name="number" value="5"/> 
	</jsp:include>
</body>
</html>
<!-- include는 지정한 페이지를 불러와서(포함해서) 실행 -->