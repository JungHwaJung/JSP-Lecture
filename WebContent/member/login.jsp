<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style>
	label {
		display : block; /* 새 라인에서 시작 */
		padding : 5px;
	}
	label span {
		display : inline-block;
		width : 90px;
		text-align : right;
		padding : 10px;
	}
</style>
</head>
<body>
	<center>
	<h3>Member Login</h3>
	<hr>
	<form name="loginForm" action="/jspbook/member/memberProcServlet?action=login" method=post>
		<label><span>ID:</span>
			<input type="text" name="id" size="10" ></label>
		<label><span>Password:</span>
			<input type="password" name="password" size="10" ></label><br>
		<label><span></span>
			<input type="submit" value="로그인" name="B1">&nbsp;&nbsp;</label><br><br><br><br><br><br>
			<button type="button" onclick="location.href='register.html'">회원가입 </button>
	</form>
	</center>
</body>
</html>