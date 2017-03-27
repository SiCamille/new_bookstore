package it.camille.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的用户Servlety
 * 
 * @author Camille
 * @version 1.0,2017-2-1 19:26:33
 */
public class BaseServlet extends HttpServlet {

	/**
	 * 重写doGet方法,指向doPost方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 重写doPost方法,根据传入的参数调用方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设定接受字符集为UTF-8
		request.setCharacterEncoding("UTF-8");
	
		//获得请求的method参数
		String methodName = request.getParameter("method");
		
		//获得当前类对象(子类)
		Class<? extends BaseServlet> clazz = this.getClass();
		
		//根据方法名找到方法并执行
		try {
			Method method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			if(method != null) {
				
				//执行方法,获取转发路径
				String path = (String) method.invoke(this, request,response);
				
				//如转发路径不为空,则转发至指定路径
				if(path != null) {
					request.getRequestDispatcher(path).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}








