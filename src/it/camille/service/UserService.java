package it.camille.service;

import it.camille.dao.UserDao;
import it.camille.domain.User;
import it.camille.utils.MailUtils;
import it.camille.utils.UUIDUtils;

/**
 * 用户业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 16:42:31
 */
public class UserService {

	/**
	 * 用户注册方法业务层类
	 * 
	 * @param user 需要注册的用户信息
	 */
	public void regist(User user) {
		// 对信息进行初始化封装
		user.setState(0);
		String uid = UUIDUtils.getUUID();
		user.setUid(uid);
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		
		// 调用持久层保存信息
		UserDao dao = new UserDao();
		dao.save(user);
		
		MailUtils.sendMail(user.getEmail(),code);
	}

	/**
	 * 业务层根据指定的邮箱和验证码查找用户
	 * 
	 * @param email 指定邮箱
	 * @param code 指定验证码
	 * @return User 查找到的用户
	 */
	public User findByEmailAndCode(String email, String code) {
		UserDao dao = new UserDao();
		return dao.findByEmailAndCode(email,code);
	}

	/**
	 * 业务层根据ID更新指定用户信息
	 * 
	 * @param user 指定用户信息
	 */
	public void update(User user) {
		UserDao dao = new UserDao();
		dao.update(user);
	}

	/**
	 * 业务层根据用户名查找用户信息
	 * 
	 * @param username 指定用户名
	 * @return User 查找到的用户信息
	 */
	public User checkUsername(String username) {
		UserDao dao = new UserDao();
		return dao.checkUsername(username);
	}

	/**
	 * 业务层检查用户登录信息方法
	 * 
	 * @param user 用户登录信息
	 * @return User 查询后对象
	 */
	public User login(User user) {
		UserDao dao = new UserDao();
		return dao.checkLogin(user);
	}

}
