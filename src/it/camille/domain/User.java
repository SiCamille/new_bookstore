package it.camille.domain;

/**
 * 客户实体类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:35:43
 */
public class User {

	/** 客户ID */
	private String uid;
	
	/** 客户用户名 */
	private String username;
	
	/** 客户密码 */
	private String password;
	
	/** 客户邮箱 */
	private String email;
	
	/** 客户状态 */
	private int state;
	
	/** 客户验证码 */
	private String code;
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
}
