<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scriptlet.jsp</title>
</head>
<body>
	<div align="center">
	<h2>스크랩트릿 테스트 : 1-10까지 출력</h2>
	<hr>
	<table border="1">
		<tr bgcolor="pink"><th>X</th><th>Y(=X*X)</th></tr>
	<%
		for(int i=1; i<=10; i++) {
	%>
		<tr><td width="40" align="center"><%=i %></td><td width="100" align="center"><%=i*i %></td>
	<%
		}
	%>
	</table>
	</div>
</body>
</html>