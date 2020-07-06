package zzu.bs.service.imp;

import zzu.bs.bean.Admin;
import zzu.bs.dao.AdminDao;
import zzu.bs.dao.imp.AdminDaoImp;
import zzu.bs.service.AdminService;
import zzu.bs.utils.BeanFactory;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/15
 * version v1.0
 **/

public class AdminServiceImp implements AdminService {

    private AdminDao adminDao = BeanFactory.getBean(AdminDaoImp.class);

    @Override
    public Admin getAdmin(String username, String password) throws Exception {
        return adminDao.getAdmin(username, password);
    }

    @Override
    public boolean register(String name, String email, String password) throws Exception {
        return adminDao.register(name, email, password);
    }

    @Override
    public List<Map<String,Object>> getAdmins(int start, int end) throws Exception {
        return adminDao.getAdmins(start, end);
    }

    @Override
    public int queryAdminCount() throws Exception {
        return adminDao.queryAdminCount();
    }

    @Override
    public boolean deleteAdminByUid(int uid) throws Exception {
        return adminDao.deleteAdminByUid(uid);
    }
}
