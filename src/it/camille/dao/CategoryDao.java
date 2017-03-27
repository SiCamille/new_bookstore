package it.camille.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import it.camille.domain.Category;
import it.camille.utils.JDBCUtils;

/**
 * 分类持久类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:27:46
 */
public class CategoryDao {

	/**
	 * 持久层查找全部分类
	 * 
	 * @return List<Category> 全部分类集合
	 */
	public List<Category> get() {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 编写SQL语句
		String sql = "select * from category";
		
		//执行SQL语句
		List<Category> list;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}
}
