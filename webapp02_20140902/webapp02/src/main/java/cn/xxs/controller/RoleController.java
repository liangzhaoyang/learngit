package cn.xxs.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xxs.entity.Role;
import cn.xxs.service.RoleService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="getAllRoles")
	@ResponseBody
	public List<Role> getAllRoles() {
		List<Role> roles = roleService.search(new HashMap<String, Object>()); 
		return roles;
	}
}
