package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;
@SuppressWarnings("deprecation")
public class UserGrantControllerTest extends JUnitActionBase{
	//加载授权测试
	/*@Test    
	public void UserGrantLoad() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/UserGrantLoad.do");    
	        //参数设置
	        request.addParameter("orgid", "1");
	       // request.addParameter("name", "o");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    }
	*/
	
	//授权管理查询测试
	@Test    
	public void UserGrantQuery() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/UserGrantQuery.do");    
	        //参数设置
	        request.addParameter("q_status", "100");
	        request.addParameter("q_id", "100");
	        request.addParameter("q_name", "100");
	        request.addParameter("q_role", "100");
	        request.addParameter("q_orgId", "100");
	    
	        
	       // request.addParameter("name", "o");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    }
	/*
	//授权测试
	@Test    
	public void UserGrant() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/UserGrant.do");    
	        //参数设置
	        request.addParameter("id", "3,4,5");
	       // request.addParameter("name", "o");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    }
	
	
	
	//取消授权测试
	@Test    
	public void CancelGrant() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/CancelGrant.do");    
	        //参数设置
	        request.addParameter("id", "3,4,5");
	       // request.addParameter("name", "o");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    }*/
}
