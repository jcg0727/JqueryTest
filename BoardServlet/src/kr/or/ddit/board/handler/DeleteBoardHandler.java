package kr.or.ddit.board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.common.CommandHandler;

public class DeleteBoardHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/WEB-INF/views/board/deleteBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		IBoardService service = IBoardServiceImpl.getInstance();
		
		int cnt = service.deleteContent(boardNo);
		String msg = "실패";
		if(cnt>0) {
			msg = "성공";
		}
		
		String redirectUrl = req.getContextPath() + "/board/list.do?msg="+URLEncoder.encode(msg, "utf-8");
		return redirectUrl;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;  //얘는 왜 false만 리턴???
	}

}
