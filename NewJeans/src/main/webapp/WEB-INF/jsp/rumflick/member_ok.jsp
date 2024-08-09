<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<%
		PreparedStatement pstmt = null;
	
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mId");
		String mPasswd = request.getParameter("mPasswd");
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
		String mDate = request.getParameter("mDate");
		String mIp = request.getRemoteAddr();
	
		try{
			String sql = "insert into m_member";
			sql = sql + " values(seq_m_member.nextval,?,?,?,?,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mId);
			pstmt.setString(2,mPasswd);
			pstmt.setString(3,mName);
			pstmt.setString(4,mTel);
			pstmt.setString(5,mEmail);
			pstmt.setInt(6,zipNo);
			pstmt.setString(7,roadAddrPart1);
			pstmt.setString(8,addrDetail);
			pstmt.setString(9,mDate);
			pstmt.setString(10,mIp);
			pstmt.executeUpdate();
			
			response.sendRedirect("list.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	%>
</body>
</html>