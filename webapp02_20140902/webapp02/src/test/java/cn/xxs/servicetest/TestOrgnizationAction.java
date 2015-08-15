package cn.xxs.servicetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class TestOrgnizationAction extends JUnitActionBase{
	
	@Test    
    public void testOrgUpdate() throws Exception {    
    MockHttpServletRequest request = new MockHttpServletRequest();  
    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/updateOrgnizationInfo.do");    
        //参数设置
        /*
        request.addParameter("orgname", "保密办");
        request.addParameter("bianzhishu", "10");
        request.addParameter("renshu", "30");
        request.addParameter("zhuanzhiganbushu", "3");
        */
        String str="一般般好";
        request.addParameter("orgname", "保密学院");
        request.addParameter("xueshengshu", "232");
        request.addParameter("jiuyeqingkuang",str);//中文传输的时候会出现乱码
   
        request.setMethod("POST");
       
        this.excuteAction(request, response);    
        // 执行结果    
        String result = response.getContentAsString();    
        Assert.assertNotNull(result);  
      
    } 
}
