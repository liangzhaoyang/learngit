package cn.xxs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.MenuItem;
import cn.xxs.service.MenuService;
import cn.xxs.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("currUser")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value="selectPage")
//	public ModelAndView selectPage(HttpServletRequest request,HttpServletResponse response) {
//		//可以将分页参数获取封装，已达到更好的复用效果。
//		//page=2&pageSize=10&totalPages=19&totalRows=188
//		String pagec = request.getParameter("page"); 
//		String pageSize = request.getParameter("pageSize"); 
//		String totalPages = request.getParameter("totalPages"); 
//		String totalRows = request.getParameter("totalRows"); 
//		
//		PageContext page = PageContext.getContext();
//		
//		if(null == pagec)
//		{
//			page.setCurrentPage(1);
//			page.setPageSize(2);
//		}
//		else{
//			page.setCurrentPage(Integer.parseInt(pagec));
//			page.setPageSize(Integer.parseInt(pageSize));
//			page.setTotalPages(Integer.parseInt(totalPages));
//			page.setTotalRows(Integer.parseInt(totalRows));
//		}
//		page.setPagination(true);
//
//		List<Role> users = null; // userService.selectPage();
//		
//		ModelAndView mv = new ModelAndView("show");
//		mv.addObject("userList", users);
//		mv.addObject("page",page);
//		return mv;
//	}
	
//	@RequestMapping(value="main", method=RequestMethod.GET)
//	public String welcome(HttpSession session) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", "administrator");
//		map.put("password", "E10ADC3949BA59ABBE56E057F20F883E");
//		session.setAttribute("user", userService.selectForLogin(map));
//		List<Role> roles = null; //userService.get("sunlight");
//		session.setAttribute("roles", roles);
//		Role role = new Role();
//		role.setId(roles.get(0).getId());
//		session.setAttribute("curRole", role);
//		return "main";
//	}

	@RequestMapping(value="changeRole.do", method=RequestMethod.GET)
	public ModelAndView changeRole(@PathParam("roleId") int roleId, HttpSession session, ModelAndView model) {
		List<MenuItem> menus = menuService.getMenusByRoleId(roleId);
		_logger.debug("menusize:" + menus.size());
		model.addObject("menus", menus);
		return new ModelAndView("common/left").addObject("menus", menus);
	}

//	@RequestMapping(value="redirect", method=RequestMethod.GET)
//	public String redirect(@PathParam("path") String path, HttpSession session, ModelAndView model) {
//		//model.addObject("curRole", session.getAttribute("curRole"));
//		return path;
//	}

	@RequestMapping("showDialog")
	public ModelAndView showDialog(String url) {
		_logger.debug("url:" + url);
		return new ModelAndView("common/wrapper").addObject("url", url.substring(1, url.length()-1));
	}

	private static Logger _logger = Logger.getLogger(MenuController.class);
}
