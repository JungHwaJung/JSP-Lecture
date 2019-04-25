<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세보기</title>
<style>
	th { background : yellowgreen; }
	tr, td { text-align : center;}
</style>
</head>
<body>
<div align=center>
	<H3>게시글 상세보기</H3>
	<hr>
	<c:set var="bm" value="${requestScope.bDto}"/>
	<table border="1" style="border-collapse:collapse;" width="500">
		<tr><th>항목</th><th>내용</th></tr>
		<tr><td>글번호</td><td>${bm.id}</td></tr>
		<tr><td>글제목</td><td>${bm.title}</td></tr>
		<tr><td>글쓴이</td><td>${bm.name}</td></tr>
		<tr><td>최종 수정일</td><td>${bm.date}</td></tr>
		<tr><td>글내용</td><td style="width:300px; height:300px;">${bm.content}</td></tr>
	</table>
	<a href="noticeServlet?action=list&page=1">목록으로</a>
</div>
</body>
</html>