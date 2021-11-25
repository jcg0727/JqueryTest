package kr.or.ddit.board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.CommandHandler;

public class InsertBoardHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/WEB-INF/views/board/insertBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;  //GET방식이면 자기자신 페이지 다시 리턴??
		}else {
			BoardVO board = new BoardVO();
			board.setBoardTitle(req.getParameter("title"));
			board.setBoardWriter(req.getParameter("writer"));
			board.setBoardContent(req.getParameter("content"));
			
			IBoardService service = IBoardServiceImpl.getInstance();
			
			int cnt = service.insertContent(board);
			
			String msg = "실패";
			if(cnt > 0) {
				msg = "성공";
			}
			
			//properties파일에 매핑해논 url형식으로 보내줌.
			String redirectUrl = req.getContextPath() + "/board/list.do?msg="+URLEncoder.encode(msg, "utf-8");   
			return redirectUrl;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {  //POST때 redirect해준다.
			return true;
		}
	}

}
