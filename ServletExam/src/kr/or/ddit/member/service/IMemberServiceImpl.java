package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

//비지니스로직은 다 서비스에서 처리
public class IMemberServiceImpl implements IMemberService{
	private IMemberDao memDao;
	private static IMemberService service;
	
	public IMemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();  //일반적으로 생성자에 만듬
	}

	public static IMemberService getInstance() {
		if(service == null) {
			service = new IMemberServiceImpl();
		}
		return service;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = memDao.insertMember(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt =  memDao.deleteMember(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> displayMemberAll() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = memDao.displayMemberAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = null;
		try {
			mv =  memDao.getMember(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = memDao.getSearchMember(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int selectTotalCount() {
		int cnt=0;
		try {
			cnt = memDao.selectTotalCount(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> displayMemberByPaging(PagingVO pagingVO) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = memDao.displayMemberByPaging(pagingVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memList;
	}

}
