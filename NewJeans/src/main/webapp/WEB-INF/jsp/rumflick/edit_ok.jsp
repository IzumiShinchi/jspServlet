<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	
	int mNo = Integer.parseInt(request.getParameter("no"));
	String mPasswd = request.getParameter("mPasswd");
	String mName = request.getParameter("mName");
	String mZipcode = request.getParameter("zipNo");
	String mAddr = request.getParameter("roadAddrPart1");
	String mAddrdetail = request.getParameter("addrDetail");
	String mTel = request.getParameter("mTel1")+"-"+request.getParameter("mTel2")+"-"+request.getParameter("mTel3");
	String mEmail1 = request.getParameter("mEmail1")+"@"+request.getParameter("mEmail2");
	String mIp = request.getRemoteAddr();
	
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "SELECT m_passwd FROM m_member where m_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,mNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String rmPasswd = rs.getString("m_passwd");
				if(mPasswd.equals(rmPasswd)){
					sql = "UPDATE m_member set m_name=?,m_zipcode=?,m_addr=?,m_addrdetail=?";
					sql = sql + ",m_tel=?,m_email=?, m_ip=? where m_no=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,mName);
					pstmt.setString(2,mZipcode);
					pstmt.setString(3,mAddr);
					pstmt.setString(4,mAddrdetail);
					pstmt.setString(5,mTel);
					pstmt.setString(6,mEmail1);
					pstmt.setString(7,mIp);
					pstmt.setInt(8,mNo);
					pstmt.executeUpdate();
					
					response.sendRedirect("list.jsp");
				}
				else{
					out.println("<script>alert('회원수정을 위한 비밀번호가 틀렸습니다.');history.back();</script>");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null) rs.close();
			if(pstmt != null) rs.close();
			if(conn != null) conn.close();
		}
	%>
</body>
</html>