package zzu.bs.dao;

import zzu.bs.bean.Admin;

import java.util.List;
import java.util.Map;

public interface AdminDao {

    public Admin getAdmin(String username, String password) throws Exception;

    public boolean register(String name, String email, String passwd) throws Exception;

    public List<Map<String,Object>> getAdmins(int start, int end) throws Exception;

    public int queryAdminCount() throws Exception;

    public boolean deleteAdminByUid(int uid) throws Exception;
}
