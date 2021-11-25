package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.AtchFileVO;
import kr.or.ddit.member.vo.MemberVO;

public class ViewMemberHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/WEB-INF/views/member/view.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		IMemberService memService = IMemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		MemberVO memVO = memService.getMember(memId);
		
		List<AtchFileVO> fileVOList = fileService.getAtchFileList(memVO.getAtchFileId());
		if(fileVOList.size()>0) {
			req.setAttribute("fileVO", fileVOList.get(0));
		}
		
		req.setAttribute("memVO", memVO);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
