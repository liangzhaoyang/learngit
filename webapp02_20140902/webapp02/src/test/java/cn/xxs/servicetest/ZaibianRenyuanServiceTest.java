package cn.xxs.servicetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class ZaibianRenyuanServiceTest extends JUnitActionBase {
	//在编人员修改测试
	   
	/*	@Test    
		public void testPersonQueryById() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/zaibianModify.do");    
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
		//在编人员插入测试
		/*@Test    
		public void testPersonQueryById() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/zaibianInsert.do");    
		        //参数设置
		        request.addParameter("orgid", "1");
		        request.addParameter("code", "106");
		        request.addParameter("num", "11");
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);  
		}*/
		//在编人员明细加载测试
		@Test    
		public void testPersonQueryById() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/zaibianQuery.do");    
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
