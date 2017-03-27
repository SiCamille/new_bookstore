package it.camille.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import it.camille.domain.AdminUser;
import it.camille.utils.JDBCUtils;

/**
 * 管理员持久类
 * 
 * @author Camille
 * @version 1.0,2016-12-20 16:11:22
 */
public class AdminDao {

	/** 获取DBUtils对象 */
	private QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * 持久层检查用户登录信息
	 * 
	 * @param user 录入的管理员信息
	 * @return AdminUser 数据库实体
	 */
	public AdminUser login(AdminUser user) {
		// 编写SQL语句
		String sql = "select * from adminuser where username = ? and password = ?";
		
		// 执行SQL语句
		try {
			user = runner.query(sql, new BeanHandler<AdminUser>(AdminUser.class),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		// 将结果返回
		return user;
	}

}
