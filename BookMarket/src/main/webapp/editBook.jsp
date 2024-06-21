<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file = "menu.jsp" %>
<%@ include file = "dbconn.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link href = "./resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>도서 편집</title>
</head>
<script type="text/javascript">
	function deleteConfirm(id) {
		if(confirm("해당 도서를 삭제합니다!!")==true) {
			location.href = "./deleteBook.jsp?id=" + id;
		} else {
			return;
		}
	}
</script>
<%
	String edit = request.getParameter("edit");
%>
<body>
<div class="container py-4">
	<div class="p-5 mb-4 bg-body-tertiary rounded-3">
		<div class="container-fluid py-5">
		<h1 class="display-5 fw-bold">도서 편집</h1>
		<p class="col-md-8 fs-4">Book Editing</p>
		</div>
	</div>
	<div class="row align-items-md-stretch text-center">
	<%
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM book";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
	%>
	<div class="col-md-4">
		<div class="h-100 p-2 rounded-3">
			<img src="./resources/images/<%=rs.getString("b_fileName")%>" style="width:250; height:350"/>
			<p><h5><b><%=rs.getString("b_name") %></b></h5></p>
			<p> <%=rs.getString("b_author") %></p>
			<br> <%=rs.getString("b_publisher") %> | <%=rs.getString("b_unitPrice") %>원
			<p> <%=rs.getString("b_description").substring(0, 60) %>....</p>
			<p> <%=rs.getString("b_unitPrice") %>원</p>
			<p> <% 
				if(edit.equals("update")) {
				%>
				<a href="./updateBook.jsp?id=<%=rs.getString("b_id")%>" class="btn btn-success" role="button">수정 &raquo;</a>
				<%
					} else if(edit.endsWith("delete")) {
				%>
				<a href="#" onclick="deleteConfirm('<%=rs.getString("b_id")%>')" class="btn btn-danger" role="button">삭제 &raquo;</a></p>
				<%
					}
				%>
		</div>
	</div>
	<%
		}
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	%>
	</div>
	<%@ include file = "footer.jsp" %>
</div>

</body>
</html>