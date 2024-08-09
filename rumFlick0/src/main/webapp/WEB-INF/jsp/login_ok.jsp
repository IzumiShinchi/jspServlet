<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String mId = request.getParameter("mId");
		String mPasswd = request.getParameter("mPasswd");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT m_no, m_id, m_passwd FROM m_member WHERE m_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		rs = pstmt.executeQuery();
			
		if(rs.next()) {
			String no = rs.getString("m_no");
			String id = rs.getString("m_id");
			String passwd = rs.getString("m_passwd");
			if(mId.equals(id) && mPasswd.equals(passwd)) {
				//세션설정
				session.setAttribute("mNo", no);
				session.setAttribute("mId", id);
				//out.print("<script>alert('환영합니다.')</script>");
				response.sendRedirect("/rum/login.do?userId="+id);
			} else {
				out.println("<script>alert('ID 또는 비밀번호가 일치하지 않습니다.\\n다시 확인해주세요!!');history.back();</script>");
			}
		} 
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>