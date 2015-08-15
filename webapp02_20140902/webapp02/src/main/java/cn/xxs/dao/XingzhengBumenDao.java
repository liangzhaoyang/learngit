package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.XingzhengBumen;

public interface XingzhengBumenDao {
	public void insertXingzhengBumen(XingzhengBumen admin);
	public void deleteXingzhengBumen(int id);
	public List<XingzhengBumen> queryXingzhengBumen();
	public List<XingzhengBumen> queryXingzhengBumenByOrg(int orgid);
	public void updateXingzhengBumen(XingzhengBumen admin);
}
