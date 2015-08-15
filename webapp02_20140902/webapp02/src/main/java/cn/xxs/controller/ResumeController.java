package cn.xxs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.Chengguo;
import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.Person;
import cn.xxs.entity.Resume;
import cn.xxs.entity.ResumeInfo;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.User;
import cn.xxs.entity.XueliMingxi;
import cn.xxs.paging.PageContext;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.ResumeService;
import cn.xxs.service.SequenceService;
import cn.xxs.utility.BaseController;

@Controller
@RequestMapping("/")
public class ResumeController extends BaseController{
	@Autowired
	private ResumeService resumeService;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private ConstantsService constantService;
	private static Logger log=Logger.getLogger(ResumeController.class);
	@Autowired
	private SequenceService sequenceService;
	
	@RequestMapping(value = "jlwh")
	public ModelAndView jlwh(HttpServletRequest request, HttpServletResponse response, Model model) {
		
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

		return new ModelAndView("resume/resumeManage");
	}
	
	@RequestMapping(value = "jllr")
	public ModelAndView jllr(HttpServletRequest request, HttpServletResponse response, Model model) {
		
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

		return new ModelAndView("resume/resumeLr");
	}
	//简历添加
	@RequestMapping(value = "jltj")
	public ModelAndView jltj(HttpSession session, Model model) {
		List<SelectItem> minzus=constantService.getSelectListByType("民族");
		List<SelectItem> xuelis=constantService.getSelectListByType("学历");
		List<SelectItem> zhengzhimianmaos=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> xingzhengjibies=constantService.getSelectListByType("行政级别");
		List<SelectItem> xingbies=constantService.getSelectListByType("性别");
		//
		model.addAttribute("minzuList", minzus);
		model.addAttribute("xueliList", xuelis);
		model.addAttribute("zhengzhimianmaoList", zhengzhimianmaos);
		model.addAttribute("xingzhengjibieList", xingzhengjibies);
		model.addAttribute("xingbieList", xingbies);
	
		return new ModelAndView("resume/resumeAdd");
	}
	//简历详情
	@RequestMapping(value = "jlxq")
	public ModelAndView jlxq(HttpSession session, Model model,String personid) {
		log.debug("aaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccccc"+personid);
		List<SelectItem> minzus=constantService.getSelectListByType("民族");
		List<SelectItem> xuelis=constantService.getSelectListByType("学历");
		List<SelectItem> zhengzhimianmaos=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> xingzhengjibies=constantService.getSelectListByType("行政级别");
		List<SelectItem> xingbies=constantService.getSelectListByType("性别");
		//
		model.addAttribute("minzuList", minzus);
		model.addAttribute("xueliList", xuelis);
		model.addAttribute("zhengzhimianmaoList", zhengzhimianmaos);
		model.addAttribute("xingzhengjibieList", xingzhengjibies);
		model.addAttribute("xingbieList", xingbies);
		
		
		session.setAttribute("personid", personid);
	
		return new ModelAndView("resume/resumeXq");
	}

