<%@page import="kr.or.ddit.member.vo.PagingVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
	String msg = (String)request.getAttribute("msg");
	PagingVO pagingVO = (PagingVO)request.getAttribute("pagingVO");
// 	PagingVO paginVO = new PagingVO();
// 	paginVO.setCurrentPageNo(1);
// 	paginVO.setTotalCount(memList.size());
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="4"><a href="<%=request.getContextPath() %>/insertMember">[회원등록]</a></td> <!-- 절대경로. 상대경로로 쓰면 아무것도 안쓰고 파일명만 -->
		</tr>
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
		</tr>
<%
	int memSize = memList.size();
	if(memSize > 0){
		for(int i=0;i<memSize;i++){
%>
			<tr>
				<td><%=memList.get(i).getMemId() %></td>
				<td><a href="viewMember?memId=<%= memList.get(i).getMemId()%>"><%=memList.get(i).getMemName() %></a></td>
				<td><%=memList.get(i).getMemTel() %></td>
				<td><%=memList.get(i).getMemAddr() %></td>
			</tr>
<%			
		}
%>
	<%if(pagingVO.getTotalCount() > 0) { %>
		<tr>
			<td colspan="4" align="center">
				<%if(pagingVO.getFirstPageNo() > pagingVO.getPageCount()) {%>
				<a href="selectAllMember?pageNo=<%=pagingVO.getFirstPageNo()-pagingVO.getPageCount() %>">[이전]</a>
				<%} %>
				<%for(int pNo=pagingVO.getFirstPageNo(); pNo<=pagingVO.getLastPageNo();pNo++){ %>
					<a href="selectAllMember?pageNo=<%=pNo %>">[<%=pNo %>]</a>
				<%} %>
				<%if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()){ %>
					<a href="selectAllMember?pageNo=<%=pagingVO.getFirstPageNo()+pagingVO.getPageCount()%>">[다음]</a>
				<%} %>
			</td>
		</tr>
	<%}%>
<%		
	}else{
%>
		<tr>
			<td colspan="4">회원 정보가 없습니다.</td>
		</tr>
<%		
	}
%>
	</table>
<%
	if(msg.equals("성공")){
%>
	<script type="text/javascript">
		alert('정상적으로 처리되었습니다.');
	</script>
<%	
	}
%>
</body>
</html>