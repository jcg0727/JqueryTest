package kr.or.ddit.member.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.TBMemberVO;

@WebServlet("/MemberCheckServlet")
public class MemberCheckServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		retrieveMember(req, resp);
	}

	private void retrieveMember(HttpServletRequest req, HttpServletResponse resp) {
		try {
			TBMemberVO member = new TBMemberVO();
			member.setMemId(req.getParameter("id"));
			
			MemberService service = new MemberService();
			TBMemberVO mem = service.retrieveMember(member);
			
			if(mem==null) {
				req.setAttribute("cnt", 0);
			}else {
				req.setAttribute("cnt", 1);
			}
			
			RequestDispatcher disp = req.getRequestDispatcher("/html/member/idCheck.jsp");
			disp.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
