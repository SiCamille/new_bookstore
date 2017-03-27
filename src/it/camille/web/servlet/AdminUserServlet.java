package it.camille.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import it.camille.domain.AdminUser;
import it.camille.service.AdminUserService;
import it.camille.utils.BaseServlet;

/**
 * 管理员用户界面
 * 
 * @author Camille
 * @version 1.0,2016-12-20 15:57:24
 */
@WebServlet(urlPatterns="/adminUserServlet")
public class AdminUserServlet extends BaseServlet {
	
	/** 获取业务层对象 */
	private AdminUserService service = new AdminUserService();

	/**
	 * 检查管理员登录
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// 创建实体
		AdminUser user = new AdminUser();
		
		// 封装实体
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 检查用户信息
		user = service.login(user);
		// 如果信息正确,则跳转至主页面,如信息错误,则给出提示
		if(user != null) {
			request.getSession().setAttribute("adminUser", user);
			try {
				response.sendRedirect(request.getContextPath() + "/adminjsps/admin/main.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("msg", "我靠( ‵o′)凸");
			return "/adminjsps/login.jsp";
		}
		return null;
	}
	
	
}
