<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form03 Process</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String[] fruit = request.getParameterValues("fruit");
	%>
	
	<p>선택한 과일</p>
	<p>
		<% if(fruit!=null) {
			for(int i=0; i<fruit.length; i++ ) {
			out.print(" " + fruit[i]);
				}
			}
		%>
	</p>
</body>
</html>