package cn.xxs.servicetest;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.Log;
import cn.xxs.entity.LogCondition;
import cn.xxs.service.LogService;

/**
 * @author 作者 :wan
      创建时间：2014年8月11日 下午2:00:55
 * 类说明
 */
public class LogServiceTest {
	private static Logger log=Logger.getLogger(LogServiceTest.class);
	@Autowired
	private LogService logService;
	
	@Before
    public void before(){                                                                   
        //@SuppressWarnings("resource
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});//,"applicationContext.xml"
        //需要通过名字引入的话，名字就是类名小写
        logService=(LogService)context.getBean("logService");
    }
	
	@Test
	public void getLogByCondition()
	{
		LogCondition logCon=new LogCondition();
		logCon.setLogtimeFrom(Timestamp.valueOf("2010-04-05 00:00:00"));
		logCon.setLogtimeTo(Timestamp.valueOf("2011-01-01 00:00:00"));
		
		List<Log>logs=logService.getLogByCondition(logCon);
		log.debug("条件查询日志：");
		for(Log temp:logs)
		{
			log.debug(temp.toString());
		}
	}
}
