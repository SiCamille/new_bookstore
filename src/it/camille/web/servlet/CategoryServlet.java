package it.camille.web.servlet;

import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.camille.domain.Category;
import it.camille.service.CategoryService;
import it.camille.utils.BaseServlet;

/**
 * 分类WEB层控制类
 * 
 * @author Camille
 * @version 1.0,
 */
@WebServlet(urlPatterns="/categoryServlet")
public class CategoryServlet extends BaseServlet {

	/**
	 * 查找全部分类
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 转发路径
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) {
		// 调用业务层获取全部分类集合
		CategoryService service = new CategoryService();
		List<Category> list = service.findAllCategory();
		request.setAttribute("list", list);
		return "/jsps/left.jsp";
	}
}
