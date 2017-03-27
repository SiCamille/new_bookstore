package it.camille.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车实体
 * 
 * @author Camille
 * @version 1.0,2016-12-17 8:40:34
 */
public class Cart {

	/** 购物车集合 */
	private Map<String,CartItem> cart = new LinkedHashMap<String, CartItem>();
	
	/** 购物总价 */
	private double total;

	/**
	 * 获取全部购物项的方法
	 * 
	 * @return Collection<CartItem> 购物项集合
	 */
	public Collection<CartItem> getCartItems() {
		return cart.values();
	}
	
	public Map<String, CartItem> getMap() {
		return cart;
	}

	public double getTotal() {
		return total;
	}
	
	/**
	 * 添加到购物车的方法
	 * 
	 * @param cartItem 需要添加的购物项
	 */
	public void addCart(CartItem cartItem) {
		// 判断购物项是否包含在购物车中
		String bid = cartItem.getBook().getBid();
		if(cart.containsKey(bid)) {
			
			// 如果包含 则数量添加
			CartItem _cartItem = cart.get(bid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			
			// 如果不包含 则直接加入购物车
			cart.put(bid, cartItem);
		}
		
		// 将合计增加
		total += cartItem.getSubtotal();
	}
	
	/**
	 * 将购物项从购物车中移除
	 * 
	 * @param bid 需要移除的图书ID
	 */
	public void removeCart(String bid) {
		// 调用移除方法
		CartItem cartItem = cart.remove(bid);
		
		// 合计减少
		total -= cartItem.getSubtotal();
	}
	
	/**
	 * 将购物车清空
	 */
	public void clearCart() {
		cart.clear();
		total = 0d;
	}
	
}
