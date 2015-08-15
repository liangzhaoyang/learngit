package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.Resume;

public interface JiatingChengyuanDao {
	public void insertJiatingChengyuan(Resume resume);

//	public void insertJiatingChengyuan(@Param(value = "jiatingchengyuanid")Integer jiatingchengyuanid,
//			@Param(value = "personid")Integer personid, @Param(value = "chengwei")String chengwei,@Param(value = "xingming") String xingming,
//			@Param(value = "chushengnianfen")Date chushengnianfen, @Param(value = "jiazhengzhimianmao")String jiazhengzhimianmao,
//			@Param(value = "gongzuodanweijizhiwu")String gongzuodanweijizhiwu);
	public void insertJiatingChengyuan(JiatingChengyuan jiatingChengyuan);
	public void updateJiatingChengyuan(JiatingChengyuan jiatingChengyuan);
	public List<JiatingChengyuan> selectJiatingChengyuanByPersonid(int id);
}
