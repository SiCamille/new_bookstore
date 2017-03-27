package it.camille.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author Camille
 * @version 1.0,2016-12-18 16:10:39
 */
public class Order {

	/** 订单ID */
	private String oid;
	
	/** 订单总价 */
	private Double total;
	
	/** 订单时间 */
	private Date ordertime;

	/** 订单状态(1:未付款;2:付款未发货;3:发货未收货;4:确认收货,订单完成) */
	private Integer state;
	
	/** 订单地址 */
	private String address;
	
	/** 订单所属用户 */
	private User user;
	
	/** 所包含订单项 */
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}





