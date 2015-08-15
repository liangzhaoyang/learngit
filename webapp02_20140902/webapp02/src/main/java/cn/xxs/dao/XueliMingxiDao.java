package cn.xxs.dao;

import cn.xxs.entity.Resume;
import cn.xxs.entity.XueliMingxi;

public interface XueliMingxiDao 
{
	public void insertXueliMingxi(Resume resume);

//	public void insertXueliMingxi(@Param(value = "xuelimingxiid")Integer xuelimingxiid, @Param(value = "personid")Integer personid,
//			@Param(value = "leibie")int leibie,@Param(value = "biyeyuanxiao")String biyeyuanxiao,@Param(value = "zhuanye") String zhuanye);
	public void insertXueliMingxi(XueliMingxi xueliMingxi);
	public void updateXueliMingxi(XueliMingxi xueliMingxi);
	public XueliMingxi selectXueliMingxiByPersonid(int id);
}
