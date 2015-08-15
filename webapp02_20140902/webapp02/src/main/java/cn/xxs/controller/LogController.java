package cn.xxs.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.entity.Log;
import cn.xxs.entity.LogCondition;
import cn.xxs.entity.SelectItem;
import cn.xxs.paging.PageContext;
import cn.xxs.service.ConstantsService;
import cn.xxs.service.LogService;
import cn.xxs.utility.BaseController;

/**
 * @author 作者 :wan
      创建时间：2014年8月12日 下午8:57:57
 * 类说明
 */

@Controller
@RequestMapping("/")
public class LogController extends BaseController{
	private static Logger logger=Logger.getLogger(LogController.class);
	@Autowired
	private LogService logService;
	@Autowired
	private ConstantsService constantService;
	
	//日志查询
	@RequestMapping(value = "rzgl")
	public ModelAndView logChaxun(HttpSession session, Model model) {
		List<SelectItem> logtypeList=constantService.getSelectListByType("日志类型");
		model.addAttribute("logtypeList", logtypeList);
		return new ModelAndView("log/logsQuery");
	}
	
	//日志查询加载
	@RequestMapping(value = "rzcxInfo")
	@ResponseBody
	public Map<String, Object> jigouChaxunQuery(HttpServletRequest request,/*@ModelAttribute LogCondition logCon,*/ int page, int rows) {
		//logger.debug("condition:" + logCon.getUserid()+","+logCon.getType()+"---"+logCon.getDesc()+",date:"+logCon.getLogtimeFrom()+"#####"+page+"---"+rows);
		PageContext pageContext = PageContext.getContext();
		
		pageContext.setCurrentPage(page);
		pageContext.setPageSize(rows);
		pageContext.setPagination(true);
		
		String name=request.getParameter("name");
		String logtype=request.getParameter("logtype");
		String dateFrom=request.getParameter("dateFrom");
		String dateTo=request.getParameter("dateTo");
		String miaoshu=request.getParameter("miaoshu");
		logger.debug("##########"+request.getParameter("dateFrom")+"#####"+request.getParameter("name"));
		
		List<SelectItem> logtypeList=constantService.getSelectListByType("日志类型");
		
		LogCondition logCon=new LogCondition();
		if((name!=null)&&!( name.equals("")&&logtype.equals("")&&dateFrom.equals("")&&dateTo.equals("")&&miaoshu.equals("")))
		{
			logCon.setUserid(name);
			if(logtype.matches("[0-9]+"))
				logCon.setType((short)Integer.parseInt(logtype));
			if(!dateFrom.equals(""))
				logCon.setLogtimeFrom(Timestamp.valueOf(dateFrom));
			if(!dateTo.equals(""))
				logCon.setLogtimeTo(Timestamp.valueOf(dateTo));
			logCon.setDesc(miaoshu);
		}
		
		
		List<Log> logs=logService.getLogByCondition(logCon);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Log temp:logs)
		{
			Map<String,Object> map1=new HashMap<String,Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map1.put("logtime", df.format(temp.getLogtime()));
			if(temp.getType()>0)
			map1.put("type", logtypeList.get(temp.getType()-1).getName());
			else
				map1.put("type","");
			map1.put("userid", temp.getUserid());
			map1.put("desc", temp.getDescriptions());
			
			list.add(map1);
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("total", pageContext.getTotalRows());
		json.put("pages", (pageContext.getTotalRows() + rows - 1) / rows);
		json.put("rows", list);

		return json;
	}
	//日志条件查询
	@RequestMapping(value="dorzcx", method=RequestMethod.POST)
	@ResponseBody
	public String riziTiaojianChaxun(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String name=request.getParameter("name");
		String logtype=request.getParameter("logtype");
		String dateFrom=request.getParameter("dateFrom");
		String dateTo=request.getParameter("dateTo");
		String miaoshu=request.getParameter("miaoshu");
		LogCondition logCon=new LogCondition();
		logCon.setUserid(name);
		if(logtype.matches("[0-9]+"))
			logCon.setType((short)Integer.parseInt(logtype));
		if(!dateFrom.equals(""))
		{
			dateFrom+=" 00:00:00";
			logger.debug("date from:"+dateFrom);
			logCon.setLogtimeFrom(Timestamp.valueOf(dateFrom));
		}
		if(!dateTo.equals(""))
		{
			dateTo +=" 00:00:00";
			logger.debug("date to:"+dateTo);
			logCon.setLogtimeTo(Timestamp.valueOf(dateTo));
		}
		logCon.setDesc(miaoshu);
		//
		return "success"; 
	}
}
