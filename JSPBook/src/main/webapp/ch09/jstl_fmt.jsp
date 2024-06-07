<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 처리</title>
</head>
<body>
	<fmt:setLocale value="ko"/>
	<fmt:setBundle basename="ch09.com.test.message"></fmt:setBundle>
	이름 : <fmt:message key="name"></fmt:message><br>
	인사 : <fmt:message key="hello"></fmt:message><br>
	
	<fmt:setLocale value="en"/>
	<fmt:setBundle basename="ch09.com.test.message"></fmt:setBundle>
	이름 : <fmt:message key="name"></fmt:message><br>
	인사 : <fmt:message key="hello"></fmt:message>
	
</body>
</html>