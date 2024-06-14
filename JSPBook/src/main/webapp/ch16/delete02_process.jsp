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
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "SELECT id, passwd FROM member WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String rId = rs.getString("id");
				String rPasswd = rs.getString("passwd");
				
				if(id.equals(rId) && passwd.equals(rPasswd)) {
					sql = "DELETE FROM MEMBER WHERE id=? AND passwd=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, passwd);
					pstmt.executeUpdate();
					out.println("<script>alert('MEMBER 테이블을 삭제했습니다.');</script>");
				} else {
					out.println("<script>alert('일치하는 비밀번호가 아닙니다.')</script>");
				}
			} else {
				out.println("<script>alert('MEMBER 테이블에 일치하는 아이디가 없습니다.')</script>");
			}
		} catch(SQLException e) {
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace();
		} finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		response.sendRedirect("select01.jsp");
	%>	
</body>
</html>