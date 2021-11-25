package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/insertMember")
public class InsertMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 뷰페이지로 전달
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/insertForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 2. 서비스 객체 생성
		IMemberService memberService = IMemberServiceImpl.getInstance();
		
		// 3. 회원정보 등록
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memberService.insertMember(mv);
		
		String msg = "실패";
		
		if(cnt > 0) {
			msg = "성공";
		}
		
//		PrintWriter out = resp.getWriter();   //내부적으로 버퍼에 저장해서 안찬상태에서 forward로 하면 지금까지 한건 필요없네 하고(forward시킨 화면에서 보여줄거기 때문에) 
//		out.println("안녕하세요 방가방가");        //클리어시켜서 이건 안나옴. ->flush()로 써주면 됨 하지만 flush하면 forward못함. 이미 응답했기때문에
//		out.flush();						  //but inclue()하면 가능!	
		
		// 4. 목록 조회화면으로 이동 
		//forward로 하면 새로고침할때마다 insert된다 
		//또 목록조회 떠도 url은 계속 insert로 되어있음->언발란스   =>리다이렉트로 하기  url자체가 달라짐. 다시 요청하는 것
		//톰캣 내라서 주소 저렇게 줘도 되는데 리다이렉트는 사용자 입장에서 보는거라 주소 다 줘야됨
		String redirectUrl = req.getContextPath() + "/selectAllMember?msg="+URLEncoder.encode(msg, "utf-8");
		//왜 url에 msg붙여서 보내지???
//		req.getRequestDispatcher("/displayMemberAll").forward(req, resp);
//		req.getRequestDispatcher("/displayMemberAll").include(req, resp);
		resp.sendRedirect(redirectUrl);
		
		
		
		
	}
}
