package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.RoleDao;
import cn.xxs.entity.Role;

@Service
public class RoleService{
	@Autowired
	private RoleDao roleDao;

	public List<Role> search(Map<String, Object> condition)
	{
		return roleDao.searchByCondition(condition);
	}

	public List<Role> queryRolesByUserId(String userId) {
		return roleDao.queryRolesByUserId(userId);
	}
	
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}
}
