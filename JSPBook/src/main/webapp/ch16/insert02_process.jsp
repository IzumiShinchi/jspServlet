<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>
<%@ page import="java.sql.PreparedStatement"%>
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
		
		PreparedStatement pstmt = null;
		
		try{
			String sql = "INSERT INTO member(id, passwd, name) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			out.println("<script>alert('MEMBER 테이블 삽입에 성공했습니다.')</script>");
		} catch(SQLException e){
			out.println("<script>alert('MEMBER 테이블 삽입에 실패했습니다.')</script>");
			out.println("<script>alert('SQLException : ' + e.getMessage())</script>");
			e.printStackTrace();
		} finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		response.sendRedirect("select02.jsp");
		/* 이동한 페이지에서 바로 확인 가능~ */
	%>
</body>
</html>