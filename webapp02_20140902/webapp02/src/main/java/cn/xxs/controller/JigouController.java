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
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.OrgnizationCondition;
import cn.xxs.entity.Person;
import cn.xxs.entity.RenyuanBianzhiMingxi;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.User;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.entity.ZaibianRenyuanMingxi;
import cn.xxs.paging.PageContext;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.ExportService;
import cn.xxs.service.GanbuService;
import cn.xxs.service.LeaderService;
import cn.xxs.service.OrgnizationService;
import cn.xxs.service.RenyuanBianzhiMingxiService;
import cn.xxs.service.ResumeService;
import cn.xxs.service.ShemiService;
import cn.xxs.service.ZaibianRenyuanMingxiService;
import cn.xxs.utility.BaseController;

@Controller
@RequestMapping("/")
public class JigouController extends BaseController {
	private Logger logger = Logger.getLogger(JigouController.class);
	@Autowired
	private OrgnizationService orgService;
	@Autowired
	private ConstantsService constantService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private RenyuanBianzhiMingxiService renyuanBianzhiMingxiService;
	@Autowired
	private ZaibianRenyuanMingxiService zaibianRenyuanMingxiService;
	@Autowired
	private ShemiService shemiService;
	@Autowired
	private GanbuService ganbuService;
	@Autowired
	private LeaderService leaderService;
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private ExportService exportService;

	@RequestMapping(value = "jgcx")
	public ModelAndView jigouBasicLoad(HttpSession session, Model model) {
		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");
		//
		model.addAttribute("jglbList", jglbs);
		model.addAttribute("xzjbList", xzjbs);
		model.addAttribute("zfxlList", zfxls);
		return new ModelAndView("orgnizationmanage/orgnizationbasicinfo");
	}

	// 机构详情
	@RequestMapping(value = "JigouXiangqing")
	public ModelAndView jigouXiangqingInfo(HttpSession session, String id,
			Model model) {
		logger.debug("#######################当前操作的机构id：" + id);
		// 将用户信息存在session中
		session.setAttribute("orgid", id);
		int orgid = Integer.parseInt(id);

		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		Orgnization org = orgService.getOrgnizationById(orgid);
		if (org.getJigouleibie() > 0)
			model.addAttribute("jigouleibie",
					jglbs.get(org.getJigouleibie() - 1).getName());
		else
			model.addAttribute("jigouleibie", "");
		if (org.getXingzhengjibie() > 0)
			model.addAttribute("xingzhengjibie",
					xzjbs.get(org.getXingzhengjibie() - 1).getName());
		else
			model.addAttribute("xingzhengjibie", "");
		if (org.getBianzhishu() > 0)
			model.addAttribute("bianzhirenshu",
					org.getBaomiganbubianzhirenshu());
		else
			model.addAttribute("bianzhirenshu", "");

		List<ZaibianRenyuanMingxi> zbrys = zaibianRenyuanMingxiService
				.getAllZaibian(orgid);
		List<RenyuanBianzhiMingxi> rybzs = renyuanBianzhiMingxiService
				.getAllBianzhi(orgid);

		for (RenyuanBianzhiMingxi temp : rybzs) {
			model.addAttribute("rybz" + temp.getCode(), temp.getNum());
		}
		//
		for (ZaibianRenyuanMingxi temp : zbrys) {
			model.addAttribute("zbry" + temp.getCode(), temp.getNum());
		}
		return new ModelAndView("orgnizationmanage/orgnizationdetailinfo");
	}

	@RequestMapping(value = "JgcxInfo")
	@ResponseBody
	public Map<String, Object> jigouChaxunLoad(HttpSession session) {
		// 获取会话中的用户机构的id
		String id = (String) session.getAttribute("orgid");

		logger.debug("-----------------" + id);
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt(id));
		Orgnization org = (Orgnization) map.get("orgnization");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");
		// List<SelectItem> jcsss=constantService.getSelectListByType("基础设施类别");

