package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapClient객체를 제공하는 팩토리 클래스
 * 
 */
public class SqlMapClientFactory {
	private static SqlMapClient smc;
	
	private SqlMapClientFactory() {
		
	}
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			try {
				Charset charset = Charset.forName("UTF-8");  //설정파일의 인코딩 설정
				Resources.setCharset(charset);
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);   
				
				rd.close();
			}catch(IOException e) {
				System.err.println("SqlMapClient 객체 생성 실패!!!");
				e.printStackTrace();
			}
		}
		return smc;
	}
}
