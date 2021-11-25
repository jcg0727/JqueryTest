<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Object result = request.getAttribute("resultCreateMem");
	String msg = "등록에 실패했습니다.";
	if(result == null){
		msg = "회원 등록이 되었습니다.";
	}
%>
	{
		"resultMsg" : "<%=msg %>"
	}