<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" import="jakarta.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 보기</title>
</head>
<body>
<h1>게시판 목록 보기</h1>

<%-- <%Object req=request.getAttribute("list");%>
<%=req.toString() %> --%>

<table border="1">
	<tr>
		<td colspan="5">
			<form action="search.do">
				<select name="searchName" size="1">
					<option value="author">작성자</option>
					<option value="title">제목</option>
					<option value="writeday">작성일</option>
				</select>
				<input type="text" name="searchValue">
				<input type="submit" value="찾기">
			</form>
		</td>
	</tr>
	
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	
	<c:forEach items="${list}" var="dto" step="1" >
		<tr>
			<td>${dto.num}</td>
			<td>
				<c:forEach begin="1" end="${dto.repindent}">
					<%="&nbsp;&nbsp;" %>
				</c:forEach>
				<a href="retrieve.do?num=${dto.num}">${dto.title}</a>
			</td>
			<%-- <td><a href="retrieve.do?num=${dto.num}">${dto.title}</a></td> --%>
			<%-- <td>${dto.title}</td> --%>
			<td>${dto.author}</td>
			<td>${dto.writeday}</td>
			<td>${dto.readcnt}</td>
		</tr>
	</c:forEach>
	
</table>
<a href="writeui.do">글쓰기</a>
</body>
</html>