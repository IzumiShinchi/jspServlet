<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	<%
		int no = Integer.parseInt(request.getParameter("no"));
		String mPasswd = request.getParameter("mPasswd");
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql1 = "select m_passwd from m_member where m_no=?";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1,no);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			String passwd = rs.getString("m_passwd");
			
			if(mPasswd.equals(passwd)){
				String sql2 = "delete from m_member where m_no=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1,no);
				pstmt.executeUpdate();
				
				response.sendRedirect("list.jsp");
			}
			else{
				out.println("<script>alert('회원탈퇴를 위한 비밀번호가 틀렸습니다.');history.back();</script>");
			}
			
		}
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>