	//简历修改
	@RequestMapping(value = "jlxg")
	public ModelAndView jlxg(HttpSession session, Model model,String personid) {
		log.debug("aaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccccc"+personid);
		List<SelectItem> minzus=constantService.getSelectListByType("民族");
		List<SelectItem> xuelis=constantService.getSelectListByType("学历");
		List<SelectItem> zhengzhimianmaos=constantService.getSelectListByType("政治面貌_1");
		List<SelectItem> xingzhengjibies=constantService.getSelectListByType("行政级别");
		List<SelectItem> xingbies=constantService.getSelectListByType("性别");
		List<SelectItem> xuelileibies=constantService.getSelectListByType("学历类别");
		//
		model.addAttribute("minzuList", minzus);
		model.addAttribute("xueliList", xuelis);
		model.addAttribute("zhengzhimianmaoList", zhengzhimianmaos);
		model.addAttribute("xingzhengjibieList", xingzhengjibies);
		model.addAttribute("xingbieList", xingbies);
		model.addAttribute("leibieList", xuelileibies);
		
		int id = Integer.parseInt(personid);
		Map<String, Object> map=resumeService.selectResumeInfo(id);
		Person person=(Person)map.get("person");
		log.debug("-------------------------------------------------"+person.getName());
		Jiangcheng jiangcheng=(Jiangcheng)map.get("jiangcheng");
		Chengguo chengguo=(Chengguo)map.get("chengguo");
		List<JiatingChengyuan> list=(List<JiatingChengyuan>)map.get("jiatingChengyuan");
		XueliMingxi xueliMingxi=(XueliMingxi)map.get("xueliMingxi");
		Resume resume=(Resume)map.get("resume");
		model.addAttribute("jiangcheng",jiangcheng);
		model.addAttribute("xueliMingxi",xueliMingxi);
		model.addAttribute("resume",resume);
		model.addAttribute("chengguo",chengguo);
		model.addAttribute("person",person);
		
		//Map<String, Object> json=new HashMap<String,Object>();
		//固定格式
		for(int i=0;i<list.size();i++){
			model.addAttribute("chengwei"+(i+1), list.get(i).getChengwei());
			model.addAttribute("xingming"+(i+1), list.get(i).getXingming());
			model.addAttribute("chushengnianfen"+(i+1), list.get(i).getChushengnianfen());
			model.addAttribute("zhengzhimianmao"+(i+1), list.get(i).getZhengzhimianmao());
			model.addAttribute("gongzuodanweijizhiwu"+(i+1), list.get(i).getGongzuodanweijizhiwu());
			session.setAttribute("jid"+(i+1),  list.get(i).getId());
		}
		session.setAttribute("personid", personid);
		return new ModelAndView("resume/resumeModify");
	}
	@RequestMapping(value="jlxg11")
	@ResponseBody
	public void updateShemiRenyuanInfo(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		log.debug("////////------------------------------------------");
		int id = Integer.parseInt((String) session.getAttribute("personid"));
//		User user = (User)session.getAttribute("user");
//		log.debug("personid:" +id);
//		log.debug("person:"+person.getName()+","+person.getMinzu());
		 Map<String,Object> map1 = new HashMap<String,Object>(); 
//		  Map<String,String[]> map=request.getParameterMap();
	      //  request.
	        log.debug(id);
	        log.debug(request.getParameter("name"));
	    	Person person=new Person();
	    	person.setId(id);
			person.setName(request.getParameter("name"));
//			person.setOrgid(user.getOrgid());
			person.setXingbie(Integer.parseInt(request.getParameter("xingbie")));
			person.setMinzu(Integer.parseInt(request.getParameter("minzu")));
			person.setChushengnianyue(java.sql.Date.valueOf(request.getParameter("chushengnianyue")));
			person.setZhengzhimianmao(Integer.parseInt(request.getParameter("zhengzhimianmao")));
			person.setXueli(Integer.parseInt(request.getParameter("xueli")));
			person.setZhuanye(request.getParameter("zhuanye"));
			
			XueliMingxi xueliMingxi = new XueliMingxi();
			int xid=sequenceService.getSequence("xuelimingxi");
			xueliMingxi.setId(xid);
			xueliMingxi.setPersonid(id);
			xueliMingxi.setZhuanye(request.getParameter("zhuanye"));
			xueliMingxi.setLeibie(Integer.parseInt(request.getParameter("leibie")));
			xueliMingxi.setBiyeyuanxiao(request.getParameter("biyeyuanxiao"));
			xueliMingxi.setXueli(Integer.parseInt(request.getParameter("xingbie")));
			
			Resume resume = new Resume();
//			int rid=sequenceService.getSequence("resume");
//			resume.setId(rid);
			resume.setPersonid(id);
			resume.setZhaopian(request.getParameter("zhaopian"));
//			resume.setOrgid(user.getOrgid());
			resume.setZhuanchang(request.getParameter("zhuanchang"));
			resume.setBeizhu(request.getParameter("beizhu"));
			resume.setJianli(request.getParameter("jianli"));
			resume.setXianrenzhuiwu(request.getParameter("xianrenzhuiwu"));
			resume.setZhuanyejishuzhiwu(request.getParameter("zhuanyejishuzhiwu"));
			resume.setCanjiagongzuoshijian(java.sql.Date.valueOf(request.getParameter("canjiagongzuoshijian")));
			
			Jiangcheng jiangcheng = new Jiangcheng();
//			int jcid=sequenceService.getSequence("jiangcheng");
//			jiangcheng.setId(jcid);
			jiangcheng.setPersonid(id);
			jiangcheng.setContent(request.getParameter("jiangcheng"));
//			log.debug("aaaaaaaaaaaaaaaaaaaaaaaaa"+request.getParameter("chengwei1"));
			if (request.getParameter("chengwei1")!=""){
			JiatingChengyuan jiatingChengyuan1=new JiatingChengyuan();
//			int jid1=sequenceService.getSequence("jiatingChengyuan");
			int jid1 =(int) session.getAttribute("jid1");
			jiatingChengyuan1.setId(jid1);
			jiatingChengyuan1.setPersonid(id);
			jiatingChengyuan1.setChengwei(request.getParameter("chengwei1"));
			jiatingChengyuan1.setXingming(request.getParameter("xingming1"));
			jiatingChengyuan1.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen1")));
			jiatingChengyuan1.setZhengzhimianmao(request.getParameter("zhengzhimianmao1"));
			jiatingChengyuan1.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu1"));
			map1.put("jiatingchengyuan1", jiatingChengyuan1);
			}
			
			if (request.getParameter("chengwei2")!=""){
			JiatingChengyuan jiatingChengyuan2=new JiatingChengyuan();
//			int jid2=sequenceService.getSequence("jiatingChengyuan");
			int jid2 =(int) session.getAttribute("jid2");
			jiatingChengyuan2.setId(jid2);
			jiatingChengyuan2.setPersonid(id);
			jiatingChengyuan2.setChengwei(request.getParameter("chengwei2"));
			jiatingChengyuan2.setXingming(request.getParameter("xingming2"));
			jiatingChengyuan2.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen2")));
			jiatingChengyuan2.setZhengzhimianmao(request.getParameter("zhengzhimianmao2"));
			jiatingChengyuan2.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu2"));
			map1.put("jiatingchengyuan2", jiatingChengyuan2);
			}
			if (request.getParameter("chengwei3")!=""){
			JiatingChengyuan jiatingChengyuan3=new JiatingChengyuan();
//			int jid3=sequenceService.getSequence("jiatingChengyuan");
			int jid3 =(int) session.getAttribute("jid3");;
			jiatingChengyuan3.setId(jid3);
			jiatingChengyuan3.setPersonid(id);
			jiatingChengyuan3.setChengwei(request.getParameter("chengwei3"));
			jiatingChengyuan3.setXingming(request.getParameter("xingming3"));
			jiatingChengyuan3.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen3")));
			jiatingChengyuan3.setZhengzhimianmao(request.getParameter("zhengzhimianmao3"));
			jiatingChengyuan3.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu3"));
			map1.put("jiatingchengyuan3", jiatingChengyuan3);
			}
			
			if (request.getParameter("chengwei4")!=""){
			JiatingChengyuan jiatingChengyuan4=new JiatingChengyuan();
//			int jid4=sequenceService.getSequence("jiatingChengyuan");
			int jid4 =(int) session.getAttribute("jid4");
			jiatingChengyuan4.setId(jid4);
			jiatingChengyuan4.setPersonid(id);
			jiatingChengyuan4.setChengwei(request.getParameter("chengwei4"));
			jiatingChengyuan4.setXingming(request.getParameter("xingming4"));
			jiatingChengyuan4.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen4")));
			jiatingChengyuan4.setZhengzhimianmao(request.getParameter("zhengzhimianmao4"));
			jiatingChengyuan4.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu4"));
			map1.put("jiatingchengyuan4", jiatingChengyuan4);
			}
			
			Chengguo chengguo = new Chengguo();
//			int cid=sequenceService.getSequence("chengguo");
//			chengguo.setId(cid);
			chengguo.setPersonid(id);
			chengguo.setContent(request.getParameter("chengguo"));
			log.debug(xueliMingxi.getZhuanye());
			
			map1.put("xuelimingxi", xueliMingxi);      
	        map1.put("person", person);
	        map1.put("resume", resume);
	        map1.put("chengguo", chengguo);
	        map1.put("jiangcheng", jiangcheng);
			resumeService.updateResumeInfo(map1);
	
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			//更新信息
//			if(!person.getName().isEmpty()) {	
//				person.setId(Integer.parseInt( (String) session.getAttribute("personId")));
//				resumeService.updateResumeInfo(map);
//			}
//		} catch(Exception e) {
//			transactionManager.rollback(status);
//			map.put("errMsg", e.getMessage());
//			out.print(map.toString());
//			return ;
//		}
		session.removeAttribute("personid");
		transactionManager.commit(status);
//		map.put("errMsg", "");
		out.print("success");
		return ;
	}

