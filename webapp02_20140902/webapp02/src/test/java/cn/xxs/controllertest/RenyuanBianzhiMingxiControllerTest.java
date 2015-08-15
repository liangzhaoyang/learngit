package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;
@SuppressWarnings("deprecation")
public class RenyuanBianzhiMingxiControllerTest extends JUnitActionBase {
	//编制加载测试
	@Test    
		public void RenyuanBianzhiLoad() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/RenyuanBianzhiLoad.do");    
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);  
		      
		    } 
	//编制修改测试
	/*@Test    
	public void bianzhiModify() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiModify.do");    
	        request.setMethod("POST");
	        //参数设置
	       request.addParameter("orgid", "1");
	       request.addParameter("code", "104");
	       request.addParameter("num", "16");
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}
	//编制新建测试
	@Test    
	public void bianzhiInsert() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/bianzhiInsert.do");    
	        request.setMethod("POST");
	        //参数设置
	       request.addParameter("orgid", "2");
	       request.addParameter("code", "104");
	       request.addParameter("num", "16");
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	    
	}*/
}
