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
			Statement stmt=null;
			ResultSet rs=null;
			
			try{
				String sql = "SELECT * FROM member";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					String id = rs.getString(1);
					String passwd = rs.getString(2);
					String name = rs.getString(3);
		%>
					<tr>
						<td><%=id %></td>
						<td><%=passwd %></td>
						<td><%=name %></td>
					</tr>
		<%
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}
		%>
	</table>
	<a href="insert01.jsp">가입</a>
	<a href="update01.jsp">수정</a>	
	<a href="delete01.jsp">삭제</a>		
</body>
</html>