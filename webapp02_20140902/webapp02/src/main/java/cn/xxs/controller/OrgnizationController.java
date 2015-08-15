package cn.xxs.controller;

import java.io.IOException;
import java.sql.Date;
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

import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.User;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.ExportService;
import cn.xxs.service.OrgnizationService;
import cn.xxs.utility.BaseController;

/*
 * 定义一个机构控制层类
 */

@Controller
@RequestMapping("/")
public class OrgnizationController extends BaseController {
	private static Logger logger = Logger
			.getLogger(OrgnizationController.class);
	@Autowired
	private OrgnizationService orgService;
	@Autowired
	private ConstantsService constantService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	private ExportService exportService;
	// 保密行政管理部门信息加载
	@RequestMapping("bmxzjgwh")
	public ModelAndView baomiXingzhengBumenWeihu(HttpSession session,
			Model model) {

		//
		List<SelectItem> jglbs = constantService.getSelectListByType("机构类别");
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<SelectItem> zfxls = constantService.getSelectListByType("政府序列");
		List<SelectItem> jifoufenlei_1s = constantService
				.getSelectListByType("机构分类1");
		List<SelectItem> jifoufenlei_2s = constantService
				.getSelectListByType("机构分类2");
		List<SelectItem> shengfens = constantService.getSelectListByType("省份");
		//
		model.addAttribute("jglbList", jglbs);
		model.addAttribute("xzjbList", xzjbs);
		model.addAttribute("zfxlList", zfxls);
		model.addAttribute("jgflList1", jifoufenlei_1s);
		model.addAttribute("jgflList2", jifoufenlei_2s);
		model.addAttribute("shengfenList", shengfens);
		// 机构基本信息
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();
		Orgnization org = orgService.getOrgnizationById(orgid);
		String zhuguanDanwei;
		if (org.getSuorgid() != null) {
			zhuguanDanwei = orgService.getOrgnizationById(org.getSuorgid())
					.getOrgname();
			model.addAttribute("zhuguanDanwei", zhuguanDanwei);
		}
		//
		Date date = new Date(System.currentTimeMillis());
		logger.debug("当前日期：" + date);

		model.addAttribute("orgnization", org);
		if (org.getTianbiaoren() != null)
			model.addAttribute("tianbiaoren", org.getTianbiaoren());
		else
			model.addAttribute("tianbiaoren", user.getName());
		if (org.getTianbiaoriqi() != null)
			model.addAttribute("tianbiaoriqi", org.getTianbiaoriqiString());
		else
			model.addAttribute("tianbiaoriqi", date);

		return new ModelAndView("departmentmanage/departmentmaintenance");
	}

	// 行政部门信息加载
	@RequestMapping(value = "bmxzxzbmcx")
	@ResponseBody
	public Map<String, Object> xingzhengBumenXinxiChaxun(HttpSession session) {
		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();

		// PageContext pageContext = PageContext.getContext();
		// pageContext.setCurrentPage(page);
		// pageContext.setPageSize(rows);
		// pageContext.setPagination(true);
		// 机构设置信息
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_1");
		List<XingzhengBumen> xzbm = orgService.getXingzhengBumenByOrg(orgid);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (XingzhengBumen temp : xzbm) {
			logger.debug(temp.toString());
			Map<String, Object> map1 = new HashMap<String, Object>();

			map1.put("name", temp.getName());
			map1.put("id", temp.getId());
			map1.put("orgid", temp.getOrgid());
			if (temp.getBianzhirenshu() > 0)
				map1.put("bianzhirenshu", temp.getBianzhirenshu());
			else
				map1.put("bianzhirenshu", "");
			if (temp.getShijirenshu() > 0)
				map1.put("shijirenshu", temp.getShijirenshu());
			else
				map1.put("shijirenshu", "");
			if (temp.getXingzhengjibie() > 0)
				map1.put("xingzhengjibie",
						xzjbs.get(temp.getXingzhengjibie() - 1).getName());
			else
				map1.put("xingzhengjibie", "");

			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);

		return json;
	}

