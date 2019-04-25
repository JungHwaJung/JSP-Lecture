<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>twitter_list.jsp</title>
<style>
	a:hover { color : pink; }
</style>
</head>
<body>
<div align=center>
	<H3>My Simple Twitter!!</H3>
	<a href="memberProcServlet?action=list&page=1">회원 목록으로</a>
	<HR>
	<form action="/jspbook/member/twitServlet" method="POST">
		<!-- 세션에 저장된 이름 출력 -->
		@${memberName}&nbsp;
		<input type="text" name="msg">&nbsp;&nbsp;<input type="submit" value="트윗">
	</form>
	<HR>
	<div align="left">
	<ul>
		<!-- application 내장객체를 통해 msgs 이름으로 저장된 ArrayList를 가지고 옴 -->
		<c:set var="msgs" value="${applicationScope.msgs}"/>
		<!-- msgs가 null 이 아닌 경우에만 목록 출력 -->
		<c:if test="${not empty msgs}">
			<c:forEach var="msg" items="${msgs}">
				<li>${msg}</li>
			</c:forEach>
		</c:if>
	</ul>
	</div>
	</div>
</body>
</html>