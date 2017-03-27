package it.camille.service;

import java.util.List;
import it.camille.dao.AdminBookDao;
import it.camille.domain.Book;

/**
 * 图书管理业务层类
 * 
 * @author Camille
 * @version 1.0,2017-2-5 22:19:36
 */
public class AdminBookService {

	private AdminBookDao dao = new AdminBookDao();
	
	/**
	 * 查询全部图书业务层方法
	 * 
	 * @return List<Book> 全部图书集合
	 */
	public List<Book> findAllBook() {
		return dao.findAllBook();
	}

}
