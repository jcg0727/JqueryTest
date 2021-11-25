package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.util.SqlMapClientFactory;
import kr.or.ddit.board.vo.BoardVO;

public class IBoardDaoImpl implements IBoardDao{
	private static IBoardDao dao;
	
	private SqlMapClient smc;
	
	private IBoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new IBoardDaoImpl();
		}
		return dao;
	}
	
	public List<BoardVO> displayAll() {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = smc.queryForList("board.getBoardAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int insertContent(BoardVO bv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard", bv);
			if(obj == null) {
				cnt = 1;
			}else {
				cnt = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteContent(int no) {
		int cnt=0;
		try {
			cnt = smc.delete("board.deleteBoard",no);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateContent(BoardVO b) {
		int cnt=0;
		try {
			cnt = smc.update("board.updateBoard",b);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchContent(BoardVO b) {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = smc.queryForList("board.searchBoard",b);
		}catch(SQLException e) {
			
		}
		return boardList;
	}

	@Override
	public BoardVO getBoard(String boardNo) {
		BoardVO bv = null;
		try {
			bv = (BoardVO) smc.queryForObject("board.getBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bv;
	}

}
