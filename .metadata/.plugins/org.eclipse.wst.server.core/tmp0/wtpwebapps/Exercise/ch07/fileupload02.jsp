<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="fileupload" method="post" action="fileupload02_process.jsp" enctype="multipart/form-data">
		<fileldset>
			<div>
				<p>파일 : <input type="file" name="filename"></p>
				<input type="submit" value="파일 올리기">
			</div>
		</fileldset>
	</form>
</body>
</html>