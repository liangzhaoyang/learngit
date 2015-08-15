package cn.xxs.servicetest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.xxs.entity.Orgnization;
import cn.xxs.service.OrgnizationImportService;

@RunWith(Junit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml"})  
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
public class OrgnizationImportServiceTest {
//	@Test
	public void orgnizationBaseRead() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });
		OrgnizationImportService service = (OrgnizationImportService) context.getBean("orgnizationImportService");

		String filename = null;
		try {
			filename = new File(getClass().getClassLoader().getResource("exceltemplate").getFile(), "简历.xls").getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(filename);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//			service.parseOrgnizationBaseInfo(filename, service.makeConstantsMap(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void lingdaoBanziRead() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });
		OrgnizationImportService service = (OrgnizationImportService) context.getBean("orgnizationImportService");

		String filename = null;
		try {
			filename = new File(getClass().getClassLoader().getResource("exceltemplate").getFile(), "保密委员会办公室、保密局领导班子成员及工作人员基本情况填报表.xls").getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(filename);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//			service.parseLingdaoBanziInfo(filename, service.makeConstantsMap(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void renyuanBianzhiRead() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });
		OrgnizationImportService service = (OrgnizationImportService) context.getBean("orgnizationImportService");

		String filename = null;
		try {
			filename = new File(getClass().getClassLoader().getResource("exceltemplate").getFile(), "简历.xls").getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(filename);
		Map<String, Object> map = new HashMap<String, Object>();
		Orgnization org = new Orgnization();
		org.setJigouleibie(1);
		org.setXingzhengjibie(3);
		map.put("orgnization",  org);
		List<String> filenames = new ArrayList<String>();
		filenames.add(filename);
		try {
			service.parseResumes(filenames, service.makeConstantsMap(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void makeConstantsMap() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml" });
		OrgnizationImportService service = (OrgnizationImportService) context.getBean("orgnizationImportService");
		try {
//			_logger.debug(service.makeConstantsMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Logger _logger = Logger.getLogger(OrgnizationImportServiceTest.class);
}
