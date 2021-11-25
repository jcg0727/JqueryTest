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

import org.apache.log4j.Logger;

import kr.or.ddit.board.handler.NullHandler;


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
		process(req, resp);
	}
	         

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
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
