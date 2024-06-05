<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" import="jakarta.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원테이블보기</title>
</head>
<body>
<h1>사원테이블보기</h1>

<%-- <%Object req=request.getAttribute("list");%>
<%=req.toString() %> --%>

<table border="1">
	<tr>
		<td colspan="8">사원테이블</td>
	</tr>
	
	<tr>
		<td>사원번호</td>
		<td>사원이름</td>
		<td>직책</td>
		<td>관리자</td>
		<td>입사일</td>
		<td>연봉</td>
		<td>커미션</td>
		<td>부서번호</td>
	</tr>
	
	<c:forEach items="${list}" var="dto" step="1" >
		<tr>
			<td>${dto.empno}</td>
			<td>${dto.ename}</td>
			<td>${dto.job}</td>
			<td>${dto.mgr}</td>
			<td>${dto.hiredate}</td>
			<td>${dto.sal}</td>
			<td>${dto.comm}</td>
			<td>${dto.deptno}</td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>