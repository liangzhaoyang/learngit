package cn.xxs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xxs.entity.RenyuanBianzhiMingxi;

public interface RenyuanBianzhiMingxiDao 
{
	//public String getAllBianzhi(@Param(value = "orgid")int orgid,@Param(value = "code")int code);
	public List<RenyuanBianzhiMingxi> getAllBianzhi(@Param(value = "orgid")int orgid);
	//public void updateBianzhi(RenyuanBianzhiMingxi bianzhi);
	//public void insertBianzhi(RenyuanBianzhiMingxi bianzhi);
	public void insertBianzhi(@Param(value = "orgid")int orgid, @Param(value = "code")int code, @Param(value = "num")int num);
	public void updateBianzhi(@Param(value = "orgid")int orgid, @Param(value = "code")int code, @Param(value = "num")int num);
	
	public void deleteRenyuanBianzhiByOrgid(Integer orgid);
	public void insertRenyuanBianzhi(RenyuanBianzhiMingxi entity);
}
