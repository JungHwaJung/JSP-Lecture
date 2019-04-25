<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원수정</title>
	<style>
		label {
			display : block;
			padding : 10px;
		}
		lable span {
			display : inline-block;
			padding : 10px;
		}
	</style>
</head>
<body>
	<h3>회원 수정</h3>
	<hr>
	<c:set var="bm" value="${requestScope.member}"/>
	<table border="1" style="border-collapse:collapse;">
	<tr bgcolor="skyblue"><th height="30" width="80">항목</th><th width="300">내용</th></tr>
		<form name="update" action="memberProcServlet?action=execute&id=${bm.id}" method=post>
		<tr><td height="25">아이디</td><td>${bm.id}</td></tr>
		<tr><td height="25">이름</td><td><input type="text" name="name" size="40" value="${bm.name}"></td></tr>
		<tr><td height="25">생일</td><td><input type="text" name="birthday" size="40" value="${bm.birthday}"></td></tr>
		<tr><td height="25">주소</td><td><input type="text" name="address" size="40" value="${bm.address}"></td></tr>
		<tr><td colspan="2" height="30">
				<input type="submit" value="수정하기" name="U1">&nbsp;&nbsp;
				<input type="reset" value="재작성" name="U2">
		</td></tr>
		</form>
		<a href="memberProcServlet?action=list&page=${currentBbsPage}">목록으로</a>
	</table>
</body>
</html>