package it.camille.service;

import java.util.List;
import it.camille.dao.CategoryDao;
import it.camille.domain.Category;

/**
 * 分类业务类
 * 
 * @author Camille
 * @version 1.0,2016-12-16 20:27:37
 */
public class CategoryService {

	/**
	 * 业务层查找全部分类
	 * 
	 * @return List<Category> 全部分类集合
	 */
	public List<Category> findAllCategory() {
		CategoryDao dao = new CategoryDao();
		return dao.get();
	}

}
