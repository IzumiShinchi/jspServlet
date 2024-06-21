<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<title>Database SQL</title>
</head>
<body>
<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("UTF-8");

		String depart = request.getParameter("depart");
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "select * from student where depart = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, depart);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String depart2 = rs.getString("depart");
				
				if (depart.equals(depart2)) {
					sql = "delete from student where depart = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, depart);					
					pstmt.executeUpdate();
					out.println("Student 테이블을 삭제했습니다.");
				} else
					out.println("Student 테이블을 삭제하지 못했습니다.");
			} else
				out.println("Student 테이블에 일치하는 학과가 없습니다.");
		} catch (SQLException ex) {
			out.println("SQLException: " + ex.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)

				conn.close();

		}
	%>
</body>
</html>