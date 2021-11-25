package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.CommandHandler;

public class NullHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
