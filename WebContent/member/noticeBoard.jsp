<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="member.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");

	BbsDAO bDao = new BbsDAO();
	List<BbsMember> blist = bDao.selectAll();
	bDao.close();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NoticeBoard</title>
<style>
	th { background : yellowgreen; }
	input {
		display : block; 
		padding : 5px;
	}
	tr, th, td { text-align : center; }
	a:hover { color : pink; }
</style>
</head>
<body>
<div align=center>
	<H3>게시판</H3>
	<a href="loginMain.jsp">회원 목록으로</a><br><br>
	<a href="write.jsp">글쓰기</a><br>
	<HR>
	<br>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>글번호</th><th style="width:150px;">제목</th><th style="width:100px;">글쓴이</th><th>최종수정일</th><th>액션</th></tr>
	<%
		for(BbsMember member : blist) {
	%>
	<tr><td><%=member.getId() %></td>
		<%
			String readUri = "noticeServlet?action=read&id=" + member.getId();
		%>
		<td><a href='<%=readUri %>'><%=member.getTitle() %></a></td>
		<td><%=member.getName() %></td>
		<td><%=member.getDate().substring(0, 16) %></td>
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