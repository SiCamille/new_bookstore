package it.camille.web.servlet;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.camille.domain.Book;
import it.camille.service.BookService;
import it.camille.utils.BaseServlet;

/**
 * 图书WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2016-12-17 8:10:57
 */
@WebServlet(urlPatterns="/bookServlet")
public class BookServlet extends BaseServlet {

	/**
	 * 查询全部图书
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return 请求转发
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) {
		// 调用业务层获取全部图书
		BookService service = new BookService();
		List<Book> list = service.findAll();
		// 将获取到的图书封装到请求信息中,并转发到jsp
		request.setAttribute("list", list);
		return "/jsps/book/list.jsp";
	}

	/**
	 * 根据分类ID获取图书
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String findBookByCid(HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数(分类ID)
		String cid = request.getParameter("cid");
		
		// 调用业务层根据分类ID查找图书
		BookService service = new BookService();
		List<Book> list = service.findBookByCid(cid);
		
		// 将获取的图书封装到请求信息中,并转发至jsp
		request.setAttribute("list", list);
		return "jsps/book/list.jsp";
	}
	
	/**
	 * 根据ID获取图书详情
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String bookDesc(HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数
		String bid = request.getParameter("bid");
		
		// 调用业务层获取图书信息
		BookService service = new BookService();
		Book book = service.findBook(bid);
		
		// 将获取到的图书封装到
		request.setAttribute("book", book);
		return "jsps/book/desc.jsp";
	}
}
