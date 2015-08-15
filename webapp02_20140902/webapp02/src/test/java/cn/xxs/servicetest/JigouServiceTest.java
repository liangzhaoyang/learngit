package cn.xxs.servicetest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.service.JigouService;

/**
 * @author 作者 :wan 创建时间：2014年8月11日 下午8:02:27 类说明
 */
public class JigouServiceTest {
	private static Logger log = Logger.getLogger(JigouServiceTest.class);
	@Autowired
	private JigouService jigouService;

	@Before
	public void before() {
		// @SuppressWarnings("resource
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });// ,"applicationContext.xml"
		// 需要通过名字引入的话，名字就是类名小写
		jigouService = (JigouService) context.getBean("jigouService");
	}

	@Test
	public void getJiGouInfoByOrgid() {
		Map<String, Object> map = new HashMap<String, Object>();
		map = jigouService.getJigouXiangqing(0);
		log.debug(((Orgnization) map.get("orgnization")).toString());
		log.debug(((XingzhengBumen) map.get("xingzhengBumen")).toString());
		log.debug(((JichuSheshi) map.get("jichuSheshi")).toString());
		log.debug(((GuanliJigou) map.get("guanliJigou")).toString());
		log.debug(((ShiyeDanwei) map.get("shiyeDanwei")).toString());
	}
}
