package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드 제공
 */
public class JDBCUtil {  
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!!!");
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		}
	}
	
	public static Connection getConnection() {   //자주 써서 코드 중복 없애기 위해 메서드로 만듬.
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe",
					"PJM04", "java");
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
