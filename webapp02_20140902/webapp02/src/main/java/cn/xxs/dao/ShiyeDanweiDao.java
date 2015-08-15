package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.ShiyeDanwei;

public interface ShiyeDanweiDao {
	public void insertShiyeDanwei(ShiyeDanwei pub);
	public void deleteShiyeDanwei(int id);
	public List<ShiyeDanwei> queryShiyeDanwei();
	public List<ShiyeDanwei> queryShiyeDanweiByOrg(int orgid);
	public void updateShiyeDanwei(ShiyeDanwei pub);
}
