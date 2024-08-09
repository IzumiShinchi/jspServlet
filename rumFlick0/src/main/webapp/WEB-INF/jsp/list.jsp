<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	%>
	<h2>회원목록</h2>
	<table border="1" width="700">
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>연락처</th>
			<th>전자우편주소</th>
			<th>생년월일</th>
			<th>가입일</th>
			<th>회원정보수정/회원탈퇴</th>
		</tr>
		<%
			String sql = "SELECT m_no, m_id, m_name, m_tel, m_email, m_date, m_year FROM m_member order by m_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
		%>
			<tr>
				<td><%=rs.getInt("m_no") %></td>
				<td><%=rs.getString("m_id") %></td>
				<td><%=rs.getString("m_name") %></td>
				<td><%=rs.getString("m_tel") %></td>
				<td><%=rs.getString("m_email") %></td>
				<td><%=rs.getString("m_date") %></td>
				<td><%=rs.getString("m_year") %></td>
				<td><a href="/rum/edit.do?no=<%=rs.getInt("m_no")%>">회원정보수정</a>/<a href="/rum/delete.do?no=<%=rs.getInt("m_no")%>">회원탈퇴</a></td>
			</tr>
		<%		
			}
		%>
	</table>
	<a href="/rum/main.do">메인페이지</a>
	<%
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>