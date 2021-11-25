<%@page import="kr.or.ddit.common.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<CodeVO> list = (List<CodeVO>)request.getAttribute("CodeVoList");
%>

[
<%
	for(int i=0;i<list.size();i++){
		CodeVO vo = list.get(i);
		String code = vo.getCode();
		String codeName = vo.getCodeName();  //화면에 보여질 부분
		if(i>0){
		%> , <%
		}
		%>
		{ "id" : "<%=code %>", "name" : "<%=codeName %>" }
		 
		<%
	}
%>
]