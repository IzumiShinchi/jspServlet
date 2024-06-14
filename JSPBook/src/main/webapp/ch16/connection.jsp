<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database SQL</title>
</head>
<body>
	<%
		Connection conn = null;
	
		try{
			String url = "jdbc:oracle:thin:@localhost:1521/oracle";
			String user = "scott";
			String password = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			out.println("데이터베이스 연결에 성공했습니다.");
		} catch(SQLException e){
			out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		} finally{
			if(conn!=null) conn.close();
		}
	%>
</body>
</html>