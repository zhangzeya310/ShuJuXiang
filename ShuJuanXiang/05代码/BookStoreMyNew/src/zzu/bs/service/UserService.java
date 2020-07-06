package zzu.bs.service;

import zzu.bs.bean.User;

public interface UserService {
	
	//验证用户信息
	public User checkUser(String email,String password) throws Exception;
	
	//验证email信息是否存在，返回Boolean类型
	public boolean checkNickname(String email) throws Exception;
	
	//注册用户信息
	public boolean registerUser(String email,String nickname,String password,String address,String date)throws Exception;
	
	//修改用户信息
	public boolean reviseUser(String email,String nickname,String password,String address,String phone)throws Exception;
}
