package kr.or.ddit.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.base.dao.BaseDao;
import kr.or.ddit.common.MyUtil;
import kr.or.ddit.common.vo.CodeVO;

public class CodeDao extends BaseDao {
	private SqlMapClient smc;
	
	public CodeDao() {
		//super : BaseDao의 getConnection 이라는 뜻
		smc = super.getSqlMapClient();
	}
	
	
	public List<CodeVO> retrieveCodeList(CodeVO codeVo) throws Exception {
		List<CodeVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("code.retrieveCodeList", codeVo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	

	
}
