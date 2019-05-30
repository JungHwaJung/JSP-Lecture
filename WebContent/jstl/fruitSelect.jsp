<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fruitSelect</title>
</head>
<body>
	<c:if test="${param.color == 1 }">
		<span style="color:red;">사과</span>
	</c:if>
	<c:if test="${param.color == 2 }">
		<span style="color:green;">메론</span>
	</c:if>
	<c:if test="${param.color == 3 }">
		<span style="color:yellow;">바나나</span>
	</c:if>
</body>
</html>