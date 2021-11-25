<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int cnt = (Integer)request.getAttribute("cnt");
	String msg = "";
	
	if(cnt>0){
		msg = "이미 사용중인 아이디입니다.";
	}else{
		msg = "사용 가능한 아이디입니다.";
	}
%>
	{
		"msg" : "<%=msg %>"
	}