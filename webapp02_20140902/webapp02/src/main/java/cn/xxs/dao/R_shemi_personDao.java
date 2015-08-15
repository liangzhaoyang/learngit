package cn.xxs.dao;

import org.apache.ibatis.annotations.Param;

public interface R_shemi_personDao {
	//public String selectSpId(String id);
	public void deleter_shemi_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int id);
	public void insertr_shemi_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int personid);
	
}
