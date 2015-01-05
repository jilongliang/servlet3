package ivyy.taobao.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *@Author:jilongliang
 *@Date  :2012-5-21
 *@Project:servlet3.0
 */
 //@WebFilter(filterName="myFilter",urlPatterns="/*") //*启动所以的servlet都过滤
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		System.out.println("doFilter");
		
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		
	}

}
