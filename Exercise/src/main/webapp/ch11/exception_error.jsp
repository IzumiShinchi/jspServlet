<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(response.getStatus()==500) {
	%>
	오류 발생 : <%=exception.getMessage()%>
	
	<%-- include login page --%>
	<%@ include file = "exception.jsp" %>
	<%		
		} else {
	%>
	오류 발생 코드 : <%=response.getStatus()%><br>
	<a href="/exception.jsp">처음 페이지로 이동</a>
	<%
		}
	%>
</body>
</html>