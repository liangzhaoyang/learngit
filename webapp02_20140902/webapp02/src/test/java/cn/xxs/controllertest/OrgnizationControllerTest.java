package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

@SuppressWarnings("deprecation")
public class OrgnizationControllerTest extends JUnitActionBase{
	
	//按主管单位统计测试
	/*@Test    
	public void CountByZhuguanDanwei() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/CountByZhuguanDanwei.do");    
	        request.setMethod("POST");
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);    
	    }*/
	//按行政级别分类统计
		
		@Test    
		public void CountByXingzhengJibie() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/CountByXingzhengJibie.do");    
		        request.setMethod("POST");
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);    
		    }
		//按机构类别分类统计
	/*	@Test    
		public void CountByJigouLeibie() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/CountByJigouLeibie.do");    
		        request.setMethod("POST");
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);    
		    }
*/
}
