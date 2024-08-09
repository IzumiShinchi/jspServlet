<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>
<%@ include file="admin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<%
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	%>
	<h2>회원목록</h2>
	<table border="1" width="700">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>연락처</th>
			<th>전자우편</th>
			<th>생년월일</th>
			<th>가입일</th>
			<th>회원정보수정/회원탈퇴</th>
		</tr>
	<%
		String sql = "select * from m_member order by m_id desc";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
	%>
			<tr>
				<td><%=rs.getInt("m_no") %></td>
				<td><%=rs.getString("m_id") %></td>
				<td><%=rs.getString("m_name") %></td>
				<td><%=rs.getString("m_tel") %></td>
				<td><%=rs.getString("m_email") %></td>
				<td><%=rs.getString("m_date") %></td>
				<td><%=rs.getString("m_year") %></td>
				<td><a href="edit.jsp?no=<%=rs.getInt("m_no")%>">회원정보수정</a> /
					<a href="delete.jsp?no=<%=rs.getInt("m_no")%>">회원탈퇴</a></td>
			</tr>
	<%		
		}
	%>
	</table>
	<br>
	<button onclick="history.back()"></button>
	<a href="/rumflick/openIndex.do">메인 페이지</a>
	<%
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>