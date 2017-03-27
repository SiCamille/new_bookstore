package it.camille.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import it.camille.domain.Category;
import it.camille.utils.JDBCUtils;
import it.camille.utils.UUIDUtils;

/**
 * 分类管理持久层类
 * 
 * @author Camille
 * @version 1.0,2016-12-29 13:58:07
 */
public class AdminCategoryDao {

	/** 获取DBUtils对象 */
	private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * 持久层获取全部分类
	 * 
	 * @return List<Category>
	 */
	public List<Category> findAllCategory() {
		// 编写SQL语句
		String sql = "select * from category";
		
		// 执行SQL语句
		List<Category> list;
		try {
			list = runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * 持久层删除分类
	 * 
	 * @param cid 需要删除的分类ID
	 */
	public void delete(String cid) {
		// 编写SQL语句
		String sql = "delete from category where cid = ?";
		
		// 执行SQL语句
		try {
			runner.update(sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 持久层根据ID获取分类
	 * 
	 * @param cid 需要获取的分类ID
	 * @return null 无此对象 Category 分类对象
	 */
	public Category findByCid(String cid) {
		// 编写SQL语句
		String sql = "select * from category where cid = ?";
		
		// 执行SQL语句
		Category c;
		try {
			c = runner.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return c;
	}

	/**
	 * 持久层修改分类
	 * 
	 * @param c 需要修改的分类实体
	 */
	public void update(Category c) {
		// 编写SQL语句
		String sql = "update category set cname = ? where cid = ?";
		
		// 执行SQL语句
		try {
			runner.update(sql, c.getCname(), c.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层添加分类
	 * 
	 * @param c 需要添加的分类实体
	 */
	public void add(Category c) {
		// 编写SQL语句
		String sql = "insert into category values(?,?)";
		
		// 执行SQL语句
		try {
			runner.update(sql, UUIDUtils.getUUID(), c.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
