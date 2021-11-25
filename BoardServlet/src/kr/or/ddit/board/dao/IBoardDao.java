package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	List<BoardVO> displayAll();

	int insertContent(BoardVO bv);

	int deleteContent(int no);

	int updateContent(BoardVO b);

	List<BoardVO> searchContent(BoardVO b);

	BoardVO getBoard(String boardNo);

}
