package zzu.bs.dao.imp;

import zzu.bs.bean.User;
import zzu.bs.dao.BgUserDao;
import zzu.bs.utils.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/17
 * version v1.0
 **/

public class BgUserDaoImp implements BgUserDao {
    @Override
    public List<Map<String, Object>> getUsers(int start, int end) throws Exception {
        String sql2 = "select * from t_user limit ?,?";
        return JDBCUtils.queryForList(sql2,start, end);
    }

    @Override
    public int queryUserCount() throws Exception {
        String sql = "select count(*) from t_user";
        return JDBCUtils.queryForInt(sql);
    }

    @Override
    public boolean deleteUserByUid(Integer uid) throws Exception {
        String sql = "delete from t_user where uid=?";
        return JDBCUtils.update(sql,uid);
    }

    @Override
    public User getUserByUid(Integer uid) throws Exception {
        String sql = "select * from t_user where uid=?";
        List<User> list =  JDBCUtils.queryForList(sql, User.class, uid);
        return list.get(0);
    }

    @Override
    public boolean editUserByUid(Integer uid, String email, String nickname, String passwd, String status, String address) throws Exception {
        String sql = "update t_user set email=?,nickname=?,password=?,status=?,address=? where uid=?";
        System.out.println(sql);
        return JDBCUtils.update(sql,email, nickname, passwd,status,address,uid);
    }
}
