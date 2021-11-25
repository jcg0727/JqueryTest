package kr.or.ddit.common.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.member.vo.AtchFileVO;

public class FileDownloadHandler implements CommandHandler{
	// db에 저장되있는 파일 다운로드 할거기 때문에 파일경로 필요없음(ServletExam-DownloadServlet)
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		long atchFileId = req.getParameter("fileId") != null ? Long.parseLong(req.getParameter("fileId")) : 0;
		int fileSn = req.getParameter("fileSn") != null ? Integer.parseInt(req.getParameter("fileSn")) : 0;
		
		
		// 파일 정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO paramVO = new AtchFileVO();
		paramVO.setAtchFileId(atchFileId);
		paramVO.setFileSn(fileSn);
		
		AtchFileVO atchFileVO = fileService.getAtchFile(paramVO);
				
		if(atchFileVO!=null) {
			
			// 파일 다운로드 처리를  위한 응답헤더 정보 설정하기
			resp.setContentType("application/octet-stream");   //바이너리 데이터는 다 octet으로 보냄  쏴주는 데이터
			resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(atchFileVO.getOrignalFileNm(),"utf-8")+"\"");

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVO.getFileStreCours()));  //이거 가져오면 이 안에 파일명까지 다 있어서 
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			int readBytes = 0;
			while((readBytes = bis.read())!= -1) {
				bos.write(readBytes);
			}
			bis.close();
			bos.close();
		}
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
