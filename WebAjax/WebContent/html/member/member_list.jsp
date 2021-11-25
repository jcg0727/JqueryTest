<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

[
<%
	//<%= 하면 자바로 쓴 코드 가져올 수 있음.
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("memberVoList");
	for(int i=0;i<list.size();i++){
		//JSON으로 만들어주기
		MemberVO mem = list.get(i);
		String id = mem.getMemId();
		String password = mem.getMemPass();
		String name = mem.getMemName();
		String birth = mem.getMemBir().substring(0,10);
		String tel = mem.getMemHp();
		String addr = mem.getMemAddr1();
		String regno2 = mem.getMemRegno2();
		if(i>0){
%>,<%
		}
		%>
		
		{
			"id" : "<%= id %>"
			,"password" : "<%= password %>"
			,"name" : "<%= name %>"
			,"birth" : "<%= birth %>"
			,"tel" : "<%= tel %>"
			,"addr" : "<%= addr%>"
			,"regno2" : "<%= regno2%>"
		}
		<%
	}
%>
]
