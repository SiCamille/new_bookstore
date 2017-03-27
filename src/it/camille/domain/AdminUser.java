package it.camille.domain;

/**
 * 管理员实体类
 * 
 * @author Camille
 * @version 1.0,2016-12-20 16:00:08
 */
public class AdminUser {

	/** 管理员ID */
	private String aid;
	
	/** 管理员账号 */
	private String username;
	
	/** 管理员密码 */
	private String password;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
