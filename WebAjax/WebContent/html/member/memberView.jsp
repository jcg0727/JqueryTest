
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/listcss.css">
<%
    MemberVO mem = (MemberVO)request.getAttribute("resultRetrieve");
%>
<script type="text/javascript">
$(document).ready(function(){
	$("button").click(function(){
		goMemberUpdate();
	});
});
function goMemberUpdate(){
	$("#action").val("R");    //이렇게 안주고 form에다 바로 줄 수 있는데 그러면 개발자도구에서 소스가 다 보임
	$("#viewType").val("E");

	var fm = document.getElementById("tmpFm");
	fm.method = "post";
	fm.action = "/MemberServlet";
	fm.submit();	
}
function goList(){
	window.location.href="/html/member/member_list.html";
}
function goBack(){
	window.history.back();
}
</script>
</head>    

<body>
	<h1>회원정보</h1>
	<form id="sId">
		<input type="hidden" name="action" value="R">
		<input type="text" name="id">
		<input type="button" value="검색" onclick="memSearch()">  
		<table>
			<colgroup>
				<col style="width:15%">
	               <col style="width:35%">
	               <col style="width:15%">
	               <col style="width:35%">
			</colgroup>
			<tr>
				<td>회원ID</td>
				<td><%=mem.getMemId() %></td>
				<td>회원이름</td>
				<td><%=mem.getMemName() %></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><%=mem.getMemPass() %></td>
				<td>전화번호</td>
				<td><%=mem.getMemHp() %></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td><%=mem.getMemRegno1() %>-<%=mem.getMemRegno2() %></td>
				<td>생년월일</td>
				<td><%=mem.getMemBir() %></td>
			</tr>
			<tr>
				<td>주소</td>
				<td colspan='3'><%=mem.getMemAddr1()%> <%=mem.getMemAddr2() %></td>
			</tr>
		</table>
	</form>
	<form id="tmpFm">
		<input type="hidden" id="action" name="action">
		<input type="hidden" id="viewType" name="viewType">
		<input type="hidden" name="id" id="updateMemId" value="<%=mem.getMemId() %>">
		<input type="button" value="이전화면" onclick="goBack()">
		<input type="button" value="목록" onclick="goList()">
		<input type="button" value="개인정보 수정" onclick="goMemberUpdate()">
	</form>
</body>
</html>	