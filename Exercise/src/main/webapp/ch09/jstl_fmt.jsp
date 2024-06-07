<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fmt setlocale, bundle</title>
</head>
<body>
	<fmt:setLocale value='<%=request.getParameter("language") %>'></fmt:setLocale>
<!-- 	안과 밖 구분하려고 따옴표 따로 쓰는 거였어. -->
	<fmt:bundle basename="ch09.com.bundle.myBundle">
		<a href="?language=ko">Korean</a> | <a href="?language=en">English</a>
		<form action="#" method="post">
			<p><fmt:message key="id"></fmt:message> : <input type="text" name="id"></p>
			<p><fmt:message key="password"></fmt:message> : <input type="paasword" name="password"></p>
			<p><input type="submit" value="<fmt:message key="button"/>"></p>
		</form>	
	</fmt:bundle>
</body>
</html>