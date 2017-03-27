package it.camille.domain;

/**
 * 图书实体类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:46:19
 */
public class Book {
	
	/** 图书ID */
	private String bid;
	
	/** 图书名称 */
	private String bname;
	
	/** 图书单价 */
	private double price;
	
	/** 图书作者 */
	private String author;
	
	/** 图书图片 */
	private String image;
	
	/** 订单ID */
	private String cid;
	
	/** 图书状态(0:有货;1:缺货) */
	private int isdel;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	
}
