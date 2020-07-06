package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.SecondCategory;

public interface SecondCategoryDao {

	//根据一级目录id查出对应的所有二级目录
	public List<SecondCategory> querySecondCategoryByCategoryId(Integer categoryId) throws Exception;
}
