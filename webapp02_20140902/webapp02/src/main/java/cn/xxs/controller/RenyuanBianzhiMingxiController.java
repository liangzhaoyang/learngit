package cn.xxs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.RenyuanBianzhiMingxi;
import cn.xxs.entity.User;
import cn.xxs.service.RenyuanBianzhiMingxiService;

@Controller
@RequestMapping("/")
public class RenyuanBianzhiMingxiController 
{
	@Autowired
	private RenyuanBianzhiMingxiService renyuanBianzhiMingxiService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	private static Logger log=Logger.getLogger(ShemiPersonController.class);
	@RequestMapping(value = "xzglbmrybzqkwh")
	public ModelAndView xzglbmrybzqkwh(HttpServletRequest request, HttpServletResponse response, Model model) {

		return new ModelAndView("bianzhimingxi/renyuanBianzhiMingxi");
	}

	@RequestMapping(value="rybzmx")
	@ResponseBody
	public Map<String, Object> rybzmx(HttpSession session) throws IOException {
		User user = (User)session.getAttribute("user");
		log.debug("orgid:"+user.getOrgid());
	
		List<RenyuanBianzhiMingxi> map = renyuanBianzhiMingxiService.getAllBianzhi(user.getOrgid());

		Map<String, Object> json = new HashMap<String, Object>();
		for(RenyuanBianzhiMingxi temp:map)
		{
			json.put("data"+temp.getCode(), temp.getNum());
		}
		json.put("success", true);
		return json;
	}

	void addtoList(List<RenyuanBianzhiMingxi> lst, int orgid, int code, String valueInString) throws Exception {
		RenyuanBianzhiMingxi entity = new RenyuanBianzhiMingxi();
		entity.setOrgid(orgid);
		entity.setCode(code);
		int val = 0;
		try {
			val = Integer.parseInt(valueInString);
		} catch(NumberFormatException e) {
			throw new Exception("输入内容中存在非数字内容，请确认。");
		}
		entity.setNum(val);
		lst.add(entity);
	}
	@RequestMapping(value="rybzmxbc", method=RequestMethod.POST)
	@ResponseBody
	public void rybzmxbc(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		User user = (User)session.getAttribute("user");
		List<RenyuanBianzhiMingxi> lst = new ArrayList<RenyuanBianzhiMingxi>();
		int orgid = user.getOrgid();
		for(int i = 101; i <=115; ++i) {
			try {
				addtoList(lst, orgid, i, request.getParameter("num" + (i - 100)));
			} catch(Exception e) {
				out.print(e.getMessage());
				return;
			}
		}
		
		try {
			renyuanBianzhiMingxiService.saveRenyuanBianzhi(orgid, lst);
		} catch(Exception e) {
			log.debug(e.getMessage());
			out.print("人员编制信息保存失败。");
			return;
		}
		
		out.print("人员编制信息保存成功");
	}
}