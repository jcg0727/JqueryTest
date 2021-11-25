package kr.or.ddit.member.vo;

import java.util.Date;

public class AtchFileVO {
	private long atchFileId;       // 첨부파일 ID
	private int fileSn = 0;        // 파일순번
	private String fileStreCours;  // 파일저장경로
	private String streFileNm;     // 저장파일이름
	private String orignalFileNm;  // 원본파일이름
	private String fileExtsn;      // 파일확장명
	private String fileCn;         // 파일내용
	private long fileSize = 0;     // 파일크기
	private Date creatDate;       // 파일생성일자
	
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignalFileNm() {
		return orignalFileNm;
	}
	public void setOrignalFileNm(String orignalFileNm) {
		this.orignalFileNm = orignalFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getCreateDate() {
		return creatDate;
	}
	public void setCreateDate(Date createDate) {
		this.creatDate = createDate;
	}
	
	
	
}
