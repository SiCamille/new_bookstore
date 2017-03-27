package it.camille.domain;

/**
 * 分类实体类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:24:59
 */
public class Category {

	/** 分类ID */
	private String cid;
	
	/** 分类名称 */
	private String cname;
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
