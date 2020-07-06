package zzu.bs.service.imp;

import zzu.bs.bean.User;
import zzu.bs.dao.BgUserDao;
import zzu.bs.dao.imp.BgUserDaoImp;
import zzu.bs.service.BgUserService;
import zzu.bs.utils.BeanFactory;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/17
 * version v1.0
 **/

public class BgUserServiceImp implements BgUserService {
    private BgUserDao userDao = BeanFactory.getBean(BgUserDaoImp.class);

    public BgUserServiceImp() {
    }

    @Override
    public List<Map<String, Object>> getUsers(int start, int end) throws Exception {
        return userDao.getUsers(start, end);
    }

    @Override
    public int queryUserCount() throws Exception {
        return userDao.queryUserCount();
    }

    @Override
    public boolean deleteUserByUid(Integer uid) throws Exception {
        return userDao.deleteUserByUid(uid);
    }

    @Override
    public User getUserByUid(Integer uid) throws Exception {
        return userDao.getUserByUid(uid);
    }

    @Override
    public boolean editUserByUid(Integer uid, String email, String nickname, String passwd, String status, String address) throws Exception {
        return userDao.editUserByUid(uid, email, nickname, passwd, status, address);
    }
}