		// 机构信息
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("chenglishijian", org.getChenglishijianString());
		map1.put("orgname", org.getOrgname());
		if (org.getSuorgid() != null)
			map1.put("suorgid", orgService.getOrgnizationById(org.getSuorgid())
					.getOrgname());
		if (org.getXingzhengjibie() > 0)
			map1.put("xingzhengjibie", xzjbs.get(org.getXingzhengjibie() - 1)
					.getName());
		map1.put("jingfeilaiyuan", org.getJingfeilaiyuan());
		if (org.getZhengfuxulie() > 0)
			map1.put("zhengfuxulie", zfxls.get(org.getZhengfuxulie() - 1)
					.getName());
		if (org.getJigouleibie() > 0)
			map1.put("jigouleibie", jglbs.get(org.getJigouleibie() - 1)
					.getName());
		list.add(map1);

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 行政部门信息查询
	@RequestMapping(value = "jgcxxzbm")
	@ResponseBody
	public Map<String, Object> xingzhengBumenChaxun(HttpSession session) {
		// 获取会话中的用户机构的id
		String id = (String) session.getAttribute("orgid");

		logger.debug("-----------------" + id);
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt(id));
		List<XingzhengBumen> xzbmList = (List<XingzhengBumen>) map
				.get("xingzhengBumen");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// List<SelectItem> jglbs=constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		// List<SelectItem> zfxls=constantService.getSelectListByType("政府序列");
		// List<SelectItem> jcsss=constantService.getSelectListByType("基础设施类别");

		// 机构信息
		Map<String, Object> map1 = new HashMap<String, Object>();

