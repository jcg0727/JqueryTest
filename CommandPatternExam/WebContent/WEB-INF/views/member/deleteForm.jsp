<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO mem = (MemberVO)request.getAttribute("memVO");

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원수정</title>
</head>
<body>
	<form action="delete.do" method="post">
		<table>
			<tr>
				<td>I D:</td>
				<td><%=mem.getMemId() %>
					<input type="hidden" name="memId" value="<%=mem.getMemId() %>">
				</td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value="<%=mem.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value="<%=mem.getMemTel()%>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr"><%=mem.getMemAddr() %></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원정보 삭제"> 
	</form>
</body>
</html>