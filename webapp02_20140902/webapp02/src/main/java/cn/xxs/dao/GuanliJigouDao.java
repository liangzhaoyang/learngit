package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.GuanliJigou;

public interface GuanliJigouDao {
	public void insertGuanliJigou(GuanliJigou manager);
	public void deleteGuanliJigou(int id);
	public List<GuanliJigou> queryGuanliJigou();
	public List<GuanliJigou> queryGuanliJigouByOrg(int orgid);
	public void updateGuanliJigou(GuanliJigou manager);
}
