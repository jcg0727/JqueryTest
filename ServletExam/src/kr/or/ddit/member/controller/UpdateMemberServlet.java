package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
				
		//1. 서비스객체 생성하기
		IMemberService memService = IMemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회하기
		MemberVO memVO = memService.getMember(memId);
		
		// 3. request객체에 회원정보 저장
		req.setAttribute("memVO", memVO);
		
		// 4. view화면으로 전달
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/updateForm.jsp");
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
		
		// 3. 회원정보 변경
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		
		int cnt = memberService.updateMember(mv);
		
		String msg = "실패";
		
		if(cnt > 0) {
			msg = "성공";
		}
		
		String redirectUrl = req.getContextPath() + "/selectAllMember?msg="+URLEncoder.encode(msg, "utf-8");
		resp.sendRedirect(redirectUrl);
	}
}
