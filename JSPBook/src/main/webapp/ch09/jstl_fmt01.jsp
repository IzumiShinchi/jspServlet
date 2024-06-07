<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internationalization</title>
</head>
<body>
	<p>----------기본 로케일----------</p>
		<fmt:setBundle basename="ch09.com.bundle.myBundle" var="resourceBundle"></fmt:setBundle>
		<p> 제목 : <fmt:message key="title" bundle="${resourceBundle}"></fmt:message></p>
		<p> <fmt:message key="username" var="usrMsg" bundle="${resourceBundle}"></fmt:message></p>
			이름 : ${usrMsg}
			
			
	<p>----------영문 로케일----------</p>
		<fmt:setLocale value="en"></fmt:setLocale>
		<fmt:setBundle basename="ch09.com.bundle.myBundle" var="resourceBundle"></fmt:setBundle>
		<p> 제목 : <fmt:message key="title" bundle="${resourceBundle}"></fmt:message></p>
		<p> 이름 : <fmt:message key="username" bundle="${resourceBundle}"></fmt:message></p>
	
</body>
</html>