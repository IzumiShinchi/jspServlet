<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>JSTL</title>
</head>
<body>
	<%
		String number = request.getParameter("num");
	%>
	<c:set var="number" value="<%=number%>" />
	<c:choose>
		<c:when test="${number>=90}">
			점수 <c:out value="${number}" />은 A학점입니다.
		</c:when>
		<c:when test="${number>=80}">
			점수 <c:out value="${number}" />은 B학점입니다.
		</c:when>
		<c:when test="${number>=70}">
			점수 <c:out value="${number}" />은 C학점입니다.
		</c:when>
		<c:when test="${number>=60}">
			점수 <c:out value="${number}" />은 D학점입니다.
		</c:when>		
		<c:otherwise>
			점수 <c:out value="${number}" />은 F학점입니다.
		</c:otherwise>
	</c:choose>
</body>
</html>