<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardVO boardVO = (BoardVO)request.getAttribute("board"); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
	border-collapse:collapse; 
}
</style>
</head>
<body>
	<table border="1">
		<tr>
			<td>제목</td>
			<td colspan="3"><%=boardVO.getBoardTitle() %></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=boardVO.getBoardWriter() %></td>
			<td>작성일</td>
			<td><%=boardVO.getBoardDate().substring(0,10) %></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><%=boardVO.getBoardContent() %></td>
		</tr>
	</table>
		<tr>
			<td colspan="4">
				<a href="<%=request.getContextPath()%>/board/list.do">[목록]</a>
				<a href="<%=request.getContextPath()%>/board/update.do?boardNo=<%=boardVO.getBoardNo()%>">[수정]</a>
				<a href="<%=request.getContextPath()%>/board/delete.do?boardNo=<%=boardVO.getBoardNo()%>">[삭제]</a>
			</td>
		</tr>
	
</body>
</html>