package it.camille.service;

import it.camille.dao.AdminDao;
import it.camille.domain.AdminUser;

/**
 * 管理员业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-29 11:32:18
 */
public class AdminUserService {
	
	/** 获取数据交互层对象 */
	private AdminDao dao = new AdminDao();

	/**
	 * 业务层检查管理员信息
	 * 
	 * @param user 输入的管理员实体
	 * @return　AdminUser 获取的数据库实体
	 */
	public AdminUser login(AdminUser user) {
		return dao.login(user);
	}

}
