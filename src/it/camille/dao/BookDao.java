package it.camille.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import it.camille.domain.Book;
import it.camille.utils.JDBCUtils;

/**
 * 图书持久类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:59:05
 */
public class BookDao {

	/**
	 * 持久层查询全部图书
	 * 
	 * @return List<Book> 全部图书集合
	 */
	public List<Book> get() {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 编写SQL语句
		String sql = "select * from book";
		
		// 执行sql语句
		List<Book> list;
		try {
			list = runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * 持久层根据分类ID查找图书
	 * 
	 * @param cid 分类ID
	 * @return List<Book> 图书集合
	 */
	public List<Book> findBookByCid(String cid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 编写SQL语句
		String sql = "select * from book where cid = ?";
		
		// 执行SQL语句
		List<Book> list;
		try {
			list = runner.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * 持久层根据ID查询图书
	 * 
	 * @param bid 图书ID
	 * @return Book 查询到的图书
	 */
	public Book findBook(String bid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
	
		// 编写SQL语句
		String sql = "select * from book where bid = ?";
		
		// 执行SQL语句
		Book book;
		try {
			book = runner.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return book;
	}
	

}





