package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.TBMemberVO;

public class MemberService {
	private MemberDao dao;
	
	public MemberService() {
		if(dao == null)
			dao = new MemberDao();
	}
	
//	public MemberVO createMember(MemberVO memberVo) throws Exception {
//		int result = dao.createMember(memberVo);
//		if(result > 0) {
//			MemberVO tmpMemberVo = new MemberVO();
//			tmpMemberVo.setId(memberVo.getId());
//			return dao.retrieve(tmpMemberVo);
//		} else {
//			return null;
//		}
//	}
	public Object createMember(TBMemberVO member) throws Exception {
		return dao.createMember(member);
	}
	
	public int updateMember(MemberVO memberVo) {
		return dao.updateMember(memberVo);
//		return memberVo;
	}
	public int deleteMember(MemberVO memberVo) {
		return dao.deleteMember(memberVo);
	}
	public TBMemberVO retrieveMember(TBMemberVO memberVo) throws Exception {
		return dao.retrieveMember(memberVo);
	}
	public List<MemberVO> retrieveMemberList(MemberVO memberVo) throws Exception {
		return dao.retrieveList(memberVo);
	}

	
}
