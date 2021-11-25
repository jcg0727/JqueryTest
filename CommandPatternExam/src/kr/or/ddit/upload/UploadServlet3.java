package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * 서블릿3부터 지원하는(WAS버전이 낮으면 못씀) Part인터페이스를  이용한 파일업로드 예제
 */
@WebServlet(name="UploadServlet3", urlPatterns= {"/UploadServlet3"})
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet3 extends HttpServlet{
	private static final String UPLOAD_DIR = "upload_files";  
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("")+File.separator + UPLOAD_DIR; //-> 장점 getRealPath()해서 언제든지 접근할 수 있음. 배포새로되면 기존의 업로드파일 날라감. 
	    //웹애플리케이션 경로로 따라다님. 관리가 편함. 원래는 절대경로로 업로드경로 해서 파일업로드 하는게 안전   
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			for(Part part : req.getParts()) {   //req.getParts()하면 파싱된 것들 가져올 수 있음. 원래는 body가 한 덩어리인데 한덩어리 한번에 넣기 힘들어서 파트 나눠서 여러개 바디처럼.  
				System.out.println(part.getHeader("content-disposition"));           //각각의 바디마다 헤더정보 넣음.
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")) {   //폼필드가 아니거나 파일첨부를 안한 경우가 아니면
					part.write(uploadPath+File.separator + fileName);  //파일 저장
					System.out.println("파일명 : "+fileName + "저장완료!");
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("파라미터 값: "+req.getParameter("sender"));
		resp.setContentType("text/html");
		resp.getWriter().println("업로드 완료...!!!");
	}

	/**
	 * Part객체 파싱하여 파일이름 추출하기
	 * @param part 
	 * @return 파일명 : 파일명이 존재하지 않으면 null (폼필드)
	 */
	private String getFileName(Part part) {
		for(String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {  //파일은 filename이 있고 폼필드는 filename이 없음. 이걸로 구분
				return content.substring(content.indexOf("=")+2, content.length()-1);
			}
		}
		return null;  // filename이 없는 경우...(=>폼필드인 경우)
	}
}






