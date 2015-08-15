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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.User;
import cn.xxs.entity.ZaibianRenyuanMingxi;
import cn.xxs.service.ZaibianRenyuanMingxiService;

@Controller
@RequestMapping("/")
public class ZaibianRenyuanController {

	@Autowired
	private ZaibianRenyuanMingxiService zaibianRenyuanMingxiService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	private static Logger log = Logger.getLogger(ShemiPersonController.class);

	@RequestMapping(value = "xzglbmzbryqkwh")
	public ModelAndView xzglbmzbryqkwh(HttpServletRequest request, HttpServletResponse response, Model model) {

		return new ModelAndView("bianzhimingxi/zaibianRenyuanMingxi");
	}

	// 在编人员明细加载
	@RequestMapping(value = "zbrymx")
	@ResponseBody
	public Map<String, Object> zbrymx(HttpSession session) throws IOException {
		User user = (User) session.getAttribute("user");
		log.debug("orgid:" + user.getOrgid());

		List<ZaibianRenyuanMingxi> map = zaibianRenyuanMingxiService.getAllZaibian(user.getOrgid());

		Map<String, Object> json = new HashMap<String, Object>();
		for (ZaibianRenyuanMingxi temp : map) {
			json.put("data" + temp.getCode(), temp.getNum());
		}
		json.put("success", true);
		return json;
	}

	void addtoList(List<ZaibianRenyuanMingxi> lst, int orgid, int code, String valueInString) throws Exception {
		ZaibianRenyuanMingxi entity = new ZaibianRenyuanMingxi();
		entity.setOrgid(orgid);
		entity.setCode(code);
		int val = 0;
		try {
			val = Integer.parseInt(valueInString);
		} catch (NumberFormatException e) {
			throw new Exception("输入内容中存在非数字内容，请确认。");
		}
		entity.setNum(val);
		lst.add(entity);
	}

	@RequestMapping(value = "zbrymxbc")
	@ResponseBody
	public void zbrymxbc(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("user");

		List<ZaibianRenyuanMingxi> lst = new ArrayList<ZaibianRenyuanMingxi>();
		int orgid = user.getOrgid();
		for (int i = 101; i <= 118; ++i) {
			try {
				addtoList(lst, orgid, i, request.getParameter("num" + (i - 100)));
			} catch (Exception e) {
				out.print(e.getMessage());
				return;
			}
		}

		try {
			zaibianRenyuanMingxiService.saveRenyuanBianzhi(orgid, lst);
		} catch (Exception e) {
			log.debug(e.getMessage());
			out.print("在编人员信息保存失败。");
			return;
		}

		out.print("在编人员信息保存成功");
	}
}
