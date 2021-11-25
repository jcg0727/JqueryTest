<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (Integer)request.getAttribute("resultUpdate");
	String resultMsg = "실패!";
	if(result >0){
		resultMsg = "성공😁";
	}
%>    
	{
		"resultMsg" : "<%=resultMsg %>"
	}