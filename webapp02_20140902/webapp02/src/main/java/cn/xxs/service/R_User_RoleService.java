package cn.xxs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.R_User_RoleDao;
import cn.xxs.entity.R_User_Role;
@Service
public class R_User_RoleService {

	@Autowired
	private R_User_RoleDao r_user_roledao;
	
	public void insertR_User_Role(R_User_Role r_user_role) {
		r_user_roledao.insertR_User_Role(r_user_role);
	}

	public void deleteRolesForUserId(String userId) {
		r_user_roledao.deleteRoleByUserId(userId);
	}
	
	public void insertRolesForUserid(String userId, List<Integer> roles) {
		R_User_Role userRole = new R_User_Role();
		userRole.setUserid(userId);
		for(Integer roleId : roles) {
			userRole.setRoleId(roleId);
			r_user_roledao.insertR_User_Role(userRole);
		}
	}
	
	public void modifyRolesForUserId(String userId, List<Integer> roles) {
		deleteRolesForUserId(userId);
		insertRolesForUserid(userId, roles);
	}
}
