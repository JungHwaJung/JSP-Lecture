<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>movieList</title>
</head>
<body>
<%
	String[] movieList = {"타이타닉", "시네마 천국", "혹성 탈출", "킹콩"};
		pageContext.setAttribute("movieList", movieList);
%>
	<table border="1" style="width:100%; text-align= center">
		<tr>
			<th>index</th>
			<th>count</th>
			<th>title</th>
		</tr>
	<c:forEach var="movie" items="${movieList}" varStatus="status">
		<tr>
			<th>${status.index}</th>
			<th>${status.count}</th>
			<th>${movie}</th>
		</tr>
	</c:forEach>
	</table>
</body>
</html>