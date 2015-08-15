package cn.xxs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.BaomiGanbuQingkuangDao;
import cn.xxs.dao.BaomiQingkuangDao;
import cn.xxs.dao.GuanliJigouDao;
import cn.xxs.dao.JichuSheshiDao;
import cn.xxs.dao.OrgnizationDao;
import cn.xxs.dao.RenyuanBianzhiQingkuangDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.dao.ShemiRenyuanQingkuangDao;
import cn.xxs.dao.ShiyeDanweiDao;
import cn.xxs.dao.XingzhengBumenDao;
import cn.xxs.dao.ZaibianRenyuanQingkuangDao;
import cn.xxs.entity.BaomiGanbuQingkuang;
import cn.xxs.entity.BaomiQingkuang;
import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.OrgnizationCondition;
import cn.xxs.entity.RenyuanBianzhiQingkuang;
import cn.xxs.entity.ShemiRenyuanQingkuang;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.entity.ZaibianRenyuanQingkuang;

@Service
public class OrgnizationService {
	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private XingzhengBumenDao xingzhengBumenDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	private GuanliJigouDao guanliJigouDao;
	@Autowired
	private ShiyeDanweiDao shiyeDanweiDao;
	@Autowired
	private BaomiQingkuangDao baomiQingkuangDao;
	@Autowired
	private RenyuanBianzhiQingkuangDao renyuanBianzhiQingkuangDao;
	@Autowired
	private ZaibianRenyuanQingkuangDao zaibianRenyuanQingkuangDao;
	@Autowired
	private BaomiGanbuQingkuangDao baomiGanbuQingkuangDao;
	@Autowired
	private ShemiRenyuanQingkuangDao shemiRenyuanQingkuangDao;
	@Autowired
	private JichuSheshiDao jichuSheshiDao;

	public void setShenheInfo(Orgnization org) {
//		Orgnization org = new Orgnization();
//		org.setStatus((int) map.get("status"));
//		org.setId((int) map.get("orgid"));
//		org.setShenheren((String) map.get("shenheren"));
		orgnizationDao.setShenheInfo(org);
	}

	public void insertOrgnization(Orgnization org) {
		org.setStatus(1);

		orgnizationDao.insertOrgnization(org);
	}

	public Orgnization getOrgnizationById(int id) {
		return orgnizationDao.getOrgnizationById(id);
	}

	public void UpdateOrgnization(Orgnization org) {
		orgnizationDao.updateOrgnizationInfo(org);
		;
	}

	// �����������?�ż������û����
	public Map<String, Object> getJigouXiangqing(int orgid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgnization", orgnizationDao.getOrgnizationById(orgid));
		map.put("xingzhengBumen", xingzhengBumenDao.queryXingzhengBumenByOrg(orgid));
		map.put("jichuSheshi", jichuSheshiDao.queryJichuSheshiByOrg(orgid));
		map.put("guanliJigou", guanliJigouDao.queryGuanliJigouByOrg(orgid));
		map.put("shiyeDanwei", shiyeDanweiDao.queryShiyeDanweiByOrg(orgid));
		return map;
	}

	public List<XingzhengBumen> getXingzhengBumenByOrg(int orgid)
	{
		return xingzhengBumenDao.queryXingzhengBumenByOrg(orgid);
	}
	
	public List<GuanliJigou> getGuanliJigouByOrg(int orgid)
	{
		return guanliJigouDao.queryGuanliJigouByOrg(orgid);
	}
	
	public List<ShiyeDanwei> getShiyeDanweiByOrg(int orgid)
	{
		return shiyeDanweiDao.queryShiyeDanweiByOrg(orgid);
	}
	
	public List<JichuSheshi> getJichuSheshiByOrg(int orgid)
	{
		return jichuSheshiDao.queryJichuSheshiByOrg(orgid);
	}
	
	// ����������Ϣ
	public void insertXingzhengBumen(XingzhengBumen admin) {
//		Integer seq = sequenceDao.getSequence("xingzhengbumen");
//		admin.setId(seq);
		xingzhengBumenDao.insertXingzhengBumen(admin);
	}

