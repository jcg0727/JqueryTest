<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INTRO post방식</title>
</head>
<%    //이부분은 자바코드
String age = request.getParameter("age");    //name이 age인 것의 값 가져옴    
String strName = request.getParameter("name");     
String str = "박정민";
%>
<body>
	하이~ <span id="name"><%=strName %></span><br>
	올해로 <span id="name"><%=age %></span>살 이시군요^^
</body>
</html>