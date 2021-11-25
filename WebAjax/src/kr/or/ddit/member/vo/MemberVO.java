package kr.or.ddit.member.vo;

public class MemberVO {
	
	private String memId;
	private String memPass;
	private String memName;
	private String memBir;
	private String memRegno1;
	private String memRegno2;
	private String memAddr1;
	private String memAddr2;
	private String memMail;
	private String memHp;
	private String memZip;
	
	public MemberVO() {
	}
	
	public MemberVO(String id, String pass, String name, String memMail, String hp, String birthday,String memZip, String regno1, String regno2, String addr1, String addr2) {
		super();
		this.memId = id;
		this.memPass = pass;
		this.memName = name;
		this.memBir = birthday;
		this.memRegno1 = regno1;
		this.memRegno2 = regno2;
		this.memAddr1 = addr1;
		this.memAddr2 = addr2;
		this.memHp = hp;
		this.memZip = memZip;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemBir() {
		return memBir;
	}

	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}

	public String getMemRegno1() {
		return memRegno1;
	}

	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}

	public String getMemRegno2() {
		return memRegno2;
	}

	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}

	public String getMemAddr1() {
		return memAddr1;
	}

	public void setMemAddr1(String memAddr1) {
		this.memAddr1 = memAddr1;
	}

	public String getMemAddr2() {
		return memAddr2;
	}

	public void setMemAddr2(String memAddr2) {
		this.memAddr2 = memAddr2;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}

	public String getMemHp() {
		return memHp;
	}

	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}

	public String getMemZip() {
		return memZip;
	}

	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}

	
}
