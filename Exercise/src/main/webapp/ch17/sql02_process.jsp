<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<title>JSTL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String num = request.getParameter("num");
		String depart = request.getParameter("depart");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
	
	%>
	<sql:setDataSource var="dataSource"
		url="jdbc:mysql://localhost:3306/ExerciseDB"
		driver="com.mysql.jdbc.Driver" user="root" password="1234" />


	<sql:update dataSource="${dataSource}" var="resultSet">
		INSERT INTO student(num, depart, name, address, phone) values(?,?,?,?,?)
		<sql:param value="<%=num%>" />
		<sql:param value="<%=depart%>" />
		<sql:param value="<%=name%>" />
		<sql:param value="<%=address%>" />
		<sql:param value="<%=phone%>" />
	</sql:update>
	<c:import var="url" url="sql01.jsp"  />
	${url}
</body>
</html>