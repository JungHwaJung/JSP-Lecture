<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selProduct.jsp</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	session.setAttribute("username", request.getParameter("username"));
%>
<body>
	<center>
		<h2>상품 선택</h2>
		<hr>
		<%=session.getAttribute("username") %>님이 로그인한 상태입니다.
		<hr>
		<form name="form1" method="POST" action="add.jsp">
			<select name="product">
				<option>사과</option>
				<option>귤</option>
				<option>파인애플</option>
				<option>자몽</option>
				<option>레몬</option>
			</select>
			<input type="submit" value="추가"/>
		</form>
		<a href="checkOut.jsp">계산</a>
	</center>
</body>
</html>