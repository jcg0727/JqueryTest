package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.common.dao.IAtchFileDaoImpl;
import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.IMemberServiceImpl;
import kr.or.ddit.member.vo.AtchFileVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class InsertMemberHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/WEB-INF/views/member/insertForm.jsp";
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else {  //POST
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile");
			AtchFileVO atchFileVO = new AtchFileVO();
			if(item != null && !item.getName().equals("")) {
				atchFileVO = fileService.saveAtchFile(item);   // 첨부파일 저장
			}
			
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
			mv.setAtchFileId(atchFileVO.getAtchFileId());
			
			int cnt = memberService.insertMember(mv);
			
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