		for (XingzhengBumen xzbm : xzbmList) {
			// 行政部门
			map1 = new HashMap<String, Object>();
			map1.put("xzname", xzbm.getName());
			map1.put("xzbianzhirenshu", xzbm.getBianzhirenshu());
			map1.put("xzshijirenshu", xzbm.getShijirenshu());
			map1.put("xzxingzhengjibie", xzjbs
					.get(xzbm.getXingzhengjibie() - 1).getName());
			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 事业部门信息查询
	@RequestMapping(value = "jgcxsybm")
	@ResponseBody
	public Map<String, Object> shiyeBumenChaxun(HttpSession session) {
		// 获取会话中的用户机构的id
		String id = (String) session.getAttribute("orgid");

		logger.debug("-----------------" + id);
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt(id));
		List<ShiyeDanwei> sydwList = (List<ShiyeDanwei>) map.get("shiyeDanwei");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// List<SelectItem> jglbs=constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		// List<SelectItem> zfxls=constantService.getSelectListByType("政府序列");
		// List<SelectItem> jcsss=constantService.getSelectListByType("基础设施类别");

		// 机构信息
		Map<String, Object> map1 = new HashMap<String, Object>();

		for (ShiyeDanwei sydw : sydwList) {
			// 事业单位
			map1 = new HashMap<String, Object>();
			map1.put("syname", sydw.getName());
			map1.put("sybianzhirenshu", sydw.getBianzhirenshu());
			map1.put("syshijirenshu", sydw.getShijirenshu());
			map1.put("syxingzhengjibie", xzjbs
					.get(sydw.getXingzhengjibie() - 1).getName());
			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 管理机构信息查询
	@RequestMapping(value = "jgcxgljg")
	@ResponseBody
	public Map<String, Object> guanliJigouChaxun(HttpSession session) {
		// 获取会话中的用户机构的id
		String id = (String) session.getAttribute("orgid");

		logger.debug("-----------------" + id);
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt(id));
		List<GuanliJigou> gljgList = (List<GuanliJigou>) map.get("guanliJigou");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// List<SelectItem> jglbs=constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		// List<SelectItem> zfxls=constantService.getSelectListByType("政府序列");
		// List<SelectItem> jcsss=constantService.getSelectListByType("基础设施类别");

		// 机构信息
		Map<String, Object> map1 = new HashMap<String, Object>();
		for (GuanliJigou gljg : gljgList) {
			// 管理机构
			map1 = new HashMap<String, Object>();
			map1.put("glname", gljg.getName());
			map1.put("glbianzhirenshu", gljg.getBianzhirenshu());
			map1.put("glshijirenshu", gljg.getShijirenshu());
			map1.put("glxingzhengjibie", xzjbs
					.get(gljg.getXingzhengjibie() - 1).getName());
			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 基础设施信息查询
	@RequestMapping(value = "jgcxjcss")
	@ResponseBody
	public Map<String, Object> jichuSheshiChaxun(HttpSession session) {
		// 获取会话中的用户机构的id
		String id = (String) session.getAttribute("orgid");

		logger.debug("-----------------" + id);
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt(id));
		List<JichuSheshi> jcssList = (List<JichuSheshi>) map.get("jichuSheshi");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<SelectItem> jcsss = constantService.getSelectListByType("基础设施类别");

		for (JichuSheshi jcss : jcssList) {
			// 基础设施
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("jcid", jcss.getId());
			map1.put("jcname", jcss.getName());
			if (jcss.getLeibie() > 0)
				map1.put("jcleibie", jcsss.get(jcss.getLeibie() - 1).getName());
			else
				map1.put("jcleibie", "");
			if (jcss.getMianji() > 0)
				map1.put("jcmianji", jcss.getMianji());
			else
				map1.put("jcmianji", "");
			if (jcss.getTouruzijin() > 0)
				map1.put("jctouruzijin", jcss.getTouruzijin());
			else
				map1.put("jctouruzijin", "");
			if (!jcss.getJianshedanwei().equals("#"))
				map1.put("jcjianshedanwei", jcss.getJianshedanwei());
			else
				map1.put("jcjianshedanwei", "");
			// map1.put("jcjianshedanwei", jcss.getJianshedanwei());
			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 机构日期查询
	@RequestMapping(value = "jigouchaxunriqi")
	@ResponseBody
	public Map<String, Object> getInfo(HttpSession session) {
		// 获取会话中的用户机构的id
		String orgid = (String) session.getAttribute("orgid");
		Orgnization org = orgService
				.getOrgnizationById(Integer.parseInt(orgid));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shenheren", org.getShenheren());
		map.put("tianbiaoriqi", org.getTianbiaoriqiString());
		map.put("tianbiaoren", org.getTianbiaoren());
		map.put("success", true);

		return map;
	}

	@RequestMapping(value = "JigouChaxunQuery")
	@ResponseBody
	public Map<String, Object> jigouChaxunQuery(
			@ModelAttribute OrgnizationCondition orgCon, int page, int rows) {
		logger.debug("condition:" + orgCon.getXingzhengjibie() + ","
				+ orgCon.getZhengfuxulie() + "---" + orgCon.getJigouleibie()
				+ "," + orgCon.getOrgname() + page + "---" + rows);
		PageContext pageContext = PageContext.getContext();

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");
		//
		List<Orgnization> orgs = orgService.getJigouJibenInfo(orgCon);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//
		for (Orgnization temp : orgs) {
			logger.debug(temp.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", temp.getId());
			map.put("orgname", temp.getOrgname());
			if (temp.getJigouleibie() > 0)
				map.put("jigouleibie", jglbs.get(temp.getJigouleibie() - 1)
						.getName());
			else
				map.put("jigouleibie", "");
			map.put("chenglishijian", temp.getChenglishijianString());
			if (temp.getXingzhengjibie() > 0)
				map.put("xingzhengjibie",
						xzjbs.get(temp.getXingzhengjibie() - 1).getName());
			else
				map.put("xingzhengjibie", "");
			map.put("jingfeilaiyuan", temp.getJingfeilaiyuan());
			if (temp.getZhengfuxulie() > 0)
				map.put("zhengfuxulie", zfxls.get(temp.getZhengfuxulie() - 1)
						.getName());
			else
				map.put("zhengfuxulie", "");
			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);

		return json;
	}

	// 保密领导班子查询
	@RequestMapping(value = "jgcxbmjldbz")
	@ResponseBody
	public Map<String, Object> baomiLingdaoBanziChaxun(HttpSession session,
			int page, int rows) {
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));
		logger.debug("#############机构ID" + orgid);
		PageContext pageContext = PageContext.getContext();

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<Person> persons = leaderService.selectAllLeader(orgid);

		List<SelectItem> sfzzs = constantService.getSelectListByType("是否专职");
		List<SelectItem> jszcs = constantService.getSelectListByType("技术职称");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别");
		List<SelectItem> zws = constantService.getSelectListByType("职务");
		List<SelectItem> xbs = constantService.getSelectListByType("性别");
		List<SelectItem> xls = constantService.getSelectListByType("学历");
		List<SelectItem> zzmms = constantService.getSelectListByType("政治面貌");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Person e : persons) {
			logger.debug("人员信息长度：" + persons.size() + "###"
					+ e.getDanweimingcheng());

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("zhiwu", zws.get(e.getZhiwu() - 1).getName());
			map.put("name", e.getName());
			map.put("xingbie", xbs.get(e.getXingbie() - 1).getName());
			map.put("chushengnianyue", e.getChushengnianyue());
			if (e.getZhengzhimianmao() == 1)
				map.put("zhengzhimianmao", zzmms.get(0).getName());
			else
				map.put("zhengzhimianmao", zzmms.get(1).getName());
			// map.put("zhengzhimianmao",
			// zzmms.get((int) e.getZhengzhimianmao() - 1).getName());
			map.put("zhuanye", e.getZhuanye());
			map.put("xueli", xls.get(e.getXueli() - 1).getName());
			if (e.getXingzhengjibie() == 99)
				map.put("xingzhengjibie", "其他");
			else
				map.put("xingzhengjibie", xzjbs
						.get((int) e.getXingzhengjibie()).getName());
			map.put("jishuzhicheng", jszcs.get((int) e.getJishuzhicheng() - 1)
					.getName());
			map.put("renmingdanwei", e.getRenmingdanwei());
			map.put("shifouzhuanzhi", sfzzs.get(e.getShifouzhuanzhi() - 1)
					.getName());
			map.put("jianrenqitazhiwu", e.getJianrenqitazhiwu());
			map.put("kaishiriqi", e.getKaishiriqi());
			map.put("suoshubumen", e.getSuoshubumen());
			map.put("danweimingcheng", e.getDanweimingcheng());

			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);

		return json;
	}

	// 保密干部查询
	@RequestMapping(value = "jgcxbmgb")
	@ResponseBody
	public Map<String, Object> baomiGanbuChaxun(HttpSession session, int page,
			int rows) {
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));
		logger.debug("-----------------" + orgid);

		PageContext pageContext = PageContext.getContext();

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		Orgnization org = orgService.getOrgnizationById(orgid);
		List<Person> persons = ganbuService.selectAllGanbu(orgid);
		logger.debug("###########" + persons.size());

		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> sfzzs = constantService.getSelectListByType("是否专职");
		List<SelectItem> jszcs = constantService.getSelectListByType("技术职称");
		List<SelectItem> xzjb2s = constantService.getSelectListByType("行政级别");
		List<SelectItem> xzjb1s = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zws = constantService.getSelectListByType("职务");
		List<SelectItem> xbs = constantService.getSelectListByType("性别");
		List<SelectItem> xls = constantService.getSelectListByType("学历");
		List<SelectItem> zzmms = constantService.getSelectListByType("政治面貌");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Person e : persons) {
			Map<String, Object> map = new HashMap<String, Object>();

			// 机构信息
			map.put("jigouleibie", jglbs.get(org.getJigouleibie() - 1)
					.getName());
			map.put("xingzhengjibie1", xzjb1s.get(org.getXingzhengjibie() - 1)
					.getName());
			map.put("bianzhirenshu", org.getBianzhishu());
			// 干部人员信息
			map.put("zhiwu", zws.get(e.getZhiwu() - 1).getName());
			map.put("name", e.getName());
			map.put("xingbie", xbs.get(e.getXingbie() - 1).getName());
			map.put("chushengnianyue", e.getChushengnianyue());
			if (e.getZhengzhimianmao() == 1)
				map.put("zhengzhimianmao", zzmms.get(0).getName());
			else
				map.put("zhengzhimianmao", zzmms.get(1).getName());
			// map.put("zhengzhimianmao",
			// zzmms.get((int) e.getZhengzhimianmao() - 1).getName());
			map.put("zhuanye", e.getZhuanye());
			map.put("xueli", xls.get(e.getXueli() - 1).getName());
			// map.put("shemidengji",
			// smdjs.get((int)e.getShemidengji()-1).getName());
			map.put("xingzhengjibie2", xzjb2s.get((int) e.getXingzhengjibie())
					.getName());
			map.put("jishuzhicheng", jszcs.get((int) e.getJishuzhicheng() - 1)
					.getName());
			// map.put("renmingdanwei", e.getRenmingdanwei());
			map.put("shifouzhuanzhi", sfzzs.get(e.getShifouzhuanzhi() - 1)
					.getName());
			map.put("jianrenqitazhiwu", e.getJianrenqitazhiwu());
			map.put("kaishiriqi", e.getKaishiriqi());
			map.put("tel", e.getTel());
			map.put("mobilenum", e.getMobilenum());
			map.put("suoshubumen", e.getSuoshubumen());
			map.put("danweimingcheng", e.getDanweimingcheng());

			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 涉密信息信息查询
	@RequestMapping(value = "jgcxsmrycx")
	@ResponseBody
	public Map<String, Object> shemiRenyuanChaxun(HttpSession session,
			int page, int rows) {
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));
		logger.debug("-----------------" + orgid);

