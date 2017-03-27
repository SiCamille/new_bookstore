package it.camille.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import it.camille.domain.Book;
import it.camille.domain.Order;
import it.camille.domain.OrderItem;
import it.camille.utils.JDBCUtils;

/**
 * 订单持久类
 * 
 * @author Camille
 * @version 1.0,2016-12-18 16:45:04
 */
public class OrderDao {

	/**
	 * 持久层将订单实体存储至数据库中
	 * 
	 * @param order 订单实体
	 */
	public void saveOrder(Connection conn, Order order) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner();

		// 编写SQL语句
		String sql = "insert into orders values(?,?,?,?,?,?)";

		// 获取参数
		Object[] params = { order.getOid(), order.getTotal(), order.getOrdertime().toLocaleString(), order.getState(),
				order.getAddress(), order.getUser().getUid() };

		// 执行SQL语句
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层将订单项实体存储至数据库中
	 * 
	 * @param orderItem 订单项实体
	 */
	public void saveOrderItem(Connection conn, OrderItem orderItem) {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Object[] params = { orderItem.getIid(), orderItem.getCount(), orderItem.getSubtotal(),
				orderItem.getBook().getBid(), orderItem.getOrder().getOid() };
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层根据订单ID查询订单
	 * 
	 * @param oid 订单ID
	 * @return Order 订单实体
	 */
	public Order findByOid(String oid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		// 编写SQL语句,获取订单实体
		String sql = "select * from orders where oid = ?";

		// 执行SQL语句
		Order order;
		try {
			order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);

			// 编写SQL语句,获取订单项实体
			sql = "select * from orderitem o, book b where o.bid = b.bid and o.oid = ?";

			// 执行SQL语句
			List<Map<String, Object>> orderitems = runner.query(sql, new MapListHandler(), oid);

			// 便利集合,将订单项添加至订单中
			for (Map<String, Object> map : orderitems) {
				Book book = new Book();
				BeanUtils.populate(book, map);

				OrderItem orderitem = new OrderItem();
				BeanUtils.populate(orderitem, map);
				orderitem.setBook(book);
				orderitem.setOrder(order);
				order.getOrderItems().add(orderitem);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		// 将获取到的订单返回
		return order;
	}

	/**
	 * 持久层根据用户ID获取订单实体
	 * 
	 * @param uid 用户ID
	 * @return List<Order> 订单实体集合
	 */
	public List<Order> findByUid(String uid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		// 编写SQL语句,根据用户ID获取订单实体
		String sql = "select * from orders where uid = ? order by ordertime desc";

		// 执行SQL语句
		List<Order> list;
		try {
			list = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);

			for (Order order : list) {
				// 编写SQL语句,获取订单项,并封装至订单中
				sql = "select * from orderitem o, book b where o.bid = b.bid and o.oid = ?";

				// 执行SQL语句
				List<Map<String, Object>> orderItems = runner.query(sql, new MapListHandler(), order.getOid());

				// 将订单项封装置订单中
				for (Map<String, Object> map : orderItems) {
					Book book = new Book();
					BeanUtils.populate(book, map);

					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					orderItem.setBook(book);
					orderItem.setOrder(order);
					order.getOrderItems().add(orderItem);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return list;
	}

	/**
	 * 持久层根据订单ID删除订单项
	 * 
	 * @param conn 连接对象
	 * @param oid 订单ID
	 */
	public void deleteOrderItemByOid(Connection conn, String oid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner();

		// 编写SQL语句
		String sql = "delete from orderitem where oid = ?";

		// 执行SQL语句
		try {
			runner.update(conn, sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层根据订单ID删除订单的方法
	 * 
	 * @param conn 连接对象
	 * @param oid 订单ID
	 */
	public void deleteOrder(Connection conn, String oid) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner();

		// 编写SQL语句
		String sql = "delete from orders where oid = ?";

		// 执行SQL语句
		try {
			runner.update(conn, sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 持久层修改订单
	 * 
	 * @param order 修改后订单
	 */
	public void update(Order order) {
		// 获取DBUtils对象
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		// 编写SQL语句
		String sql = "update orders set total = ?, ordertime = ?, state = ?, address = ?, uid = ? where oid = ?";

		Object[] params = { order.getTotal(), order.getOrdertime(), order.getState(), order.getAddress(),
				order.getUser().getUid(), order.getOid() };

		// 执行SQL语句
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
