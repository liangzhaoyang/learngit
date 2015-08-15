package cn.xxs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.GuanliJigouDao;
import cn.xxs.dao.JichuSheshiDao;
import cn.xxs.dao.OrgnizationDao;
import cn.xxs.dao.ShiyeDanweiDao;
import cn.xxs.dao.XingzhengBumenDao;

/**
 * @author 作者 :wan 创建时间：2014年8月11日 下午7:36:17 类说明
 */
@Service
public class JigouService {
	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private XingzhengBumenDao xingzhengBumenDao;
	@Autowired
	private JichuSheshiDao jichuSheshiDao;
	@Autowired
	private GuanliJigouDao guanliJigouDao;
	@Autowired
	private ShiyeDanweiDao shiyeDanweiDao;

	// 保密行政管理部门及机构设置基本情况
	public Map<String, Object> getJigouXiangqing(int orgid) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("orgnization", orgnizationDao.getOrgnizationById(orgid));
		map.put("xingzhengBumen", xingzhengBumenDao.queryXingzhengBumenByOrg(orgid));
		map.put("jichuSheshi", jichuSheshiDao.queryJichuSheshiByOrg(orgid));
		map.put("guanliJigou", guanliJigouDao.queryGuanliJigouByOrg(orgid));
		map.put("shiyeDanwei", shiyeDanweiDao.queryShiyeDanweiByOrg(orgid));
		return map;
	}
}
