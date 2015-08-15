package cn.xxs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xxs.entity.ZaibianRenyuanMingxi;


public interface ZaibianRenyuanMingxiDao {
	public List<ZaibianRenyuanMingxi> getAllZaibian(@Param(value = "orgid")int orgid);
	public void insertZaibian(@Param(value = "orgid")int orgid, @Param(value = "code")int code, @Param(value = "num")int num);
	public void updateZaibian(@Param(value = "orgid")int orgid, @Param(value = "code")int code, @Param(value = "num")int num);

	public void deleteZaibianRenyuanByOrgid(Integer orgid);
	public void insertZaibianRenyuan(ZaibianRenyuanMingxi entity);
}
