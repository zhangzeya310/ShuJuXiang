package zzu.bs.service;

import zzu.bs.bean.User;

import java.util.List;
import java.util.Map;

public interface BgUserService {

    public List<Map<String, Object>> getUsers(int start, int end) throws Exception;

    public int queryUserCount() throws Exception;

    public boolean deleteUserByUid(Integer uid) throws Exception;

    public User getUserByUid(Integer uid) throws Exception;

    public boolean editUserByUid(Integer uid, String email, String nickname, String passwd, String status, String address) throws Exception;
}
