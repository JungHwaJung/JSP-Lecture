<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<c:if test="${param.login == 1}">
		<c:choose>
			<c:when test="${param.id.equals('root') && param.pass == 1233}">
					당신은 <span style="color:red;">사용자</span> 입니다.
			</c:when>
			<c:otherwise>당신은 root 사용자가 아닙니다</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${param.login == 2 }">
		<c:choose>
			<c:when test="${param.id.equals('root') && param.pass == 1233}">
				당신은 <span style="color:red;">관리자</span> 입니다.
			</c:when>
			<c:otherwise>당신은 root 관리자가 아닙니다</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>