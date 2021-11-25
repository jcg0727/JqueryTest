<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.ZipVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<ZipVO> list = (List<ZipVO>)request.getAttribute("list");
%>

[
	<%
		for(int i=0;i<list.size();i++){
			ZipVO zipVO = list.get(i);
			if(i>0){
			%>,<%
			}
			%>
			{
				"zipcode" : "<%=zipVO.getZipcode() %>"
				,"sido" : "<%=zipVO.getSido() %>"
				,"gugun" : "<%=zipVO.getGugun() %>"
				,"dong" : "<%=zipVO.getDong() %>"
				,"bunji" : "<%=zipVO.getBunji() %>"
			}
	<%	
		}
%>
]
    