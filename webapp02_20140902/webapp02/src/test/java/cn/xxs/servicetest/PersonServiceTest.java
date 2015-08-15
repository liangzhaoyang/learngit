package cn.xxs.servicetest;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;
import cn.xxs.service.PersonService;

/**
 * @author 作者 :wan
      创建时间：2014年8月7日 下午1:45:49
 * 类说明
 */
public class PersonServiceTest {

	private static Logger log=Logger.getLogger(PersonServiceTest.class);
	@Autowired
	private PersonService personTest;

	
	@Before
    public void before(){                                                                   
        //@SuppressWarnings("resource
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});//,"applicationContext.xml"
        
        //需要通过名字引入的话，名字就是类名小写
        personTest=(PersonService)context.getBean("personService");
    }
	
	/*
	@Test
	public void insertPerson()
	{
		Person person=new Person();
		person.setName("John jobs");
		person.setOrgid(0);
		person.setZhiwu(1);
		person.setXingbie(0);
		person.setKaishiriqi(Date.valueOf("2008-10-10"));
		person.setChushengnianyue(Date.valueOf("1974-10-9"));
		person.setXueli(0);
		person.setZhengzhimianmao(0);
		person.setZhuanye("信息工程");
		person.setXingzhengjibie(0);
		person.setJishuzhicheng(0);
		person.setRenmingdanwei("保密办");
		person.setShifouzhuanzhi(0);
		person.setJianrenqitazhiwu("否");
		person.setTel("8889688");
		person.setMobilenum("156xxxx0101");
		
		personTest.insertPerson(person);
		log.debug("插入信息成功！");
	}
	
	
	@Test
	public void updatePerson()
	{
		Person person=new Person();
		person.setId(10);
		//注意sql中的日期格式
		person.setOrgid(0);
		person.setZhiwu(0);
		person.setName("monkey");
		person.setXingbie(0);
		person.setKaishiriqi(Date.valueOf("2009-1-1"));
		person.setChushengnianyue(Date.valueOf("1980-9-1"));
		person.setRenmingdanwei("保密单位");
		person.setXueli(0);
		person.setZhengzhimianmao(0);
		person.setZhuanye("xinxi");
		person.setXingzhengjibie(0);
		person.setJishuzhicheng(0);
		person.setShifouzhuanzhi(0);
		person.setJianrenqitazhiwu("xxx");
		person.setSuoshubumen(0);
		person.setDanweimingcheng(0);
		person.setTel("999999999");
		person.setMobilenum("19919292");
		
		
		personTest.updatePerson(person);
		log.debug("更新信息成功！"); 
	}
	*/
	
	@Test
	public void getPersonByCondition()
	{
		PersonCondition personCon=new PersonCondition();
		
		personCon.setChushengnianyueFrom(Date.valueOf("1973-1-1"));//Date.valueOf("1973-1-1")
		//personCon.setChushengnianyueTo(Date.valueOf("1975-5-28"));
		personCon.setOrderinfo("chushengnianyue");
		List<Person> persons=personTest.getPersonByCondition(personCon);
		
		log.info("条件查询人员信息");
		for(Person temp:persons)
		{
			log.debug("姓名:"+temp.getName()+",性别:"+temp.getXingbie()+",出生年月:"+temp.getChushengnianyue()+",政治面貌:"+
		temp.getZhengzhimianmao()+",学历:"+temp.getXueli()+",行政级别:"+temp.getXingzhengjibie()+",专业:"+temp.getZhuanye()+",是否专职:"+
					temp.getZhiwu()+",部门名称:"+temp.getSuoshubumen()+",单位名称:"+temp.getDanweimingcheng());
			
		}
		
		log.info("-----------------------");
		
	}

	/*
	@Test
	public void getPersonById()
	{
		Person temp=personTest.getPersonById("6");
		
		log.info(temp.toString());
		
	}
	*/
	/*
	@Test
	public void getAllPerson()
	{
		List<Person> persons=personTest.getAllPerson();
		
		log.debug("显示所有人的信息：");
		for(Person temp:persons)
		{
			log.debug(temp.toString());
		}
		
	}
	*/
}
