package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.TBMemberVO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {

	//GET방식 호출 시
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//POST방식 호출 시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		//CRUD
		if("R".equals(action)){
			retrieveMember(request, response);
		} else if("C".equals(action)){
			createMember(request, response);    	   
		} else if("U".equals(action)) {
			updateMember(request, response);
		} else if("D".equals(action)) {
			deleteMember(request, response);
		} else {
			retrieveMemberList(request, response);
		}

	}


	private void deleteMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			MemberVO member = new MemberVO();
			member.setMemId(request.getParameter("id"));
			member.setMemPass(request.getParameter("password"));

			MemberService service = new MemberService();
			int cnt = service.deleteMember(member);
			request.setAttribute("resultDelete", cnt);

			RequestDispatcher disp = request.getRequestDispatcher("/html/member/memberDelete.jsp");
			disp.forward(request, response);

		}catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			MemberVO member = new MemberVO();
			member.setMemId(request.getParameter("id"));
			member.setMemPass(request.getParameter("password"));
			member.setMemName(request.getParameter("name"));
			member.setMemHp(request.getParameter("hp"));
			member.setMemRegno1(request.getParameter("regno1"));
			member.setMemRegno2(request.getParameter("regno2"));
			member.setMemBir(request.getParameter("birth"));
			member.setMemAddr2(request.getParameter("addr2"));

			MemberService service = new MemberService();
			int cnt = service.updateMember(member);
			request.setAttribute("resultUpdate", cnt);

			RequestDispatcher disp = request.getRequestDispatcher("/html/member/memberUpdate.jsp");
			disp.forward(request, response);
		}catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void createMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			TBMemberVO member = new TBMemberVO();
			member.setMemId(request.getParameter("id"));
			member.setMemPass(request.getParameter("password"));
			member.setMemName(request.getParameter("name"));
			member.setMemHp(request.getParameter("hp"));
			member.setMemMail(request.getParameter("email"));
			member.setMemBir(request.getParameter("birth"));
			member.setMemAddr1(request.getParameter("addr1"));
			member.setMemAddr2(request.getParameter("addr2"));
			member.setMemZip(request.getParameter("zipcode"));
			member.setMemJob(request.getParameter("job"));

			MemberService service = new MemberService();
			Object result = service.createMember(member);
			request.setAttribute("resultCreateMem", result);

			RequestDispatcher disp = request.getRequestDispatcher("/html/member/memberNew.jsp");
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void retrieveMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			//request에서 받아와서 세팅해 줄 것임
			TBMemberVO paramVo = new TBMemberVO();
			paramVo.setMemId(request.getParameter("id"));    //가져올때는 getParameter
			String viewType = request.getParameter("viewType");
			
			//서비스 호출해서 '목록 조회'를 수행
			MemberService service = new MemberService();
			TBMemberVO resultVO = service.retrieveMember(paramVo);

			//조회결과를 request에 담아서 전송
			request.setAttribute("resultRetrieve", resultVO);      //보낼때는 setAttribute
			//조회결과를 보낼 URL을 지정
			String url="";
//			if(viewType == null || viewType.length() == 0) {
			if("E".equals(viewType)) {
				url = "/html/member/memberViewEdit.jsp";
			}else {
				url = "/html/member/memberView.jsp"; 
			}
			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request, response);  //만들어진 정보가지고 가는 것

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void retrieveMemberList(HttpServletRequest request, HttpServletResponse response) {
		try {
			//request에서 받아와서 세팅해 줄 것임
			MemberVO paramVo = new MemberVO();
			paramVo.setMemId(request.getParameter("id"));    //가져올때는 getParameter
			paramVo.setMemName(request.getParameter("name"));

			//서비스 호출해서 '목록 조회'를 수행
			MemberService service = new MemberService();
			List<MemberVO> resultList = service.retrieveMemberList(paramVo);   //dao에서 받아온 회원list저장

			//조회결과를 request에 담아서 전송
			request.setAttribute("memberVoList", resultList);      //보낼때는 setAttribute   
			//조회결과를 보낼 URL을 지정
			RequestDispatcher  disp = request.getRequestDispatcher("/html/member/member_list.jsp");
			disp.forward(request, response);  //만들어진 정보가지고 가는 것 ->jsp로 보내는 것

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}