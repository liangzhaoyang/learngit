package cn.xxs.servicetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class LeaderServiceTest extends JUnitActionBase{
	//领导班子信息查询测试
	/*@Test    
		public void testPersonQueryById() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/leaderPersonQuery.do");    
		        //参数设置
		        request.addParameter("orgid", "0");
		        request.addParameter("name", "on");
		        //request.addParameter("xingbie", "0");
		   
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);  
		}*/
		//领导班子人员删除测试
		@Test    
		public void testDeleteLeader() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/leaderPersonDelete.do");    
		        //参数设置
		        request.addParameter("id","1,2,3");
		        //request.addParameter("name", "Bob");
		        //request.addParameter("xingbie", "0");
		   
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);  
		}

}
