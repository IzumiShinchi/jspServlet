<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id = request.getParameter("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
</title>
<script>
	function idCheck(id){
		if(id==""){
			alert("중복체크할 아이디를 입력하세요.");
			member.id.focus();
			return false;
		}
		location.href="/rumflick/idsearch.do?id="+id;
	}
	
	function closewin(){
		opener.member.mId.value="<%=id%>";
		window.close();
	} 

</script>
</head>
<body onload="member.id.focus()">
	<% 
	if(id==null){ 
	%>
		<h3>아이디 중복조회</h3>
		<form name="member" method="post" action="#">
			아이디 : <input type="text" name="id">
			<input type="button" value="아이디중복조회" onclick="idCheck(member.id.value)">
		</form>
	<% } else{%>
		<h4><%=id %>는 가입할 수 있는 아이디입니다.</h4>
		<input type="button" value="닫기" onclick="closewin()">
	<% } %>
</body>
</html>