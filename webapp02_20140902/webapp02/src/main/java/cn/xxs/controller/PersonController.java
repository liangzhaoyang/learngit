package cn.xxs.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.User;
import cn.xxs.paging.PageContext;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.PersonService;
import cn.xxs.service.SequenceService;
import cn.xxs.utility.BaseController;

@Controller
@RequestMapping("/")
public class PersonController extends BaseController{
	
	private static Logger log=Logger.getLogger(PersonController.class);
	@Autowired
	private PersonService personService;
	@Autowired
	private SequenceService sequenceService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private ConstantsService constantService;
	//人员选择
	@RequestMapping(value = "ryxz")
	public ModelAndView ryxz(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<SelectItem> sfzzs=constantService.getSelectListByType("是否专职");
		List<SelectItem> xbs=constantService.getSelectListByType("性别");
		List<SelectItem> zzmms=constantService.getSelectListByType("政治面貌_1");
	
		model.addAttribute("shifouzhuanzhiList", sfzzs);
		model.addAttribute("xingbieList", xbs);
		model.addAttribute("zhengzhimianmaoList", zzmms);
		return new ModelAndView("person/personList");
	}
	//人员选择
	@RequestMapping(value = "ryxz11")
	public ModelAndView ryxz11(HttpServletRequest request, HttpServletResponse response, Model model,String id) {
		log.debug("++++++++++++++++++++++++  id:"+id);
		if(id!=null && id!="")
		{
			//将人员id信息存在session中
//			session.setAttribute("personId", id);
			Person person=personService.getPersonById(Integer.parseInt(id));
			if(person!=null)
			{
				log.debug("minzu:"+person.getMinzu()+",name:"+person.getName());
				model.addAttribute("person", person);
			}
		}
		//
		List<SelectItem> minzus=constantService.getSelectListByType("民族");
		List<SelectItem> zhiwus=constantService.getSelectListByType("职务");
		List<SelectItem> xuelis=constantService.getSelectListByType("学历");
		List<SelectItem> zhengzhimianmaos=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> xingzhengjibies=constantService.getSelectListByType("行政级别");
		List<SelectItem> jishuzhichengs=constantService.getSelectListByType("技术职称");
		List<SelectItem> shemidengjis=constantService.getSelectListByType("涉密等级");
		List<SelectItem> renyuanleixings=constantService.getSelectListByType("人员类型");
		//
		model.addAttribute("minzuList", minzus);
		model.addAttribute("zhiwuList", zhiwus);
		model.addAttribute("xueliList", xuelis);
		model.addAttribute("zhengzhimianmaoList", zhengzhimianmaos);
		model.addAttribute("xingzhengjibieList", xingzhengjibies);
		model.addAttribute("jishuzhichengList", jishuzhichengs);
		model.addAttribute("shemidengjiList", shemidengjis);
		model.addAttribute("renyuanleixingList", renyuanleixings);
		return new ModelAndView("person/personAdd");
	}
	//人员查询
	@RequestMapping(value="rycx")
	@ResponseBody
	public Map<String, Object> rycx(HttpSession session, @ModelAttribute PersonCondition personInfo, int page, int rows) throws IOException {
		User user = (User)session.getAttribute("user");
		log.debug("user:" + user);
		log.debug("xxxxxxxxxxxxxxxxxxxxxxxx" + personInfo.getYaohaibumengrenyuan());
		PageContext pageContext = PageContext.getContext();
		log.debug(user.getOrgid());
		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);
		
