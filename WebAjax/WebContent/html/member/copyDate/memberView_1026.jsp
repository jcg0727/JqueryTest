
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	MemberVO mem = (MemberVO)request.getAttribute("resultRetrieve");
		String id = mem.getMemId();
		String password = mem.getMemPass();
		String name = mem.getMemName();
		String birth = mem.getMemBir();
		String addr = mem.getMemAddr1();
		String tel = mem.getMemHp();
%>
    
	{
		"id" : "<%=id %>"
		,"password" : "<%=password %>"
		,"name" : "<%=name %>"
		,"birth" : "<%=birth %>"
		,"addr" : "<%=addr %>"
		,"tel" : "<%=tel %>"
	}    
