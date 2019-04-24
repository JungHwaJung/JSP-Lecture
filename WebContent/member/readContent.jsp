<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	BbsMember member = (BbsMember) request.getAttribute("bDto");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>readContent.jsp</title>
<style>
	th { background : yellowgreen; }
	tr, td { text-align : center;}
</style>
</head>
<body>
<div align=center>
	<H3>게시판</H3>
	<hr>
	<input type="hidden" id="id" name="id" value="<%=member.getId() %>">
	<table border="1" style="border-collapse:collapse;">
		<tr><th>항목</th><th>내용</th></tr>
		<tr><td>글번호</td><td><%=member.getId() %></td></tr>
		<tr><td>글제목</td><td><%=member.getTitle() %></td></tr>
		<tr><td>글쓴이</td><td><%=member.getName() %></td></tr>
		<tr><td>최종 수정일</td><td><%=member.getDate().substring(0, 16) %></td></tr>
		<tr><td>글내용</td><td style="width:300px; height:300px;"><%=member.getContent() %></td></tr>
	
	</table>
</div>
</body>
</html>