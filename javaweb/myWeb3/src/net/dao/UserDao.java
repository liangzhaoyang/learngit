package net.dao;

import java.util.List;

import net.domain.User;

public interface UserDao {

//	void add(User user);

//	public User find(String username, String password);
	public boolean findUser(String username,String password);
	public List<User> getUserList();
	public User GetUserInfoById(int id);
	public User GetUserByName_psw(String name,String password);
	public int UpdateUser(User user);
	public int AddUser(User user);
	public int DeleteUserById(String id);
	public String CheckUserByName(String name);
	public String CheckByEmail(String email);
	public String CheckByName_Id(int id,String name);
	public String CheckByEmail_Id(int id,String email);
	public int UpdatePSW(int id,String password);
}