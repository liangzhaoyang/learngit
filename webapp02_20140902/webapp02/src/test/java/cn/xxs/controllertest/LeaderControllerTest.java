package cn.xxs.controllertest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

public class LeaderControllerTest extends JUnitActionBase{
	//ldbz人员加载测试
	/*	@Test    
		public void LeaderLoad() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/LeaderLoad.do");    
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
	//领导班子信息查询测试
			/*@Test    
			public void LeaderQuery() throws Exception {    
			    MockHttpServletRequest request = new MockHttpServletRequest();  
			    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
			        MockHttpServletResponse response = new MockHttpServletResponse();    
			        request.setRequestURI("/LeaderQuery.do");    
			        //参数设置
			        request.addParameter("orgid", "2");
			        request.addParameter("name", "l");
			        //request.addParameter("xingbie", "0");
			   
			        request.setMethod("POST");
			       
			        this.excuteAction(request, response);    
			        // 执行结果    
			        String result = response.getContentAsString();    
			        Assert.assertNotNull(result);  
			} */
			//领导班子人员删除测试
			@Test    
			public void testDeleteLeader() throws Exception {    
			    MockHttpServletRequest request = new MockHttpServletRequest();  
			    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
			        MockHttpServletResponse response = new MockHttpServletResponse();    
			        request.setRequestURI("/leaderPersonDelete.do");    
			        //参数设置
			        request.addParameter("orgid","1,1,1,2,2");
			        request.addParameter("personid","3,2,4,5,6");
			        //request.addParameter("name", "Bob");
			        //request.addParameter("xingbie", "0");
			   
			        request.setMethod("POST");
			       
			        this.excuteAction(request, response);    
			        // 执行结果    
			        String result = response.getContentAsString();    
			        Assert.assertNotNull(result);  
			}
		
		//新建ldbz
	/*	@Test    
		public void leaderInsert() throws Exception {    
		    MockHttpServletRequest request = new MockHttpServletRequest();  
		    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		        MockHttpServletResponse response = new MockHttpServletResponse();    
		        request.setRequestURI("/leaderInsert.do");    
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