		PageContext pageContext = PageContext.getContext();

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<Person> persons = shemiService.selectAllShemiRenyuan(orgid);

		List<SelectItem> rylxs = constantService.getSelectListByType("人员类型");
		List<SelectItem> sfyhbmrys = constantService
				.getSelectListByType("是否要害部门人员");
		// List<SelectItem> sfzzs=constantService.getSelectListByType("是否专职");
		List<SelectItem> jszcs = constantService.getSelectListByType("技术职称");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别");
		// List<SelectItem> zws=constantService.getSelectListByType("职务");
		List<SelectItem> xbs = constantService.getSelectListByType("性别");
		List<SelectItem> xls = constantService.getSelectListByType("学历");
		List<SelectItem> zzmms = constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> mzs = constantService.getSelectListByType("民族");
		List<SelectItem> smdjs = constantService.getSelectListByType("涉密等级");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Person e : persons) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", e.getName());
			map.put("xingbie", xbs.get(e.getXingbie() - 1).getName());
			map.put("chushengnianyue", e.getChushengnianyue());
			map.put("minzu", mzs.get(e.getMinzu() - 1).getName());
			map.put("zhengzhimianmao",
					zzmms.get((int) e.getZhengzhimianmao() - 1).getName());
			map.put("xueli", xls.get(e.getXueli() - 1).getName());
			map.put("shemidengji", smdjs.get((int) e.getShemidengji() - 1)
					.getName());
			if (e.getXingzhengjibie() == 99)
				map.put("xingzhengjibie", "其他");
			else
				map.put("xingzhengjibie", xzjbs
						.get((int) e.getXingzhengjibie()).getName());
			map.put("jishuzhicheng", jszcs.get((int) e.getJishuzhicheng() - 1)
					.getName());
			map.put("yaohaibumengrenyuan",
					sfyhbmrys.get((int) e.getYaohaibumengrenyuan() - 1)
							.getName());
			map.put("renyuanleixing", rylxs
					.get((int) e.getRenyuanleixing() - 1).getName());
			map.put("suoshubumen", e.getSuoshubumen());
			map.put("danweimingcheng", e.getDanweimingcheng());

			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);
		// model.addAttribute("org", org);
		return json;
	}

	// 简历信息查询
	@RequestMapping(value = "jgcxjl")
	@ResponseBody
	public Map<String, Object> jianliXinxiChaxun(HttpSession session, int page,
			int rows) {
		// 获取会话中的用户机构的id
		String orgid = (String) session.getAttribute("orgid");
		logger.debug("-----------------" + orgid);

		PageContext pageContext = PageContext.getContext();

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<Person> persons = resumeService.getAllResume(orgid);

		List<SelectItem> mzs = constantService.getSelectListByType("民族");
		List<SelectItem> xbs = constantService.getSelectListByType("性别");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Person e : persons) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("jlid", e.getId());
			map.put("jlname", e.getName());
			if (e.getXingbie() > 0)
				map.put("jlxingbie", xbs.get(e.getXingbie() - 1).getName());
			else
				map.put("jlxingbie", "");
			map.put("jlchushengnianyue", e.getChushengnianyue());
			if (e.getMinzu() > 0)
				map.put("jlminzu", mzs.get(e.getMinzu() - 1).getName());
			else
				map.put("jlminzu", "");
			map.put("jlxianrenzhuiwu", e.getXianrenzhuiwu());
			map.put("jlzhuanyejishuzhiwu", e.getZhuanyejishuzhiwu());
			map.put("jlcanjiagongzuoshijian", e.getCanjiagongzuoshijian());

			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);

		return json;
	}

	// 机构审核
	@RequestMapping(value = "jgsh")
	public ModelAndView jigouShenheBasicLoad(HttpSession session, Model model) {
		//
		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");

		model.addAttribute("jglbList", jglbs);
		model.addAttribute("xzjbList", xzjbs);
		model.addAttribute("zfxlList", zfxls);
		return new ModelAndView("orgnizationmanage/orgnizationaudit");
	}

	// 机构审核数据加载
	@RequestMapping(value = "JigouShenheQuery")
	@ResponseBody
	public Map<String, Object> jigouShenheQuery(
			@ModelAttribute OrgnizationCondition orgCon, int page, int rows) {

		PageContext pageContext = PageContext.getContext();
		logger.debug("#######page:" + page + "#######rows:" + rows);

		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);

		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");
		// OrgnizationCondition orgCon=new OrgnizationCondition();
		List<Orgnization> orgs = orgService.getDaiShenheInfo(orgCon);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (Orgnization temp : orgs) {
			logger.debug(temp.toString());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", temp.getId());
			map.put("orgname", temp.getOrgname());
			if (temp.getJigouleibie() > 0)
				map.put("jigouleibie", jglbs.get(temp.getJigouleibie() - 1)
						.getName());
			map.put("chenglishijian", temp.getChenglishijianString());
			if (temp.getXingzhengjibie() > 0)
				map.put("xingzhengjibie",
						xzjbs.get(temp.getXingzhengjibie() - 1).getName());
			map.put("jingfeilaiyuan", temp.getJingfeilaiyuan());
			if (temp.getZhengfuxulie() > 0)
				map.put("zhengfuxulie", zfxls.get(temp.getZhengfuxulie() - 1)
						.getName());
			list.add(map);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);
		logger.debug("#######total:" + pageContext.getTotalRows()
				+ "#######pages:" + (pageContext.getTotalRows() + rows - 1)
				/ rows);

		return json;
	}

	// 机构审计详情
	@RequestMapping(value = "JigouShenjiXiangqing")
	public ModelAndView jigouShejiXiangqingInfo(HttpSession session, String id,
			Model model) {
		logger.debug("当前操作的机构id：" + id);
		// 将用户信息存在session中
		session.setAttribute("orgid", id);
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");

		int orgid = Integer.parseInt(id);
		Orgnization org = orgService.getOrgnizationById(orgid);
		if (org.getJigouleibie() > 0)
			model.addAttribute("jigouleibie",
					jglbs.get(org.getJigouleibie() - 1).getName());
		else
			model.addAttribute("jigouleibie", "");
		if (org.getXingzhengjibie() > 0)
			model.addAttribute("xingzhengjibie",
					xzjbs.get(org.getXingzhengjibie() - 1).getName());
		else
			model.addAttribute("xingzhengjibie", "");
		if (org.getBianzhishu() > 0)
			model.addAttribute("bianzhirenshu",
					org.getBaomiganbubianzhirenshu());
		else
			model.addAttribute("bianzhirenshu", "");

		List<ZaibianRenyuanMingxi> zbrys = zaibianRenyuanMingxiService
				.getAllZaibian(orgid);
		List<RenyuanBianzhiMingxi> rybzs = renyuanBianzhiMingxiService
				.getAllBianzhi(orgid);

		for (RenyuanBianzhiMingxi temp : rybzs) {
			model.addAttribute("rybz" + temp.getCode(), temp.getNum());
		}
		//
		for (ZaibianRenyuanMingxi temp : zbrys) {
			model.addAttribute("zbry" + temp.getCode(), temp.getNum());
		}

		return new ModelAndView("orgnizationmanage/orgnizationauditdetail");
	}

	// 机构审核信息提交
	@RequestMapping(value = "doJigouShenhe", method = RequestMethod.POST)
	@ResponseBody
	public void ShenheXinxiSave(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String shenheren = request.getParameter("shenheren");
			String st = request.getParameter("status");
			if (!shenheren.equals("")) {
				Orgnization org1 = new Orgnization();
				org1.setShenheren(shenheren);
				org1.setStatus(Integer.parseInt(st));
				org1.setId(Integer.parseInt((String) session
						.getAttribute("orgid")));
				orgService.setShenheInfo(org1);
			}
		} catch (Exception e) {
			transactionManager.rollback(status);
			map.put("errMsg", e.getMessage());
			out.print(map.toString());
			return;
		}

		transactionManager.commit(status);
		map.put("errMsg", "");
		out.print("success");
		return;
	}
	//导出机构信息
	@RequestMapping(value ="jgcxbmxzdc")
	@ResponseBody
	public ModelAndView baomijuXinxiDaochu(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("###########导出机构信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));
		
		exportService.exportOrgInfo(request, response,orgid);
		
		return null;
	}
	// 导出领导班子信息
	@RequestMapping(value = "jgcxldbzdc")
	@ResponseBody
	public ModelAndView lingdaoBanziXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出领导班子信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));
		
		exportService.exportLdbzInfo(request, response, orgid);

		return null;
	}

	// 导出在编人员信息
	@RequestMapping(value = "jgcxzbrydc")
	@ResponseBody
	public ModelAndView zaibianRenyuanXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出在编人员信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));

		exportService.exportZbryInfo(request, response, orgid);

		return null;
	}

	// 导出人员编制信息
	@RequestMapping(value = "jgcxrybzdc")
	@ResponseBody
	public ModelAndView renyuanBianzhiXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出人员编制信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));

		exportService.exportRybzInfo(request, response, orgid);

		return null;
	}

	// 导出保密干部信息
	@RequestMapping(value = "jgcxbmgbdc")
	@ResponseBody
	public ModelAndView baomiGanbuXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出保密干部信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));

		exportService.exportBmgbInfo(request, response, orgid);

		return null;
	}

	// 导出涉密人员信息
	@RequestMapping(value = "jgcxsmrydc")
	@ResponseBody
	public ModelAndView shemiRenyuanXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出涉密人员信息###########");
		// 获取会话中的用户机构的id
		int orgid = Integer.parseInt((String) session.getAttribute("orgid"));

		exportService.exportSmryInfo(request, response, orgid);

		return null;
	}

	// 导出简历信息
	@RequestMapping(value = "jgcxjlxqdc")
	@ResponseBody
	public ModelAndView baomiXinxiDaochu(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("###########导出简历信息###########");
		// 获取会话中的人员的id
		int personid = Integer.parseInt((String) session.getAttribute("personid"));

		exportService.exportJlInfo(request, response, personid);

		return null;
	}
}
