
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/memberview.css">
<%
    MemberVO mem = (MemberVO)request.getAttribute("resultRetrieve");
%>
<script type="text/javascript">
function updateMem(){
	$.ajax({
		url : "/MemberServlet"
		,type : "post"
		,dataType : "json"
		,data : $("#mf").serialize()
		,success : function(data){
			console.log(data);
			alert(data.resultMsg);
			goMemberView();
		}	
		,error : function(xhr){
			console.log(xhr);
			alert('실패');
		}
	});
}

function deleteMem(){
	$.ajax({
		url : "/MemberServlet"
		,type : "post"
		,dataType : "json"
		,data : $("#mf").serialize()
		,success : function(data){
			console.log(data.msg);
			alert(data.msg);
		}
		,error : function(xhr){
			alert(xhr.status);
		}
	});
}
function goList(){
	window.location.href="/html/member/member_list.html";
}
function goBack(){
	window.history.back();
}

function goMemberView(){
	$("#action").val("R");
	$("#selectedMemId").val(<%=mem.getMemId() %>);
	
	var fm = document.getElementById("tmpFm");
	fm.method = "post";
	fm.action = "/MemberServlet";
	fm.submit();
}
</script>

</head>    

<body>
	<h1>회원정보</h1>
	<form id="mf">
		<input type="hidden" id="action" name="action" value="U">
		<input type="hidden" name="id" value="<%=mem.getMemId() %>">
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
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="password"></td>
				<td>전화번호</td>
				<td><input type="text" name="hp"></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td><input type="text" name="regno1" style="width: 45%"> - <input type="text" name="regno2" style="width: 45%"></td>
				<td>생년월일</td>
				<td><input type="text" name="birth"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td colspan='3'><input type="text" name="addr2"></td>
			</tr>
		</table>
	</form>
	<form id="tmpFm">
		<input type="hidden" id="selectedMemId" name="id">
		<input type="hidden" id="action" name="action">
		<input type="button" value="이전화면" onclick="goBack()" style="width:20%;">
		<input type="button" value="목록" onclick="goList()" style="width:20%;">
		<input type="button" value="저장" onclick="updateMem()" style="width:20%;">
		<input type="button" value="회원탈퇴" onclick="deleteMem()" style="width:20%;">
	</form>
</body>
</html>	