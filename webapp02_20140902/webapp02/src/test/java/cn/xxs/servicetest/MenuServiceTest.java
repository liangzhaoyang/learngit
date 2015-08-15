package cn.xxs.servicetest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.MenuItem;
import cn.xxs.service.MenuService;

public class MenuServiceTest {
	@Test
	public void test1() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });
		MenuService menuService = (MenuService) context.getBean("menuService");
		List<MenuItem> menuItems = menuService.getMenusByRoleId(4);
		System.out.println(menuItems.size());
	}
}
