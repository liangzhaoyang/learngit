package cn.xxs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xxs.service.TongjiService;
import cn.xxs.utility.BaseController;

@Controller
public class tongjiController  extends BaseController {

	@RequestMapping("rybztj")
	public ModelAndView rybztj() {
		return new ModelAndView("tongji/renyuanbianzhitongji");
	}
	
	@RequestMapping(value="rybztjcx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rybzcx() {
		List<Map<String, Object>> resultList = tongjiService.rybztj();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",  resultList);
		return map;
//		return new ModelAndView("tongji/renyuanbianzhitongji").addObject("result", resultList);
	}
	
	@RequestMapping("zbrytj")
	public ModelAndView zbrytj() {
		return new ModelAndView("tongji/zaibianrenyuantongji");
	}
	
	@RequestMapping(value="zbrytjcx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> zbrytjcx() {
		List<Map<String, Object>> resultList = tongjiService.zbrytj();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",  resultList);
		return map;
	}
	
	@RequestMapping("jgtj")
	public ModelAndView jgtj() {
		return new ModelAndView("tongji/jigoutongji");
	}

	@RequestMapping(value="jgtjcx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> jgtjcx() {
		List<Map<String, Object>> resultList = tongjiService.jgtj();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",  resultList);
		return map;
	}
	
	@Autowired
	private TongjiService tongjiService;
}
