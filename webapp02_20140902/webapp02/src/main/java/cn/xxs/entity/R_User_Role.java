package cn.xxs.entity;

/**
 * r_user_role 实体类 Fri Aug 01 13:57:24 CST 2014 leo
 */

public class R_User_Role {
	private String userId;
	private int roleId;

	public void setUserid(String userid) {
		this.userId = userid;
	}

	public String getUserid() {
		return userId;
	}

	public void setRoleId(int roles) {
		this.roleId = roles;
	}

	public int getRoleId() {
		return roleId;
	}
}
