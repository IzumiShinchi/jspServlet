<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<%
		int no = Integer.parseInt(request.getParameter("no"));
		String mPasswd = request.getParameter("mPasswd"); //입력받은 비밀번호
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String mTel1 = request.getParameter("mTel1");
		String mTel2 = request.getParameter("mTel2");
		String mTel3 = request.getParameter("mTel3");
		String mTel = mTel1 + "-" + mTel2 + "-" + mTel3;
		String mEmail1 = request.getParameter("mEmail1");
		String mEmail2 = request.getParameter("mEmail2");
		String mEmail = mEmail1 + "@" + mEmail2;
		int zipNo = Integer.parseInt(request.getParameter("zipNo"));
		String roadAddrPart1 = request.getParameter("roadAddrPart1");
		String addrDetail = request.getParameter("addrDetail");
		
		String sql1 = "SELECT m_passwd FROM m_member WHERE m_no=?";
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1,	no);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String passwd = rs.getString("m_passwd");		//DB 입력된 비밀번호
			if(mPasswd.equals(passwd)) {
				
				String sql2 =  "UPDATE m_member SET m_id=?, m_name=?, m_tel=?, m_email=?, m_zipcode=?, m_addr=?, m_addrdetail=? WHERE m_no=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, mId);
				pstmt.setString(2, mName);
				pstmt.setString(3, mTel);
				pstmt.setString(4, mEmail);
				pstmt.setInt(5, zipNo);
				pstmt.setString(6, roadAddrPart1);
				pstmt.setString(7, addrDetail);
				pstmt.setInt(8, no);
				pstmt.executeUpdate();
				
				response.sendRedirect("/rum/list.do");
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