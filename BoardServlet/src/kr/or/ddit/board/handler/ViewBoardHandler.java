package kr.or.ddit.board.handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.CommandHandler;

public class ViewBoardHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/views/board/viewBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String boardNo = req.getParameter("boardNo");
		
		IBoardService service = IBoardServiceImpl.getInstance();
		
		BoardVO board = service.getBoard(boardNo);
		
		req.setAttribute("board", board);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
