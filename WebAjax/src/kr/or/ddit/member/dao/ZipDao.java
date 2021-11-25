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
import kr.or.ddit.member.vo.ZipVO;

public class ZipDao extends BaseDao {
	private SqlMapClient smc;
	
	public ZipDao() {
		//super : BaseDao의 getConnection 이라는 뜻
		smc = super.getSqlMapClient();
	}
	
	
	public List<ZipVO> retrieveSidoList() throws Exception {
		List<ZipVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("zip.retrieveSidoList");
			System.out.println("시도!!!!!"+list.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<ZipVO> retrievGugunList(ZipVO zipVO) {
		List<ZipVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("zip.retrieveGugunList", zipVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<ZipVO> retrievDongList(ZipVO zipVO) {
		List<ZipVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("zip.retrieveDongList", zipVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<ZipVO> retrievZipCodeList(ZipVO zipVO) {
		List<ZipVO> list = new ArrayList<>();
		try{
			list = smc.queryForList("zip.retrieveZipCodeList", zipVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	

	
}
