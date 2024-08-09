<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복조회</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select m_id from m_member where m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			
			String rid = "";
			
			if(rs.next()) rid = rs.getString("m_id");
			
			if(id.equals(rid)){
	%>	
				<script>
					alert("<%=rid%>는 이미 가입된 아이디입니다.\n다시 검색해주세요!");
					history.back();
				</script>
	<%		
			} else{
	%>			
				<script>
					alert("<%=id%>는 가입할 수 있는 아이디입니다.");
					location.href="idCheck.jsp?id=<%=id%>";
				</script>
	<%			
			}
		}catch(Exception e){
			out.println("SQLException : "+e.getMessage());
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	%>
</body>
</html>