		personInfo.setOrgid(user.getOrgid());
		List<Map<String, Object>> persons = personService.selectPersonByCondition(personInfo);
		List<SelectItem> rylxs=constantService.getSelectListByType("人员类型");
		List<SelectItem> sfyhbmrys=constantService.getSelectListByType("是否要害部门人员");
		List<SelectItem> sfzzs=constantService.getSelectListByType("是否专职");
		List<SelectItem> jszcs=constantService.getSelectListByType("技术职称");
		List<SelectItem> xzjbs=constantService.getSelectListByType("行政级别");
		List<SelectItem> zws=constantService.getSelectListByType("职务");
		List<SelectItem> xbs=constantService.getSelectListByType("性别");
		List<SelectItem> xls=constantService.getSelectListByType("学历");
		List<SelectItem> zzmms=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> mzs=constantService.getSelectListByType("民族");
		List<SelectItem> smdjs=constantService.getSelectListByType("涉密等级");
	
		
		for(Map<String, Object> e: persons) {
		log.debug("aaaaaaaaaaaaaaaaaaaaaaaaaaaaacccccccccccccccccc"+(int)e.get("renyuanleixing"));
		if((int)e.get("renyuanleixing")>0){
			e.put("renyuanleixing", rylxs.get((int)e.get("renyuanleixing")-1).getName());
		}
		if((int)e.get("yaohaibumengrenyuan")>0){
			e.put("yaohaibumengrenyuan", sfyhbmrys.get((int)e.get("yaohaibumengrenyuan")-1).getName());
		}
		if((int)e.get("shifouzhuanzhi")>0){
			e.put("shifouzhuanzhi", sfzzs.get((int)e.get("shifouzhuanzhi")-1).getName());
		}
		if((int)e.get("jishuzhicheng")>0){
			e.put("jishuzhicheng", jszcs.get((int)e.get("jishuzhicheng")-1).getName());
		}
		if((int)e.get("zhiwu")>0 ){
			e.put("zhiwu", zws.get((int)e.get("zhiwu")-1).getName());
		}
		if((int)e.get("xingbie")>0){
			e.put("xingbie", xbs.get((int)e.get("xingbie")-1).getName());
		}
		if((int)e.get("xingzhengjibie")>0){
			e.put("xingzhengjibie", xzjbs.get((int)e.get("xingzhengjibie")).getName());
		}
		if((int)e.get("xueli")>0){
			e.put("xueli", xls.get((int)e.get("xueli")-1).getName());
		}
		if((int)e.get("zhengzhimianmao")>0){
			e.put("zhengzhimianmao", zzmms.get((int)e.get("zhengzhimianmao")-1).getName());
		}
		if((int)e.get("minzu")>0){
			e.put("minzu", mzs.get((int)e.get("minzu")-1).getName());
		}
		if((int)e.get("shemidengji")>0){
			e.put("shemidengji", smdjs.get((int)e.get("shemidengji")-1).getName());
		}
		}

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", persons);

