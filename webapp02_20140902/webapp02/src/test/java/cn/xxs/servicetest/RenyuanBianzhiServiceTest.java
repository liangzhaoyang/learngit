package cn.xxs.servicetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class RenyuanBianzhiServiceTest extends JUnitActionBase {
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
	        request.addParameter("num", "156");
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//人员编制插入测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiInsert.do");    
	        //参数设置
	        request.addParameter("orgid", "0");
	        request.addParameter("code", "105");
	        request.addParameter("num", "11");
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//人员编制明细加载测试
	@Test    
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
	}
}