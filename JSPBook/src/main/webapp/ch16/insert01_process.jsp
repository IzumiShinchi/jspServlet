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
	<%
		request.setCharacterEncoding("UTF-8");
	
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		
		Statement stmt = null;
		
		try{
			String sql = "INSERT INTO member(id, passwd, name) VALUES ('" + id + "', '" + passwd + "', '" + name+ "')";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			out.println("MEMBER 테이블 삽입에 성공했습니다.");
		} catch(SQLException e){
			out.println("MEMBER 테이블 삽입에 실패했습니다.");
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace();
		} finally{
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}
		
		response.sendRedirect("select01.jsp");
		/* 이동한 페이지에서 바로 확인 가능~ */
	%>
</body>
</html>