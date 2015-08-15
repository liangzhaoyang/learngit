package cn.xxs.servicetest;

import java.sql.Date;

import org.junit.Test;

import cn.xxs.entity.Chengguo;
import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.Person;
import cn.xxs.entity.Resume;

public class ResumeServiceTest extends JUnitActionBase {
	//简历加载测试
	/*	@Test  
		public void testPersonAdd() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/getAllResume.do");    
		        //参数设置
		        request.addParameter("orgid", "0");
		        //request.addParameter("zhuanyejishuzhiwu", "leo1");
		       // request.addParameter("num", "10");
		    
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result); 
	    } */
		//简历条件查询
		/*@Test  
		public void testPersonAdd() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/resumeInfoConditionQuery.do");    
		        //参数设置
		        request.addParameter("orgid", "2");
		        request.addParameter("name", "Bob");
		        request.addParameter("xianrenzhuiwu", "leo");
		        request.addParameter("zhuanyejishuzhiwu", "leo1");
		    
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result); 
	    }*/
		//删除简历
		/*@Test  
		public void delete() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/resumeDelete.do");    
		        //参数设置
		        request.addParameter("id", "4");
		        //request.addParameter("name", "Bob");
		        //request.addParameter("xianrenzhuiwu", "leo");
		        //request.addParameter("zhuanyejishuzhiwu", "leo1");
		    
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result); 
	    }*/
	//简历插入测试
	/*@Test  
	public void delete() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/resumeInsert.do");    
	        //参数设置
	        /* Resume resume = new Resume();
	        ResumePerson resumePerson = new ResumePerson();
	        JiatingChengyuan jiatingChengyuan = new JiatingChengyuan();
	        request.addParameter("orgid", "1");
	        request.addParameter("resumeid", "1");
	        request.addParameter("personid", "20");
	        request.addParameter("zhaopian", "leo1");
	        resume.setCanjiagongzuoshijian(Date.valueOf("2008-10-10"));
	        //request.addParameter("canjiagongzuoshijian", "2014-8-10");
	        request.addParameter("zhuanyejishuzhiwu", "leo");
	        request.addParameter("zhuanchang", "leo");
	        request.addParameter("xianrenzhuiwu", "leo1");
	        request.addParameter("jianli", "leo1");
	        request.addParameter("beizhu", "leo");
	        request.addParameter("name", "aaa");
	        request.addParameter("xingbie", "3");
	        request.addParameter("zhuanye", "aaaleo");
	        request.addParameter("minzu", "1");
	        //request.addParameter("chushengnianyue", "1989-8-23");
	        resumePerson.setChushengnianyue(Date.valueOf("1988-10-10"));
	        request.addParameter("zhengzhimianmao", "20");
	        request.addParameter("xuelimingxiid", "1");
	        request.addParameter("leibie", "1");
	        request.addParameter("biyeyuanxiao", "huda");
	        request.addParameter("jiangchengid", "20");
	        request.addParameter("jiangchengcotent", "leo1");
	        request.addParameter("chengguoid", "10");
	        request.addParameter("chengguocotent", "leo");
	        request.addParameter("jiatingchengyuanid", "11");
	        request.addParameter("chengwei", "baba");
	        request.addParameter("xingming", "leo1");
	        jiatingChengyuan.setChushengnianfen(Date.valueOf("1968-10-10"));
	        //request.addParameter("chushengnianfen", "1968-1-28");
	        request.addParameter("jiazhengzhimianmao", "qunzhong");
	        request.addParameter("gongzuodanweijizhiwu", "aaaaaaaaa");
	       
	        
	        
	    Resume resume = new Resume();
		Person person = new Person();
        JiatingChengyuan jiatingChengyuan = new JiatingChengyuan();
        Chengguo chengguo = new Chengguo();
        Jiangcheng jiangcheng = new Jiangcheng();
        resume.setBeizhu("leo");
        resume.setCanjiagongzuoshijian(Date.valueOf("2008-10-10"));
        resume.setId(4);
        resume.setJianli("jianli");
        resume.setPersonid(22);
        resume.setXianrenzhuiwu("chuzhang");
        resume.setZhaopian("zhaopian");
        resume.setZhuanchang("zhuangchang");
        resume.setZhuanyejishuzhiwu("zhuanyejishu");
        person.setName("aaaaaa");
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
        /*resumePerson.setChushengnianyue(Date.valueOf("1988-10-10"));
        resumePerson.setId(22);
        resumePerson.setMinzu(2);
        resumePerson.setName("aaaaa");
        resumePerson.setOrgid(1);
        resumePerson.setXingbie(1);
        resumePerson.setZhengzhimianmao(1);
        resumePerson.setZhuanye("jisuanji");
        jiatingChengyuan.setChengwei("baba");
        jiatingChengyuan.setChushengnianfen(Date.valueOf("1968-10-10"));
        jiatingChengyuan.setGongzuodanweijizhiwu("hao");
        jiatingChengyuan.setId(1);
        jiatingChengyuan.setPersonid(22);
        jiatingChengyuan.setXingming("bbbbbb");
        jiatingChengyuan.setZhengzhimianmao("dangyuan");
        chengguo.setContent("chengguo");
        chengguo.setId(1);
        chengguo.setPersonid(22);
        jiangcheng.setContent("jiangcheng");
        jiangcheng.setId(1);
        jiangcheng.setPersonid(22);
        
	        request.setMethod("POST");
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result); 
	        
    }*/
	@Test 
	public void insertResumeInfo()
	{
		Resume resume = new Resume();
		Person person = new Person();
        JiatingChengyuan jiatingChengyuan = new JiatingChengyuan();
        Chengguo chengguo = new Chengguo();
        Jiangcheng jiangcheng = new Jiangcheng();
        resume.setBeizhu("leo");
        resume.setCanjiagongzuoshijian(Date.valueOf("2008-10-10"));
        resume.setId(4);
        resume.setJianli("jianli");
        resume.setPersonid(22);
        resume.setXianrenzhuiwu("chuzhang");
        resume.setZhaopian("zhaopian");
        resume.setZhuanchang("zhuangchang");
        resume.setZhuanyejishuzhiwu("zhuanyejishu");
        person.setName("aaaaaa");
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
        /*resumePerson.setChushengnianyue(Date.valueOf("1988-10-10"));
        resumePerson.setId(22);
        resumePerson.setMinzu(2);
        resumePerson.setName("aaaaa");
        resumePerson.setOrgid(1);
        resumePerson.setXingbie(1);
        resumePerson.setZhengzhimianmao(1);
        resumePerson.setZhuanye("jisuanji");*/
        jiatingChengyuan.setChengwei("baba");
        jiatingChengyuan.setChushengnianfen(Date.valueOf("1968-10-10"));
        jiatingChengyuan.setGongzuodanweijizhiwu("hao");
        jiatingChengyuan.setId(1);
        jiatingChengyuan.setPersonid(22);
        jiatingChengyuan.setXingming("bbbbbb");
        jiatingChengyuan.setZhengzhimianmao("dangyuan");
        chengguo.setContent("chengguo");
        chengguo.setId(1);
        chengguo.setPersonid(22);
        jiangcheng.setContent("jiangcheng");
        jiangcheng.setId(1);
        jiangcheng.setPersonid(22);
        System.out.println("-------------插入成功-------------------------");
        
	}
}
