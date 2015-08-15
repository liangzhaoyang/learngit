package cn.xxs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.Orgnization;
import cn.xxs.entity.Role;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.UserCondition;
import cn.xxs.paging.PageContext;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.OrgnizationService;
import cn.xxs.service.RoleService;
import cn.xxs.service.UserService;

@Controller
@RequestMapping("/")
public class UserGrantController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private ConstantsService constantService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	private static Logger log = Logger.getLogger(ShemiPersonController.class);
	@RequestMapping(value = "sqgl")
	public ModelAndView ShemiRenyuanLoad(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return new ModelAndView("auth/authManage");
	}
	// 授权查询action
	@RequestMapping(value="sqcx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userQuery(@ModelAttribute UserCondition condition, int page, int rows) throws IOException {
		log.debug("condition:" + condition.toString());
		PageContext pageContext = PageContext.getContext();
		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);
		List<Map<String, Object>> users = userService.selectUsersByConditionPage(condition);
		List<SelectItem> yhzts=constantService.getSelectListByType("用户状态");
		
		for(Map<String, Object> userI : users) {
			StringBuffer sb = new StringBuffer("");
			List<Role> roles = roleService.queryRolesByUserId((String) userI.get("id"));
			log.debug("roles size:" + roles.size());
			for(Role role : roles) {
				sb.append(role.getName()).append(" ");
			}
			userI.put("roles", sb.toString());
			
			if(userI.get("orgid") != null) {
				Orgnization org = orgnizationService.getOrgnizationById((int)userI.get("orgid"));
				if(org != null) {
					userI.put("orgName", org.getOrgname());
				}
			}
			userI.put("status",yhzts.get((int) userI.get("status")).getName());
		}
	
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", users);

		return json;
	}
	//授权action
	@RequestMapping(value="sq", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> CancleGrant(@RequestBody UserCondition condition) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.userGrant(condition.getIds());
		} catch(Exception e) {
			map.put("message", "授权失败");
			transactionManager.rollback(status);
			return map;
		}
		
		transactionManager.commit(status);
		
		map.put("message", "授权成功"); 

		return map;
	}


	// 解除授权action
	@RequestMapping(value="jcsq", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> UserGrant(@RequestBody UserCondition condition) {
		log.debug("ids:" + condition.getIds());
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.cancelGrant(condition.getIds());
		} catch(Exception e) {
			map.put("message", "解除授权失败");
			transactionManager.rollback(status);
			return map;
		}
		
		transactionManager.commit(status);
		
		map.put("message", "解除授权成功"); 

		return map;
	}

}
