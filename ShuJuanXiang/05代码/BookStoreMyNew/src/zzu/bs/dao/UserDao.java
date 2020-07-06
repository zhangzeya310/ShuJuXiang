package zzu.bs.dao;

import zzu.bs.bean.User;

public interface UserDao {

	//验证用户是否存在，登录用
	public User checkUser(String email, String password) throws Exception;

	//验证邮箱是否已存在，注册用
	public boolean checkNickname(String email) throws Exception;

	//注册
	public boolean registerUser(String email, String nickname, String password,String address,String date)throws Exception;

	//修改个人信息
	public boolean reviseUser(String email, String nickname, String password, String address, String phone)throws Exception;

}
