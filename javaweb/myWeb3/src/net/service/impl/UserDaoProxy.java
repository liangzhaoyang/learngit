package net.service.impl;

import java.sql.Connection;
import java.util.List;

import net.dao.UserDao;
import net.dao.impl.UserDaoImpl;
import net.domain.User;
import net.utils.JdbcUtils;

public class UserDaoProxy implements UserDao {

	//private JdbcUtils dbc=null;
	private UserDao dao=null;
	private Connection conn=null;
	public UserDaoProxy(){
		
		this.conn=JdbcUtils.getConnection();
		this.dao=new UserDaoImpl(conn);
	}
	@Override
	public boolean findUser(String username,String password) {
		boolean flag=false;
		try{
			flag=dao.findUser(username,password);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.close(conn);
		}
		return flag;
	}

	@Override
	public List<User> getUserList() {
		
			return dao.getUserList();
	}
	@Override
	public User GetUserInfoById(int id) {
		// TODO Auto-generated method stub
		return dao.GetUserInfoById(id);
	}
	@Override
	public int UpdateUser(User user) {
		// TODO Auto-generated method stub
		return dao.UpdateUser(user);
	}
	@Override
	public int DeleteUserById(String id) {
		// TODO Auto-generated method stub
		return dao.DeleteUserById(id);
	}
	@Override
	public String CheckUserByName(String name) {
		// TODO Auto-generated method stub
		return dao.CheckUserByName(name);
	}
	@Override
	public String CheckByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.CheckByEmail(email);
	}
	@Override
	public int AddUser(User user) {
		// TODO Auto-generated method stub
		return dao.AddUser(user);
	}
	@Override
	public String CheckByName_Id(int id, String name) {
		// TODO Auto-generated method stub
		return dao.CheckByName_Id(id, name);
	}
	@Override
	public String CheckByEmail_Id(int id, String email) {
		// TODO Auto-generated method stub
		return dao.CheckByEmail_Id(id, email);
	}
	@Override
	public User GetUserByName_psw(String name, String password) {
		// TODO Auto-generated method stub
		return dao.GetUserByName_psw(name, password);
	}
	@Override
	public int UpdatePSW(int id, String password) {
		// TODO Auto-generated method stub
		return dao.UpdatePSW(id, password);
	}
	

}
