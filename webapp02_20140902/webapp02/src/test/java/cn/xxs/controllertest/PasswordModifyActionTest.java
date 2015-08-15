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

public class PasswordModifyActionTest extends JUnitActionBase{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgnizationService orgnizationService;
	
	//密码修改测试
	@Test    
	public void passwordModify() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();  
		HttpSession session = request.getSession();
		request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		MockHttpServletResponse response = new MockHttpServletResponse(); 
		User u = new User();
		u.setId("jiu");
		u.setPassword(MD5.md5("333333"));
		session.setAttribute("currUser",u );
		request.setRequestURI("/passwordModify.do"); 
		request.addParameter("oldPassword", "333333");
		request.addParameter("newPassword","2");
		
		request.setMethod("POST");
		
		this.excuteAction(request, response);   
		String result = response.getContentAsString();    
		Assert.assertNotNull(result);  
		System.out.println("passwordmodify");
		
	}

}
