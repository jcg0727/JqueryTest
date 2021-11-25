package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;

public class DeleteMemberHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/WEB-INF/views/member/deleteForm.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String memId = req.getParameter("memId");
		
		// 2. 서비스 객체 생성하기
		IMemberService memberService = IMemberServiceImpl.getInstance();
		
		int cnt = memberService.deleteMember(memId);
		
		String msg = "실패";
		
		if(cnt > 0) {
			msg = "성공";
		}
		String redirectUrl = req.getContextPath() + "/member/list.do?msg="+URLEncoder.encode(msg, "utf-8");
		
		return redirectUrl;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
