<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database SQL</title>
</head>
<body>
	<table width="300" border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
		</tr>
		<%
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try{
				String sql = "SELECT * FROM member";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("id");
					String passwd = rs.getString("passwd");
					String name = rs.getString("name");
		%>
					<tr>
						<td><%=id %></td>
						<td><%=passwd %></td>
						<td><%=name %></td>
					</tr>
		<%
				}
			} catch(SQLException e) {
				out.println("MEMBER 테이블 호출에 실패했습니다.");
				e.printStackTrace();
			} finally{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
		%>
	</table>
	<a href="insert02.jsp">가입</a>
	<a href="update02.jsp">수정</a>	
	<a href="delete02.jsp">삭제</a>		
</body>
</html>