package cn.xxs.servicetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class TestOrderAction extends JUnitActionBase{  
	//涉密人员查询测试
//	@Test    
//	public void testPersonQueryById() throws Exception {   
//	    MockHttpServletRequest request = new MockHttpServletRequest();
//	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
//	        MockHttpServletResponse response = new MockHttpServletResponse();    
//	        request.setRequestURI("/secertPersonQuery.do");    
//	        //参数设置
//	        request.addParameter("id", "8");
//	   
//	        request.setMethod("POST");
//	       
//	        this.excuteAction(request, response);    
//	        // 执行结果    
//	        String result = response.getContentAsString();    
//	        Assert.assertNotNull(result);  
//	      
//	    } 
	
	//涉密人员删除测试
	@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/secertPersonDelete.do");    
	        //参数设置
	        request.addParameter("id", "16");
	        //request.addParameter("name", "Bob");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}
	//保密干部基本信息查询测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/privaryPersonQuery.do");    
	        //参数设置
	        request.addParameter("id", "8");
	        request.addParameter("chushengnianyue", "1969-1-1");
	        request.addParameter("chushengnianyuese", "2014-1-1");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	
	//保密干部人员删除测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/privaryPersonDelete.do");    
	        //参数设置
	        request.addParameter("id", "14");
	        //request.addParameter("name", "Bob");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}
	*/
	
	//插入person
	/*@Test    
    public void testPersonAdd() throws Exception {    
    MockHttpServletRequest request = new MockHttpServletRequest();  
    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/personInfoInsert.do");    
        //参数设置
        request.addParameter("name", "Long3");
        request.addParameter("orgid", "0");
        request.addParameter("zhiwu", "1");
        request.addParameter("xingbie", "0");
        request.addParameter("chushengnianyue", "1974-10-9");
        request.addParameter("xueli", "0");
        request.addParameter("zhengzhimianmao", "0");
        request.addParameter("zhuanye", "信息工程");
        request.addParameter("xingzhengjibie", "0");
        request.addParameter("jishuzhicheng", "0");
        request.addParameter("renmingdanwei", "保密办");
        request.addParameter("shifouzhuanzhi", "0");
        request.addParameter("jianrenqitazhiwu", "否");
        request.addParameter("kaishiriqi", "2009-10-10");
        request.addParameter("suoshubumen", "0");
        request.addParameter("danweimingcheng", "0");
        request.addParameter("tel", "8889688");
        request.addParameter("mobilenum", "156xxxx0101");
        request.setMethod("POST");
       
        this.excuteAction(request, response);    
        // 执行结果    
        String result = response.getContentAsString();    
        Assert.assertNotNull(result);  
        
    }  
    */
	
	//领导班子信息查询测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/leaderPersonQuery.do");    
	        //参数设置
	        request.addParameter("id", "8");
	        request.addParameter("name", "Bob");
	        request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//领导班子人员删除测试
	/*@Test    
	public void testDeleteLeader() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/leaderPersonDelete.do");    
	        //参数设置
	        request.addParameter("id", "[8,15]");
	        //request.addParameter("name", "Bob");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//加载全部人员编制情况
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiQuery.do");    
	        //参数设置
	        //request.addParameter("id", "16");
	        //request.addParameter("name", "Bob");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//人员编制修改测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiModify.do");    
	        //参数设置
	        request.addParameter("orgid", "0");
	        request.addParameter("code", "102");
	        request.addParameter("num", "53");
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//编制情况插入测试
	/*@Test    
	 public void testPersonAdd() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiInsert.do");    
	        //参数设置
	        request.addParameter("orgid", "0");
	        request.addParameter("code", "104");
	        request.addParameter("num", "10");
	    
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	        
	    } */
	//简历加载测试
	/*@Test  
	public void testPersonAdd() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/getAllResume.do");    
	        //参数设置
	        //request.addParameter("orgid", "0");
	        //request.addParameter("code", "104");
	       // request.addParameter("num", "10");
	    
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result); 
    } */
	//简历条件查询
	@Test  
	public void testPersonAdd() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/resumeInfoConditionQuery.do");    
	        //参数设置
	        //request.addParameter("id", "1");
	        request.addParameter("name", "Bob");
	        request.addParameter("xianrenzhuiwu", "leo");
	        request.addParameter("zhuanyejishuzhiwu", "leo");
	    
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result); 
    }
}
    

