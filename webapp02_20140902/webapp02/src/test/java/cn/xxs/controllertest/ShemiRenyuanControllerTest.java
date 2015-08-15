package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

@SuppressWarnings("deprecation")
public class ShemiRenyuanControllerTest extends JUnitActionBase{

	//涉密人员加载测试
	/*@Test    
	public void ShemiRenyuanLoad() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/ShemiRenyuanLoad.do");    
	        //参数设置
	        request.addParameter("orgid", "1");
	       // request.addParameter("name", "o");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    } */
	//涉密人员查询测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/ShemiRenyuanQuery.do");    
	        //参数设置
	        request.addParameter("orgid", "2");
	        request.addParameter("name", "c");
	       // request.addParameter("zhengzhimianmao", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	      
	    } 
	*/
	//涉密人员删除测试
	/*@Test    
	public void testPersonQueryById() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/secertPersonDelete.do");    
	        //参数设置
	        request.addParameter("personid", "2,3,4,6,5");
	        request.addParameter("orgid", "1,1,2,2,2");
	        //request.addParameter("xingbie", "0");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	        // 执行结果    
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}*/
	//新建shemi
	 @Test    
	public void leaderInsert() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/shemiInsert.do");    
        //参数设置
        request.addParameter("orgid", "1");
        request.addParameter("personid", "2");
        //request.addParameter("xingbie", "0");
   
        request.setMethod("POST");
       
        this.excuteAction(request, response);    
        // 执行结果    
        String result = response.getContentAsString();    
        Assert.assertNotNull(result);  
} 

}
