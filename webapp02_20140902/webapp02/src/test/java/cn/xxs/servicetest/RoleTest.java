package cn.xxs.servicetest;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.service.PersonService;
import cn.xxs.service.RoleService;

public class RoleTest {
private RoleService roleService;
private PersonService personService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		roleService = (RoleService) context.getBean("roleService");
		personService = (PersonService) context.getBean("personService");
	}
	
//	@Test
//	public void addUser(){
//		Role role = new Role();
//		role.setId(1);
//		role.setName("你好");
//		role.setDescription("######");
//		roleService.insert(role);
//	}
}

