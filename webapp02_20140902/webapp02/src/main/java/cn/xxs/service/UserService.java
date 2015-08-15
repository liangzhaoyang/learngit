package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.R_User_RoleDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.dao.UserDao;
import cn.xxs.entity.User;
import cn.xxs.entity.UserCondition;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	R_User_RoleDao userRoleDao;

	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	public User selectForLogin(Map<String, Object> map) {
		return userDao.selectForLogin(map);
	}

	public void modifyPassword(User user) {
		userDao.modifyPassword(user);
	}

	public void resetPasswordForUsers(List<String> ids, String password) {
		User user = new User();
		user.setPassword(password);
		for(String id : ids) {
			user.setId(id);
			userDao.modifyPassword(user);
		}
	}
	// public List<String> queryRolesByUserId(String userId) {
	// return userDao.queryRolesByUserId(userId);
	// }

	public List<Map<String, Object>> queryUsersPage(UserCondition map) {
		return userDao.queryUsersPage(map);
	}

	public void deleteUserById(String id) {
		userDao.deleteUserById(id);
	}
	
	public void deleteUsers(List<String> userIds) {
		for(String id : userIds) {
			userDao.deleteUserById(id);
			userRoleDao.deleteRoleByUserId(id);
		}
	}

	public void updateUser(User newUser) {
		userDao.updateUser(newUser);

	}

	// 授权查询
	public List<Map<String, Object>> selectUsersByConditionPage(UserCondition condition) {
		return userDao.selectUsersByConditionPage(condition);
	}

	// 授权
	public void userGrant(List<String> ids) {
		for (String id : ids) {
			userDao.userGrant(id);
		}
	}

	// 取消授权
	public void cancelGrant(List<String> ids) {
		for (String id : ids) {
			userDao.cancelGrant(id);

		}
	}
	
	public User getUserById(String userId) {
		return userDao.getUserById(userId);
	}
}
