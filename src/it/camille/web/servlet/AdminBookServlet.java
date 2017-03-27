package it.camille.web.servlet;

import it.camille.domain.Book;
import it.camille.service.AdminBookService;
import it.camille.utils.BaseServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 图书管理WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2017-2-5 22:12:48
 */
@WebServlet(urlPatterns="/adminBookServlet")
public class AdminBookServlet extends BaseServlet{

	private AdminBookService service = new AdminBookService();
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		List<Book> list = service.findAllBook();
		request.setAttribute("list", list);
		return "/adminjsps/admin/book/list.jsp";
	}
}
