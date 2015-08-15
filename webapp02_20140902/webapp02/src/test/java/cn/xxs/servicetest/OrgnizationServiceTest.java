package cn.xxs.servicetest;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxs.entity.Orgnization;
import cn.xxs.entity.OrgnizationCondition;
import cn.xxs.entity.Person;
import cn.xxs.entity.ShemiRenyuanQingkuang;
import cn.xxs.service.LeaderService;
import cn.xxs.service.OrgnizationService;

public class OrgnizationServiceTest {
	
	private static Logger log=Logger.getLogger(OrgnizationServiceTest.class);
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private LeaderService leaderService;
	
	@Before
    public void before(){                                                                   
        //@SuppressWarnings("resource
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});//,"applicationContext.xml"
        //需要通过名字引入的话，名字就是类名小写
        orgnizationService=(OrgnizationService)context.getBean("orgnizationService");
        leaderService=(LeaderService)context.getBean("leaderService");
    }

	/*
	@Test
	public void updateOrgnizationInfo()
	{	
		String str="非常好";
		Orgnization org=new Orgnization();
		org.setOrgname("保密学院");
		org.setId(1);
		//org.setBianzhishu(100);
		//org.setRenshu(300);
		org.setZhuanzhiganbushu(1);
		org.setXueshengshu(500);
		try {
			org.setJiuyeqingkuang(str.getBytes("UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orgnizationService.UpdateOrgnization(org);
		log.debug("更新组织信息成功！");
	}
	

	@Test
	public void getOrgnizationInfo()
	{		
		List<Orgnization> admins= orgnizationService.getOrgnizationInfo();
		log.debug("获取组织信息：");
		for(Orgnization temp:admins)
		{
			log.debug(temp.toString());
		}
		log.debug("--------------------------------------");
	}
	 */
	/*
	@Test
	public void insertXingzhengBumenInfo()
	{
		XingzhengBumen admin=new XingzhengBumen();
		
		admin.setName("eee");
		admin.setOrgid(0);
		admin.setShijirenshu(210);
		admin.setXingzhengjibie(0);
		
		orgnizationService.insertXingzhengBumen(admin);
		
		log.debug("插入行政信息成功！");
		
	}
	

	@Test
	public void deleteXingzhengBumenInfo()
	{		
		orgnizationService.deleteXingzhengBumen("3");
		log.debug("删除行政信息成功！");
		
	}
	

	@Test
	public void getXingzhengBumenInfo()
	{		
		List<XingzhengBumen> admins= orgnizationService.getXingzhengBumenInfo();
		
		log.debug("行政信息查询：");
		for(XingzhengBumen temp:admins)
		{
			log.debug(temp.toString());
		}
		
	}
	*/
	/*
	@Test
	public void insertGuanliJigouInfo()
	{
		GuanliJigou admin=new GuanliJigou();
		
		admin.setName("mbbb");
		admin.setOrgid(0);
		admin.setShijirenshu(100);
		admin.setXingzhengjibie(1);
		
		orgnizationService.insertGuanliJigou(admin);
		
		log.debug("插入管理机构信息成功！");
	}
	
	@Test
	public void deleteGuanliJigouInfo()
	{		
		orgnizationService.deleteGuanliJigou("3");
	
		log.debug("删除管理机构信息成功！");
	}

	
	@Test
	public void getGuanliJigouInfo()
	{		
		List<GuanliJigou> admins= orgnizationService.getGuanliJigouInfo();
		
		log.debug("查询管理机构信息：");
		for(GuanliJigou temp:admins)
		{
			log.debug(temp.toString());
		}
		
	}
	*/
	/*
	@Test
	public void insertShiyeDanweiInfo()
	{
		ShiyeDanwei admin=new ShiyeDanwei();
		
		admin.setName("pccc");
		admin.setOrgid(0);
		admin.setShijirenshu(120);
		admin.setXingzhengjibie(0);
		
		orgnizationService.insertShiyeDanwei(admin);
		log.debug("插入事业单位信息成功！");
		
	}

	@Test
	public void deleteShiyeDanweiInfo()
	{		
		orgnizationService.deleteShiyeDanwei("1");	
		log.debug("删除事业单位信息成功！");
	}
	
	@Test
	public void getShiyeDanweiInfo()
	{		
		List<ShiyeDanwei> admins= orgnizationService.getShiyeDanweiInfo();
		
		log.debug("查询事业单位信息：");
		for(ShiyeDanwei temp:admins)
		{
			log.debug(temp.toString());
		}
		
	}
	*/
	@Test
    public void getDaiShenheInfo(){
        
		OrgnizationCondition orgCon=new OrgnizationCondition();
        List<Orgnization> info=orgnizationService.getDaiShenheInfo(orgCon);
      //打印出数据库info中的项
        log.debug("显示待审核信息：");
  		for(Orgnization temp:info)
  		{  		
  			log.debug(""+temp.getOrgname()+","+temp.getChenglishijian()+","+temp.getXingzhengjibie()+","+
  		temp.getJingfeilaiyuan()+","+temp.getZhengfuxulie()+","+temp.getJigouleibie());
  		}
    }
    
	
	@Test
    public void getJigouJibenInfo(){
		OrgnizationCondition orgCon=new OrgnizationCondition();
		
        List<Orgnization> info=orgnizationService.getJigouJibenInfo(orgCon);
      //打印出数据库info中的项
        log.debug("显示机构基本信息：");
  		for(Orgnization temp:info)
  		{  		
  			log.debug(""+temp.getOrgname()+","+temp.getJigouleibie()+","+temp.getChenglishijian()+","+temp.getXingzhengjibie()+","+
  		temp.getJingfeilaiyuan()+","+temp.getZhengfuxulie()+","+temp.getJigouleibie());
  		}
    }
	/*
	@Test
    public void insertOrgnization(){
		Orgnization org=new Orgnization();
		org.setOrgname("aaaa");
        org.setStatus(0);
        
        orgnizationService.insertOrgnization(org);
    }
	
	@Test
    public void setOrgnizationShenhe(){
        Map map=new HashMap<String,Object>();
        map.put("orgid", 4);
        map.put("status", 1);
       
        orgnizationService.setShenheInfo(map);
    }
	
	@Test
    public void getBaomiXinxi(){
		BaomiQingkuang bm=orgnizationService.getBaomiQingkuangInfo(0);
		List<Person> persons=bm.getPersons();
		for(Person temp:persons)
			log.debug(temp.toString());
		log.debug(bm.toString());
    }
    
	@Test
    public void getRenyuanBianzhiQingkuang(){
		RenyuanBianzhiQingkuang rybz=orgnizationService.getRenyuanBianzhiQingkuangInfo(0);
		List<RenyuanBianzhiMingxi> persons=rybz.getRenyuanBianzhiMingxis();
		for(RenyuanBianzhiMingxi temp:persons)
			log.debug(temp.toString());
		log.debug(rybz.toString());
    }
	@Test
    public void getZaibianRenyuanQingkuang(){
		ZaibianRenyuanQingkuang zbry=orgnizationService.getZaibianRenyuanQingkuangInfo(0);
		List<ZaibianRenyuanMingxi> persons=zbry.getZaibianRenyuanMingxi();
		for(ZaibianRenyuanMingxi temp:persons)
			log.debug(temp.toString());
		log.debug(zbry.toString());
    }
    
	@Test
    public void getBaomiGanbuQingkuang(){
		BaomiGanbuQingkuang bmgb=orgnizationService.getBaomiGanbuQingkuangInfo(0);
		List<Person> persons=bmgb.getPersons();
		for(Person temp:persons)
			log.debug(temp.toString());
		log.debug(bmgb.toString());
    }
    
	@Test
    public void getShemiRenyuanQingkuangInfo(){
		ShemiRenyuanQingkuang smqk=orgnizationService.getShemiRenyuanQingkuangInfo(2);
		List<Person> persons=smqk.getPersons();
		for(Person temp:persons)
			log.debug(temp.toString());
		log.debug(smqk.toString());
    }*/
	@Test
    public void getLingdaoBanziInfo(){
		List<Person> persons = leaderService.selectAllLeader(1);
      //打印出数据库info中的项
        log.debug("显示机构基本信息：");
  		for(Person temp:persons)
  		{  		
  			log.debug(temp.toString());
  		}
    }
}
