package cn.xxs.dao;

import cn.xxs.entity.Chengguo;
import cn.xxs.entity.Resume;

public interface ChengguoDao {
	public void insertChengguo(Resume resume);

	//public void insertChengguo(@Param(value = "chengguoid")Integer chengguoid, @Param(value = "personid")Integer personid,
			//@Param(value = "chengguocotent")String chengguocotent);
	public void insertChengguo(Chengguo chengguo);
	public void updateChengguo(Chengguo chengguo);
	public Chengguo selectChengguoByPersonid(int id);
}
