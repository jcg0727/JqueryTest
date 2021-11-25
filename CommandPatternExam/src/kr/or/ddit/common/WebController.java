package kr.or.ddit.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.member.handler.NullHandler;
import kr.or.ddit.util.FileUploadRequestWrapper;


public class WebController extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(WebController.class); 
	
	// 매핑정보를 저장(핸들러 객체 저장용 맵)
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<String, CommandHandler>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String configFilePath = config.getInitParameter("handler-config");   
		Properties handlerProp = new Properties();  //properties사용하기 위한 객체 생성
		
		// 설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);   //바로 getServletContext로 해도 됨
		
		FileReader fr;
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(Object key : handlerProp.keySet()) {   //properties에 있는 url하나씩 
			String command = (String)key;

			try {
//				Class<?> klass = WebController.class; //Class타입의 객체 만듬  - 클래스 정보를 담고있는 클래스를 만듬. 리플렉션기술..? new로 객체 만드는게 아니라 리플렉션으로 만듬
//				Class<?> klass = Class.forName("kr.or.ddit.common.WebController");  //위랑 동일. 클래스 자체에 대한 정보 얻기위해.. 
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				
				CommandHandler handlerInstance = (CommandHandler)klass.newInstance();
				// 핸들러 객체 등록
				commandHandlerMap.put(command, handlerInstance);
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		} 
		logger.info("CommandHandler 매핑정보\n => "+commandHandlerMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 작업 처리...
//		if(command.equals("/upload.do")) {
//			new 파일업로드 클래스().process();  
//		}   //=>새로운 기능 클래스 추가되면 else if절 또 추가해야됨 -> 안좋음
		// => 디자인패턴-command패턴 =>기능 가진 인터페이스를 구현한 객체로 그 메서드 호출 사용
		
		process(req, resp);
	}
	         

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 처리를 위한 래퍼클래스 사용하기
		try {
			FileUploadRequestWrapper requestWrapper = new FileUploadRequestWrapper(req);
			
			process(requestWrapper, resp);  // 감싼거 넣어주면 파일업로드 기능있으면 그 기능 쓰고 아니면 원래기능 쓰게
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = req.getRequestURI();
		System.out.println(" 전 command => "+command);
		
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		System.out.println(" 후 command => "+command);
		
		CommandHandler handler = commandHandlerMap.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = ""; // 뷰 화면 정보
		try {
			viewPage = handler.process(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		logger.info(viewPage);
		
		// VIEW 화면 처리
		if(viewPage != null) {
			if(handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			}else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
	}
}
