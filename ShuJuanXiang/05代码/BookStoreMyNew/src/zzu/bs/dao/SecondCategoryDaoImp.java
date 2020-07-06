package zzu.bs.dao;

import java.util.List;

import zzu.bs.bean.SecondCategory;
import zzu.bs.utils.JDBCUtils;

public class SecondCategoryDaoImp implements SecondCategoryDao {

	@Override
	public List<SecondCategory> querySecondCategoryByCategoryId(Integer categoryId) throws Exception {
		String sql = "select * from t_second_category where categoryid=?";
		return JDBCUtils.queryForList(sql, SecondCategory.class, categoryId);
	}

}