		return json;
	}
	//人员查询
	@RequestMapping(value="rycxById")
	@ResponseBody
	public Map<String, Object> rycxById(HttpSession session, @ModelAttribute PersonCondition personInfo,String id) throws IOException {
//		User user = (User)session.getAttribute("user");
//		log.debug("user:" + user);
		log.debug("xxxxxxxxxxxxxxxxxxxxxxxx+++++++++++++" + id);
//		PageContext pageContext = PageContext.getContext();
//		log.debug(user.getOrgid());
//		pageContext.setCurrentPage(page);
//		pageContext.setPageSize(rows);
//		pageContext.setPagination(true);
		
//		personInfo.setOrgid(user.getOrgid());
//		personInfo.setId(Integer.parseInt(id));
		Person person = personService.getPersonById(Integer.parseInt(id));
		List<SelectItem> rylxs=constantService.getSelectListByType("人员类型");
		List<SelectItem> sfyhbmrys=constantService.getSelectListByType("是否要害部门人员");
		List<SelectItem> sfzzs=constantService.getSelectListByType("是否专职");
		List<SelectItem> jszcs=constantService.getSelectListByType("技术职称");
		List<SelectItem> xzjbs=constantService.getSelectListByType("行政级别");
		List<SelectItem> zws=constantService.getSelectListByType("职务");
		List<SelectItem> xbs=constantService.getSelectListByType("性别");
		List<SelectItem> xls=constantService.getSelectListByType("学历");
		List<SelectItem> zzmms=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> mzs=constantService.getSelectListByType("民族");
		List<SelectItem> smdjs=constantService.getSelectListByType("涉密等级");
	
		
		Map<String, Object> json = new HashMap<String, Object>();
		if(person.getRenyuanleixing()>0){
			json.put("renyuanleixing", rylxs.get(person.getRenyuanleixing()-1).getName());
		}
		if(person.getYaohaibumengrenyuan()>0){
			json.put("yaohaibumengrenyuan", sfyhbmrys.get(person.getYaohaibumengrenyuan()-1).getName());
		}
		if(person.getShifouzhuanzhi()>0){
			json.put("shifouzhuanzhi", sfzzs.get(person.getShifouzhuanzhi()-1).getName());
		}
		if(person.getJishuzhicheng()>0){
			json.put("jishuzhicheng", jszcs.get(person.getJishuzhicheng()-1).getName());
		}
		if(person.getZhiwu()>0 ){
			json.put("zhiwu", zws.get(person.getZhiwu()-1).getName());
		}
		if(person.getXingbie()>0){
			json.put("xingbie", xbs.get(person.getXingbie()-1).getName());
		}
		if(person.getXingzhengjibie()>0){
			json.put("xingzhengjibie", xzjbs.get(person.getXingzhengjibie()).getName());
		}
		if(person.getXueli()>0){
			json.put("xueli", xls.get(person.getXueli()-1).getName());
		}
		if(person.getZhengzhimianmao()>0){
			json.put("zhengzhimianmao", zzmms.get(person.getZhengzhimianmao()-1).getName());
		}
		if(person.getMinzu()>0){
			json.put("minzu", mzs.get(person.getMinzu()-1).getName());
		}
		if(person.getShemidengji()>0){
			json.put("shemidengji", smdjs.get(person.getShemidengji()-1).getName());
		}
		
		json.put("id", person.getId());
		json.put("name", person.getName());
		json.put("xingbie", person.getXingbie());
		json.put("minzu", person.getMinzu());
		json.put("zhiwu", person.getZhiwu());
		json.put("chushengnianyue", person.getChushengnianyue());
		json.put("xueli", person.getXueli());
		json.put("zhengzhimianmao", person.getZhengzhimianmao());
		json.put("zhuanye", person.getZhuanye());
		
		json.put("xingzhengjibie", person.getXingzhengjibie());
		json.put("jishuzhicheng", person.getJishuzhicheng());
		json.put("renmingdanwei", person.getRenmingdanwei());
		json.put("shifouzhuanzhi", person.getShifouzhuanzhi());
		json.put("jianrenqitazhiwu", person.getJianrenqitazhiwu());
		json.put("kaishiriqi", person.getKaishiriqi());
		json.put("suoshubumen", person.getSuoshubumen());
		json.put("danweimingcheng", person.getDanweimingcheng());
		
		json.put("tel", person.getTel());
		json.put("mobilenum", person.getMobilenum());
		json.put("shemidengji", person.getShemidengji());
		json.put("yaohaibumengrenyuan", person.getYaohaibumengrenyuan());
		json.put("renyuanleixing", person.getRenyuanleixing());
		
		json.put("success", true);
		return json;
	}
	//人员添加
	@RequestMapping(value="dorytj",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addShemiRenyuanInfo(HttpSession session,HttpServletRequest request, HttpServletResponse response, @ModelAttribute Person person) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		log.debug("------------------------------------------");
		log.debug("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+request.getParameter("leixing"));
		User user = (User)session.getAttribute("user");
		log.debug("user:" + user+"orgid:"+user.getOrgid());
		log.debug("person:"+person.getName()+","+person.getMinzu());
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		Map<String, Object> map = new HashMap<String, Object>();
		if(request.getParameter("panduan")==""){
		try {
			if(!person.getName().isEmpty()) {				
				int id=sequenceService.getSequence("person");
				session.setAttribute("id", id);
				person.setId(id);
				person.setOrgid(user.getOrgid());
				log.debug("id:"+id+",orgid:"+user.getOrgid());
				personService.insertPerson(person);
//				shemiService.insertr_shemi_person(user.getOrgid(), id);
			}
		} catch(Exception e) {
			transactionManager.rollback(status);
			map.put("errMsg", e.getMessage());
			out.print(map.toString());
			//return ;
		}
		}else
			session.setAttribute("id", Integer.parseInt(request.getParameter("panduan")));
		transactionManager.commit(status);
		map.put("errMsg", "");
		out.print("success");
		if(request.getParameter("leixing").equals("涉密人员添加")){
		return new ModelAndView(new RedirectView("smryAdd.do"));
		}else if(request.getParameter("leixing").equals("保密干部添加")){
			return new ModelAndView(new RedirectView("bmgbAdd.do"));
			}else 
				return new ModelAndView(new RedirectView("ldbzAdd.do"));
	}
	//人员修改
	@RequestMapping(value="ryxg")
	public ModelAndView updateShemiRenyuan(HttpSession session, Model model,String id) {
		log.debug("++++++++++++++++++++++++  id:"+id);
		if(id!=null && id!="")
		{
			//将人员id信息存在session中
			session.setAttribute("personId", id);
			Person person=personService.getPersonById(Integer.parseInt(id));
			if(person!=null)
			{
				log.debug("minzu:"+person.getMinzu()+",name:"+person.getName());
				model.addAttribute("person", person);
			}
		}
		//
		List<SelectItem> minzus=constantService.getSelectListByType("民族");
		List<SelectItem> zhiwus=constantService.getSelectListByType("职务");
		List<SelectItem> xuelis=constantService.getSelectListByType("学历");
		List<SelectItem> zhengzhimianmaos=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> xingzhengjibies=constantService.getSelectListByType("行政级别");
		List<SelectItem> jishuzhichengs=constantService.getSelectListByType("技术职称");
		List<SelectItem> shemidengjis=constantService.getSelectListByType("涉密等级");
		List<SelectItem> renyuanleixings=constantService.getSelectListByType("人员类型");
		//
		model.addAttribute("minzuList", minzus);
		model.addAttribute("zhiwuList", zhiwus);
		model.addAttribute("xueliList", xuelis);
		model.addAttribute("zhengzhimianmaoList", zhengzhimianmaos);
		model.addAttribute("xingzhengjibieList", xingzhengjibies);
		model.addAttribute("jishuzhichengList", jishuzhichengs);
		model.addAttribute("shemidengjiList", shemidengjis);
		model.addAttribute("renyuanleixingList", renyuanleixings);
		
		return new ModelAndView("person/personModify");
	}
	//人员修改
	@RequestMapping(value="doryxg11")
	@ResponseBody
	public void updateShemiRenyuanInfo(HttpSession session,HttpServletRequest request, HttpServletResponse response, @ModelAttribute Person person) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		log.debug("////////------------------------------------------");
//		int id = (int)session.getAttribute("personId");
//		log.debug("personid:" +id);
		log.debug("person:"+person.getName()+","+person.getMinzu());
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//更新信息
			if(!person.getName().isEmpty()) {	
				person.setId(Integer.parseInt( (String) session.getAttribute("personId")));
				personService.updatePerson(person);
			}
		} catch(Exception e) {
			transactionManager.rollback(status);
			map.put("errMsg", e.getMessage());
			out.print(map.toString());
			return ;
		}
		session.removeAttribute("personId");
		transactionManager.commit(status);
		map.put("errMsg", "");
		out.print("success");
		return ;
	}
	}
	
