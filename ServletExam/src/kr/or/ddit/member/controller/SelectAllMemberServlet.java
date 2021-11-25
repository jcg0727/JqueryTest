package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

@WebServlet("/selectAllMember")

public class SelectAllMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//page번호 셋팅. null값이면 1로 셋팅
		int pageNo = req.getParameter("pageNo") == null    //링크로 안들어오고 다르게 들어오는 경우 없을 수 있음...
				? 1 : Integer.parseInt(req.getParameter("pageNo"));  //파라미터로 넘어오는건 String이라
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		System.out.println("msg : "+ msg);
	
		// 1. 서비스 객체 생성하기
		IMemberService memberService = IMemberServiceImpl.getInstance();
		
		// 2. 페이징 객체 생성
		PagingVO pagingVO = new PagingVO();
		int totalCount = memberService.selectTotalCount();
		pagingVO.setTotalCount(totalCount);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setCountPerPage(5);
		pagingVO.setPageCount(5);
		System.out.println(totalCount);
		
		
		// 3. 회원정보 조회
		List<MemberVO> memList = memberService.displayMemberByPaging(pagingVO);
		req.setAttribute("msg", msg);   //여기서도 똑같은 msg띄우려고 url로 가져왔나?
		req.setAttribute("memList", memList);
		req.setAttribute("pagingVO", pagingVO);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
//		dispatcher.include(req, resp);  //모듈화..? 밑에 가서 붙는다?
		dispatcher.forward(req, resp);  //쓰려다가 다른애가 하는게 더 나을거 같아서 forward보내면 다 맡겨버림. 쓰던거는 지워짐
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