	public void deleteXingzhengBumen(int id) {
		xingzhengBumenDao.deleteXingzhengBumen(id);
	}

	public void updateXingzhengBumen(XingzhengBumen admin) {
		xingzhengBumenDao.updateXingzhengBumen(admin);
	}

	public List<XingzhengBumen> getXingzhengBumenInfo() {
		return xingzhengBumenDao.queryXingzhengBumen();
	}

	// ��������
	public void insertGuanliJigou(GuanliJigou admin) {
//		Integer seq = sequenceDao.getSequence("guanlijigou");
//		admin.setId(seq);
		guanliJigouDao.insertGuanliJigou(admin);
	}

	public void deleteGuanliJigou(int id) {
		guanliJigouDao.deleteGuanliJigou(id);
	}

	public void updateGuanliJigou(GuanliJigou admin) {
		guanliJigouDao.updateGuanliJigou(admin);
	}

	public List<GuanliJigou> getGuanliJigouInfo() {
		return guanliJigouDao.queryGuanliJigou();
	}

	// ��ҵ��λ����
	public void insertShiyeDanwei(ShiyeDanwei admin) {
//		Integer seq = sequenceDao.getSequence("shiyedanwei");
//		admin.setId(seq);
		shiyeDanweiDao.insertShiyeDanwei(admin);
	}

	public void deleteShiyeDanwei(int id) {
		shiyeDanweiDao.deleteShiyeDanwei(id);
	}

	public void updateShiyeDanwei(ShiyeDanwei admin) {
		shiyeDanweiDao.updateShiyeDanwei(admin);
	}

	public List<ShiyeDanwei> getShiyeDanweiInfo() {
		return shiyeDanweiDao.queryShiyeDanwei();
	}

	public void updateJichuSheshi(JichuSheshi jcss)
	{
		jichuSheshiDao.updateJichuSheshi(jcss);
	}
	
	public void insertJichuSheshi(JichuSheshi jcss)
	{
		jichuSheshiDao.insertJichuSheshi(jcss);
	}
	
	public void deleteJichuSheshi(int id)
	{
		jichuSheshiDao.deleteJichuSheshi(id);
	}
	// ��ȡ�������Ϣ
	public List<Orgnization> getDaiShenheInfo(OrgnizationCondition orgCon) {
		return orgnizationDao.getDaiShenheInfoPage(orgCon);
	}

	// ��ȡ�����Ϣ
	public List<Orgnization> getJigouJibenInfo(OrgnizationCondition orgCon) {
		return orgnizationDao.queryJigouJibenInfoPage(orgCon);
	}

	//
	public BaomiQingkuang getBaomiQingkuangInfo(int orgid) {
		return baomiQingkuangDao.queryBaomiQingkuangInfoPage(orgid);
	}

	//
	public RenyuanBianzhiQingkuang getRenyuanBianzhiQingkuangInfo(int orgid) {
		return renyuanBianzhiQingkuangDao.queryRenyuanBianzhiInfo(orgid);
	}

	//
	public ZaibianRenyuanQingkuang getZaibianRenyuanQingkuangInfo(int orgid) {
		return zaibianRenyuanQingkuangDao.queryZaibianRenyuanMingxiInfo(orgid);
	}

	//
	public BaomiGanbuQingkuang getBaomiGanbuQingkuangInfo(int orgid) {
		return baomiGanbuQingkuangDao.queryBaomiGanbuQingkuangInfoPage(orgid);
	}

	//
	public ShemiRenyuanQingkuang getShemiRenyuanQingkuangInfo(int orgid) {
		return shemiRenyuanQingkuangDao.queryShemiRenyuanQingkuangInfoPage(orgid);
	}

	public Orgnization getOrgnizationByName(String orgName)// ͨ�������
	{
		return orgnizationDao.getOrgnizationByName(orgName);
	}

	public List<Map<String, Object>> countBySuorgid() {
		return orgnizationDao.countBySuorgid();
	}

	public List<Map<String, Object>> countByXingzhengJibie() {
		return orgnizationDao.countByXingzhengJibie();
	}

	public List<Map<String, Object>> countByJigouLeibie() {
		return orgnizationDao.countByJigouLeibie();
	}
}
