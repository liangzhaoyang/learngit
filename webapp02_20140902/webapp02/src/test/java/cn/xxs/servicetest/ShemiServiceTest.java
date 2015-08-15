package cn.xxs.servicetest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class ShemiServiceTest extends JUnitActionBase {
	//涉密人员查询测试
	/*@SuppressWarnings("deprecation")
	@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/secertPersonQuery.do");    
	        //参数设置
	        request.addParameter("orgid", "0");
	        request.addParameter("name", "o");
	        request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    } 
	
	 */
	    

	
	//涉密人员删除测试
	@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/secertPersonDelete.do");    
	        //参数设置
	        request.addParameter("id", "2,3,4");
	        //request.addParameter("name", "Bob");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}

}
