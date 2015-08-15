package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.Role;

public interface RoleDao {
	public List<Role> searchByCondition(Map<String, Object> condition);
	public List<Role> queryRolesByUserId(String userId);
	public List<Role> getAllRoles();
}