	// 管理部门信息加载
	@RequestMapping(value = "bmxzglbmcx")
	@ResponseBody
	public Map<String, Object> guanliBumenXinxiChaxun(HttpSession session) {
		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();

		// PageContext pageContext = PageContext.getContext();
		// pageContext.setCurrentPage(page);
		// pageContext.setPageSize(rows);
		// pageContext.setPagination(true);
		// 机构设置信息
		List<SelectItem> glbms = constantService.getSelectListByType("行政级别_1");
		List<GuanliJigou> gljg = orgService.getGuanliJigouByOrg(orgid);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (GuanliJigou temp : gljg) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", temp.getName());
			map1.put("id", temp.getId());
			map1.put("orgid", temp.getOrgid());
			if (temp.getBianzhirenshu() > 0)
				map1.put("bianzhirenshu", temp.getBianzhirenshu());
			else
				map1.put("bianzhirenshu", "");
			if (temp.getShijirenshu() > 0)
				map1.put("shijirenshu", temp.getShijirenshu());
			else
				map1.put("shijirenshu", "");
			if (temp.getXingzhengjibie() > 0)
				map1.put("xingzhengjibie",
						glbms.get(temp.getXingzhengjibie() - 1).getName());
			else
				map1.put("xingzhengjibie", "");
			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);

