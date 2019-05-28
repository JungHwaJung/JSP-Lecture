<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>물건 선택</title>
</head>
<body>
<h2>물건을 선택하세요.</h2>
<hr>
	<form action="productCalc.jsp" method="post">
		<input type="checkbox" name="product" value="1"> 모자 1000원
		<input type="checkbox" name="product" value="2"> TV 20000원	
		<input type="checkbox" name="product" value="3"> 신발 10000원
		<input type="submit" value="제출">
	</form>
</body>
</html>