//package net.service.impl;
//
//import net.dao.UserDao;
//import net.dao.impl.UserDaoImpl;
//import net.domain.User;
//
////对web层提供所有的业务服务
//public class BusinessServiceImpl {
//
//	private UserDao dao=new UserDaoImpl();//工厂模式 spring修改
//	
//	//对web层提供登陆服务
//	public User login(String username,String password){
//		
//		return dao.find(username, password);
//
//	}
//}
