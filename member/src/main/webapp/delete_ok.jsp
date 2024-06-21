<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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
		String mPasswd = request.getParameter("mPasswd"); //입력받은 비밀번호
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "SELECT m_passwd FROM m_member WHERE m_no=?";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1,	no);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String passwd = rs.getString("m_passwd");		//DB 입력된 비밀번호
			if(mPasswd.equals(passwd)) {
				String sql2 =  "DELETE FROM m_member WHERE m_no=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				
				response.sendRedirect("list.jsp");
			} else {
				out.println("<script>alert('회원탈퇴를 위해 입력하신 비밀번호가 틀렸습니다. \\n 다시 입력해주세요!');history.back();</script>");
			}
		}
		
	%>
	
	<%
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>