/**
 *@author ZengJia
 */
package cn.xxs.servicetest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.R_User_Role;
import cn.xxs.service.R_User_RoleService;


public class R_User_RoleServiceTest {
	@Autowired
	private R_User_RoleService r_user_roleService;
	
	@SuppressWarnings("resource")
	@Before
    public void before(){                                                                   
        //@SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});//,"applicationContext.xml"
        r_user_roleService=(R_User_RoleService)context.getBean("r_User_RoleService");
    }
	
	@Test
	public void insertRole() {
		R_User_Role r_user_role = new R_User_Role();
		r_user_role.setUserid("jia");
		r_user_role.setRoleId(5);
		r_user_roleService.insertR_User_Role(r_user_role);
	}
}