		return json;
	}

	// 事业单位信息加载
	@RequestMapping(value = "bmxzsydwcx")
	@ResponseBody
	public Map<String, Object> shiyeDanweiXinxiChaxun(HttpSession session) {
		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();

		// PageContext pageContext = PageContext.getContext();
		// pageContext.setCurrentPage(page);
		// pageContext.setPageSize(rows);
		// pageContext.setPagination(true);
		// 机构设置信息
		List<SelectItem> sydws = constantService.getSelectListByType("行政级别_1");
		List<ShiyeDanwei> sydw = orgService.getShiyeDanweiByOrg(orgid);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (ShiyeDanwei temp : sydw) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", temp.getName());
			map1.put("id", temp.getId());
			map1.put("orgid", temp.getOrgid());
			if (temp.getBianzhirenshu() > 0)
				map1.put("bianzhirenshu", temp.getBianzhirenshu());
			else
				map1.put("bianzhirenshu", "");
			if (temp.getShijirenshu() > 0)
				map1.put("shijirenshu", temp.getShijirenshu());
			else
				map1.put("shijirenshu", "");
			if (temp.getXingzhengjibie() > 0)
				map1.put("xingzhengjibie",
						sydws.get(temp.getXingzhengjibie() - 1).getName());
			else
				map1.put("xingzhengjibie", "");

			list.add(map1);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);

		return json;
	}

	// 基础设施信息加载
	@RequestMapping(value = "bmxzjcsscx")
	@ResponseBody
	public Map<String, Object> jichuSheshiXinxiChaxun(HttpSession session) {
		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();

		// 机构设置信息
		List<SelectItem> leibies = constantService
				.getSelectListByType("基础设施类别");
		List<JichuSheshi> jcss = orgService.getJichuSheshiByOrg(orgid);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (JichuSheshi temp : jcss) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", temp.getName());
			map1.put("id", temp.getId());
			map1.put("orgid", temp.getOrgid());
			if (temp.getMianji() > 0)
				map1.put("mianji", temp.getMianji());
			else
				map1.put("mianji", "");
			if (!temp.getJianshedanwei().equals("#"))
				map1.put("jianshedanwei", temp.getJianshedanwei());
			else
				map1.put("jianshedanwei", "");
			if (temp.getTouruzijin() > 0)
				map1.put("touruzijin", temp.getTouruzijin());
			else
				map1.put("touruzijin", "");
			if (temp.getLeibie() > 0)
				map1.put("leibie", leibies.get(temp.getLeibie() - 1).getName());
			else
				map1.put("leibie", "");

			list.add(map1);
		}

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);
		return json;
	}

	// 行政部门信息保存
	@RequestMapping(value = "doxzbmbc", method = RequestMethod.POST)
	@ResponseBody
	public String baocunXingzhengXinxi(HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();
		// List<SelectItem> xzjbs=constantService.getSelectListByType("行政级别_1");
		// List<SelectItem>
		// xzjb2s=constantService.getSelectListByType("行政级别_2");
		// List<SelectItem>
		// leibies=constantService.getSelectListByType("基础设施类别");

		String orgname = request.getParameter("orgname");
		logger.debug("=========" + request.getParameter("orgname"));
		String zhuguandanwei = request.getParameter("suorgid");
		logger.debug("=========" + request.getParameter("zhuguandanwei"));
		String chenglishijian = request.getParameter("chenglishijian");
		logger.debug("=========" + request.getParameter("chenglishijian"));
		String xingzhengjibie = request.getParameter("xingzhengjibie");
		logger.debug("=========" + request.getParameter("xingzhengjibie"));
		String jingfeilaiyuan = request.getParameter("jingfeilaiyuan");
		logger.debug("=========" + request.getParameter("jingfeilaiyuan"));
		String zhengfuxulie = request.getParameter("zhengfuxulie");
		logger.debug("=========" + request.getParameter("zhengfuxulie"));
		String jigouleibie = request.getParameter("jigouleibie");
		logger.debug("=========" + request.getParameter("jigouleibie"));
		String jigoufenlei1 = request.getParameter("jigoufenlei1");
		logger.debug("=========" + request.getParameter("jigoufenlei1"));
		String jigoufenlei2 = request.getParameter("jigoufenlei2");
		String bianzhishu = request.getParameter("bianzhishu");
		String renshu = request.getParameter("renshu");
		String baomiganbubianzhirenshu = request
				.getParameter("zhuanzhiganbushu");
		String xueshengshu = request.getParameter("xueshengshu");
		String jiuyeqingkuang = request.getParameter("jiuyeqingkuang");
		String shengfen = request.getParameter("shengfen");
		String tianbiaoren = request.getParameter("tianbiaoren");
		String tianbiaoriqi = request.getParameter("tianbiaoriqi");
		// 更新机构信息
		Orgnization org = new Orgnization();
		org.setId(orgid);
		org.setOrgname(orgname.trim());
		org.setSuorgid(orgService.getOrgnizationByName(zhuguandanwei.trim())
				.getId());
		org.setChenglishijian(Date.valueOf(chenglishijian.trim()));
		org.setXingzhengjibie(Integer.parseInt(xingzhengjibie.trim()));
		org.setJingfeilaiyuan(jingfeilaiyuan.trim());
		org.setZhengfuxulie((short) Integer.parseInt(zhengfuxulie.trim()));
		org.setJigouleibie((short) Integer.parseInt(jigouleibie.trim()));
		org.setBianzhishu(Integer.parseInt(bianzhishu.trim()));
		org.setRenshu(Integer.parseInt(renshu.trim()));
		org.setZhuanzhiganbushu(Integer.parseInt(baomiganbubianzhirenshu.trim()));
		org.setXueshengshu(Integer.parseInt(xueshengshu.trim()));
		org.setJiuyeqingkuang(jiuyeqingkuang.trim());
		org.setJigoufenlei1(Integer.parseInt(jigoufenlei1.trim()));
		org.setJigoufenlei2(Integer.parseInt(jigoufenlei2.trim()));
		org.setShengfen(Integer.parseInt(shengfen.trim()));
		org.setStatus(1);
		org.setTianbiaoren(tianbiaoren);
		org.setTianbiaoriqi(Date.valueOf(tianbiaoriqi));
		orgService.UpdateOrgnization(org);
		//
		int i, j;
		// 行政部门信息维护

		String xzbminsert = request.getParameter("xzbminsert");
		String xzbmupdate = request.getParameter("xzbmupdate");
		String xzbmdelete = request.getParameter("xzbmdelete");
		logger.debug("##########" + xzbminsert + "update:" + xzbmupdate
				+ "delete:" + xzbmdelete);
		// 行政部门插入
		if (!xzbminsert.equals("")) {
			String[] xzbmInsertNum = xzbminsert.split(";");
			logger.debug("##########" + xzbmInsertNum.length);
			for (i = 0; i < xzbmInsertNum.length; i++) {
				String[] xzbmInfo = xzbmInsertNum[i].split(",");
				boolean flag = false;
				// 检测该条记录是否都为空
				for (j = 0; j < xzbmInfo.length; j++) {
					if (!xzbmInfo.equals("")) {
						flag = true;
						break;
					}
				}
				if (flag) {
					XingzhengBumen xzbm = new XingzhengBumen();
					Integer seq = sequenceDao.getSequence("xingzhengbumen");
					xzbm.setId(seq);
					xzbm.setOrgid(orgid);
					xzbm.setName(xzbmInfo[1]);
					if (!xzbmInfo[2].equals(""))
						xzbm.setBianzhirenshu(Integer.parseInt(xzbmInfo[2]));
					if (!xzbmInfo[3].equals(""))
						xzbm.setShijirenshu(Integer.parseInt(xzbmInfo[3]));
					if (!xzbmInfo[4].equals(""))
						xzbm.setXingzhengjibie(Integer.parseInt(xzbmInfo[4]));
					//
					orgService.insertXingzhengBumen(xzbm);
				} else
					continue;
			}
		}
		// 行政部门更新
		if (!xzbmupdate.equals("")) {
			String[] xzbmUpdateNum = xzbmupdate.split(";");
			logger.debug("##########" + xzbmUpdateNum.length);
			for (i = 0; i < xzbmUpdateNum.length; i++) {
				String[] xzbmInfo = xzbmUpdateNum[i].split(",");

				if ((!xzbmUpdateNum[i].equals(""))
						&& (!xzbmUpdateNum[i].replaceAll(",", "").trim()
								.equals("")) && (!xzbmInfo[0].equals(""))) {
					XingzhengBumen xzbm = new XingzhengBumen();
					xzbm.setId(Integer.parseInt(xzbmInfo[0]));
					xzbm.setOrgid(orgid);
					xzbm.setName(xzbmInfo[1]);
					if (!xzbmInfo[2].equals(""))
						xzbm.setBianzhirenshu(Integer.parseInt(xzbmInfo[2]));
					if (!xzbmInfo[3].equals(""))
						xzbm.setShijirenshu(Integer.parseInt(xzbmInfo[3]));
					if (xzbmInfo[4].matches("[0-9]+"))
						xzbm.setXingzhengjibie(Integer.parseInt(xzbmInfo[4]));
					else
						xzbm.setXingzhengjibie(getXingzhengJibie(xzbmInfo[4]));
					//
					orgService.updateXingzhengBumen(xzbm);
				} else
					continue;

			}
		}
		// 行政部门删除
		if (!xzbmdelete.equals("")) {
			String[] xzbmDeleteNum = xzbmdelete.split(";");
			logger.debug("##########" + "deleteLen:" + xzbmDeleteNum.length);
			for (i = 0; i < xzbmDeleteNum.length; i++) {
				String[] xzbmInfo = xzbmDeleteNum[i].split(",");
				//
				if (!xzbmInfo[0].equals("")) {
					logger.debug("delete id:" + xzbmInfo[0]);
					orgService.deleteXingzhengBumen(Integer
							.parseInt(xzbmInfo[0]));
					;
				}
			}
		}

		// 管理机构信息维护
		String gljginsert = request.getParameter("gljginsert");
		String gljgupdate = request.getParameter("gljgupdate");
		String gljgdelete = request.getParameter("gljgdelete");
		logger.debug("##########" + gljginsert + "update:" + gljgupdate
				+ "delete:" + gljgdelete);
		// 管理机构插入
		if (!gljginsert.equals("")) {
			String[] gljgInsertNum = gljginsert.split(";");
			logger.debug("##########" + gljgInsertNum.length);
			for (i = 0; i < gljgInsertNum.length; i++) {
				String[] gljgInfo = gljgInsertNum[i].split(",");
				boolean flag = false;
				// 检测该条记录是否都为空
				for (j = 0; j < gljgInfo.length; j++) {
					if (!gljgInfo.equals("")) {
						flag = true;
						break;
					}
				}
				if (flag) {
					GuanliJigou gljg = new GuanliJigou();
					Integer seq = sequenceDao.getSequence("guanlijigou");
					gljg.setId(seq);
					gljg.setOrgid(orgid);
					gljg.setName(gljgInfo[1]);
					if (!gljgInfo[2].equals(""))
						gljg.setBianzhirenshu(Integer.parseInt(gljgInfo[2]));
					if (!gljgInfo[3].equals(""))
						gljg.setShijirenshu(Integer.parseInt(gljgInfo[3]));
					if (!gljgInfo[4].equals(""))
						gljg.setXingzhengjibie(Integer.parseInt(gljgInfo[4]));
					//
					orgService.insertGuanliJigou(gljg);
				} else
					continue;
			}
		}
		// 管理机构更新
		if (!gljgupdate.equals("")) {
			String[] gljgUpdateNum = gljgupdate.split(";");
			logger.debug("##########" + gljgUpdateNum.length);
			for (i = 0; i < gljgUpdateNum.length; i++) {
				String[] gljgInfo = gljgUpdateNum[i].split(",");
				logger.debug("#################guanlijigou" + gljgUpdateNum[i]);
				if ((!gljgUpdateNum[i].equals(""))
						&& (!gljgUpdateNum[i].replaceAll(",", "").trim()
								.equals("")) && (!gljgInfo[0].equals(""))) {
					GuanliJigou gljg = new GuanliJigou();
					gljg.setId(Integer.parseInt(gljgInfo[0]));
					gljg.setOrgid(orgid);
					gljg.setName(gljgInfo[1]);
					if (!gljgInfo[2].equals(""))
						gljg.setBianzhirenshu(Integer.parseInt(gljgInfo[2]));
					if (!gljgInfo[3].equals(""))
						gljg.setShijirenshu(Integer.parseInt(gljgInfo[3]));
					logger.debug("#################guanlijigou" + gljgInfo[4]);
					if (gljgInfo[4].matches("[0-9]+")
							&& (!gljgInfo[4].equals("")))
						gljg.setXingzhengjibie(Integer.parseInt(gljgInfo[4]));
					else
						gljg.setXingzhengjibie(getXingzhengJibie(gljgInfo[4]));
					//
					orgService.updateGuanliJigou(gljg);
				} else
					continue;

			}
		}
		// 管理机构删除
		if (!gljgdelete.equals("")) {
			String[] gljgDeleteNum = gljgdelete.split(";");
			logger.debug("##########" + "deleteLen:" + gljgDeleteNum.length);
			for (i = 0; i < gljgDeleteNum.length; i++) {
				String[] gljgInfo = gljgDeleteNum[i].split(",");
				//
				if (!gljgInfo[0].equals("")) {
					logger.debug("delete id:" + gljgInfo[0]);
					orgService.deleteGuanliJigou(Integer.parseInt(gljgInfo[0]));
					;
				}
			}
		}
		// 事业单位信息维护
		String sydwinsert = request.getParameter("sydwinsert");
		String sydwupdate = request.getParameter("sydwupdate");
		String sydwdelete = request.getParameter("sydwdelete");
		logger.debug("##########" + sydwinsert + "update:" + sydwupdate
				+ "delete:" + sydwdelete);
		// 事业单位插入
		if (!sydwinsert.equals("")) {
			String[] sydwInsertNum = sydwinsert.split(";");
			logger.debug("##########" + sydwInsertNum.length);
			for (i = 0; i < sydwInsertNum.length; i++) {
				String[] sydwInfo = sydwInsertNum[i].split(",");
				boolean flag = false;
				// 检测该条记录是否都为空
				for (j = 0; j < sydwInfo.length; j++) {
					if (!sydwInfo.equals("")) {
						flag = true;
						break;
					}
				}
				if (flag) {
					ShiyeDanwei sydw = new ShiyeDanwei();
					Integer seq = sequenceDao.getSequence("xingzhengbumen");
					sydw.setId(seq);
					sydw.setOrgid(orgid);
					sydw.setName(sydwInfo[1]);
					if (!sydwInfo[2].equals(""))
						sydw.setBianzhirenshu(Integer.parseInt(sydwInfo[2]));
					if (!sydwInfo[3].equals(""))
						sydw.setShijirenshu(Integer.parseInt(sydwInfo[3]));
					if (!sydwInfo[4].equals(""))
						sydw.setXingzhengjibie(Integer.parseInt(sydwInfo[4]));
					//
					orgService.insertShiyeDanwei(sydw);
				} else
					continue;
			}
		}
		// 事业单位更新
		if (!sydwupdate.equals("")) {
			String[] sydwUpdateNum = sydwupdate.split(";");
			logger.debug("##########" + sydwUpdateNum.length);
			for (i = 0; i < sydwUpdateNum.length; i++) {
				String[] sydwInfo = sydwUpdateNum[i].split(",");

				if ((!sydwUpdateNum[i].equals(""))
						&& (!sydwUpdateNum[i].replaceAll(",", "").trim()
								.equals("")) && (!sydwInfo[0].equals(""))) {
					ShiyeDanwei sydw = new ShiyeDanwei();
					sydw.setId(Integer.parseInt(sydwInfo[0]));
					sydw.setOrgid(orgid);
					sydw.setName(sydwInfo[1]);
					if (!sydwInfo[2].equals(""))
						sydw.setBianzhirenshu(Integer.parseInt(sydwInfo[2]));
					if (!sydwInfo[3].equals(""))
						sydw.setShijirenshu(Integer.parseInt(sydwInfo[3]));
					if (sydwInfo[4].matches("[0-9]+"))
						sydw.setXingzhengjibie(Integer.parseInt(sydwInfo[4]));
					else
						sydw.setXingzhengjibie(getXingzhengJibie(sydwInfo[4]));
					//
					orgService.updateShiyeDanwei(sydw);
				} else
					continue;

			}
		}
		// 事业单位删除
		if (!sydwdelete.equals("")) {
			String[] sydwDeleteNum = sydwdelete.split(";");
			logger.debug("##########" + "deleteLen:" + sydwDeleteNum.length);
			for (i = 0; i < sydwDeleteNum.length; i++) {
				String[] sydwInfo = sydwDeleteNum[i].split(",");
				//
				if (!sydwInfo[0].equals("")) {
					logger.debug("delete id:" + sydwInfo[0]);
					orgService.deleteShiyeDanwei(Integer.parseInt(sydwInfo[0]));
					;
				}
			}
		}
		// 更新基础设施的信息
		String jcssinsert = request.getParameter("jcssinsert");
		String jcssupdate = request.getParameter("jcssupdate");
		String jcssdelete = request.getParameter("jcssdelete");
		logger.debug("##########" + jcssinsert + "update:" + jcssupdate
				+ "delete:" + jcssdelete);

		if (!jcssinsert.equals("")) {
			String[] jcssInsertNum = jcssinsert.split(";");
			logger.debug("##########" + jcssInsertNum.length);
			for (i = 0; i < jcssInsertNum.length; i++) {
				String[] jcssInfo = jcssInsertNum[i].split(",");
				boolean flag = false;
				// 检测该条记录是否都为空
				for (j = 0; j < jcssInfo.length; j++) {
					if (!jcssInfo.equals("")) {
						flag = true;
						break;
					}
				}
				if (flag) {
					JichuSheshi jcss = new JichuSheshi();
					Integer seq = sequenceDao.getSequence("jichusheshi");
					jcss.setId(seq);
					jcss.setOrgid(orgid);
					jcss.setName(jcssInfo[1]);
					if (!jcssInfo[2].equals(""))
						jcss.setLeibie(Integer.parseInt(jcssInfo[2]));
					if (!jcssInfo[3].equals(""))
						jcss.setMianji(Double.parseDouble(jcssInfo[3]));
					if (!jcssInfo[4].equals(""))
						jcss.setTouruzijin(Double.parseDouble(jcssInfo[4]));
					jcss.setJianshedanwei(jcssInfo[5]);
					//
					orgService.insertJichuSheshi(jcss);
				} else
					continue;
			}
		}
		// 基础设施更新
		if (!jcssupdate.equals("")) {
			String[] jcssUpdateNum = jcssupdate.split(";");
			logger.debug("##########" + jcssUpdateNum.length);
			for (i = 0; i < jcssUpdateNum.length; i++) {
				String[] jcssInfo = jcssUpdateNum[i].split(",");

				if ((!jcssUpdateNum[i].equals(""))
						&& (!jcssUpdateNum[i].replaceAll(",", "").trim()
								.equals("")) && (!jcssInfo[0].equals(""))) {
					JichuSheshi jcss = new JichuSheshi();
					jcss.setId(Integer.parseInt(jcssInfo[0]));
					jcss.setOrgid(orgid);
					jcss.setName(jcssInfo[1]);
					if (jcssInfo[2].matches("[0-9]+"))
						jcss.setLeibie(Integer.parseInt(jcssInfo[2]));
					else
						jcss.setLeibie(getJichuSheshiLeibie(jcssInfo[2]));
					if (!jcssInfo[3].equals(""))
						jcss.setMianji(Double.parseDouble(jcssInfo[3]));
					if (!jcssInfo[4].equals(""))
						jcss.setTouruzijin(Double.parseDouble(jcssInfo[4]));
					jcss.setJianshedanwei(jcssInfo[5]);
					//
					orgService.updateJichuSheshi(jcss);
				} else
					continue;

			}
		}
		// 基础设施删除
		if (!jcssdelete.equals("")) {
			String[] jcssDeleteNum = jcssdelete.split(";");
			logger.debug("##########" + "deleteLen:" + jcssDeleteNum.length);
			for (i = 0; i < jcssDeleteNum.length; i++) {
				String[] jcssInfo = jcssDeleteNum[i].split(",");
				//
				if (!jcssInfo[0].equals("")) {
					logger.debug("delete id:" + jcssInfo[0]);
					orgService.deleteJichuSheshi(Integer.parseInt(jcssInfo[0]));
					;
				}
			}
		}
		// logger.debug("##########"+jcssInsertNum.length+"updateLen:"+jcssUpdateNum.length+"deleteLen:"+jcssDeleteNum.length);

		return "success";

	}

	// 获取常量列表
	@RequestMapping(value = "bmxzclcx")
	@ResponseBody
	public Map<String, Object> getConstantsInfo() {
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_2");
		List<SelectItem> leibies = constantService
				.getSelectListByType("基础设施类别");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("xzjblist", xzjbs);
		map.put("lblist", leibies);
		map.put("success", true);

		return map;
	}

	// 获取基础设施的索引
	public int getJichuSheshiLeibie(String leibie) {
		List<SelectItem> leibies = constantService
				.getSelectListByType("基础设施类别");
		for (SelectItem temp : leibies) {
			if (temp.getName().equals(leibie.trim()))
				return temp.getCode();
		}
		return 1;
	}

	// 获取行政几倍的索引
	public int getXingzhengJibie(String xzjb) {
		List<SelectItem> xzjbs = constantService.getSelectListByType("行政级别_2");
		for (SelectItem temp : xzjbs) {
			if (temp.getName().equals(xzjb.trim()))
				return temp.getCode();
		}
		return 3;
	}
	//导出机构信息
	@RequestMapping(value ="bmxzdc")
	@ResponseBody
	public ModelAndView baomiXinxiDaochu(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("###########导出机构信息###########");
		// 获取机构的ID值
		User user = (User) session.getAttribute("user");
		logger.debug("user:" + user.toString() + "orgid:" + user.getOrgid());
		int orgid = user.getOrgid();
		
		exportService.exportOrgInfo(request, response,orgid);
		
		return null;
	}
	
	@RequestMapping(value = "bmxzxinxiweihuload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> baomiQingkuangWeihuLoad() {
		Map<String, Object> map = orgService.getJigouXiangqing(Integer
				.parseInt("0"));
		logger.debug("-------------------------------------------------");
		Orgnization org = (Orgnization) map.get("orgnization");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("chenglishijian", org.getChenglishijian());
		map1.put("orgname", org.getOrgname());
		map1.put("suorgid", orgService.getOrgnizationById(org.getSuorgid())
				.getOrgname());
		map1.put(
				"xingzhengjibie",
				constantService.getSelectionConstantName("行政级别_1",
						org.getXingzhengjibie()));
		map1.put("jingfeilaiyuan", org.getJingfeilaiyuan());
		map1.put(
				"zhengfuxulie",
				constantService.getSelectionConstantName("政府序列",
						org.getZhengfuxulie()));
		map1.put(
				"jigouleibie",
				constantService.getSelectionConstantName("机构类别",
						org.getJigouleibie()));

		list.add(map1);

		Map<String, Object> json = new HashMap<String, Object>();
		// 固定格式
		json.put("total", 1);
		json.put("pages", 1);
		json.put("rows", list);

		return json;
	}

	@RequestMapping(value = "bmxzjgtj")
	public ModelAndView addOrgnization() {
		// List<Role> roles = roleService.getAllRoles();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("roles", roles);
		return new ModelAndView("departmentmanage/departmanageAdd");
	}

	@RequestMapping(value = "azgdwtjjgsl")
	public ModelAndView azgdwtjjgsl(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return new ModelAndView(
				"orgnizationmanage/orgnizationcountByZhuguandanwei");
	}

	@RequestMapping(value = "axzjbtjjgsl")
	public ModelAndView axzjbtjjgsl(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return new ModelAndView(
				"orgnizationmanage/orgnizationcountByXingzhengjibie");
	}

	@RequestMapping(value = "ajglbtjjgsl")
	public ModelAndView ajglbtjjgsl(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return new ModelAndView(
				"orgnizationmanage/orgnizationcountByJigouleibie");
	}

	// 按主管单位分类统计
	@RequestMapping(value = "zgdwtj", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> zgdwtj() throws IOException {

		List<Map<String, Object>> orgs = orgService.countBySuorgid();

		Map<String, Object> json = new HashMap<String, Object>();

		json.put("rows", orgs);

		return json;
	}

	// 按行政级别分类统计
	@RequestMapping(value = "xzjbtj", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> xzjbtj() throws IOException {

		List<Map<String, Object>> orgs = orgService.countByXingzhengJibie();

		Map<String, Object> json = new HashMap<String, Object>();

		json.put("rows", orgs);

		return json;
	}

	// 按机构类别分类统计
	@RequestMapping(value = "jglbtj", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jglbtj() throws IOException {

		List<Map<String, Object>> orgs = orgService.countByJigouLeibie();

		Map<String, Object> json = new HashMap<String, Object>();

		json.put("rows", orgs);

		return json;
	}

}
