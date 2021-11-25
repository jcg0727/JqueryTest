package kr.or.ddit.common.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.common.dao.IAtchFileDao;
import kr.or.ddit.common.dao.IAtchFileDaoImpl;
import kr.or.ddit.member.vo.AtchFileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class AtchFileServiceImpl implements IAtchFileService{

	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = IAtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	@Override
	public AtchFileVO saveAtchFile(FileItem item) throws Exception {
		
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		// 전체 경로를 제외한 파일명만 추출하기
		String orignFileName = new File(item.getName()).getName();   //이거 안쓰고 싶으면 substring으로 잘라내...
		long fileSize = item.getSize();   // 파일 사이즈 가져오기
		String storeFileName = "";
		String filePath = "";
		File storeFile = null;
		
		do {  //db에 저장해서 나중에 꺼내볼 수 있게
			// 저장 파일명 추출
			storeFileName = UUID.randomUUID().toString().replace("-", "");  //랜덤하게 파일명 추출
			filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + storeFileName;
			
			storeFile = new File(filePath);
		}while(storeFile.exists());   // 파일명이 중복되지 않을 때까지...  
		
		// 확장자 이름 추출
		String fileExtension = orignFileName.lastIndexOf(".") < 0 ? "" : orignFileName.substring(orignFileName.lastIndexOf(".")+1);
		
		item.write(storeFile);  // 업로드 파일 저장
		
		// 파일정보 저장 
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setStreFileNm(storeFileName);
		atchFileVO.setFileSize(fileSize);
		atchFileVO.setOrignalFileNm(orignFileName);
		atchFileVO.setFileExtsn(fileExtension);
		atchFileVO.setFileStreCours(filePath);
		
		fileDao.insertAtchFile(atchFileVO);  //insert잘 됬으면 atchFileId도 세팅 안했지만 들어있음.
		
		item.delete(); //메모리 쓰레숄드 넘으면 임시저장. -> 임시저장된거 지워주려고
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws Exception {
		List<AtchFileVO> fileVOList = fileDao.getAtchFileList(atchFileId);
		return fileVOList;
	}

	@Override
	public AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws Exception {
		AtchFileVO fileVO = fileDao.getAtchFile(atchFileVO);
		return fileVO;
	}

}
