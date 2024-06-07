<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internationalization</title>
</head>
<body>
	<p> <jsp:useBean id="now" class="java.util.Date"></jsp:useBean></p>
	<p> <fmt:formatDate value="${now}" type="date"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="time"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" dateStyle="default" timeStyle="default"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" dateStyle="short" timeStyle="short"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="medium"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"></fmt:formatDate></p>
	<p> <fmt:formatDate value="${now}" type="both" pattern="yyy년 MM월 dd일 E요일 HH시 mm분 ss초"></fmt:formatDate></p>
</body>
</html>