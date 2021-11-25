package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao{
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	@Override
	public int insertMember(MemberVO mv) throws Exception{
		int cnt=0;
		Object obj = smc.insert("member.insertMember",mv);
		
		if(obj == null){
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) throws Exception{
		int cnt =0;
		cnt = smc.delete("member.deleteMember",memId);
			
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) throws Exception{
		int cnt = 0;
		cnt = smc.update("member.updateMember", mv);
						
		return cnt;
	}

	@Override
	public List<MemberVO> displayMemberAll() throws Exception{
		List<MemberVO> memList = new ArrayList<>();
		
		memList = smc.queryForList("member.getMemberAll");
			
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) throws Exception{
		MemberVO mv = null;
		mv = (MemberVO)smc.queryForObject("member.getMember", memId);
		return mv;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) throws Exception{  //검색할 정보들 담고있음
		List<MemberVO> memList = new ArrayList<>();
		
		memList = smc.queryForList("member.getSearchMember", mv);
		
		return memList;
	}

	@Override
	public int selectTotalCount() throws Exception {
		int totCnt =0; 
		totCnt =(int) smc.queryForObject("member.selectTotalCount");
		
		return totCnt;
	}

	@Override
	public List<MemberVO> displayMemberByPaging(PagingVO pagingVO) throws Exception {
		List<MemberVO> memList = new ArrayList<>();
		
		memList = smc.queryForList("member.getMemberByPaging", pagingVO);
		
		return memList;		
	}

}
