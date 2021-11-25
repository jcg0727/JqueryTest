package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Oreilly의 MultipartRequest를 이용한 파일 업로드 예제
 * (생성자 호출과 동시에 파일이 생성되기 때문에 선택적인 파일 생성 처리 불가)
 */
public class UploadServlet1 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet으로는 파일 업로드 못함.-> 메서드 정의할 필요 x
		resp.setContentType("text/plain");
		
		PrintWriter out = resp.getWriter();
		
		String encType = "UTF-8";
		int maxFileSize = 5 * 1024 * 1024;  //5MB
		
		//MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩캐릿터셋, 동일한 파일명 보호여부])
		//DefaultFileRenamePolicy => name.zip, name1.zip, name2.zip...
		MultipartRequest mr = new MultipartRequest(req, "d:/D_Other/", maxFileSize, encType, new DefaultFileRenamePolicy());
		//생성자에 넣어주면 req객체에서 알아서 파일을 찾아서 업로드하는거????
		
		File file01 = mr.getFile("file01");  
		System.out.println(file01);   // 첨부된 파일의 전체경로 출력
		
		// 파라미터 값 읽어오기
		System.out.println(mr.getParameter("title"));  	
		
		out.println("업로드 완료됨." + file01.toString() + "\ntitle => "+mr.getParameter("title"));
		
		
	}
}
