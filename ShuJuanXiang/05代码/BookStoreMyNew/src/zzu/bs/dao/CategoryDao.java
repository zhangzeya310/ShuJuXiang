package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.Category;

public interface CategoryDao {

	//查出所有一级目录
	public List<Category>queryAllCategory()throws Exception;
}
