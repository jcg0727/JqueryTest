package kr.or.ddit.base.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.common.SqlMapClientFactory;

public class BaseDao {
	private SqlMapClient smc;
	
	protected SqlMapClient getSqlMapClient() {
		if(smc == null) {
			smc = SqlMapClientFactory.getInstance();
		}
		return smc;
	}
}
