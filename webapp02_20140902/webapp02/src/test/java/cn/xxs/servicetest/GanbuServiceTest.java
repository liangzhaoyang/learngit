package cn.xxs.servicetest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class GanbuServiceTest extends JUnitActionBase{
	//保密干部基本信息查询测试
		/*	@Test    
			public void testPersonQueryById() throws Exception {    
			    MockHttpServletRequest request = new MockHttpServletRequest();  
			    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
			        MockHttpServletResponse response = new MockHttpServletResponse();    
			        request.setRequestURI("/privaryPersonQuery.do");    
			        //参数设置
			        request.addParameter("orgid", "0");
			        //request.addParameter("chushengnianyueFrom", "1970-1-1");
			        //request.addParameter("chushengnianyueTo", "1970-1-1");
			   
			        request.setMethod("POST");
			       
			        this.excuteAction(request, response);    
			        // 执行结果    
			        String result = response.getContentAsString();    
			        Assert.assertNotNull(result);  
			}
			*/
			//保密干部人员删除测试
			@Test    
			public void testPersonQueryById() throws Exception {    
			    MockHttpServletRequest request = new MockHttpServletRequest();  
			    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
			        MockHttpServletResponse response = new MockHttpServletResponse();    
			        request.setRequestURI("/privaryPersonDelete.do");    
			        //参数设置
			        request.addParameter("id", "2,3,4,5,6,7,8");
			        //request.addParameter("name", "Bob");
			        //request.addParameter("xingbie", "0");
			   
			        request.setMethod("POST");
			       
			        this.excuteAction(request, response);    
			        // 执行结果    
			        String result = response.getContentAsString();    
			        Assert.assertNotNull(result);  
			}
			


}
