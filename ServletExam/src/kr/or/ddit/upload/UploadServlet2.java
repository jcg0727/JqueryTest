package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//request객체 자체가 다른 모양으로 들어가있음 application www~이 방식이랑 달라서 req.해도 안나옴
//->req.getParameter못씀 안꺼내짐
/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일업로드 예제
 */
public class UploadServlet2 extends HttpServlet{
	private static final String UPLOAE_DIR = "upload_files";  //임시폴더명. 
	// 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨.)  메모리가 3MB까지 가지고 있다가 그 이상되면 떨궈라. 
	private static final int MEMOTY_THRESHOLD = 1024*1024*3;   //COS방식은 메모리에 저장 안하고 바로 파일저장함. 파일저장 쉽게 하게 한것
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE = 1024*1024*40;
	// 요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024*1024*40;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일은 파일대로 form은 폼대로 저장하기
		
		// 인코딩 타입이 multipart/form-data 인 경우...
		if(ServletFileUpload.isMultipartContent(req)) {  
			// 폼필드 데이터 저장용...
			Map<String, String> formMap = new HashMap<>();  //파일 뿐만 아니라 form데이터도 다 경계선해서 보내서 따로 담아놓으려고 만듬
			
			// 파일인 경우 처리
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMOTY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));  //레파지도리(저장소)세팅. ()이 안에 있는건 자바에서 디폴트로 임시 저장소해줌. 
			
			ServletFileUpload upload = new ServletFileUpload(factory);  //서블릿 객체 만드는 시점에 생성자에 만든거 넣어주기
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			// 웹애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAE_DIR;  //File.seperator->운영체제에 맞춰서 붙여줌
			File uploadDir = new File(uploadPath);                                                  //윈도우면 /, 유닉스는\ ..
			if(!uploadDir.exists()) {  //업로드 경로가 없으면 만들어주기
				uploadDir.mkdir();   //context밑에 upload_files라는 폴더 생김.
			}
			
			try {   //파싱작업
				List<FileItem> formItems = upload.parseRequest(req);  //경계선으로 나눠진 쪼가리들 리스트로 저장
				
				if(formItems != null && formItems.size() > 0) {  //있으면
					for(FileItem item : formItems) {
						if(!item.isFormField()) {  // 파일인 경우... 폼필드가 아니면 파일
							// 전체 경로를 제외한 파일명만 추출하기
							String fileName = new File(item.getName()).getName();  //경로에서 파일 이름만 추출하기 위해. 이거 안쓰면 substr써야됨.
							String filePath = uploadPath + File.separator + fileName;  //제공해주는 기능 사용해서 파일 쓰기
							File storeFile = new File(filePath);
							item.write(storeFile);  // 업로드 파일 저장  실제쓸때는 db에도 파일정보 저장. 그냥 파일저장만 하면 나중에 못꺼내옴. 
							req.setAttribute("message", "업로드 완료됨 => "+fileName);
						}else { // 폼필드인 경우...
							formMap.put(item.getFieldName(), item.getString());  //폼필드 담으려고 만들었던 맵에 저장
						}
					}
				}
			}catch(Exception e) {
				req.setAttribute("message", "예외발생: "+e.getMessage());
			}
			
			for(Entry<String, String> entry : formMap.entrySet()) {
				System.out.println("파라미터명 : "+entry.getKey());
				System.out.println("파라미터값 : "+entry.getValue());
			}
			System.out.println("안꺼내짐"+req.getParameter("sender"));   //application방식이 아니라서 
			System.out.println("꺼내짐"+formMap.get("sender"));
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨");
		}
	}
}
