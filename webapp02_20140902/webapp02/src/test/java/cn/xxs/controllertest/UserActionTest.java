package cn.xxs.controllertest;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

import cn.xxs.entity.User;
import cn.xxs.service.OrgnizationService;
import cn.xxs.service.RoleService;
import cn.xxs.service.UserService;
import cn.xxs.utility.MD5;

public class UserActionTest extends JUnitActionBase{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgnizationService orgnizationService;
	//用户登录测试
	@Test    
	public void userLogin() throws Exception {    
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/login.do");    
	        
	        request.addParameter("id", "jia");
	        request.addParameter("password", "456");
	   
	        request.setMethod("POST");
	       
	        this.excuteAction(request, response);    
	          
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
	}
	
	//用户查询测试
	@Test	
	public void queryUser() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();  
	        HttpSession session = request.getSession();
	        session.setAttribute("currUser", new User());
	        request.setRequestURI("/queryUser.do"); 
	        request.addParameter("q_id", "jiu");
	        request.addParameter("q_name", "九");
	        request.addParameter("q_orgId", "4");
	        request.addParameter("q_role", "4");
	        request.setMethod("POST");

	        this.excuteAction(request, response);    
	        request.setMethod("POST");
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
		System.out.println("queryUser");
	}
	
	//添加用户测试
	@Test
	public void userAdd() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();  
	    request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	        MockHttpServletResponse response = new MockHttpServletResponse();  
	        HttpSession session = request.getSession();
	        session.setAttribute("currUser", new User());
	        request.setRequestURI("/addUser.do"); 
	        request.addParameter("a_id", "jiu");
	        request.addParameter("a_name", "九");
	        request.addParameter("a_password",MD5.md5("1"));
	        request.addParameter("status", "1");
	        request.addParameter("deleteflag", "false");
	        request.addParameter("firsttime", "true");
	        request.addParameter("a_orgId", "4");
	        request.addParameter("a_roles", "4:1");
	        request.setMethod("POST");
		       
	        this.excuteAction(request, response);    
	        request.setMethod("POST");
	        String result = response.getContentAsString();    
	        Assert.assertNotNull(result);  
		System.out.println("userAdd");
	}
	
	//删除用户测试，不能物理删除，只是修改deleteflag
	@Test
	public void deleteUserByID() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();  
		request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		MockHttpServletResponse response = new MockHttpServletResponse();  
		HttpSession session = request.getSession();
		session.setAttribute("currUser", new User());
		request.setRequestURI("/deleteUser.do"); 
		request.addParameter("ids", "jiu:jiujiu:lisi");
		request.setMethod("POST");
		
		this.excuteAction(request, response);    
		request.setMethod("POST");
		String result = response.getContentAsString();    
		Assert.assertNotNull(result);  
		System.out.println("userDelete");
	}
	
	//更新用户信息
	@Test
	public void updateUserById() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();  
		request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		MockHttpServletResponse response = new MockHttpServletResponse();  
		HttpSession session = request.getSession();
		session.setAttribute("currUser", new User());
		request.setRequestURI("/updateUser.do"); 
		request.addParameter("id", "jiu");
		request.addParameter("orgid", "3");
		request.addParameter("name", "九九");
		request.addParameter("status", "1");
		request.addParameter("roleid", "1");
		
		request.setMethod("POST");
		
		this.excuteAction(request, response);   
		String result = response.getContentAsString();    
		Assert.assertNotNull(result);  
		System.out.println("userupdate");
	}
	
}
