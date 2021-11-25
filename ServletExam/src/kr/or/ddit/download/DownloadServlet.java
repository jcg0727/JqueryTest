package kr.or.ddit.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//다운로드는 get방식으로 많이 지원함.
//다운로드 기능 서블릿 만들고 이 서블릿 호출하면 다운된다.
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{
	
	private static final String DOWNLOAD_DIR = "d:/D_Other/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = req.getParameter("fileName");
		
		// 파일 다운로드 처리를  위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");   //바이너리 데이터는 다 octet으로 보냄  쏴주는 데이터
		resp.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		
		/**
		 * Content-Disposition 헤더에 대하여...
		 * 1. response 헤더에서 사용되는 경우... ex) 파일 다운로드
		 * Content-Disposition: inline(default)
		 * Content-Disposition: attachment                           //밑에 다운로드 뜨는거
		 * Content-Disposition: attachment; filename="filename.jpg"  //다른이름저장처럼 강제적으로 저장
		 * 
		 * 2. mutipart body를 위한 헤더 정보로 사용되는 경우... ex)파일업로드
		 * Content-Disposition: form-data
		 * Content-Disposition: form-data; name="fieldName"
		 * Content-Disposition: form-data; name="fieldName"; filename="filename.jpg"  //파일인 경우는 filename도 붙는다.
		 */
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(DOWNLOAD_DIR + fileName));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		int readBytes = 0;
		while((readBytes = bis.read())!= -1) {
			bos.write(readBytes);
		}
		bis.close();
		bos.close();		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}



