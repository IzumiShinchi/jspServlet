<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form02</title>
</head>
<body>
	<form name="form" method="post" action="form02_process.jsp">
		<div>
			<label>이름 : </label>
			<input type="text" name="name">
		</div>
		<div>
			<label>주소 : </label>
			<input type="text" name="addr">
		</div>
		<div>
			<label>이멜 : </label>
			<input type="text" name="mail">
		</div>
		<div>
			<input type="submit" value="전송">
		</div>
	</form>
</body>
</html>