<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file = "dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database SQL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "SELECT id, passwd FROM member WHERE id= '" +  id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String rId = rs.getString(1);
				String rPasswd = rs.getString(2);
				
				if(id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "UPDATE member SET name = '" + name + "' WHERE id= '" + id + "'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					out.println("MEMBER 테이블을 수정했습니다.");
				} else {
					out.println("일치하는 비밀번호가 없습니다.");
				}
			} else {
				out.println("MEMBER 테이블에 일치하는 아이디가 없습니다.");
			}
		} catch(SQLException e) {
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace();
		} finally{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}
		response.sendRedirect("select01.jsp");
	%>
</body>
</html>