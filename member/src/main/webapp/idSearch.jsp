<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
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
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select m_id from m_member where m_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			String rId = "";
			
			if(rs.next()) rId=rs.getString("m_id");
			//위에 SQL이 정상적으로 실행되서 id가 넘어오면 담기고, 아니면 가져올 rId가 없음. 즉 rId가 있으면 가입x. 없으면 가입o
			if(id.equals(rId)) {
// 				검색된 아이디는 <%=rId>이며, 사용할 수 없는 아이디입니다.<br>
// 				다른 아이디를 입력해주세요!<br>
// 				<input type="button" value="닫기" onclick="window.close()">
	%>
				<script>
					alert("<%=rId%>는 누군가 사용하는 아이디입니다.\n다시 생각해주세요~");
					history.back();
				</script>
	<%
			} else {
// 				사용할 수 있는 아이디입니다.<br>
// 				<input type="button" value="닫기" onclick="window.close()">
	%>
				<script>
					alert("<%=id%>는 사용할 수 있는 아이디입니다.");
					location.href="idCheck.jsp?id=<%=id%>";
				</script>
	<%
			}
		} catch(Exception e) {
			out.println("SQLException : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();	
		}
	%>
</body>
</html>