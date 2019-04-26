<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
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
	${memberName} 회원님 반갑습니다.<br>
	<a href="memberProcServlet?action=list&page=1">회원 목록으로</a><br><br>
	<a href="fileServlet?action=bbs">다운로드</a>&nbsp;&nbsp;
	<a href="write.jsp">글쓰기</a><br>
	<HR>
	<br>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>글번호</th><th style="width:150px;">제목</th><th style="width:100px;">글쓴이</th><th>최종수정일</th><th>액션</th></tr>
	<c:set var="bmList" value="${requestScope.bbsMemberList}"/>
	<c:forEach var="bm" items="${bmList}">
		<tr height="25"><td>${bm.id}</td>
		<td><a href="noticeServlet?action=read&id=${bm.id}">${bm.title}</a></td>
		<td>${bm.name}</td>
		<td>${bm.date}</td>
		<td>&nbsp;<button onclick="location.href='noticeServlet?action=update&id=${bm.id}'">수정</button>
		&nbsp;<button onclick="location.href='noticeServlet?action=delete&id=${bm.id}'">삭제</button>&nbsp;</td></tr>
	</c:forEach>
	</table>
	<c:set var="pageList" value="${requestScope.pageList}"/>
	<c:forEach var="pageNo" items="${pageList}">
		${pageNo}
	</c:forEach>
</div>
</body>
</html>