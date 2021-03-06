<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="member" scope="page" class="jspbook.ch03.Member" />
<jsp:setProperty name="member" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h3>회원 가입</h3>
	<hr>
	<p>회원에 가입하시려면 아래 정보를 입력해 주십시오.</p>
	<form name="member_join" method=post>
		이름: <input type="text" name="name" width="200" size="10"><br>
		이메일: <input type="text" name="email" width="200" size="20"><br>
		전화번호: <input type="text" name="tel" width="200" size="20"><br>
		<input type="submit" value="가입">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="다시 입력"><br>
	</form>
	<br>
	회원가입을 축하합니다.<br>
	회원 아이디는 <jsp:getProperty name="member" property="id"/> 입니다.<br>
</body>
</html>