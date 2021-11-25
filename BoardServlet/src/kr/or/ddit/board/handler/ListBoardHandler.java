package kr.or.ddit.board.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.CommandHandler;

public class ListBoardHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/WEB-INF/views/board/listBoard.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		IBoardService service = IBoardServiceImpl.getInstance();
		
		List<BoardVO> boardlist = service.displayAll();
		
		req.setAttribute("boardlist", boardlist);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
