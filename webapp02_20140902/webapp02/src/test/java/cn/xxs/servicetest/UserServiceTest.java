package cn.xxs.servicetest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.User;
import cn.xxs.service.UserService;
import cn.xxs.utility.MD5;

public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userService");
	}
	
	@Test
	public void insertUser(){
		User user = new User();
		user.setId("zhangsan");
		user.setName("张三");
		user.setOrgid(3);
		user.setPassword(MD5.md5("111111"));
		user.setStatus(1);
		user.setFirsttime(true);
		user.setDeleteflag(false);
		userService.insertUser(user);
	}
	@Test
	public void selectForLogin(){
		Map<String,Object> userInfo = new HashMap<String,Object>();
		userInfo.put("id", "zhangsan");
		userInfo.put("password", MD5.md5("111111"));
		userService.selectForLogin(userInfo);
	}
	
	@Test
	public void modifyPassword(){
		User user = new User();
		user.setId("jiu");
		user.setPassword(MD5.md5("333333"));
		user.setFirsttime(false);
		userService.modifyPassword(user);
	}
	
//	@Test
//	public void queryRoleIdByUserId(){
//		userService.queryRoleIdByUserId("zhangsan");
//	}
	
//	@Test
//	public void queryUsers(){
//		Map<String ,Object> condition = new HashMap<String,Object>();
//		condition.put("id", "zhangsan");
//		List<Map<String,Object>> list = userService.queryUsers(condition);
//		for (int i = 0; i < list.size(); i++) {
//			Map<String,Object> userInfo = list.get(i);
//			for (int j = 0; j < userInfo.entrySet().size(); j++) {
//				
//			}
// 		}
// 		userService.queryRoleIdByUserId("zhangsan");
//	}
	
	@Test
	public void updateUser(){
		User newUser = new User();
		newUser.setId("zhaoliu");
		newUser.setName("赵");
		newUser.setOrgid(new Integer(2));
		newUser.setStatus(new Integer(0));
		userService.updateUser(newUser);
	}
	
	@Test
	public void deleteUserById() {
		User user = new User();
		user.setId("lisi");
		userService.deleteUserById(user.getId());
	}
}

