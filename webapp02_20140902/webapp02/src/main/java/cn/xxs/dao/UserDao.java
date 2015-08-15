package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.User;
import cn.xxs.entity.UserCondition;


public interface UserDao {

	public int insertUser(User user);
	public User selectForLogin(Map<String,Object> map);
	public void modifyPassword(User user);
	public void setFirstTimeFlag(String userId);
	public List<Map<String,Object>> queryUsersPage(UserCondition condition);
	public void deleteUserById(String id);
	public void updateUser(User newUser);
//	public List<String> queryRolesByUserId(String userId);
//	授权查询
	public List<Map<String, Object>> selectUsersByConditionPage(UserCondition condition);
//	授权
	public void userGrant(String id);
//	取消授权
	public void cancelGrant(String id);
	
	public User getUserById(String userId);
}
