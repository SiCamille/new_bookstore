package it.camille.service;

import java.util.List;
import it.camille.dao.AdminCategoryDao;
import it.camille.domain.Category;

/**
 * 分类管理业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-29 13:57:15
 */
public class AdminCategoryService {

	private AdminCategoryDao dao = new AdminCategoryDao();
	
	/**
	 * 业务层调用全部分类
	 * 
	 * @return List<Category> 全部分类集合
	 */
	public List<Category> findAllCategory() {
		return dao.findAllCategory();
	}

	/**
	 * 业务层删除分类
	 * 
	 * @param cid 需要删除的分类ID
	 */
	public void deleteCategory(String cid) {
		if(cid != null) {
			dao.delete(cid);
		}
	}

	/**
	 * 业务层根据ID查询分类
	 * 
	 * @param cid 分类ID
	 * @return null 无此对象 Category 分类对象
	 */
	public Category findByCid(String cid) {
		if(cid == null) {
			return null;
		}
		return dao.findByCid(cid);
	}

	/**
	 * 业务层修改分类
	 * 
	 * @param c 需要修改的分类实体
	 */
	public void update(Category c) {
		dao.update(c);
	}

	/**
	 * 业务层添加分类
	 * 
	 * @param c 需要添加的分类实体
	 */
	public void add(Category c) {
		dao.add(c);
	}
	
}
