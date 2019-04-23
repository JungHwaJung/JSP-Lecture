<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<body>
<%
	BbsMember member = (BbsMember) request.getAttribute("bDto");
%>
<h3>글 수정</h3>
	<hr>
	<form name="nbupdateForm" action="/jspbook/member/noticeServlet?action=execute" method=post>
		<input type="hidden" id="id" name="id" value="<%=member.getId() %>">
		<label><span>Id:</span>
			<%=member.getId() %></label>
		<label><span>제목:</span>
			<input type="text" name="title" value="<%=member.getTitle() %>" size="10" ></label>
		<label><span>내용:</span>
			<input type="text" name="content" value="<%=member.getContent() %>" size="10" ></label>
		<label><span></span>
			<input type="submit" value="수정" name="B1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="B2"></label>
	</form>
</body>
</html>