	//������ѯ���� ��������ְ��רҵ����ְ��Ͳμӹ���ʱ��
	@RequestMapping(value="jlcx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jlcx(HttpSession session,Model model, @ModelAttribute ResumeInfo resumeInfo, int page, int rows) throws IOException {
		User user = (User)session.getAttribute("user");
		log.debug("user:" + user);
		log.debug("xxxxxxxxxxxxxxxxxxxxxxxx" + resumeInfo.getCanjiagongzuoshijianFrom());
		log.debug("xxxxxxxxxxxxxxxxxxxxxxxx" + resumeInfo.getCanjiagongzuoshijianTo());
		PageContext pageContext = PageContext.getContext();
		log.debug(user.getOrgid());
		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);
		
		resumeInfo.setOrgid(String.valueOf(user.getOrgid()));
		List<Map<String, Object>> persons = resumeService.selectResumePage(resumeInfo);
		
		List<SelectItem> mzs=constantService.getSelectListByType("民族");
		List<SelectItem> xbs=constantService.getSelectListByType("性别");
		for(Map<String, Object> e: persons) {
				e.put("xingbie", xbs.get((int)e.get("xingbie")-1).getName());
				e.put("minzu", mzs.get((int)e.get("minzu")-1).getName());
			}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", persons);

		return json;
	
	}

	@RequestMapping(value = "jlsc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jlsc(@RequestBody ResumeInfo resumeInfo) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		Map<String, Object> map = new HashMap<String, Object>();
		log.debug("xxxxxxxxxxxxxxxccccccccccccccccccccc"+resumeInfo.getIds());
	
		try {
			resumeService.deleteResume(resumeInfo.getIds());
		} catch(Exception e) {
			map.put("message", "删除失败");
			transactionManager.rollback(status);
			return map;
		}
		
		transactionManager.commit(status);
		
		map.put("message", "删除成功"); 

		return map;
	}
	//�½�������Ϣ
	@RequestMapping(value="jlmxbc")
	public void ResumeInsert(HttpServletRequest request,HttpServletResponse response, HttpSession session)   
            throws IOException, ParseException {   
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
//        Map<?, ?> map=request.getParameterMap();
        Map<String,Object> map1 = new HashMap<String,Object>(); 
        User user = (User)session.getAttribute("user");
        log.debug(request.getParameter("id"));
        log.debug(user.getOrgid());
        log.debug(request.getParameter("name"));
        int pid=sequenceService.getSequence("person");
    	Person person=new Person();
    	person.setId(pid);
		person.setName(request.getParameter("name"));
		person.setOrgid(user.getOrgid());
		person.setXingbie(Integer.parseInt(request.getParameter("xingbie")));
		person.setMinzu(Integer.parseInt(request.getParameter("minzu")));
		person.setChushengnianyue(java.sql.Date.valueOf(request.getParameter("chushengnianyue")));
		person.setZhengzhimianmao(Integer.parseInt(request.getParameter("zhengzhimianmao")));
		person.setXueli(Integer.parseInt(request.getParameter("xueli")));
		person.setZhuanye(request.getParameter("zhuanye"));
		
		XueliMingxi xueliMingxi = new XueliMingxi();
		int xid=sequenceService.getSequence("xuelimingxi");
		xueliMingxi.setId(xid);
		xueliMingxi.setPersonid(pid);
		xueliMingxi.setZhuanye(request.getParameter("zhuanye"));
		xueliMingxi.setLeibie(Integer.parseInt(request.getParameter("leibie")));
		xueliMingxi.setBiyeyuanxiao(request.getParameter("biyeyuanxiao"));
		xueliMingxi.setXueli(Integer.parseInt(request.getParameter("xingbie")));
		
		Resume resume = new Resume();
		int rid=sequenceService.getSequence("resume");
		resume.setId(rid);
		resume.setPersonid(pid);
		resume.setZhaopian(request.getParameter("zhaopian"));
		resume.setOrgid(user.getOrgid());
		resume.setZhuanchang(request.getParameter("zhuanchang"));
		resume.setBeizhu(request.getParameter("beizhu"));
		resume.setJianli(request.getParameter("jianli"));
		resume.setXianrenzhuiwu(request.getParameter("xianrenzhuiwu"));
		resume.setZhuanyejishuzhiwu(request.getParameter("zhuanyejishuzhiwu"));
		resume.setCanjiagongzuoshijian(java.sql.Date.valueOf(request.getParameter("canjiagongzuoshijian")));
		
		Jiangcheng jiangcheng = new Jiangcheng();
		int jcid=sequenceService.getSequence("jiangcheng");
		jiangcheng.setId(jcid);
		jiangcheng.setPersonid(pid);
		jiangcheng.setContent(request.getParameter("jiangcheng"));
		log.debug("aaaaaaaaaaaaaaaaaaaaaaaaa"+request.getParameter("chengwei1"));
		if (request.getParameter("chengwei1")!=""){
		JiatingChengyuan jiatingChengyuan1=new JiatingChengyuan();
		int jid1=sequenceService.getSequence("jiatingChengyuan");
		jiatingChengyuan1.setId(jid1);
		jiatingChengyuan1.setPersonid(pid);
		jiatingChengyuan1.setChengwei(request.getParameter("chengwei1"));
		jiatingChengyuan1.setXingming(request.getParameter("xingming1"));
		jiatingChengyuan1.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen1")));
		jiatingChengyuan1.setZhengzhimianmao(request.getParameter("zhengzhimianmao1"));
		jiatingChengyuan1.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu1"));
		map1.put("jiatingchengyuan1", jiatingChengyuan1);
		}
		
		if (request.getParameter("chengwei2")!=""){
		JiatingChengyuan jiatingChengyuan2=new JiatingChengyuan();
		int jid2=sequenceService.getSequence("jiatingChengyuan");
		jiatingChengyuan2.setId(jid2);
		jiatingChengyuan2.setPersonid(pid);
		jiatingChengyuan2.setChengwei(request.getParameter("chengwei2"));
		jiatingChengyuan2.setXingming(request.getParameter("xingming2"));
		jiatingChengyuan2.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen2")));
		jiatingChengyuan2.setZhengzhimianmao(request.getParameter("zhengzhimianmao2"));
		jiatingChengyuan2.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu2"));
		map1.put("jiatingchengyuan2", jiatingChengyuan2);
		}
		if (request.getParameter("chengwei3")!=""){
		JiatingChengyuan jiatingChengyuan3=new JiatingChengyuan();
		int jid3=sequenceService.getSequence("jiatingChengyuan");
		jiatingChengyuan3.setId(jid3);
		jiatingChengyuan3.setPersonid(pid);
		jiatingChengyuan3.setChengwei(request.getParameter("chengwei3"));
		jiatingChengyuan3.setXingming(request.getParameter("xingming3"));
		jiatingChengyuan3.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen3")));
		jiatingChengyuan3.setZhengzhimianmao(request.getParameter("zhengzhimianmao3"));
		jiatingChengyuan3.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu3"));
		map1.put("jiatingchengyuan3", jiatingChengyuan3);
		}
		if (request.getParameter("chengwei3")!=""){
		JiatingChengyuan jiatingChengyuan4=new JiatingChengyuan();
		int jid4=sequenceService.getSequence("jiatingChengyuan");
		jiatingChengyuan4.setId(jid4);
		jiatingChengyuan4.setPersonid(pid);
		jiatingChengyuan4.setChengwei(request.getParameter("chengwei4"));
		jiatingChengyuan4.setXingming(request.getParameter("xingming4"));
		jiatingChengyuan4.setChushengnianfen(java.sql.Date.valueOf(request.getParameter("chushengnianfen4")));
		jiatingChengyuan4.setZhengzhimianmao(request.getParameter("zhengzhimianmao4"));
		jiatingChengyuan4.setGongzuodanweijizhiwu(request.getParameter("gongzuodanweijizhiwu4"));
		map1.put("jiatingchengyuan4", jiatingChengyuan4);
		}
		
		Chengguo chengguo = new Chengguo();
		int cid=sequenceService.getSequence("chengguo");
		chengguo.setId(cid);
		chengguo.setPersonid(pid);
		chengguo.setContent(request.getParameter("chengguo"));
		log.debug(xueliMingxi.getZhuanye());
		
		map1.put("xuelimingxi", xueliMingxi);      
        map1.put("person", person);
        map1.put("resume", resume);
        map1.put("chengguo", chengguo);
        map1.put("jiangcheng", jiangcheng);
        
        
        log.debug("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+map1.get("jiatingchengyuan1"));
        
        
		resumeService.insertResumeInfo(map1);
		out.print("新建简历成功");
	    } 



	
	//��������
	@RequestMapping(value="jlxq11")
	@ResponseBody
	public  Map<String, Object> jlxq11(HttpServletRequest request,HttpSession session) {
		//获取会话中的personid
		int id = Integer.parseInt((String) session.getAttribute("personid"));
		Map<String, Object> map=resumeService.selectResumeInfo(id);
		Person person=(Person)map.get("person");
		log.debug("-------------------------------------------------"+person.getName());
		Jiangcheng jiangcheng=(Jiangcheng)map.get("jiangcheng");
		Chengguo chengguo=(Chengguo)map.get("chengguo");
		List<JiatingChengyuan> list=(List<JiatingChengyuan>)map.get("jiatingChengyuan");
		XueliMingxi xueliMingxi=(XueliMingxi)map.get("xueliMingxi");
		Resume resume=(Resume)map.get("resume");
		Map<String, Object> json=new HashMap<String,Object>();
		//固定格式
		for(int i=0;i<list.size();i++){
			json.put("chengwei"+(i+1), list.get(i).getChengwei());
			json.put("xingming"+(i+1), list.get(i).getXingming());
			json.put("chushengnianfen"+(i+1), list.get(i).getChushengnianfen());
			json.put("zhengzhimianmao"+(i+1), list.get(i).getZhengzhimianmao());
			json.put("gongzuodanweijizhiwu"+(i+1), list.get(i).getGongzuodanweijizhiwu());
			log.debug(json.get("chengwei"+(i+1)));
		}
	
//		log.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+ person.getXingbie());
		log.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+chengguo.getContent());
//		log.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+ person.getZhuanye());
		json.put("name", person.getName());
		json.put("xingbie", person.getXingbie());
		json.put("chushengnianyue", person.getChushengnianyue());
		json.put("minzu", person.getMinzu());
		json.put("zhengzhimianmao", person.getZhengzhimianmao());
		json.put("xueli", person.getXueli());
		json.put("zhuanye", person.getZhuanye());
		json.put("success", true);
		//resume
		json.put("zhaopian", resume.getZhaopian());
		json.put("canjiagongzuoshijian", resume.getCanjiagongzuoshijian());
		json.put("zhuanyejishuzhiwu", resume.getZhuanyejishuzhiwu());
		json.put("zhuanchang", resume.getZhuanchang());
		json.put("xianrenzhuiwu", resume.getXianrenzhuiwu());
		json.put("jianli", resume.getJianli());
		json.put("beizhu", resume.getBeizhu());
		//xuelimingxi
		json.put("biyeyuanxiao", xueliMingxi.getBiyeyuanxiao());
		json.put("leibie", xueliMingxi.getLeibie());
		//jiangcheng
		json.put("jiangcheng", jiangcheng.getContent());
		//chengguo
		json.put("chengguo", chengguo.getContent());
		return json;
	}
	
}
