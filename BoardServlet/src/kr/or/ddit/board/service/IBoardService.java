package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	List<BoardVO> displayAll();

	int insertContent(BoardVO bv);

	int deleteContent(int no);

	int updateContent(BoardVO b);

	List<BoardVO> searchContent(BoardVO b);

	BoardVO getBoard(String boardNo);

}
