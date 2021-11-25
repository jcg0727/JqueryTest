package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.IBoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class IBoardServiceImpl implements IBoardService{
	private static IBoardDao dao;
	private static IBoardService service;
	
	public IBoardServiceImpl() {
		dao = IBoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(service == null) {
			service = new IBoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BoardVO> displayAll() {
		return dao.displayAll();
	}

	@Override
	public int insertContent(BoardVO bv) {
		return dao.insertContent(bv);
	}

	@Override
	public int deleteContent(int no) {
		return dao.deleteContent(no);
	}

	@Override
	public int updateContent(BoardVO b) {
		return dao.updateContent(b);
	}

	@Override
	public List<BoardVO> searchContent(BoardVO b) {
		return dao.searchContent(b);
	}

	@Override
	public BoardVO getBoard(String boardNo) {
		return dao.getBoard(boardNo);
	}

}
