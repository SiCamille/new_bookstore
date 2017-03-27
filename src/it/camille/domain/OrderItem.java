package it.camille.domain;

/**
 * 订单项实体
 * 
 * @author Camille
 * @version 1.0,2016-12-18 16:18:20
 */
public class OrderItem {

	/** 订单项ID */
	private String iid;
	
	/** 订单项数量 */
	private Integer count;
	
	/** 订单项小计 */
	private Double subtotal;
	
	/** 所对应图书 */
	private Book book;
	
	/** 所对应订单 */
	private Order order;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
