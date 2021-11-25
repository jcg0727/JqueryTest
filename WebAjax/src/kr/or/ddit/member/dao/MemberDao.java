package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.base.dao.BaseDao;
import kr.or.ddit.common.MyUtil;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.TBMemberVO;

public class MemberDao extends BaseDao {
	private SqlMapClient smc;
	
	public MemberDao() {
		//super : BaseDao의 getConnection 이라는 뜻
		smc = super.getSqlMapClient();
	}
	
	public Object createMember(TBMemberVO memberVo) throws Exception {
		Object result = smc.insert("member.insertMember", memberVo);
		return result;
	}

	public int deleteMember(MemberVO memberVo) {
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int updateMember(MemberVO memberVo) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", memberVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public TBMemberVO retrieveMember(TBMemberVO memberVo) throws Exception {
		TBMemberVO vo = (TBMemberVO) smc.queryForObject("member.retrieveMember", memberVo);
		
		return vo;
	}
	
	public List<MemberVO> retrieveList(MemberVO memberVo) throws Exception {
		List<MemberVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("member.retrieveList", memberVo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	

	
}
