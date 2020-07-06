package zzu.bs.dao.imp;

import zzu.bs.bean.Category;
import zzu.bs.bean.SecondCategory;
import zzu.bs.dao.BgCategoryDao;
import zzu.bs.utils.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/21
 * version v1.0
 **/

public class BgCategoryDaoImp implements BgCategoryDao {
    @Override
    public List<Category> listAllCategory() throws Exception {
        String sql = "select * from t_category ";
        return JDBCUtils.queryForList(sql, Category.class);
    }

    @Override
    public List<SecondCategory> listAllSecondCategoryById(Integer id) throws Exception {
        String sql = "select * from t_second_category where categoryid=?";
        return JDBCUtils.queryForList(sql, SecondCategory.class, id);
    }

    @Override
    public List<SecondCategory> listAllSecondCategories() throws Exception {
        String sql = "select * from t_second_category ";
        return JDBCUtils.queryForList(sql,SecondCategory.class);
    }

    @Override
    public boolean addCategory(Category c) throws Exception {
        String sql = "insert into t_category(categoryname,categorydesc) values (?,?) ";
        return JDBCUtils.update(sql, c.getCategoryName(), c.getCategoryDesc());
    }

    @Override
    public boolean addSecondCategory(SecondCategory s) throws Exception {
        String sql = "insert into t_second_category(secondcategoryname,categoryid) values (?,?) ";
        return JDBCUtils.update(sql, s.getSecondCategoryName(),s.getCategoryId());
    }

    @Override
    public boolean editCategory(Category c) throws Exception {
        String sql = "update t_category set categoryname=?,categorydesc=? where categoryid=?";
        return JDBCUtils.update(sql, c.getCategoryName(),c.getCategoryDesc(), c.getCategoryId());
    }

    @Override
    public boolean editSecondCategory(SecondCategory s) throws Exception {
        String sql = "update t_second_category set secondcategoryname=?,categoryid=? where secondcategoryid=?";
        return JDBCUtils.update(sql, s.getSecondCategoryName(),s.getCategoryId(),s.getSecondCategoryId());
    }

    @Override
    public boolean deleteCategoryByid(Integer id) throws Exception {
        String sql = "delete from t_category where cateforyid=?";
        return JDBCUtils.update(sql, id);
    }

    @Override
    public boolean deleteSecondCategoryByid(Integer id) throws Exception {
        String sql = "delete from t_second_category where secondcategoryid=?";
        return JDBCUtils.update(sql, id);
    }
}
