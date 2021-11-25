package kr.or.ddit.common.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.CodeService;
import kr.or.ddit.common.vo.CodeVO;

//import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 7428836381231581524L;

	//GET방식 호출 시
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	//POST방식 호출 시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		retrieveCodeList(request, response);

	}


	private void retrieveCodeList(HttpServletRequest request, HttpServletResponse response) {
		try {
			//request에서 받아와서 세팅해 줄 것임
			CodeVO paramVo = new CodeVO();
			paramVo.setGroupCode(request.getParameter("groupCode"));
			//서비스 호출해서 '목록 조회'를 수행
			CodeService service = new CodeService();
			List<CodeVO> resultList = service.retrieveCodeList(paramVo);   //dao에서 받아온 회원list저장

			//조회결과를 request에 담아서 전송
			request.setAttribute("CodeVoList", resultList);      //보낼때는 setAttribute   
			//조회결과를 보낼 URL을 지정
			RequestDispatcher  disp = request.getRequestDispatcher("/html/member/codeList.jsp");
			disp.forward(request, response);  //만들어진 정보가지고 가는 것 ->jsp로 보내는 것

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}