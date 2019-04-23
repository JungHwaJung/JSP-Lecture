<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="member.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	
	if(username != null) {
		session.setAttribute("user", username);
	}
%>
<%	
	BbsDAO bDao = new BbsDAO();
	List<BbsMember> blist = bDao.selectAll();
	bDao.close();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notice Board</title>
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
	<a href="loginMain.jsp">회원 목록으로</a><br><br>
	<a href="write.jsp">글쓰기</a><br>
	<HR>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>글번호</th><th>이름</th><th>제목</th><th>날짜</th><th>내용</th><th>액션</th></tr>
	<%
		for(BbsMember member : blist) {
	%>
	<tr><td><%=member.getId() %></td>
		<td><%=member.getName() %></td>
		<td><%=member.getTitle() %></td>
		<td><%=member.getContent() %></td>
		<td><%=member.getDate() %></td>
		<%
			String updateUri = "noticeServlet?action=update&id=" + member.getId();
			String deleteUri = "noticeServlet?action=delete&id=" + member.getId();
		%>
		<td>&nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>
		&nbsp;<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp;</td></tr>
	<%
		}
	%>
	</table>
</div>
</body>
</html>