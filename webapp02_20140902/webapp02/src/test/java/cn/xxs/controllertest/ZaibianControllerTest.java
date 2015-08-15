package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;
@SuppressWarnings("deprecation")
public class ZaibianControllerTest extends JUnitActionBase{
//在编加载测试
/*@Test    
	public void ZaibianRenyuanLoad() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/ZaibianRenyuanLoad.do");    
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    } */
//在编修改测试
@Test    
public void zaibianModify() throws Exception {    
    MockHttpServletRequest request = new MockHttpServletRequest();  
    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/zaibianModify.do");    
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
//在编新建测试
@Test    
public void zaibianInsert() throws Exception {    
    MockHttpServletRequest request = new MockHttpServletRequest();  
    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/zaibianInsert.do");    
        request.setMethod("POST");
        //参数设置
       request.addParameter("orgid", "2");
       request.addParameter("code", "104");
       request.addParameter("num", "16");
        this.excuteAction(request, response);    
        // 执行结果    
        String result = response.getContentAsString();    
        Assert.assertEquals("", result);
}
}
