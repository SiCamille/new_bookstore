package it.camille.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import it.camille.dao.OrderDao;
import it.camille.domain.Order;
import it.camille.domain.OrderItem;
import it.camille.utils.JDBCUtils;

/**
 * 订单业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-18 16:43:52
 */
public class OrderService {

	/**
	 * 业务层将订单实体存储至数据库中
	 * 
	 * @param order 订单实体
	 */
	public void save(Order order) {
		// 由于需要同时存储订单和订单项,故使用事务处理
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			OrderDao dao = new OrderDao();
			dao.saveOrder(conn,order);
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				dao.saveOrderItem(conn,orderItem);
			}
			
			// 如处理成功,则提交事务
			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			
			// 如处理失败,则事务回滚
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}
	}

	/**
	 * 业务层根据订单ID查询订单
	 * 
	 * @param oid 订单ID
	 * @return Order 订单实体
	 */
	public Order findByOid(String oid) {
		OrderDao dao = new OrderDao();
		return dao.findByOid(oid);
	}

	/**
	 * 业务层根据用户ID查询订单
	 * 
	 * @param uid 用户ID
	 * @return List<Order> 订单实体集合
	 */
	public List<Order> findByUid(String uid) {
		OrderDao dao = new OrderDao();
		return dao.findByUid(uid);
	}

	/**
	 * 业务层根据订单ID删除订单的方法
	 * 
	 * @param oid 订单ID
	 */
	public void delete(String oid) {
		// 由于需要同时删除订单和删除订单项,故使用事务处理
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			OrderDao dao = new OrderDao();
			// 执行删除订单项方法
			dao.deleteOrderItemByOid(conn,oid);
			
			// 执行删除订单方法
			dao.deleteOrder(conn,oid);
			
			// 如处理成功,则提交事务
			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			
			// 如处理失败,则事务回滚
			e.printStackTrace();
			DbUtils.rollbackAndCloseQuietly(conn);
		}
	}

	/**
	 * 业务层修改订单
	 * 
	 * @param order 修改后订单
	 */
	public void update(Order order) {
		OrderDao dao = new OrderDao();
		dao.update(order);
	}

}
