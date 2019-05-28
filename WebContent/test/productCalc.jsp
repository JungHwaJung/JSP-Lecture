<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>물건 선택</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String product[] = request.getParameterValues("product");
	
	int sum = 0;
	for(String p: product) {
		int n = Integer.parseInt(p);
		switch(n) {
		case 1:
			out.print("모자는 " + 1000 + "원<br>");
			sum += 1000;
			break;
		case 2:
			out.print("TV는 " + 20000 + "원<br>");
			sum += 20000;
			break;
		case 3:
			out.print("신발은 " + 10000 + "원<br>");
			sum += 10000;
			break;
		}
	}
	out.print("<br>총 가격은 " + sum + "원 입니다");
%>
</body>
</html>