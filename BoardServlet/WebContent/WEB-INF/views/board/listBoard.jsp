<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<BoardVO> boardlist = (List<BoardVO>)request.getAttribute("boardlist"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>게시글</h1>
		<table id="tb">
			<tr>
				<td colspan="4"><a href="<%=request.getContextPath() %>/board/insert.do">[게시글등록]</a></td>
			</tr>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
<%
	if(boardlist.size()>0){		
		for(int i=0;i<boardlist.size();i++){
%>
			<tr>
				<td><%=boardlist.get(i).getBoardNo() %></td>
				<td><a href="select.do?boardNo=<%=boardlist.get(i).getBoardNo()%>"><%=boardlist.get(i).getBoardTitle() %></a></td>
				<td><%=boardlist.get(i).getBoardWriter() %></td>
				<td><%=boardlist.get(i).getBoardDate() %></td>
			</tr>
<%
		}
	}else{
%>
		<tr>
			<td>등록된 게시글이 없습니다.</td>
		</tr>
<%		
	}
%>
		</table>
</body>
</html>