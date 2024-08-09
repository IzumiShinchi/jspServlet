<%@ page import="java.sql.*"%>
<%
	Connection conn = null;

	String url = "jdbc:oracle:thin:@localhost:1521/oracle";
	String user = "scott";
	String password = "tiger";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection(url, user, password);
%>