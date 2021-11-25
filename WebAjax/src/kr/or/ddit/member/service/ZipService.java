package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.ZipDao;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class ZipService {
	private ZipDao dao;
	
	public ZipService() {
		if(dao == null)
			dao = new ZipDao();
	}

	
	public List<ZipVO> retrievSidoList() throws Exception {
		return dao.retrieveSidoList();
	}


	public List<ZipVO> retrievGugunList(ZipVO zipVo) {
		return dao.retrievGugunList(zipVo);
	}


	public List<ZipVO> retrievDongList(ZipVO zipVo) {
		return dao.retrievDongList(zipVo);
	}


	public List<ZipVO> retrievZipCodeList(ZipVO zipVo) {
		return dao.retrievZipCodeList(zipVo);
	}

	
}
