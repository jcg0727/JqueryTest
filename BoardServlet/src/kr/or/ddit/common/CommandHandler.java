package kr.or.ddit.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	/**
	 * 요청한 내용을 실제 처리하는 메서드
	 * @param req
	 * @param resp
	 * @return 뷰페이지 정보
	 * @throws Exception
	 */
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
	/**
	 * 해당 화면에 대해 포워드 및 리다이렉트 여부 결정하기 위한 메서드
	 * @param req
	 * @return true면 리다이렉트, false이면 forward
	 */
	public boolean isRedirect(HttpServletRequest req);
}
