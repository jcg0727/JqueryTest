package kr.or.ddit.board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.CommandHandler;

public class UpdateBoardHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/views/board/updateBoard.jsp";
			
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			//서버에서 게시글 가져오기.
			String boardNo = req.getParameter("boardNo");
			
			IBoardService service = IBoardServiceImpl.getInstance();
			
			BoardVO boardVO = service.getBoard(boardNo);
			req.setAttribute("board", boardVO);
			
			return VIEW_PAGE;
		}else {
			BoardVO board = new BoardVO();
			board.setBoardTitle(req.getParameter("title"));
			board.setBoardWriter(req.getParameter("writer"));
			board.setBoardContent(req.getParameter("content"));
			board.setBoardNo(Integer.parseInt(req.getParameter("no")));
			
			IBoardService service = IBoardServiceImpl.getInstance();
			
			int cnt = service.updateContent(board);
			
			String msg = "실패";
			if(cnt > 0) {
				msg = "성공";
			}
			
			String redirectUrl = req.getContextPath()+"/board/list.do?msg="+URLEncoder.encode(msg, "utf-8");
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
