<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	int result = (Integer)request.getAttribute("resultDelete");
	String msg = "실패😣";
	if(result>0){
		msg = "성공😁";
	}
%>
	{
		"msg" : "<%=msg %>"
	}