package it.camille.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 权限过滤器
 * 
 * @author Camille
 * @version 1.0,2016-12-19 10:59:45
 */
public class PrivilegeFilter implements Filter{

	/**
	 * 过滤器初始化方法
	 * 
	 * @param filterConfig 过滤器初始化对象
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("PrivilegeFilter过滤器创建成功");
	}

	/**
	 * 过滤器执行方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @chain 过滤器执行对象
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("过滤器执行了");
		chain.doFilter(request, response);
	}

	/**
	 * 过滤器销毁方法
	 */
	@Override
	public void destroy() {
		System.out.println("PrivilegeFilter过滤器已销毁");
	}

}
