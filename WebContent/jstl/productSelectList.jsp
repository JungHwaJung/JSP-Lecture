<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>productList</title>
</head>
<body>
	당신이 선택한 물품은
	<c:forEach var="item" items="${paramValues.item}" varStatus="status">
		${item} <c:if test="${not status.last }">, </c:if>
	</c:forEach>
	입니다.
</body>
</html>