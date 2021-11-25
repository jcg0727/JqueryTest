//package kr.or.ddit.member.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import kr.or.ddit.base.dao.BaseDao;
//import kr.or.ddit.common.MyUtil;
//import kr.or.ddit.member.vo.MemberVO;
//
//public class MemberDao_1023 extends BaseDao {
//	
//	private Connection con;
//	private PreparedStatement stmt;
//	private ResultSet rs;
//	
////	public MemberDao_1023() {
////		//super : BaseDao의 getConnection 이라는 뜻
////		con = super.getConnection();
////	}
//	
//	public int createMember(MemberVO memberVo) throws Exception {
//		String sql = "insert into TB_MEMBER  values"
//				+ " (TB_MEMBER_SEQ.nextval, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)";
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, memberVo.getId());
//		stmt.setString(2, memberVo.getName());
//		stmt.setString(4, memberVo.getBirthday());
//		stmt.setString(5, memberVo.getAddr1());
//		stmt.setString(6, memberVo.getHp());
//		
//		int result =stmt.executeUpdate();  
//		stmt.close();
//		return result;
//	}
//	
//	public int updateMember(MemberVO memberVo) throws Exception {
//		String sql = "update TB_MEMBER set NAME = ?, AGE = ?, BIRTHDAY = ?, ADDR = ?, TEL = ?, GENDER = ?"
//				+ " where ID = ? ";
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, memberVo.getName());
//		stmt.setString(3, memberVo.getBirthday());
//		stmt.setString(4, memberVo.getAddr1());
//		stmt.setString(5, memberVo.getHp());
//		stmt.setString(7, memberVo.getId());
//		
//		int result =stmt.executeUpdate();  
//		stmt.close();
//		return result;
//	}
//	
//	public int deleteMember(MemberVO memberVo) throws Exception {
//		String sql = "delete TB_MEMBER "
//					+ " where ID = ? ";
//		stmt = con.prepareStatement(sql);
//		stmt.setString(1, memberVo.getId());
//		
//		int result =stmt.executeUpdate();  
//		stmt.close();
//		return result;
//	}
//
//	public MemberVO retrieveMember(MemberVO memberVo) throws Exception {
//		String sql = ""
//				+ "select MEM_ID id        "
//				+ "     , MEM_PASS pass    "
//				+ "     , MEM_NAME name    "
//				+ "     , MEM_BIR birthday "
//				+ "     , MEM_REGNO2 regno2 "
//				+ "     , MEM_ZIP          "
//				+ "     , MEM_ADD1 addr    "
//				+ "     , MEM_ADD2         "
//				+ "     , MEM_HP tel       "
//				+ "     , MEM_MAIL         "
//				+ "     , MEM_JOB          "
//				+ "from member             "
//				+ "where 1=1               "
//				+ "";
//		stmt = con.prepareStatement(sql);
//		
//		if(!MyUtil.isEmpty(memberVo.getId())) {
//			sql+= "AND MEM_ID = ? ";
//			stmt.setString(1, memberVo.getId());
//		}
//		
//		rs = stmt.executeQuery();
//		
//		MemberVO vo = memberVo;
//		while(rs.next()) {
//			vo = new MemberVO();
//			vo.setId(rs.getString("id"));
//			vo.setName(rs.getString("name"));
//			vo.setBirthday(rs.getString("birthday"));
//			vo.setRegno2(rs.getString("regno2"));
//			vo.setAddr1(rs.getString("addr"));
//			vo.setHp(rs.getString("tel"));
//		}
//		rs.close();
//		stmt.close();
//		
//		return vo;
//	}
//	
//	public List<MemberVO> retrieveList(MemberVO memberVo) throws Exception {
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		
//		String sql = ""
//			+ "select MEM_ID id        "
//			+ "     , MEM_PASS pass    "
//			+ "     , MEM_NAME name    "
//			+ "     , MEM_BIR birthday "
//			+ "     , MEM_REGNO2 regno2 "
//			+ "     , MEM_ZIP          "
//			+ "     , MEM_ADD1 addr    "
//			+ "     , MEM_ADD2         "
//			+ "     , MEM_HP tel       "
//			+ "     , MEM_MAIL         "
//			+ "     , MEM_JOB          "
//			+ "from member             "
//			+ "where 1=1               "
//			+ "";
//		
//		
//		//검색조건으로 ID가 있으면 조회조건으로 ID 추가
//		if(!MyUtil.isEmpty(memberVo.getId())) {
//			sql+= "AND MEM_ID = ? ";
//		}
//		//검색조건으로 name이 있으면 조회조건으로 NAME 추가
//		if(memberVo != null && memberVo.getName() != null && memberVo.getName().length()>0) {
//			sql+= "AND MEM_NAME = ? ";
//		}
//		
//		stmt = con.prepareStatement(sql);
//		if(!MyUtil.isEmpty(memberVo.getId())) {
//			stmt.setString(1, memberVo.getId());
//		}else if(!MyUtil.isEmpty(memberVo.getName())) {
//			stmt.setString(1, memberVo.getName());
//		}else if(!MyUtil.isEmpty(memberVo.getId()) && !MyUtil.isEmpty(memberVo.getName())) {
//			stmt.setString(1, memberVo.getId());
//			stmt.setString(2, memberVo.getName());
//		}
//		
//		rs = stmt.executeQuery();
//		while(rs.next()) {
//			MemberVO vo = new MemberVO();
//			vo.setId(rs.getString("id"));
//			vo.setName(rs.getString("name"));
////			vo.setAge(rs.getInt("age"));
//			vo.setBirthday(rs.getString("birthday"));
//			vo.setRegno2(rs.getString("regno2"));
//			vo.setAddr1(rs.getString("addr"));
//			vo.setHp(rs.getString("tel"));
////			vo.setGender(rs.getString("gender"));
//			list.add(vo);
//		}
//		rs.close();
//		stmt.close();
//		
//		return list;
//	}
//	
////	public List<MemberVO> tmpList(){
////		List<MemberVO> list = new ArrayList<MemberVO>();
////		MemberVO vo1 = new MemberVO(1, "psy1", "박소연", 38, "19830614", "2364587","대전시 중구", "01012341234", "F");
////		MemberVO vo2 = new MemberVO(2, "syp2", "홍길동", 20, "20010514", "1687935","대전시 서구", "01011112222", "M");
////		list.add(vo1);
////		list.add(vo2);
////		return list;
////	}
//	
//}
