package zzu.bs.dao.imp;

import zzu.bs.bean.Admin;
import zzu.bs.dao.AdminDao;
import zzu.bs.utils.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 * author John
 * date 2017/9/15
 * version v1.0
 **/

public class AdminDaoImp implements AdminDao {
    @Override
    public Admin getAdmin(String username, String password) throws Exception {
        String sql = "SELECT * FROM t_admin WHERE name = ? AND password = ?";
        Admin admin = JDBCUtils.queryForObject(sql, Admin.class, username, password);
        return admin;
    }

    @Override
    public boolean register(String name, String email, String passwd) throws Exception {
        String sql = "insert into t_admin(name,email,password) values(?,?,?)";
        return JDBCUtils.update(sql, name, email, passwd);
    }

    @Override
    public List<Map<String,Object>> getAdmins(int start, int end) throws Exception {
        String sql2 = "select * from t_admin limit ?,?";
        return JDBCUtils.queryForList(sql2,start, end);
    }

    @Override
    public int queryAdminCount() throws Exception {
        String sql = "select count(*) from t_admin";
        return JDBCUtils.queryForInt(sql);
    }

    @Override
    public boolean deleteAdminByUid(int uid) throws Exception {
        String sql = "delete from t_admin where adminid=?";
        return JDBCUtils.update(sql, uid);
    }
}
