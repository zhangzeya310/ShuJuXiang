package zzu.bs.service;

import zzu.bs.bean.User;
import zzu.bs.dao.UserDao;
import zzu.bs.dao.UserDaoImp;

public class UserServiceImp implements UserService{
	
	private UserDao userDao = (UserDao) new UserDaoImp();
	
	@Override
	public User checkUser(String email, String password) throws Exception {
		return userDao.checkUser(email,password);
	}

	@Override
	public boolean checkNickname(String email) throws Exception {
		return userDao.checkNickname(email);
	}

	@Override
	public boolean registerUser(String email, String nickname, String password,String address,String date)
			throws Exception {
		return userDao.registerUser(email,nickname,password,address,date);
	}

	@Override
	public boolean reviseUser(String email, String nickname, String password, String address, String phone)
			throws Exception {
		return userDao.reviseUser(email,nickname,password,address,phone);
	}

}
