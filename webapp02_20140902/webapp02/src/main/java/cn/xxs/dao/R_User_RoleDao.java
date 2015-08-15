package cn.xxs.dao;

import cn.xxs.entity.R_User_Role;

public interface R_User_RoleDao {

	void insertR_User_Role(R_User_Role r_user_role);
	void deleteRoleByUserId(String userId);
}
