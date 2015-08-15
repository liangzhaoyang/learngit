package cn.xxs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.Log;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.Role;
import cn.xxs.entity.User;
import cn.xxs.entity.UserCondition;
import cn.xxs.paging.PageContext;
import cn.xxs.service.LogService;
import cn.xxs.service.OrgnizationService;
import cn.xxs.service.R_User_RoleService;
import cn.xxs.service.RoleService;
import cn.xxs.service.SequenceService;
import cn.xxs.service.UserService;
import cn.xxs.utility.BaseController;
import cn.xxs.utility.MD5;

@Controller
public class UserController extends BaseController {
	private static Logger _logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private R_User_RoleService r_user_roleService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private LogService logService;
	
	private final String defaultPasswd = MD5.md5("123456");

	@RequestMapping("login")
	public ModelAndView login() {
		return new ModelAndView("login").addObject("user", new User());
	}

	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpSession session, @ModelAttribute User user, BindingResult result, Model model) {
		_logger.debug("id:" + user.getId());
		_logger.debug("pass:" + user.getPassword());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("password", user.getPassword());
		User userGet = userService.selectForLogin(map);
		if (userGet == null) {
			Log log = new Log();
			log.setLogtime(new Timestamp(new Date().getTime()));
			log.setUserid(user.getId());
			log.setType((short)1);
			log.setDescriptions("登录验证失败");
			logService.insertLog(log);
			
			result.addError(new ObjectError("id", "用户名、密码或授权状态不正确"));
			return new ModelAndView("login").addObject("user", user);
		}
		userGet.setPassword(null);
		model.addAttribute("user", userGet);
		session.setAttribute("user", userGet);

		List<Role> roles = roleService.queryRolesByUserId(user.getId());
		model.addAttribute("roleList", roles);

		Log log = new Log();
		log.setLogtime(new Timestamp(new Date().getTime()));
		log.setUserid(user.getId());
		log.setType((short)1);
		log.setDescriptions("登录成功");
		logService.insertLog(log);

		return new ModelAndView("sys/mainFrame");
	}

	// 用户管理加载的action
	@RequestMapping("yhgl")
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return new ModelAndView("user/userManage");
	}

	@RequestMapping("mmxg")
	public String mmxg() {
		_logger.debug("in mmxg");
		return "sys/mmxg";
	}
	
	@RequestMapping(value="doMmxg", method=RequestMethod.POST)
	@ResponseBody
	public void doMmxg(	HttpServletRequest request, 
										HttpServletResponse response, 
										HttpSession session, 
										@ModelAttribute User userParam) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		User user = (User)session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(!userParam.getPassword().equals(userParam.getPasswordConfirm())) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("passwordConfirm", "新密码输入不一致，请重新输入。");
			map.put("errMsg", errMap);
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}
		
		Map<String, Object> mapForSearch = new HashMap<String, Object>();
		mapForSearch.put("id", user.getId());
		mapForSearch.put("password", userParam.getOldPassword());
		if(null == userService.selectForLogin(mapForSearch)) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("oldPassword", "旧密码输入错误，请重新输入。");
			map.put("errMsg", errMap);
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		User userForModifyPasswd = new User();
		userForModifyPasswd.setId(user.getId());
		userForModifyPasswd.setPassword(userParam.getPassword());
		try {
			userService.modifyPassword(userForModifyPasswd);
		} catch (Exception e) {
			map.put("dbErr", "数据写入失败。");
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		map.put("errMsg", null);
		out.print(new ObjectMapper().writeValueAsString(map));
		return;
	}

	// 用户查询和翻页的action
	@RequestMapping(value = "yhcx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userQuery(HttpServletRequest request, @ModelAttribute UserCondition condition, Integer page, Integer rows, String sort, String order) throws IOException {
		if(_logger.isDebugEnabled()) {
			_logger.debug("condition:" + condition.toString());
			_logger.debug("page:" + page + ", rows:" + rows + ", sort:" + sort + ", order:" + order);
		}
		
		PageContext pageContext = PageContext.getContext();
		
		if(sort != null && !sort.isEmpty() && order != null && !order.isEmpty()) {
			condition.setOrder(sort + " " + order);
		}
		if(page != null)
			pageContext.setCurrentPage(page);
		if(rows != null)
			pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<Map<String, Object>> users = userService.queryUsersPage(condition);

		for (Map<String, Object> userI : users) {
			StringBuffer sb = new StringBuffer("");
			List<Role> roles = roleService.queryRolesByUserId((String) userI.get("id"));
			_logger.debug("roles size:" + roles.size());
			for (Role role : roles) {
				sb.append(role.getName()).append(" ");
			}
			userI.put("roles", sb.toString());

			if (userI.get("orgid") != null) {
				Orgnization org = orgnizationService.getOrgnizationById((int) userI.get("orgid"));
				if (org != null) {
					userI.put("orgName", org.getOrgname());
				}
			}
		}

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
//		json.put("pages", (pageContext.getTotalRows() + pageContext.getPageSize() - 1) / pageContext.getPageSize());
		json.put("rows", users);
		_logger.debug("result:" + json);
		return json;
	}

	@RequestMapping(value = "yhtj")
	public ModelAndView addUser() {
		List<Role> roles = roleService.getAllRoles();
		return new ModelAndView("user/userAdd").addObject("roles", roles);
	}

	// 用户添加和保存用户信息
	Map<String, Object> checkUser(User user, boolean checkUserExisted) {
		Map<String, Object> errMap = new HashMap<String, Object>();
		String str = user.getId();
		if (str.isEmpty()) {
			errMap.put("id", "用户名不能为空。");
		} else if (str.length() > 30) {
			errMap.put("id", "用户名长度不能大于30。");
		} else if (checkUserExisted && userService.getUserById(user.getId()) != null) {
			errMap.put("id", "用户名已经存在。");
		}

		str = user.getName();
		if (str.isEmpty()) {
			errMap.put("name", "姓名不能为空。");
		} else if (str.length() > 30) {
			errMap.put("name", "姓名长度不能大于30。");
		}

		List<Integer> roleIds = user.getRoles();
		if (roleIds == null || roleIds.size() == 0) {
			errMap.put("roles", "用户角色不能为空。");
		} else {
			List<Role> roles = roleService.getAllRoles();
			for (Integer roleid : roleIds) {
				boolean found = false;
				for (Role role : roles) {
					if (role.getId() == roleid) {
						found = true;
						break;
					}
				}
				if (!found) {
					errMap.put("roles", "角色中存在不在角色列表中的值。");
					break;
				}
			}
		}

		str = user.getOrgName();
		if (!str.isEmpty() && str.length() > 30) {
			errMap.put("orgName", "机构名长度不能大于30。");
		}

		str = user.getSuOrgName();
		if (!str.isEmpty() && str.length() > 30) {
			errMap.put("suOrgName", "上级单位不能大于30。");
		}

		return (errMap.size() > 0) ? errMap : null;
	}

	@RequestMapping(value = "doyhtj", method = RequestMethod.POST)
	@ResponseBody
	public void doAddUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> errMap = null;
		try {
			errMap = checkUser(user, true);
		} catch (Exception e) {
			map.put("dbErr", "数据操作失败。");
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}
		if (errMap != null) {
			map.put("errMsg", errMap);
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			if (!user.getOrgName().isEmpty()) {
				Orgnization org = orgnizationService.getOrgnizationByName(user.getOrgName());
				if (org == null) {
					Orgnization suOrg = orgnizationService.getOrgnizationByName(user.getSuOrgName());
					if (suOrg == null) {
						suOrg = new Orgnization();
						suOrg.setId(sequenceService.getSequence("orgnization"));
						suOrg.setOrgname(user.getSuOrgName());
						orgnizationService.insertOrgnization(suOrg);
					}

					org = new Orgnization();
					org.setId(sequenceService.getSequence("orgnization"));
					org.setOrgname(user.getOrgName());
					org.setSuorgid(suOrg.getId());
					orgnizationService.insertOrgnization(org);
				}
				user.setOrgid(org.getId());
			}

			user.setPassword(defaultPasswd);

			userService.insertUser(user);

			r_user_roleService.insertRolesForUserid(user.getId(), user.getRoles());
		} catch (Exception e) {
			transactionManager.rollback(status);
			map.put("dbErr", "数据写入失败。");
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		transactionManager.commit(status);

		map.put("errMsg", null);
		out.print(new ObjectMapper().writeValueAsString(map));
		return;
	}

	@RequestMapping(value = "yhsc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delUsers(@RequestBody UserCondition condition) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.deleteUsers(condition.getIds());
		} catch (Exception e) {
			map.put("message", "删除失败");
			transactionManager.rollback(status);
			return map;
		}

		transactionManager.commit(status);

		map.put("message", "删除成功");

		return map;
	}

	@RequestMapping(value = "mmcz", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetPasswordForUsers(@RequestBody UserCondition condition) {
		userService.resetPasswordForUsers(condition.getIds(), defaultPasswd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "密码重置成功");

		return map;
	}

	// 加载修改用户的页面
	@RequestMapping(value = "yhxg")
	public ModelAndView loadUserByUseId(String id) {
		_logger.debug("userId:" + id);
		User user = userService.getUserById(id);
		if(user.getOrgid() != null) {
			Orgnization org = orgnizationService.getOrgnizationById(user.getOrgid());
			if(org != null) {
				user.setOrgName(org.getOrgname());
				if(org.getSuorgid() != null) {
					Orgnization suOrg = orgnizationService.getOrgnizationById(org.getSuorgid());
					if(suOrg != null) {
						user.setSuOrgName(suOrg.getOrgname());
					}
				}
			}
		}
		
		List<Role> rolesForUser = roleService.queryRolesByUserId(id);
		List<Role> rolesAll = roleService.getAllRoles();
		List<Map<String, Object>> roles = new ArrayList<Map<String, Object>>();
		for(Role role : rolesAll) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId());
			map.put("name",  role.getName());
			
			boolean found = false;
			for(Role roleForUser : rolesForUser) {
				if(roleForUser.getId() == role.getId()) {
					found = true;
					break;
				}
			}
			map.put("checked", found);
			roles.add(map);
		}
		return new ModelAndView("user/userEdit").addObject("user", user).addObject("roles", roles);
	}

	@RequestMapping(value = "doYhxg", method = RequestMethod.POST)
	@ResponseBody
	public void yhxg(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> errMap = null;
		try {
			errMap = checkUser(user, false);
		} catch (Exception e) {
			map.put("dbErr", "数据操作失败。");
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}
		if (errMap != null) {
			map.put("errMsg", errMap);
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			Integer suOrgId = null;
			if(user.getSuOrgName() != null && !user.getSuOrgName().isEmpty()) {
				Orgnization suOrg = orgnizationService.getOrgnizationByName(user.getSuOrgName());
				if (suOrg == null) {
					suOrgId = sequenceService.getSequence("orgnization");
					suOrg = new Orgnization();
					suOrg.setId(suOrgId);
					suOrg.setOrgname(user.getSuOrgName());
					orgnizationService.insertOrgnization(suOrg);
				} else {
					suOrgId = suOrg.getId();
				}
			}
			
			if (!user.getOrgName().isEmpty()) {
				Orgnization org = orgnizationService.getOrgnizationByName(user.getOrgName());
				if (org == null) {
					org = new Orgnization();
					org.setId(sequenceService.getSequence("orgnization"));
					org.setOrgname(user.getOrgName());
					org.setSuorgid(suOrgId);
					orgnizationService.insertOrgnization(org);
				} else {
					if(org.getSuorgid() != suOrgId) {
						org.setSuorgid(suOrgId);
						orgnizationService.UpdateOrgnization(org);
					}
				}
				user.setOrgid(org.getId());
			}

			user.setPassword(defaultPasswd);

			userService.updateUser(user);

			r_user_roleService.modifyRolesForUserId(user.getId(), user.getRoles());
		} catch (Exception e) {
			transactionManager.rollback(status);
			map.put("dbErr", "数据写入失败。");
			out.print(new ObjectMapper().writeValueAsString(map));
			return;
		}

		transactionManager.commit(status);

		map.put("errMsg", null);
		out.print(new ObjectMapper().writeValueAsString(map));
		return;
	}

	@RequestMapping(value = "userExistCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userExistCheck(@ModelAttribute User user) {
		User userSearched = userService.getUserById(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		if (userSearched != null) {
			map.put("message", "该用户名已经存在，请选择其他用户名。");
		} else {
			map.put("message", "");
		}
		return map;
	}
	
	@RequestMapping("quit")
	public void quit(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
			Log log = new Log();
			log.setLogtime(new Timestamp(new Date().getTime()));
			log.setUserid(user.getId());
			log.setType((short)3);
			log.setDescriptions("用户退出");
			logService.insertLog(log);
		}
	}
}
