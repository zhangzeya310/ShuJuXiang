package zzu.bs.service;

import java.util.List;

import zzu.bs.bean.Category;

public interface TCategoryService {

	//查出两级目录
	public List<Category> queryAllTCategory()throws Exception;
}
