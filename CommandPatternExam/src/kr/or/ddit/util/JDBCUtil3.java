package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) ResourceBundle객체 이용하기
 */
public class JDBCUtil3 {
	static ResourceBundle bundle; //Properties객체 변수 선언
	
	static {
		try {
			bundle = ResourceBundle.getBundle("db");  //객체 생성
			
			Class.forName(bundle.getString("driver"));  
		}catch(ClassNotFoundException e) {
			System.err.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"),
					bundle.getString("user"), bundle.getString("pass"));
		}catch(SQLException ex) {
			System.out.println("DB 연결 실패!!!");
			return null;
		}
		
	}
	
	/**
	 * 자원 반납용 메서드
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();}catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
		
	}
}
