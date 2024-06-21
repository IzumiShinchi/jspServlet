<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
	<p> java 문자열 검색</p>
	<p> Hello, Java Server Pages! => ${fn:contains("Hello, Java Server Pages!", "java")}</p>
	<p> hello, java server pages! => ${fn:containsIgnoreCase("hello, java server pages!", "Java")}</p>
</body>
</html>