<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>설문조사</h2>
<hr>
	<form action="test3.jsp" method="post">
	이름 : <input type="text"  name="name"><br>
	성별 : <input type="radio" name="gender" value="male"> 남자
			<input type="radio" name="gender" value="female" checked> 여자<br>
	좋아하는 계절 : <input type="checkbox" name="season" value="1" checked>봄 
					<input type="checkbox" name="season" value="2">여름
					<input type="checkbox" name="season" value="3">가을
					<input type="checkbox" name="season" value="4">겨울
					<input type="submit" value="제출">		
	</form>
</body>
</html>