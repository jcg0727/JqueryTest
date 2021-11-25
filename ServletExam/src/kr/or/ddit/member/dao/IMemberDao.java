package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아 Service에 전달하는 DAO의 
 * interface
 */
public interface IMemberDao {
	/**
	 * MemberVO에 담긴 자료를 DB에 insert하는 메서드
	 * 
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return  DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv) throws Exception;
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String memId) throws Exception;
	
	/**
	 * 하나의 MemberVO 자료를 이용하여 DB를 update하는 메서드
	 * @param mv update할 회원정보가 들어있는 MemberVO객체
	 * @return  작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO mv) throws Exception;
	
	/**
	 * DB의 mymember테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고있는 List객체
	 */
	public List<MemberVO> displayMemberAll() throws Exception;
	
	/**
	 * 페이징 처리하여 회원정보 조회하는 메서드
	 * @return MemberVO객체를 담고있는 List객체
	 * @throws Exception
	 */
	public List<MemberVO> displayMemberByPaging(PagingVO pagingVO) throws Exception;
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 
	 * @return 해당회원이 존재하면 true, 없으면 false
	 */
	public MemberVO getMember(String memId) throws Exception;  
	
	/**
	 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색할 자료가 담긴 MemberVO객체
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<MemberVO> getSearchMember(MemberVO mv) throws Exception;

	/**
	 * 전체 회원수 조회
	 * @return 
	 */
	public int selectTotalCount() throws Exception;
}








