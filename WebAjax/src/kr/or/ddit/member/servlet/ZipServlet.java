package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.ZipService;
import kr.or.ddit.member.vo.ZipVO;

@WebServlet("/zipServlet")
public class ZipServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		retrieveZipList(req, resp);
	
	}

	private void retrieveZipList(HttpServletRequest req, HttpServletResponse resp) {
		try {
//			ZipVO zip1 = new ZipVO();
//			ZipVO zip2 = new ZipVO();
//			BeanUtils.copyProperties(zip1, zip2);  객체 카피..??
//			zip.setSido(req.getParameter("sido"));
//			zip.setGugun(req.getParameter("gugun"));
			
			ZipVO zipVo = new ZipVO();
			BeanUtils.populate(zipVo, req.getParameterMap());  //같은 이름이면 똑같이 셋팅해준다...?
			
			String flag = req.getParameter("flag");
			
			ZipService service = new ZipService();
			List<ZipVO> list = new ArrayList<ZipVO>();
			
			//flag에 따라서 시도, 구군, 동조회
			if("sido".equals(flag)) {
				list = service.retrievSidoList();
			}else if("gugun".equals(flag)) {
				list = service.retrievGugunList(zipVo);
			}else if("dong".equals(flag)){
				list = service.retrievDongList(zipVo);
			}else {
				list = service.retrievZipCodeList(zipVo);
			}
			req.setAttribute("list", list);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("html/member/zipList.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
