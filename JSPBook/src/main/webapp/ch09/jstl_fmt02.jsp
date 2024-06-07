<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internationalization</title>
</head>
<body>
	<p> 숫자 : <fmt:formatNumber value="3200100"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100" type="number"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100" type="number" groupingUsed="false"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100" type="currency" groupingUsed="true"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100" type="currency" currencySymbol="&"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="0.45" type="percent"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100" minIntegerDigits="10" minFractionDigits="2"></fmt:formatNumber></p> //minIntegerDigits:소수점이상 자리수 minFractionDigits:소수점이하 자리수
	<p> <fmt:formatNumber value="3200100.45" pattern=".000"></fmt:formatNumber></p>
	<p> <fmt:formatNumber value="3200100.456" pattern="#,#00.0#"></fmt:formatNumber></p>
</body>
</html>