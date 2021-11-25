package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public class ListMemberHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/views/member/list.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
