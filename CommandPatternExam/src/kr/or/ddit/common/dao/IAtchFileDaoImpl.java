package kr.or.ddit.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.AtchFileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class IAtchFileDaoImpl implements IAtchFileDao{

	private static IAtchFileDao dao;
	private SqlMapClient smc;
	
	private IAtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(dao == null) {
			dao = new IAtchFileDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws SQLException {
		List<AtchFileVO> fileList = smc.queryForList("atchFile.getAtchFileList", atchFileId);
		
		return fileList;
	}

	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("atchFile.insertAtchFile", atchFileVO);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws SQLException {
		AtchFileVO fileVO = (AtchFileVO) smc.queryForObject("atchFile.getAtchFile", atchFileVO);
		
		return fileVO;
	}

}
