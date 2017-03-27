package it.camille.domain;

/**
 * 购物项实体
 * 
 * @author Camille
 * @version 1.0,2016-12-17 8:38:35
 */
public class CartItem {

	/** 购物图书 */
	private Book book;
	
	/** 购物数量 */
	private int count;
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/** 购物小计 */
	public double getSubtotal() {
		return book.getPrice() * count;
	}
	
}
