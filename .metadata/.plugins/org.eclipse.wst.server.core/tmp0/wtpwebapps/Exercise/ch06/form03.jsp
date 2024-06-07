<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form03</title>
</head>
<body>
	<form name="form" method="post" action="form03_process.jsp">
		<div>
			<label>오렌지</label>
			<input type="checkbox" name="fruit" value="Orange">
		
			<label>사과</label>
			<input type="checkbox" name="fruit" value="Apple">
		
			<label>바나나</label>
			<input type="checkbox" name="fruit" value="Banana">
		
			<input type="submit" value="전송">
		</div>
	</form>
</body>
</html>