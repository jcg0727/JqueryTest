<%@page import="kr.or.ddit.member.vo.AtchFileVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO memVO = (MemberVO)request.getAttribute("memVO");
	
	// lineSeparator를 이용하여 <br>태그로 변경함.
	String memAddr = memVO.getMemAddr().replaceAll(System.lineSeparator(), "<br>");   //줄바꿈 문자를 <br>로 바꿔줌
	
	AtchFileVO fileVO = (AtchFileVO)request.getAttribute("fileVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>I D:</td>
			<td><%=memVO.getMemId() %></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><%=memVO.getMemName() %></td>
		</tr>
		<tr>
			<td>전화번호:</td>
			<td><%=memVO.getMemTel() %></td>
		</tr>
		<tr>
			<td>주소:</td>
			<td><%=memAddr %></td>
		</tr>
		<tr>
			<td>첨부파일:</td>
			<td>
			<%if(fileVO != null){ %>
				<a href="<%=request.getContextPath()%>/filedownload.do?fileId=<%=fileVO.getAtchFileId() %>&fileSn=<%=fileVO.getFileSn()%>">
					<%=fileVO.getOrignalFileNm() %></a>
			<% } %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="<%=request.getContextPath()%>list.do">[목록]</a>
				<a href="<%=request.getContextPath()%>update.do?memId=<%=memVO.getMemId() %>">[회원정보 수정]</a>
				<a href="<%=request.getContextPath()%>delete.do?memId=<%=memVO.getMemId() %>">[회원정보 삭제]</a>
			</td>
		</tr>
	</table>
</body>
</html>