package it.camille.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import it.camille.domain.User;
import it.camille.utils.JDBCUtils;

/**
 * 用户持久类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:42:40
 */
public class UserDao {

	/**
	 * 用户注册持久层方法
	 * 
	 * @param user 需要注册的用户信息
	 */
	public void save(User user) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		// 编写sql语句
		String sql = "insert into user values(?,?,?,?,?,?)";

		// 获取参数
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getState(),
				user.getCode() };

		// 执行sql语句
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层根据指定的邮箱和验证码查找用户
	 * 
	 * @param email 指定邮箱
	 * @param code 指定验证码
	 * @return User 查找到的用户
	 */
	public User findByEmailAndCode(String email, String code) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 创建SQL语句
		String sql = "select * from user where email = ? and code = ?";
		
		// 执行SQL语句
		User user;
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), email, code);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return user;
	}

	/**
	 * 持久层根据ID更新指定用户信息
	 * 
	 * @param user 指定用户信息
	 */
	public void update(User user) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 编写SQL语句
		String sql = "update user set username = ?, password = ?, email = ?,state = ?, code = ? where uid = ?";
		
		// 获取数据
		Object[] params = { user.getUsername(), user.getPassword(), user.getEmail(), user.getState(), user.getCode(),
				user.getUid() };
		// 执行SQL语句
		try {
			runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层根据用户名查找用户信息
	 * 
	 * @param username 指定用户名
	 * @return User 查找到的用户信息
	 */
	public User checkUsername(String username) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 创建SQL语句
		String sql = "select * from user where username = ?";
		
		// 执行SQL语句查找对象
		User user;
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return user;
	}

	/**
	 * 持久层检查用户登录信息
	 * 
	 * @param user 用户登录信息
	 * @return User 查询后对象
	 */
	public User checkLogin(User user) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		// 编写SQL语句
		String sql = "select * from user where username = ? and password = ?";
		
		//执行SQL语句
		User existUser;
		try {
			existUser = runner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return existUser;
	}

}
