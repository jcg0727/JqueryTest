package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomEncoding implements Filter{

	private String encoding;  //인코딩 정보
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		if(config.getInitParameter("encoding")==null) {
			this.encoding = "utf-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("request 인코딩 설정 : "+encoding);
		req.setCharacterEncoding(encoding);
		
		System.out.println("response 인코딩 설정 : "+encoding);
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp);  //filter-chin이 있으면 그 다음 필터 실행하고 없으면 종료하는 메서드.
		
	}


}
