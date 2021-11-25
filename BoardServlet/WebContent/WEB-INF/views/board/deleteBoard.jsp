<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% BoardVO boardVO = (BoardVO)request.getAttribute("board"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 삭제</title>
</head>
<body>
	<form action="delete.do" method="post">
		<input type="hidden" name="boardNo" value="<%=boardVO.getBoardNo() %>">
		<table>
			<tr>
				<td>제목:</td>
				<td><input type="text" name="title" value="<%=boardVO.getBoardTitle() %>"></td>
			</tr>
			<tr>
				<td>작성자:</td>
				<td><input type="text" name="writer" value="<%=boardVO.getBoardWriter()%>"></td>
			</tr>
			<tr>
				<td>작성일:</td>
				<td><input type="text" name="date" value="<%=boardVO.getBoardDate()%>"></td>
			</tr>
			<tr>
				<td>내용:</td>
				<td><textarea name="content"><%=boardVO.getBoardContent() %></textarea></td>
			</tr>
		</table>
		<input type="submit" value="삭제"> 
	</form>
</body>
</html>