package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.JichuSheshi;

public interface JichuSheshiDao {
	public List<JichuSheshi> queryJichuSheshiByOrg(int orgid);
	public void insertJichuSheshi(JichuSheshi manager);
	public void deleteJichuSheshi(int id);
	public void updateJichuSheshi(JichuSheshi manager);
}
