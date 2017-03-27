package it.camille.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.camille.domain.Book;
import it.camille.domain.Cart;
import it.camille.domain.CartItem;
import it.camille.service.BookService;
import it.camille.utils.BaseServlet;

/**
 * 购物车WEB层控制类
 * 
 * @author Camille
 * @version 1.0,2016-12-17 8:53:10
 */
@WebServlet(urlPatterns="/cartServlet")
public class CartServlet extends BaseServlet{

	/**
	 * 获取购物车的方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return Cart 购物车
	 */
	@SuppressWarnings("all")
	public Cart getCart(HttpServletRequest request) {
		// 从会话中获取购物车
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null) {
			// 如果购物车不存在,则新建购物车并存入会话中
			
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		// 将购物车返回
		return cart;
	}
	
	
	/**
	 * 将购物项添加到购物车的方法
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String addCart(HttpServletRequest request, HttpServletResponse response) {
		// 获取请求参数
		String bid = request.getParameter("bid");
		BookService bService = new BookService();
		Book book = bService.findBook(bid);
		int count;
		try {
			count = Integer.parseInt(request.getParameter("count"));
			if(count < 0) {
				count /= 0;
			}
		} catch (Exception e) {
			request.setAttribute("msg", "数量输入错误");
			request.setAttribute("book", book);
			return "jsps/book/desc.jsp";
		}
		
		// 将请求参数封装到购物项对象中
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		
		// 将购物项添加到购物车中
		Cart cart = getCart(request);
		cart.addCart(cartItem);
		
		// 请求转发
		return "jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物车
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		// 获取购物车并清空
		Cart cart = getCart(request);
		cart.clearCart();
		
		//请求转发
		return "jsps/cart/list.jsp";
	}
	
	/**
	 * 根据ID将购物项从购物车中移除
	 * 
	 * @param request 请求信息
	 * @param response 响应信息
	 * @return String 请求转发
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String bid = request.getParameter("bid");
		
		// 获取购物车并调用移除方法
		Cart cart = getCart(request);
		cart.removeCart(bid);
		
		// 请求转发
		return "jsps/cart/list.jsp";
	}
}












