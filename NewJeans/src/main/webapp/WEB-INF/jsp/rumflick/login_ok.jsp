<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page session="true" %>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인처리</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String mId = request.getParameter("mId");
		String mPasswd = request.getParameter("mPasswd");
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "select m_no,m_id,m_passwd from m_member where m_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			String no = rs.getString("m_no");
			String id = rs.getString("m_id");
			String passwd = rs.getString("m_passwd");
			
			if(mId.equals(id) && mPasswd.equals(passwd)){
				//세션설정
				session.setAttribute("mNo", no);
				session.setAttribute("mId", id);
			    String sessionId = session.getId();
			    // 세션 ID를 요청 속성에 저장하여 Thymeleaf로 전달
			    request.setAttribute("sessionId", sessionId);
				response.sendRedirect("login.do");
			}
			else{
				out.println("<script>alert('아이디 또는 비밀번호를 확인하시고 다시 로그인하세요!');history.back();</script>");
			}
		}
		
	
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>