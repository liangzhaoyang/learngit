package cn.xxs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.Role;
import cn.xxs.entity.User;
import cn.xxs.service.RoleService;
import cn.xxs.service.UserService;

@Controller
public class TestController {
	@RequestMapping(value="showMain")
	public ModelAndView showMain(HttpSession session, Model model) {
		ModelAndView view = new ModelAndView("sys/mainFrame");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "admin");
		map.put("password", "E10ADC3949BA59ABBE56E057F20F883E");
		User user = userService.selectForLogin(map);
		user.setPassword(null);
		model.addAttribute("user", user);
		
		List<Role> roles = roleService.queryRolesByUserId(user.getId());
		model.addAttribute("roleList", roles);
		//将用户信息存在session中
		session.putValue("user", user);
		return view;
	}
		
	@RequestMapping(value="subWndTest")
	public String subWndTest() {
		return "subWnd";
	}
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
}
