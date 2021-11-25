package kr.or.ddit.common.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.AtchFileVO;

public interface IAtchFileDao {
	
	/**
	 * 파일 ID에 해당하는 첨부파일목록 조회
	 * @param atchFileId 파일 ID
	 * @return 첨부파일목록
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws SQLException;
	
	/**
	 * 파일 ID에 해당하는 첨부파일 조회
	 * @param AtchFileVO 객체
	 * @return 첨부파일
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws SQLException;

	/**
	 * 첨부파일 정보 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException;
	
	
}
