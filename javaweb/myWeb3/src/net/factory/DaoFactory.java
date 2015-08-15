package net.factory;

import net.dao.UserDao;
import net.service.impl.UserDaoProxy;

public class DaoFactory {

	public static UserDao getUserDaoInstance(){
		return new UserDaoProxy();//返回代理实例
	}
}
