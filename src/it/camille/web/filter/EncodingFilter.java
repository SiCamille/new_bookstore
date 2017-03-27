package it.camille.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 字符集过滤器
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:39:50
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{

	/**
	 * 过滤器初始化方法
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("EncodingFilter过滤器创建成功");
	}

	/**
	 * 过滤器执行方法
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//设定post方法字符集为UTF-8
		request.setCharacterEncoding("UTF-8");
		
		//放行
		chain.doFilter(request, response);
	}

	/**
	 * 过滤器销毁方法
	 */
	@Override
	public void destroy() {
		System.out.println("EncodingFilter过滤器销毁");
	}

}
