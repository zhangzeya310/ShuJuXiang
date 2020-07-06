package zzu.bs.service;

import java.util.List;

import zzu.bs.bean.Category;
import zzu.bs.bean.SecondCategory;
import zzu.bs.dao.CategoryDao;
import zzu.bs.dao.CategoryDaoImp;
import zzu.bs.dao.SecondCategoryDao;
import zzu.bs.dao.SecondCategoryDaoImp;
import zzu.bs.utils.BeanFactory;

public class TCategoryServiceImp implements TCategoryService{

	private CategoryDao categoryDao = BeanFactory.getBean(CategoryDaoImp.class);
	private SecondCategoryDao secondCategoryDao = BeanFactory.getBean(SecondCategoryDaoImp.class);
	
	@Override
	public List<Category> queryAllTCategory() throws Exception {
		List<Category> category = categoryDao.queryAllCategory();
		for(Category ca:category){
			Integer categoryId = ca.getCategoryId();
			List<SecondCategory> secondCategory = secondCategoryDao.querySecondCategoryByCategoryId(categoryId);
			ca.setSecondCategory(secondCategory);
		}
		return category;
	}

}
