package it.camille.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import it.camille.domain.User;
import it.camille.service.UserService;
import it.camille.utils.BaseServlet;

/**
 * 用户WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 9:26:39
 */
@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {

	/**
	 * 添加用户的方法(注册)
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发路径
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) {
		// 创建用户对象
		User user = new User();

		// 使用BeanUtils将对象封装
		try {
			BeanUtils.populate(user, request.getParameterMap());
			if (user.getUsername() == "" || user.getUsername() == null || user.getPassword() == ""
					|| user.getPassword() == null || user.getEmail() == "" || user.getEmail() == null) {
				request.setAttribute("msg", "别搞事...");
				return "/jsps/msg.jsp";
			}
			UserService service = new UserService();
			service.regist(user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// 调用业务层存储对象
		request.setAttribute("msg", "注册成功,请去邮箱激活账户");

		// 请求转发
		return "/jsps/msg.jsp";
	}

	/**
	 * 激活用户的方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发路径
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) {
		// 获取需要激活的用户信息
		String email = request.getParameter("email");
		String code = request.getParameter("code");

		// 调用业务层根据信息查找用户
		UserService service = new UserService();
		User user = service.findByEmailAndCode(email, code);

		// 判断用户信息是否正确,如正确则修改用户状态
		if (user != null) {
			user.setState(1);
			user.setCode(null);
			service.update(user);
			request.setAttribute("msg", "激活成功!");
		} else {
			request.setAttribute("msg", "激活失败");
		}
		return "/jsps/msg.jsp";
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 * @throws IOException
	 */
	public String checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取传入用户名
		String username = request.getParameter("username");

		// 调用业务层根据用户名查找用户
		UserService service = new UserService();
		User user = service.checkUsername(username);

		// 判断用户是否存在 如存在 返回1 如不存在 返回0
		if (user != null) {
			response.getWriter().println(1);
		} else {
			response.getWriter().println(0);
		}
		return null;
	}

	/**
	 * 检查用户登录信息方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 转发路径
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取用户输入验证码
		String code = request.getParameter("code");
		String checkCode = (String) request.getSession().getAttribute("checkCode");
		request.getSession().removeAttribute("checkCode");
		if (!checkCode.equalsIgnoreCase(code)) {
			request.setAttribute("msg", "验证码错误");
			return "/jsps/user/login.jsp";
		}

		// 获取用户输入信息
		User user = new User();

		// 封装用户信息并查询
		try {
			BeanUtils.populate(user, request.getParameterMap());
			UserService service = new UserService();
			User existUser = service.login(user);
			if (existUser != null) {
				// 如信息正确,则保存并重定向至主页
				request.getSession().setAttribute("user", existUser);
				response.sendRedirect(request.getContextPath() + "/jsps/main.jsp");
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				return "/jsps/user/login.jsp";
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 用户退出登录方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 * @throws IOException
	 */
	public String exitLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取用户信息并销毁
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/jsps/main.jsp");
		return null;
	}

}
