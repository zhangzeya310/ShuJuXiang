package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.Category;
import zzu.bs.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao{

	@Override
	public List<Category> queryAllCategory() throws Exception {
		String sql = "select * from t_category";
		return JDBCUtils.queryForList(sql, Category.class);
	}

}
