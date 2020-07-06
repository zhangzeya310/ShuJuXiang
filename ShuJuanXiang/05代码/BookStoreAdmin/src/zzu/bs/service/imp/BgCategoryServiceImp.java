package zzu.bs.service.imp;

import zzu.bs.bean.Category;
import zzu.bs.bean.SecondCategory;
import zzu.bs.dao.BgCategoryDao;
import zzu.bs.dao.imp.BgCategoryDaoImp;
import zzu.bs.service.BgCategoryService;
import zzu.bs.utils.BeanFactory;

import java.util.List;

/**
 * author John
 * date 2017/9/21
 * version v1.0
 **/

public class BgCategoryServiceImp implements BgCategoryService {
    private BgCategoryDao bgCategoryDao = BeanFactory.getBean(BgCategoryDaoImp.class);

    @Override
    public List<Category> listAllCategory() throws Exception {
        return bgCategoryDao.listAllCategory();
    }

    @Override
    public List<SecondCategory> listAllSecondCategoryById(Integer id) throws Exception {
        return bgCategoryDao.listAllSecondCategoryById(id);
    }

    @Override
    public List<SecondCategory> listAllSecondCategoris() throws Exception {
        return bgCategoryDao.listAllSecondCategories();
    }

    @Override
    public boolean addCategory(Category category) throws Exception {
        return bgCategoryDao.addCategory(category);
    }

    @Override
    public boolean addSecondCategory(SecondCategory s) throws Exception {
        return bgCategoryDao.addSecondCategory(s);
    }

    @Override
    public boolean editCategory(Category category) throws Exception {
        return bgCategoryDao.editCategory(category);
    }

    @Override
    public boolean editSecondCategory(SecondCategory secondCategory) throws Exception {
        return bgCategoryDao.editSecondCategory(secondCategory);
    }

    @Override
    public boolean deleteCategoryByid(Integer id) throws Exception {
        return bgCategoryDao.deleteCategoryByid(id);
    }

    @Override
    public boolean deleteSecondCategoryByid(Integer id) throws Exception {
        return bgCategoryDao.deleteSecondCategoryByid(id);
    }
}
