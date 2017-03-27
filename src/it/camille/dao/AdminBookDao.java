package it.camille.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import it.camille.domain.Book;
import it.camille.utils.JDBCUtils;

/**
 * 图书管理持久层类
 * 
 * @author Camille
 * @version 1.0,2017-2-5 22:20:17
 */
public class AdminBookDao {

	/** 获取DBUtils对象 */
	private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * 查询全部图书持久层方法
	 * 
	 * @return List<Book> 全部图书集合
	 */
	public List<Book> findAllBook() {
		String sql = "select * from book";
		List<Book> list;
		try {
			list = runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

}
