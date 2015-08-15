package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.Orgnization;
import cn.xxs.entity.OrgnizationCondition;

public interface OrgnizationDao {
	public void updateOrgnizationInfo(Orgnization org);

	public List<Orgnization> getDaiShenheInfoPage(OrgnizationCondition orgCon);

	public List<Orgnization> queryJigouJibenInfoPage(OrgnizationCondition orgCon);

	public Orgnization getOrgnizationById(int id);

	public void insertOrgnization(Orgnization org);

	public void setShenheInfo(Orgnization org);

	public Orgnization getOrgnizationByName(String orgName);

	// 按主管单位分类统计
	public List<Map<String, Object>> countBySuorgid();

	// 按行政级别分类统计
	public List<Map<String, Object>> countByXingzhengJibie();

	// 按机构类别分类统计
	public List<Map<String, Object>> countByJigouLeibie();
}
