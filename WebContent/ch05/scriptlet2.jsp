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
	<h2>스크랩트릿 테스트 : 구구단 테이블</h2>
	<hr>
	<table border="1" style="border-collapse:collapse;">
		<tr align="center"><td></td><td>3</td><td>5</td><td>7</td><td>9</td></tr>
		<%
			for(int i=3; i<=9; i+=2) {
		%>
			<tr><td width="40" align="center"><%=i %></td>
		<%
				for(int k=3; k<=9; k+=2) {
		%>
			<td width="40" align="center"><%=i*k %></td>	
		<%
				}
		%>
			</tr>
		<% 
			}
		%>
	</table>
	</div>
</body>
</html>