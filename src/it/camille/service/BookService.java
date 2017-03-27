package it.camille.service;

import java.util.List;
import it.camille.dao.BookDao;
import it.camille.domain.Book;

/**
 * 图书业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:58:13
 */
public class BookService {

	/**
	 * 业务层查询全部图书
	 * 
	 * @return List<Book> 全部图书集合
	 */
	public List<Book> findAll() {
		BookDao dao = new BookDao();
		return dao.get();
	}

	/**
	 * 业务层根据分类ID查询图书
	 * 
	 * @param cid 分类ID
	 * @return List<Book> 图书集合
	 */
	public List<Book> findBookByCid(String cid) {
		BookDao dao = new BookDao();
		return dao.findBookByCid(cid);
	}

	/**
	 * 业务层根据ID查询图书
	 * 
	 * @param bid 图书ID
	 * @return Book 查询到的图书
	 */
	public Book findBook(String bid) {
		BookDao dao = new BookDao();
		return dao.findBook(bid);
	}

}
