<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Connection conn = null;
	
	try{
		String url = "jdbc:oracle:thin:@localhost:1521/orcl";
		String user = "scott";
		String password = "tiger";
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, user, password);
	}catch(Exception e){
		e.printStackTrace();
	}
%>