package kr.or.ddit.common.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.member.vo.AtchFileVO;

public interface IAtchFileService {

	/**
	 * 첨부파일을 저장하기 위한 메서드
	 * @param fileItem 저장할 객체
	 * @return 저장한 첨부파일 정보
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFile(FileItem fileItem) throws Exception;
	
	/**
	 * 첨부파일 아이디로 첨부파일 목록정보 조회
	 * @param atchFileId 첨부파일 ID
	 * @return 첨부파일 목록 정보
	 * @throws Exception
	 */
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws Exception;

	/**
	 * 첨부파일 정보 조회
	 * @param AtchFileVO 객체
	 * @return 첨부파일 정보
	 * @throws Exception
	 */
	public AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws Exception;
	
}
