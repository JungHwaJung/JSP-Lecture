<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>write.jsp</title>
<style>
	input {
		display : block; 
		padding : 5px;
	}
</style>
</head>
<body>
<div align=center>
	<H3>게시판</H3>
	<a href="noticeBoard.jsp">게시판으로</a>
	<HR>
	<form action="/jspbook/member/noticeServlet?action=write" method="POST">
		제목 <input type="text" name="title"><br><br>
		글내용 <input type="text" name="content" style="width:300px; height:300px;"><br><br>
		<input type="submit" value="등록">
	</form>
	<HR>
	</div>
</body>
</html>