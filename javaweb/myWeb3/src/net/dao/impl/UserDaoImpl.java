package net.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.dao.UserDao;
import net.domain.User;
import net.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	 private Connection conn=null;
	 private ResultSet rs=null;
	 private PreparedStatement ps=null;
	 public UserDaoImpl(Connection conn){
		 this.conn=conn;
	 }
	 public boolean findUser(String username,String password) {
		 boolean flag=false;
	  try {
			  String sql = "select * from admin where username=? and password=?";
			  ps = conn.prepareStatement(sql);
			  ps.setString(1, username);
			  ps.setString(2, password);
			  rs = ps.executeQuery();
			  if(rs.next()){
				  flag=true;
			  }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		  return flag;
	 }
	@Override
	public List<User> getUserList() {
		 try {
			  String sql = "select * from admin";
			  ps = conn.prepareStatement(sql);
			  rs = ps.executeQuery();
			  ArrayList<User> list = new ArrayList<User>();
			  while(rs.next()){
				  User user=new User(rs.getInt("userid"),rs.getString("username"),rs.getString("password"),rs.getString("permission"),rs.getString("email"),rs.getString("iphone"));
				  list.add(user);
			  }
			  return list;
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return null;
	}
	@Override
	public User GetUserInfoById(int id) {

		User user=new User();
		try {
			  String sql = "select * from admin where userid="+id;
			  ps = conn.prepareStatement(sql);
			  rs = ps.executeQuery();
			  if(rs.next()){ 
				  user=new User(id,rs.getString("username"),rs.getString("password"),rs.getString("permission"),rs.getString("email"),rs.getString("iphone"));
			  }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return user;
	}
	
	public int AddUser(User user){
		int row=0;
		try {
			String sql="insert into admin(username,password,permission,email,iphone) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getPermission());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getIphone());
			row=ps.executeUpdate();
			//System.out.println(row);
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return row;
	}
	
	public int UpdateUser(User user){
		int row=0;
		try {
			String sql="Update admin set username=?,password=?,permission=?,email=?,iphone=? where userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getPermission());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getIphone());
			ps.setInt(6, user.getUserid());
			row=ps.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return row;
	}
	@Override
	public int DeleteUserById(String id) {
		int row = 0;
		String sql = "delete from admin where userid=" + id;
		try {
			ps = conn.prepareStatement(sql);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			   e.printStackTrace();
		} finally {
			 JdbcUtils.close(rs);
			 JdbcUtils.close(ps);
			 JdbcUtils.close(conn);
		}
		return row;
	}
	 
	
	@Override
	public String CheckUserByName(String username) {
		String str = "no";
		String sql = "select username from admin where username=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				str="yes";
				//System.out.println(rs.getString("username"));
			}
		} catch (SQLException e) {
			   e.printStackTrace();
		} finally {
			 JdbcUtils.close(rs);
			 JdbcUtils.close(ps);
			 JdbcUtils.close(conn);
		}
		return str;
	}

	@Override
	public String CheckByEmail(String email) {
		String str = "no";
		String sql = "select email from admin where email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()){
				str="yes";
				//System.out.println(rs.getString("email"));
			}
		} catch (SQLException e) {
			   e.printStackTrace();
		} finally {
			 JdbcUtils.close(rs);
			 JdbcUtils.close(ps);
			 JdbcUtils.close(conn);
		}
		return str;
	}
	@Override
	public String CheckByName_Id(int id, String name) {
		String str = "no";
		String sql = "select userid from admin where username=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("userid")!=id){
					str="yes";
				}
			}
		} catch (SQLException e) {
			   e.printStackTrace();
		} finally {
			 JdbcUtils.close(rs);
			 JdbcUtils.close(ps);
			 JdbcUtils.close(conn);
		}
		return str;
	}
	@Override
	public String CheckByEmail_Id(int id, String email) {
		String str = "no";
		String sql = "select userid from admin where email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("userid")!=id){
					str="yes";
				}
			}
		} catch (SQLException e) {
			   e.printStackTrace();
		} finally {
			 JdbcUtils.close(rs);
			 JdbcUtils.close(ps);
			 JdbcUtils.close(conn);
		}
		return str;
	}
	@Override
	public User GetUserByName_psw(String name, String password) {
		//System.out.println(name+"--"+password);
		User user=new User();
		try {
			  String sql = "select * from admin where username=? and password=?";
			  ps = conn.prepareStatement(sql);
			  ps.setString(1, name);
			  ps.setString(2, password);
			  rs = ps.executeQuery();
			  while(rs.next()){

				  user=new User(rs.getInt("userid"),name,password,rs.getString("permission"),rs.getString("email"),rs.getString("iphone"));
			  }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return user;
	}
	@Override
	public int UpdatePSW(int id, String password) {
		
		int row=0;
		try {
			String sql="Update admin set password=? where userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, id);
			row=ps.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   JdbcUtils.close(rs);
		   JdbcUtils.close(ps);
		   JdbcUtils.close(conn);
		  }
		return row;
	}
}
