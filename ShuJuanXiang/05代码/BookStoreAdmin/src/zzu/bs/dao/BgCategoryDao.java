package zzu.bs.dao;

import zzu.bs.bean.Category;
import zzu.bs.bean.SecondCategory;

import java.util.List;
import java.util.Map;

public interface BgCategoryDao {

    List<Category> listAllCategory() throws Exception;

    List<SecondCategory> listAllSecondCategoryById(Integer id) throws Exception;

    List<SecondCategory> listAllSecondCategories() throws Exception;

    boolean addCategory(Category category) throws  Exception;

    boolean addSecondCategory(SecondCategory s) throws Exception;

    boolean editCategory(Category category) throws Exception;

    boolean editSecondCategory(SecondCategory secondCategory) throws Exception;

    boolean deleteCategoryByid(Integer id) throws Exception;

    boolean deleteSecondCategoryByid(Integer id) throws Exception;

}
