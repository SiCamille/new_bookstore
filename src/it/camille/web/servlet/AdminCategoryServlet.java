package it.camille.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import it.camille.domain.Category;
import it.camille.service.AdminCategoryService;
import it.camille.utils.BaseServlet;

/**
 * 分类管理WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2016-12-29 13:55:23
 */
@WebServlet(urlPatterns="/adminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet{

	private AdminCategoryService service = new AdminCategoryService();
	
	/**
	 * 查看全部分类的方法
	 * 
	 * @param req 请求信息
	 * @param resp 响应信息
	 * @return String 请求转发
	 */
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		// 调用业务层查询全部分类
		List<Category> list = service.findAllCategory();
		req.setAttribute("list", list);
		return "/adminjsps/admin/category/list.jsp";
	}
	
	/**
	 * 根据ID删除分类的方法
	 * 
	 * @param req 请求信息
	 * @param resp 响应信息
	 * @return String 请求转发
	 * @throws IOException 
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取分类ID
		String cid = req.getParameter("cid");
		
		// 调用业务层根据ID删除分类
		service.deleteCategory(cid);
		
		return list(req,resp);
	}
	
	/**
	 * 获取需要修改的分类
	 * 
	 * @param req 请求信息
	 * @param resp 响应信息
	 * @return String 请求转发
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp) {
		// 获取分类ID
		String cid = req.getParameter("cid");
		
		// 调用业务层分别根据ID查询分类
		Category c = service.findByCid(cid);
		req.setAttribute("c", c);
		return "adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 修改分类的方法
	 * 
	 * @param req 请求信息
	 * @param resp 响应信息
	 * @return String 请求转发
	 */
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		// 获取参数并封装
		Category c = new Category();
		try {
			BeanUtils.populate(c, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 调用业务层修改
		service.update(c);
		return list(req, resp);
	}
	
	/**
	 * 添加分类的方法
	 * 
	 * @param req 请求信息
	 * @param resp 响应信息
	 * @return String 请求转发
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		// 获取参数并封装
		Category c = new Category();
		try {
			BeanUtils.populate(c, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 调用业务层
		service.add(c);
		return list(req,resp);
	}
}
