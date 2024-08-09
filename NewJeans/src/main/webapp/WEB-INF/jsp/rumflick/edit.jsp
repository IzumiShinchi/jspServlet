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
		int no = Integer.parseInt(request.getParameter("no"));
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "select * from m_member where m_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,no);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			String mTel = rs.getString("m_tel");
			String mEmail = rs.getString("m_email");
			String mEmail1 = mEmail.substring(0,mEmail.indexOf("@"));
			String mEmail2 = mEmail.substring(mEmail.lastIndexOf("@")+1);
	%>
	<h2>회원정보수정</h2>
	<form name="member" action="edit_ok.jsp" method="post">
		<input type="hidden" name="no" value="<%=no%>">
		아이디:<input type="text" name="mId" value='<%=rs.getString("m_id")%>'><input type="button" value="아이디중복조회" name="idbtn"><br>
		<p></p>
		비밀번호:<input type="password" name="mPasswd"><br>
		<p></p>
		이름:<input type="text" name="mName" value='<%=rs.getString("m_name")%>'><br>
		<p></p>
		전화번호:<select name="mTel1">
				<option value="">선택하세요</option>
				<option value="010" <% if(mTel.substring(0,3).equals("010")){%>selected<%}%>>010</option>
				<option value="011" <% if(mTel.substring(0,3).equals("011")){%>selected<%}%>>011</option>
				<option value="016" <% if(mTel.substring(0,3).equals("016")){%>selected<%}%>>016</option>
				<option value="017" <% if(mTel.substring(0,3).equals("017")){%>selected<%}%>>017</option>
				<option value="018" <% if(mTel.substring(0,3).equals("018")){%>selected<%}%>>018</option>
				<option value="019"	<% if(mTel.substring(0,3).equals("019")){%>selected<%}%>>019</option>
			   </select>
				-<input type="text" name="mTel2" value="<%=mTel.substring(4,8)%>">
				-<input type="text" name="mTel3" value="<%=mTel.substring(9,13)%>">
		<p></p>
		이메일:<input type="text" name="mEmail1" value="<%=mEmail1%>">@
			 <select name="mEmail2">
			 	<option value="">선택하세요</option>
				<option value="naver.com" <% if(mEmail2.equals("naver.com")){%>selected<%}%>>naver.com</option>
				<option value="kakao.com" <% if(mEmail2.equals("kakao.com")){%>selected<%}%>>kakao.com</option>
				<option value="gmail.com" <% if(mEmail2.equals("gmail.com")){%>selected<%}%>>gmail.com</option>
				<option value="nate.com" <% if(mEmail2.equals("nate.com")){%>selected<%}%>>nate.com</option>
			 </select><br>
		<p></p>
		우편번호:<input type="text" name="zipNo" value='<%=rs.getString("m_zipcode")%>'> <input type="button" value="우편번호검색" name="zipbtn"><br>
		<p></p>
		주소:<input type="text" name="roadAddrPart1" value='<%=rs.getString("m_addr")%>'><br>
		<p></p>
		상세주소:<input type="text" name="addrDetail" value='<%=rs.getString("m_addrdetail")%>'><br>
		<p></p>
		생년월일:<input type="date" name="mDate"><br>
		<p></p>
		<input type="submit" value="수정하기">
		<input type="reset" value="취소">
	</form>
	<%
		}
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
</body>
</html>