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
 <%
 	request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    String gender = request.getParameter("gender");
    
    if(gender.equals("male")) {
    	gender = "남자";
    } else {
    	gender = "여자";
    }
    
    String season[] = request.getParameterValues("season");
    
	out.println("이름 : " + name + "<br>");
	out.println("성별 : " + gender + "<br>");
	out.println("좋아하는 계절 : ");
	for(String s: season) {
		int n = Integer.parseInt(s);
		switch(n) {
		case 1:
			out.print(" 봄");
			break;
		case 2:
			out.print(" 여름");
			break;
		case 3:
			out.print(" 가을");
			break;
		case 4:
			out.print(" 겨울");
			break;
		}
	}
  %>
       	<!--   이름은 <strong><%= name %></strong> 입니다.<br/> 
        성별은 <strong><%= gender %></strong> 입니다.<br/>
        좋아하는 계절은 <strong><%= season %></strong> 입니다. -->
        


</body>
</html>