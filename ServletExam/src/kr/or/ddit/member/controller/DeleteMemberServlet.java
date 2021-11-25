package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
	
		// 2. 서비스 객체 생성하기
		IMemberService memberService = IMemberServiceImpl.getInstance();
		
		int cnt = memberService.deleteMember(memId);
		
		String msg = "실패";
		
		if(cnt > 0) {
			msg = "성공";
		}
		
		String redirectUrl = req.getContextPath() + "/selectAllMember?msg="+URLEncoder.encode(msg, "utf-8");
		resp.sendRedirect(redirectUrl);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
