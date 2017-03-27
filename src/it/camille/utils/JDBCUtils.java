package it.camille.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接工具类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:47:23
 */
public class JDBCUtils {

	/** 获取数据库连接池对象 */
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	/** 工具类私有构造方法 */
	private JDBCUtils() {}
	
	/**
	 * 获取链接方法
	 * 
	 * @return Connection 连接对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 获取数据库连接池的方法
	 * 
	 * @return DataSource 数据库连接池
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}
	
}
