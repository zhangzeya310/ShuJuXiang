package zzu.bs.dao;

import zzu.bs.bean.User;
import zzu.bs.utils.JDBCUtils;

public class UserDaoImp implements UserDao{

	@Override
	public User checkUser(String email, String password) throws Exception {
		String sql = "SELECT * from t_user where email=? AND password=?";
		return JDBCUtils.queryForObject(sql,User.class,email,password);
	}

	@Override
	public boolean checkNickname(String email) throws Exception {
		String sql = "SELECT * from t_user where email=?";
		User user =  JDBCUtils.queryForObject(sql,User.class,email);
		if(user==null) {
			return false;
		}
		else return true;
	}

	@Override
	public boolean registerUser(String email, String nickname, String password,String address,String date)
			throws Exception {
		String sql = "INSERT INTO t_user(email,nickname,password,address,registerdate) VALUES (?,?,?,?,?)";
		return JDBCUtils.update(sql,email,nickname,password,address,date);
	}

	@Override
	public boolean reviseUser(String email, String nickname, String password, String address, String phone)
			throws Exception {
		String sql = "update t_user set nickname=?,password=?,address=?,phone=? where email=?";
		return JDBCUtils.update(sql,nickname,password,address,phone,email);
	}
	

}
