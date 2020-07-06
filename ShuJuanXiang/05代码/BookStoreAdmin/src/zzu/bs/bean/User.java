package zzu.bs.bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer uid;
	private String email;
	private String nickname;
	private String password;
	private String registerdate;
	private String registertype;
	private String status;
	private String token;
	private String address;

	public User() {
		super();
	}

	public User(String email, String nickname, String password, String registerdate, String registertype, String status,
			String token, String address) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.registerdate = registerdate;
		this.registertype = registertype;
		this.status = status;
		this.token = token;
		this.address = address;
	}

	public User(Integer uid, String email, String nickname, String password, String registerdate, String registertype,
			String status, String token, String address) {
		super();
		this.uid = uid;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.registerdate = registerdate;
		this.registertype = registertype;
		this.status = status;
		this.token = token;
		this.address = address;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}

	public String getRegistertype() {
		return registertype;
	}

	public void setRegistertype(String registertype) {
		this.registertype = registertype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", email='" + email + '\'' +
				", nickname='" + nickname + '\'' +
				", password='" + password + '\'' +
				", registerdate='" + registerdate + '\'' +
				", registertype='" + registertype + '\'' +
				", status='" + status + '\'' +
				", token='" + token + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
