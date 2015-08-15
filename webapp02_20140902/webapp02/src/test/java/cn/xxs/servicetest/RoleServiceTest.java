package cn.xxs.servicetest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.service.RoleService;

public class RoleServiceTest {
	@Autowired
	private RoleService roleService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		roleService = (RoleService) context.getBean("roleService");
	}
	
//	@Test
//	public void queryRoleIdByUserId() {
//		List<String> list = roleService.queryRoleIdByUserId("jia");
//		System.out.println(list);
//	}
//
//	@Test
//	public void queryRolesByUserId() {
//		List<Map<String, Object>> list = roleService.queryRolesByUserId("jia");
//		System.out.println(list);
//	}
}

