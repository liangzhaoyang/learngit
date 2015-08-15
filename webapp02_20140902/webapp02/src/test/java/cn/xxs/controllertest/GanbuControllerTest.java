package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;
@SuppressWarnings("deprecation")
public class GanbuControllerTest extends JUnitActionBase{
	//ganbu人员加载测试
	/*@Test    
		public void GanbuLoad() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/GanbuLoad.do");    
		        //参数设置
		        request.addParameter("orgid", "1");
		       // request.addParameter("name", "o");
		       // request.addParameter("zhengzhimianmao", "0");
		   
		        request.setMethod("POST");
		       
		        this.excuteAction(request, response);    
		        // 执行结果    
		        String result = response.getContentAsString();    
		        Assert.assertNotNull(result);  
		      
		    }*/
	//保密干部基本信息查询测试
			/*@Test    
				public void GanbuQuery() throws Exception {    
				    MockHttpServletRequest request = new MockHttpServletRequest();  
				    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
				        MockHttpServletResponse response = new MockHttpServletResponse();    
				        request.setRequestURI("/GanbuQuery.do");    
				        //参数设置
				        
				        request.addParameter("orgid", "1");
				        request.addParameter("name", "aa");
				   
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
				        request.addParameter("personid", "2,3,4,5,6");
				        request.addParameter("orgid", "1,1,1,2,2");
				        //request.addParameter("xingbie", "0");
				   
				        request.setMethod("POST");
				       
				        this.excuteAction(request, response);    
				        // 执行结果    
				        String result = response.getContentAsString();    
				        Assert.assertNotNull(result);  
				}
					
				//新建干部
				/*@Test    
				public void ganbuInsert() throws Exception {    
				    MockHttpServletRequest request = new MockHttpServletRequest();  
				    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
				        MockHttpServletResponse response = new MockHttpServletResponse();    
				        request.setRequestURI("/ganbuInsert.do");    
				        //参数设置
				        request.addParameter("orgid", "2");
				        request.addParameter("personid", "6");
				        //request.addParameter("xingbie", "0");
				   
				        request.setMethod("POST");
				       
				        this.excuteAction(request, response);    
				        // 执行结果    
				        String result = response.getContentAsString();    
				        Assert.assertNotNull(result);  
				}*/
	

}
