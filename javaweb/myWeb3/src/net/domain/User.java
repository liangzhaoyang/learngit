package net.domain;

public class User {
	
	private int userid;
	private String username;
	private String password;
	private String permission;
	private String email;
	private String iphone;

	public User(int userid, String username, String password,
			String permission, String email, String iphone) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.permission = permission;
		this.email = email;
		this.iphone = iphone;
	}
	
	public User() {
		super();
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String premission) {
		this.permission = premission;
	}
	public String getEmail() {
		return email;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
