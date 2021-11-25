package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class UpdateMemberHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/views/member/updateForm.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			String memId = req.getParameter("memId");
			
			IMemberService memService = IMemberServiceImpl.getInstance();
			MemberVO memVO = memService.getMember(memId);
			
			req.setAttribute("memVO", memVO);
			
			return VIEW_PAGE;
		}else {
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			IMemberService memberService = IMemberServiceImpl.getInstance();
			
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
			
			String redirectUrl = req.getContextPath() + "/member/list.do?msg="+URLEncoder.encode(msg, "utf-8");
			return redirectUrl;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {  //POST
			return true;
		}
	}

}
