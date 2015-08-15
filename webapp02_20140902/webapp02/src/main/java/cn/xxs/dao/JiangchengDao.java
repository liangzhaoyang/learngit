package cn.xxs.dao;

import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.Resume;

public interface JiangchengDao
{
	public void insertJiangcheng(Resume resume);

//	public void insertJiangcheng(@Param(value = "jiangchengid")Integer jiangchengid,@Param(value = "personid") Integer personid,
//			@Param(value = "jiangchengcotent")String jiangchengcotent);
	public void insertJiangcheng(Jiangcheng jiangcheng);
	public void updateJiangcheng(Jiangcheng jiangcheng);
	public Jiangcheng selectJiangchengByPersonid(int id);
}
